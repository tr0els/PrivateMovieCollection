/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui.model;

import java.io.IOException;
import java.sql.SQLException;
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
    private Category chosenCategory;
    
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
        getCategoryList();       
    }
    
    /**
     * Updates the category selected from the list
     * @param selectedCategory
     * @throws DALException
     */
    public void updateCategory(Category selectedCategory) throws DALException
    {
        bll.updateCategory(selectedCategory);
        getCategoryList();
    }
    
    /**
     * Deletes the category selected from the list
     * @param category
     * @throws DALException
     */
    public void deleteCategory(Category category) throws DALException
    {
        bll.deleteCategory(chosenCategory);
        getCategoryList();
    }
    
    /**
     * Sets the chosen category. Used for deleting a category. 
     * @param chosenCategory
     */
    public void setChosenCategory(Category chosenCategory)
    {
        this.chosenCategory = chosenCategory;
    }

    /**
     * Gets the chosen category. Used for deleting a category. 
     * @return
     */
    public Category getChosenCategory()
    {
        return chosenCategory;
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
    
    public void updateLastView(Movie mov) throws DALException 
    {
        bll.updateLastView(mov);
    }
    
    public List<Movie> timeSinceLastview() throws DALException
    {
        return bll.timeSinceLastview();
    }
    
    public void updateCategoryCatMovie(ObservableList<Category> list, Movie mov) throws DALException 
    {
        bll.updateCategoryCatMovie(list, mov);
    }
}
