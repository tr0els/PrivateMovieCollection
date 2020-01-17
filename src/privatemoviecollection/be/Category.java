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
    
    /**
     * Gets the id of a category
     * @return
     */
    public int getId()
    {
        return id;
    }

    /**
     * Gets the name of a category
     * @return
     */
    public String getName()
    {
        return name.get();
    }

    /**
     * Sets the name of a category. 
     * @param name
     */
    public void setName(String name)
    {
        this.name.set(name);
    }
    
    /**
     * Name of the category
     * @return the observable name property
     */
    public StringProperty nameProperty()
    {
        return name; 
    }
    
    /**
     * Overrides the toString method to show the name of a category. 
     * @return
     */
    @Override
    public String toString()
    {
        return getName();
    }

    /**
     * Changes the equals method so that category objects are equal 
     * if they have the same id
     * @param obj category object
     * @return boolean true or false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Category other = (Category) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
