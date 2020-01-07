/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui.model;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
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

    private BLLManager bm; 
    private Category chosenCategory; 
    
    public ObservableList<Category> categories = FXCollections.observableArrayList();
    
    public DataModel() throws IOException
    {
        bm = new BLLManager();
    }
    
    public ObservableList<Category> getCategoryList() throws IOException, Exception
    {
        categories.setAll(bm.getAllCategories());   
        return categories;

    }
    
    public void createCategory(String name) throws Exception
    {
        Category category = bm.createCategory(name);
        categories.add(category);
        getCategoryList();       
    }
    
    public void updateCategory(Category selectedCategory) throws Exception
    {
        bm.updateCategory(selectedCategory);
        getCategoryList();
    }
    
    public void deleteCategory(Category category) throws Exception
    {
        bm.deleteCategory(chosenCategory);
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
    
    
//    public ObservableList<Movie> getSearchResult(String input) throws Exception
//    {
//        List<Movie> filter = bll.search(input);
//        ObservableList<Movie> output = FXCollections.observableList(filter);
//        
//        return output;
//    }
}

