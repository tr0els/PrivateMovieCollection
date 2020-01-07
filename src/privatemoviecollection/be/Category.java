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
    private StringProperty name;
    
    public Category(int id, String name)
    {
        this.id = id;
        this.name = new SimpleStringProperty(name);
    }

    public StringProperty getName()
    {
        return name;
    }

    public void setName(StringProperty name)
    {
        this.name = name;
    }
}
