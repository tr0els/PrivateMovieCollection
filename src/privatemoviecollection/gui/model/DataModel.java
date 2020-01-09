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
    
    public DataModel() throws IOException, SQLException
    {
        bll = new BLLManager();
        
        movies.addAll(bll.getAllMovies());
    }
    
    public ObservableList<Movie> getAllMovies()
    {
        return movies;
    }    
    
    public ObservableList<Category> getCategoryList() throws IOException, Exception
    {
        categories.setAll(bll.getAllCategories());
        return categories;
    }
    
    public void createCategory(String name) throws Exception
    {
        Category category = bll.createCategory(name);
        categories.add(category);
        getCategoryList();       
    }
    
    public void updateCategory(Category selectedCategory) throws Exception
    {
        bll.updateCategory(selectedCategory);
        getCategoryList();
    }
    
    public void deleteCategory(Category category) throws Exception
    {
        bll.deleteCategory(chosenCategory);
        getCategoryList();
    }
    
    public void setChosenCategory(Category chosenCategory)
    {
        this.chosenCategory = chosenCategory;
    }

   
    public Category getChosenCategory()
    {
        return chosenCategory;
    }
    
    public ObservableList<Movie> getSearchResult(String input) throws Exception
    {
        List<Movie> filter = bll.searchMovies(input);
        ObservableList<Movie> output = FXCollections.observableList(filter);
        
        return output;
    }
    
    public void createMovie(String name, int rating, String filelink, float imdb, ArrayList<Integer> idList) throws SQLException
    {
        Movie movie = bll.createMovie(name, rating, filelink, imdb, idList);
        movies.add(movie);
    }
    
    public void deleteMovie(Movie mov) throws SQLException
    {
        movies.remove(mov);
        bll.deleteMovie(mov);
    }
    
    public void updateMovie(Movie mov) throws SQLException
    {
        bll.updateMovie(mov);
    }
    
    
    public void updateLastView(Movie mov) throws Exception 
    {
        bll.updateLastView(mov);
    }
    
    public List<Movie> timeSinceLastview() throws SQLException
    {
        return bll.timeSinceLastview();
    }
}