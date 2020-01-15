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

    public int getId() {
        return id;
    }

    public String getFilelink() {
        return filelink.get();
    }

    public void setFilelink(String filelink) {
        this.filelink.set(filelink);
    }

    public StringProperty filelinkProperty() {
        return filelink;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public int getRating() {
        return rating.get();
    }

    public void setRating(int rating) {
        this.rating.set(rating);
    }

    public ObservableValue<Integer> ratingProperty() {
        return rating.asObject();
    }
    
    public float getImdb() {
        return imdb.get();
    }

    public void setImdb(Float imdb) {
        this.imdb.set(imdb);
    }

    public ObservableValue<Float> imdbProperty() {
        return imdb.asObject();
    }
    
    public Date getLastview() {
        return lastview;
    }

    public void setLastview(Date lastview) {
        this.lastview = lastview;
    }
    
    public ObservableList<Category> getCategories() {
        return categories;
    }
    
    public void addCategories(List<Category> category) {
        categories.addAll(category);
    }

    public void addCategory(Category category) {
        categories.add(category);
    }
    
    public void removeCategory(Category category) {
        categories.remove(category);
    }
    
    public void removeCategories() {
        categories.clear();
    }
    
    @Override
    public String toString()
    {
        return getName();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

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