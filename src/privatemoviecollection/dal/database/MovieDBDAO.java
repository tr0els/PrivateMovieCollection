/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.dal.database;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.IOException;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import privatemoviecollection.be.Movie;

/**
 *
 * @author Christina
 */
public class MovieDBDAO
{
    private DatabaseConnector dbCon;
    
    public MovieDBDAO() throws IOException
    {
        dbCon = new DatabaseConnector();
    }
    
    public Movie createMovie(String name, int rating, String filelink, float imdb) throws SQLServerException, SQLException
    {
        Connection con = dbCon.getConnection();
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        
            String sql = "INSERT INTO Movie VALUES (?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
            ps.setString(1, name);
            ps.setInt(2, rating);
            ps.setString(3, filelink);
            ps.setDate(4, sqlDate);
            ps.setFloat(5, imdb);
           int affectedRows = ps.executeUpdate();
            if (affectedRows == 1)
            {
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next())
                {
                    int id = rs.getInt(1);
                    Movie mov = new Movie(id, filelink, name, imdb, rating);
                    return mov;
                }
            }
     return null;
    }
    
    public List<Movie> getAllMovies() throws SQLException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT * FROM Movie;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            
            List<Movie> movies = new ArrayList<>();
            
            while (rs.next())
            {
                int id = rs.getInt("id");
                String filelink = rs.getString("filelink");
                String name = rs.getString("name");
                int rating = rs.getInt("rating");
                Date lastview = rs.getDate("lastview");
                float imdb = rs.getFloat("imdb");
                
                Movie movie = new Movie(id, filelink, name, imdb, rating);
                movies.add(movie);              
            }
            
            return movies;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new SQLException();
        }
    }
    
    public void deleteMovie(Movie mov) throws SQLException
    {
        Connection con = dbCon.getConnection();
        
        int id = mov.getId();
        String sql = "DELETE FROM Movie WHERE id=?;";
        String sql2 = "DELETE FROM CatMovie WHERE MovieId = (?)";
        PreparedStatement ps = con.prepareStatement(sql);
        PreparedStatement ps2 = con.prepareStatement(sql2);
        ps.setInt(1, id);
        ps2.setInt(1, id);
        
        ps2.executeUpdate();
        ps.executeUpdate();
    }
    
    public void updateMovie()
    {
        
    }
    
    
    
    
}
