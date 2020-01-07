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

/**
 *
 * @author Christina
 */
public class Movie
{
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

    public StringProperty getFilelink()
    {
        return filelink;
    }

    public void setFilelink(StringProperty filelink)
    {
        this.filelink = filelink;
    }

    public StringProperty getName()
    {
        return name;
    }

    public void setName(StringProperty name)
    {
        this.name = name;
    }

    public IntegerProperty getRating()
    {
        return rating;
    }

    public void setRating(IntegerProperty rating)
    {
        this.rating = rating;
    }

    public FloatProperty getImdb()
    {
        return imdb;
    }

    public void setImdb(FloatProperty imdb)
    {
        this.imdb = imdb;
    }

    public Date getLastview()
    {
        return lastview;
    }

    public void setLastview(Date lastview)
    {
        this.lastview = lastview;
    }
    
}