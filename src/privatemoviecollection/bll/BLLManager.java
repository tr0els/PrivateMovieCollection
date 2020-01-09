/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.bll;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import privatemoviecollection.be.Movie;
import privatemoviecollection.bll.util.SearchMovies;
import privatemoviecollection.dal.database.MovieDBDAO;
import java.util.List;
import privatemoviecollection.be.Category;
import privatemoviecollection.bll.util.SearchMovies;
import static privatemoviecollection.bll.util.SearchMovies.search;
import privatemoviecollection.dal.dalException.DALException;
import privatemoviecollection.dal.database.CategoryDBDAO;
/**
 *
 * @author Christina
 */
public class BLLManager
{
    private final MovieDBDAO movieDB;
    
    private CategoryDBDAO categoryDbDao;
    
    public BLLManager() throws DALException
    {
        movieDB = new MovieDBDAO();
        categoryDbDao = new CategoryDBDAO();
    }
    
    public Movie createMovie(String name, int rating, String filelink, float imdb, ArrayList<Integer> idList) throws DALException 
    {
        Movie mov = movieDB.createMovie(name, rating, filelink, imdb, idList);
        
        return mov;
    }
    
    public void deleteMovie(Movie mov) throws DALException
    {
        movieDB.deleteMovie(mov);
    }
    
    public void updateMovie(Movie mov) throws DALException
    {
        movieDB.updateMovie(mov);
    }
    
    public List<Movie> getAllMovies() throws DALException
    {
        return movieDB.getAllMovies();
    }
    
    public List<Category> getAllCategories() throws DALException
    {
        return categoryDbDao.getAllCategories();
    }
    
    public Category createCategory(String name) throws DALException
    {
        Category category = categoryDbDao.createCategory(name);
        return category;
    }
    
    public boolean deleteCategory(Category category) throws DALException
    {
        return categoryDbDao.deleteCategory(category);
    }
    
    public void updateCategory(Category category) throws DALException
    {
        categoryDbDao.updateCategory(category);
    }
    
    public List<Movie> searchMovies(String query) throws DALException 
    {
        List<Movie> allMovies = getAllMovies();
        allMovies = search(allMovies, query);
        return allMovies;
    }
    
    public void updateLastView(Movie mov) throws DALException 
    {
        movieDB.updateLastView(mov);
    
    }

    
}
