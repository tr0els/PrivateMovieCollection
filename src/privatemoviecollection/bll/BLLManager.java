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
import privatemoviecollection.be.Category;
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
    public void deleteCategory(Category category) throws DALException
    {
        categoryDbDao.deleteCategory(category);
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
        List<Movie> movies = movieDB.searchMovies(name, rating, categories);
        return movies;
    }
    
    /**
     * Updates a movies lastview.
     * @param mov
     * @throws DALException 
     */
    public void updateLastView(Movie mov) throws DALException 
    {
        movieDB.updateLastView(mov);
    
    }
    
    /**
     * Gets a list of movies that the user hasn't seen in more than 2 years, 
     * and has a personal rating under 6.
     * @return
     * @throws DALException 
     */
    public List<Movie> timeSinceLastview() throws DALException
    {
        return movieDB.timeSinceLastview();
    }

    /**
     * Updates the CatMovie table in the database, 
     * when a movies categories have been changed.
     * @param list
     * @param mov
     * @throws DALException 
     */
    public void updateCategoryCatMovie(Movie movie) throws DALException
    {
        movieDB.updateCategoryInCatMovie(movie);
    }
}
