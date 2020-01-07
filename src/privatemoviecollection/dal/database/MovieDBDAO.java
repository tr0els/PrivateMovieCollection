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
    
    public List<Movie> getAllMovies()
    {
        return null;
    }
    
    public void deleteMovie()
    {
        
    }
    
    public void updateMovie()
    {
        
    }
    
    
    
    
}
