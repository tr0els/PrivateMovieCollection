/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.dal.database;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.IOException;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import privatemoviecollection.be.Category;
import privatemoviecollection.be.Movie;
import privatemoviecollection.dal.dalException.DALException;

/**
 *
 * @author Christina
 */
public class MovieDBDAO
{
    private DatabaseConnector dbCon;
    
    public MovieDBDAO() throws DALException
    {
        try {
        dbCon = new DatabaseConnector();
    }   catch (DALException ex )
        { 
            throw ex;
        }
    
    }
    
    public Movie createMovie(String name, int rating, String filelink, float imdb, ArrayList<Integer> idList) throws DALException
    {
        try{
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
            }}
        catch (SQLException ex)
        {
        throw new DALException("fuck you"); 
        }
     return null;
    }
    
    public List<Movie> getAllMovies() throws DALException
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
           throw new DALException("can not do this");
        }
    }
    
    public List<Category> getMovieCategories(int movieId) throws DALException
    {
        try (Connection con = dbCon.getConnection())
        {
            List<Category> categories = new ArrayList<>();

            String sql = "SELECT c.id, c.name FROM Category c, CatMovie cm WHERE c.id = cm.categoryId AND cm.MovieId = " + movieId + ";";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
        
            while (rs.next())
            {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                
                Category category = new Category(id, name);
                categories.add(category);
            }
        
            return categories;
        } catch (SQLException ex)
        {
            throw new DALException("you shall not pass!!");
        }
    }
    
    public void deleteMovie(Movie mov) throws DALException
    {   try{
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
    } catch (SQLException ex)
    {
    throw new DALException("you shall not pass!!");
    }
    }
    
    public void updateMovie(Movie mov) throws DALException
    {   try{
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
    catch (SQLException ex)
    {
    throw new DALException("you shall not pass!!");
    }
    }
    
    public void updateLastView(Movie mov) throws DALException  
    {
        try{
        Connection con = dbCon.getConnection();
        
        int id = mov.getId();
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        
        String Sql = "Update Movie set lastview = ? where id=?";
        PreparedStatement ps = con.prepareStatement(Sql);
        ps.setDate(1, sqlDate);
        ps.setInt(2, id);
        
        ps.executeUpdate();
        ps.close();}
        catch (SQLException ex)
        {
        throw new DALException("you shall not pass!!");
        }
        
    }
    
    
}
