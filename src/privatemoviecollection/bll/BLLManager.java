/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.bll;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import privatemoviecollection.be.Movie;
import privatemoviecollection.bll.util.SearchMovies;
import privatemoviecollection.dal.database.MovieDBDAO;

/**
 *
 * @author Christina
 */
public class BLLManager
{
    private final MovieDBDAO movieDB;
    
    public BLLManager() throws IOException
    {
        movieDB = new MovieDBDAO();
    }
    
    public Movie createMovie(String name, int rating, String filelink, float imdb) throws SQLException
    {
        Movie mov = movieDB.createMovie(name, rating, filelink, imdb);
        
        return mov;
    }
    
    
    
//    public List<Movie> search(String query) throws Exception
//    {
//        List<Movie> searchBase = mediaDB.getAllMedias();
//        searchBase = SearchMovies.search(searchBase, query);
//        
//        return searchBase;
//    }
}
