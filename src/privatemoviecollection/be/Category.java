/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.be;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Christina
 */
public class Category
{
    private final int id;
    private SimpleStringProperty name;
    
    public Category(int id, String name)
    {
        this.id = id;
        this.name = new SimpleStringProperty(name);
    }
    
    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name.get();
    }

    public void setName(String name)
    {
        this.name.set(name);
    }
}
