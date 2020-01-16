/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui.model;


import java.util.ArrayList;
import java.util.List;
import privatemoviecollection.be.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import privatemoviecollection.be.Category;
import privatemoviecollection.bll.BLLManager;
import privatemoviecollection.dal.dalException.DALException;

/**
 *
 * @author Christina
 */
public class DataModel
{	
    private BLLManager bll; 
    
    private ObservableList<Movie> movies = FXCollections.observableArrayList();
    private ObservableList<Category> categories = FXCollections.observableArrayList();
    
    public DataModel() throws DALException
    {
        bll = new BLLManager();
        movies.addAll(bll.getAllMovies());
    }
    
    /**
     * Gets an observable list of all movies
     * @return
     */
    public ObservableList<Movie> getAllMovies()
    {
        return movies;
    } 
    
    /**
     * Gets an observable list of all categories
     * @return
     * @throws DALException
     */
    public ObservableList<Category> getCategoryList() throws DALException
    {
        categories.setAll(bll.getAllCategories());
        return categories;
    }
    
    /**
     * Creates a category and adds it to the list of all categories
     * @param name
     * @throws DALException
     */
    public void createCategory(String name) throws DALException
    {
        Category category = bll.createCategory(name);
        categories.add(category);      
    }
    
    /**
     * Updates the category selected from the list
     * @param selectedCategory
     * @throws DALException
     */
    public void updateCategory(Category selectedCategory) throws DALException
    {
        bll.updateCategory(selectedCategory);
    }
    
    /**
     * Deletes the category selected from the list
     * @param category
     * @throws DALException
     */
    public void deleteCategory(Category category) throws DALException
    {
        categories.remove(category);
        bll.deleteCategory(category);
    }
    
    public void getSearchResult(String input, int rating, List<Category> categories) throws DALException
    {
        List<Movie> result = bll.searchMovies(input, rating, categories);
        movies.setAll(result);
    }
    
    /**
     * Creates a movie object and adds it to the movie list
     * @param name
     * @param rating
     * @param filelink
     * @param imdb
     * @param idList
     * @throws DALException
     */
    public void createMovie(String name, int rating, String filelink, float imdb, ArrayList<Category> idList) throws DALException
    {
        Movie movie = bll.createMovie(name, rating, filelink, imdb, idList);
        movies.add(movie);
    }
    
    /**
     * Deletes a movie object from the list
     * @param mov
     * @throws DALException
     */
    public void deleteMovie(Movie mov) throws DALException
    {
        movies.remove(mov);
        bll.deleteMovie(mov);
    }
    
    /**
     * Updates the movie in the list. 
     * @param mov
     * @throws DALException
     */
    public void updateMovie(Movie mov) throws DALException
    {
        bll.updateMovie(mov);
    }
    
    /**
     * Updates lastview in the database for the chosen movie.
     * @param mov
     * @throws DALException 
     */
    public void updateLastView(Movie mov) throws DALException 
    {
        bll.updateLastView(mov);
    }
    
    /**
     * Gets the movies that the user hasn't seen in more than 2 years, 
     * and has a personal rating under 6.
     * @return List<Movie>
     * @throws DALException 
     */
    public List<Movie> timeSinceLastview() throws DALException
    {
        return bll.timeSinceLastview();
    }
    
    /**
     * Updates the CatMovie table in the database, 
     * when a movie's categories have been changed.
     * @param list
     * @param mov
     * @throws DALException 
     */
    public void updateCategoryCatMovie(Movie movie) throws DALException 
    {
        bll.updateCategoryCatMovie(movie);
    }
}
