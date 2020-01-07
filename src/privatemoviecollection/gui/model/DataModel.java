/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import privatemoviecollection.be.Movie;
import privatemoviecollection.bll.BLLManager;

/**
 *
 * @author Christina
 */
public class DataModel
{
    private BLLManager bll;
    
    public DataModel() throws IOException
    {
        bll = new BLLManager();
    }
    
//    public ObservableList<Movie> getSearchResult(String input) throws Exception
//    {
//        List<Movie> filter = bll.search(input);
//        ObservableList<Movie> output = FXCollections.observableList(filter);
//        
//        return output;
//    }
    
    public void createMovie(String name, int rating, String filelink, float imdb) throws SQLException
    {
        Movie mov = bll.createMovie(name, rating, filelink, imdb);
    }
    
    
}

