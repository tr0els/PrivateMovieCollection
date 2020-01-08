/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.dal.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.StringProperty;
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
    
    public Category createCategory(String name) throws Exception
    {
        try ( Connection con = dbCon.getConnection())
        {
            String sql = "INSERT INTO Category VALUES (?)";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 1)
            {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next())
                {
                    int id = rs.getInt(1);
                    Category cg = new Category(id, name);
                    return cg;
                }
            }
            throw new Exception();

        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception();
        }
    }
    
    public List<Category> getAllCategories() throws Exception
    {
        try ( Connection con = dbCon.getConnection())
        {

            String sql = "SELECT * FROM Category;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Category> allCategories = new ArrayList<>();
            while (rs.next())
            {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                Category cg = new Category(id, name);
                allCategories.add(cg);
            }
            return allCategories;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception();
        }
    }
    
    public boolean deleteCategory(Category category) throws Exception
    {
        try ( Connection con = dbCon.getConnection())
        {
            int id = category.getId();
            String sqlcm = "DELETE FROM CatMovie WHERE CategoryId=?";
            String sql = "DELETE FROM Category WHERE id=?";
            
            PreparedStatement ps = con.prepareStatement(sqlcm);
            PreparedStatement ps2 = con.prepareStatement(sql);
                     
            ps.setInt(1, id);
            ps2.setInt(1, id);
            if (ps2.executeUpdate() == 1)
            {
                return true;
            } else
            {
                throw new Exception();
            }

        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception();
        }
        
    }
    
    public void updateCategory(Category category) throws Exception
    {
        try ( Connection con = dbCon.getConnection())
        {
            int id = category.getId();
            String name = category.getName();
            String sql = "UPDATE Category SET name=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, id);
            
            
            int affectedRows = ps.executeUpdate();
            if (affectedRows != 1)
            {
                throw new Exception();
            }

        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception();
        }
        
    }
    
}
