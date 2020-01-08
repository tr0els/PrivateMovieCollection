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
import privatemoviecollection.be.Category;
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
    
    public Movie createMovie(String name, int rating, String filelink, float imdb, ArrayList<Integer> idList) throws SQLServerException, SQLException
    {
        Connection con = dbCon.getConnection();
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        
            String sql = "INSERT INTO Movie VALUES (?,?,?,?,?);";
            String sql2 = "INSERT INTO CatMovie VALUES (?,?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
            PreparedStatement ps2 = con.prepareStatement(sql2);
            
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
                    
                    for (Integer i : idList)
                    {
                        ps2.setInt(1, i);
                        ps2.setInt(2, id);
                        ps2.executeUpdate();
                    }
                    
                    return mov;
                }
            }
     return null;
    }
    
    public List<Movie> getAllMovies() throws SQLException, Exception
    {
        try (Connection con = dbCon.getConnection())
        {
            List<Movie> movies = new ArrayList<>();
            
            String sql = "SELECT * FROM Movie;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
                        
            while (rs.next())
            {
                int id = rs.getInt("id");
                String filelink = rs.getString("filelink");
                String name = rs.getString("name");
                int rating = rs.getInt("rating");
                Date lastview = rs.getDate("lastview");
                float imdb = rs.getFloat("imdb");
                
                Movie movie = new Movie(id, filelink, name, imdb, rating);
                movie.addCategories(getMovieCategories(id));
                movies.add(movie);
            }
            
            return movies;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new SQLException();
        }
    }
    
    public List<Category> getMovieCategories(int movieId) throws Exception
    {
        Connection con = dbCon.getConnection();
        
        List<Category> categories = new ArrayList<>();

        String sql = "SELECT c.id, c.name FROM Category c, CatMovie cm WHERE c.id = cm.categoryId AND cm.MovieId = (?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, movieId);
        ResultSet rs = ps.executeQuery(sql);
        
        while (rs.next())
        {
            int id = rs.getInt("id");
            String name = rs.getString("name");

            Category category = new Category(id, name);
            categories.add(category);
        }
        
        return categories;
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
    
    public void updateMovie(Movie mov) throws SQLServerException, SQLException
    {
        Connection con = dbCon.getConnection();
        
        int id = mov.getId();
        String sql = "UPDATE Movie SET name = ?, rating = ?, filelink = ?, imdb = ? WHERE id="+id+";";
        PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, mov.getName());
            ps.setInt(2, mov.getRating());
            ps.setString(3, mov.getFilelink());
            ps.setFloat(4, mov.getImdb());
            
        ps.executeUpdate();
        ps.close();
    }
    
    
    
    
}
