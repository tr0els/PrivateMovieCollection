/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.be;

import java.util.Date;
import java.util.List;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Christina
 */
public class Movie {

    private final int id;
    private StringProperty filelink;
    private StringProperty name;
    private IntegerProperty rating;
    private FloatProperty imdb;
    private Date lastview;
    private ObservableList<Category> categories;

    public Movie(int id, String filelink, String name, float imdb, int rating) {
        this.id = id;
        this.filelink = new SimpleStringProperty(filelink);
        this.name = new SimpleStringProperty(name);
        this.imdb = new SimpleFloatProperty(imdb);
        this.rating = new SimpleIntegerProperty(rating);
        this.categories = FXCollections.observableArrayList();
    }

    /**
     * Gets the movies id;
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the movies filelink.
     * @return filelink.get().
     */
    public String getFilelink() {
        return filelink.get();
    }

    /**
     * Sets the movies filelink.
     * @param filelink 
     */
    public void setFilelink(String filelink) {
        this.filelink.set(filelink);
    }

    /**
     * returns the movies filelink property
     * @param filelink 
     */
    public StringProperty filelinkProperty() {
        return filelink;
    }

    /**
     * Gets the movies name;
     * @return name.get().
     */
    public String getName() {
        return name.get();
    }

    /**
     * Sets the movies name;
     * @param name 
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * returns the movies filelink property
     * @param nameProperty
     */
    public StringProperty nameProperty() {
        return name;
    }
    
    /**
     * Gets the movies rating.
     * @return rating.get().
     */
    public int getRating() {
        return rating.get();
    }

    /**
     * Sets the movies rating.
     * @param rating 
     */
    public void setRating(int rating) {
        this.rating.set(rating);
    }

    public ObservableValue<Integer> ratingProperty() {
        return rating.asObject();
    }
    
    /**
     * Gets the movies imdb rating.
     * @return imdb.get().
     */
    public float getImdb() {
        return imdb.get();
    }

    /**
     * Sets the movies imdb rating.
     * @param imdb 
     */
    public void setImdb(Float imdb) {
        this.imdb.set(imdb);
    }

    public ObservableValue<Float> imdbProperty() {
        return imdb.asObject();
    }
    
    /**
     * Gets the movies lastview.
     * @return lastview.
     */
    public Date getLastview() {
        return lastview;
    }

    /**
     * Sets the movies lastview.
     * @param lastview 
     */
    public void setLastview(Date lastview) {
        this.lastview = lastview;
    }
    
    /**
     * Gets the movies categories.
     * @return categories.
     */
    public ObservableList<Category> getCategories() {
        return categories;
    }
    
    /**
     * Adds a list of categories to the movie. 
     * @param category 
     */
    public void addCategories(List<Category> category) {
        categories.addAll(category);
    }

    /**
     * Adds one category to the movie.
     * @param category 
     */
    public void addCategory(Category category) {
        categories.add(category);
    }
    
    /**
     * Removes one category from the movie.
     * @param category 
     */
    public void removeCategory(Category category) {
        categories.remove(category);
    }
    
    /**
     * Removes all categories from the movie.
     */
    public void removeCategories() {
        categories.clear();
    }
    
    /**
     * Name of the movie
     * @return the observable name property
     */
    @Override
    public String toString()
    {
        return getName();
    }

    /**
     * Changes the equals method so that movie objects are equal 
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
        final Movie other = (Movie) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }   
}