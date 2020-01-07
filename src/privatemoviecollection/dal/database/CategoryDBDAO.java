/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.dal.database;

import java.io.IOException;
import java.util.List;
import privatemoviecollection.be.Category;

/**
 *
 * @author Christina
 */
public class CategoryDBDAO
{
    private DatabaseConnector dbCon;
    
    public CategoryDBDAO() throws IOException
    {
        dbCon = new DatabaseConnector();
    }
    
    public Category createCategory()
    {
        return null;
    }
    
    public List<Category> getAllCategories()
    {
        return null;
    }
    
    public void deleteCategory()
    {
        
    }
    
    public void updateCategory()
    {
        
    }
    
}
