/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.bll;

import java.util.ArrayList;
import privatemoviecollection.be.Movie;
import privatemoviecollection.dal.database.MovieDBDAO;
import java.util.List;
import javafx.collections.ObservableList;
import privatemoviecollection.be.Category;
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
    
    private final CategoryDBDAO categoryDbDao;
    
    public BLLManager() throws DALException
    {
        movieDB = new MovieDBDAO();
        categoryDbDao = new CategoryDBDAO();
    }
    
    public Movie createMovie(String name, int rating, String filelink, float imdb, ArrayList<Category> idList) throws DALException 
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
    
    /**
     * Gets a list of all categories
     * @return
     * @throws DALException
     */
    public List<Category> getAllCategories() throws DALException
    {
        return categoryDbDao.getAllCategories();
    }
    
    /**
     * Creates a category object
     * @param name
     * @return
     * @throws DALException
     */
    public Category createCategory(String name) throws DALException
    {
        Category category = categoryDbDao.createCategory(name);
        return category;
    }
    
    /**
     * Deletes a category object
     * @param category
     * @return
     * @throws DALException
     */
    public boolean deleteCategory(Category category) throws DALException
    {
        return categoryDbDao.deleteCategory(category);
    }
    
    /**
     * Updates the name of a category object
     * @param category
     * @throws DALException
     */
    public void updateCategory(Category category) throws DALException
    {
        categoryDbDao.updateCategory(category);
    }
    
    public List<Movie> searchMovies(String name, int rating, List<Category> categories) throws DALException 
    {
        List<Movie> movies = new ArrayList<>();
        movies = movieDB.searchMovies(name, rating, categories);
        
        return movies;
    }
    
    public void updateLastView(Movie mov) throws DALException 
    {
        movieDB.updateLastView(mov);
    
    }
    
    public List<Movie> timeSinceLastview() throws DALException
    {
        return movieDB.timeSinceLastview();
    }

    public void updateCategoryCatMovie(ObservableList<Category> list, Movie mov)
    {
        movieDB.updateCategoryInCatMovie(list, mov);
    }
}
