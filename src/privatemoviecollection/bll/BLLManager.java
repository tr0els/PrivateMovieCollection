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
    
    /**
     * Creates a new movie in the db
     * @param name
     * @param rating
     * @param filelink
     * @param imdb
     * @param idList
     * @return returns a movie object
     * @throws DALException 
     */
    public Movie createMovie(String name, int rating, String filelink, float imdb, ArrayList<Category> idList) throws DALException 
    {
        Movie mov = movieDB.createMovie(name, rating, filelink, imdb, idList);
        return mov;
    }
    
    /**
     * Deletes a movie from the db
     * @param mov
     * @throws DALException 
     */
    public void deleteMovie(Movie mov) throws DALException
    {
        movieDB.deleteMovie(mov);
    }
    
    /**
     * Updates a movie in the db
    */
    public void updateMovie(Movie mov) throws DALException
    {
        movieDB.updateMovie(mov);
    }
    
    /**
     * Gets a list of all movies
     * @return
     * @throws DALException 
     */
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
    
    /**
     * Searches movies based on the given parameters
     * @param name name of movie or part of it
     * @param rating value of minimum rating the movie should have
     * @param categories list of categories the movie should be in
     * @throws DALException
     * @returns a list of movie object results
     */
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
