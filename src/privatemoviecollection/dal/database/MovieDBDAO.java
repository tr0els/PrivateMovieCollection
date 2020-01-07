/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.dal.database;

import java.io.IOException;
import java.util.List;
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
    
    public Movie createMovie()
    {
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
