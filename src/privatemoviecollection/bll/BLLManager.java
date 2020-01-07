/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.bll;

import java.io.IOException;
import java.util.List;
import privatemoviecollection.be.Category;
import privatemoviecollection.bll.util.SearchMovies;
import privatemoviecollection.dal.database.CategoryDBDAO;

/**
 *
 * @author Christina
 */
public class BLLManager
{
    
    private CategoryDBDAO categoryDbDao;
    
    public BLLManager() throws IOException
    {
        categoryDbDao = new CategoryDBDAO();
    }

    
    
    public List<Category> getAllCategories() throws Exception
    {
        return categoryDbDao.getAllCategories();
    }
    
    public Category createCategory(String name) throws Exception
    {
        Category category = categoryDbDao.createCategory(name);
        return category;
    }
    
    public boolean deleteCategory(Category category) throws Exception
    {
        return categoryDbDao.deleteCategory(category);
    }
    
    public void updateCategory(Category category) throws Exception
    {
        categoryDbDao.updateCategory(category);
    }
    
    
//    public List<Movie> search(String query) throws Exception
//    {
//        List<Movie> searchBase = mediaDB.getAllMedias();
//        searchBase = SearchMovies.search(searchBase, query);
//        
//        return searchBase;
//    }

    
}
