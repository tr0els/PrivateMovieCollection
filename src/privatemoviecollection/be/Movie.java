/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.be;

import java.util.Date;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

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

    public Movie(int id, String filelink, String name, float imdb, int rating) {
        this.id = id;
        this.filelink = new SimpleStringProperty(filelink);
        this.name = new SimpleStringProperty(name);
        this.imdb = new SimpleFloatProperty(imdb);
        this.rating = new SimpleIntegerProperty(rating);
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
}