/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import privatemoviecollection.be.Movie;
import privatemoviecollection.gui.model.DataModel;

/**
 * FXML Controller class
 *
 * @author Christina
 */
public class AlertOldMoviesController implements Initializable
{
    private DataModel dm;
    @FXML
    private ListView<String> oldMovieList;
    @FXML
    private Button deleteOldMovieButton;
    @FXML
    private Button cancelButton;
    
    private ObservableList<Movie> movieList;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        movieList = FXCollections.observableArrayList();
        try
        {
            getListOfMovies();
        } catch (SQLException ex)
        {
            Logger.getLogger(AlertOldMoviesController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
    }    
    
    public void getListOfMovies() throws SQLException
    {
        ObservableList<String> movieNames = FXCollections.observableArrayList();
        if (dm.timeSinceLastview() != null)
        {
            movieList.setAll(dm.timeSinceLastview());
        
            for (Movie movie : movieList)
            {
                movieNames.add(movie.getName());
            }
            oldMovieList.setItems(movieNames);
        }
    }
    
    public void transfer(DataModel datamodel)
    {
        dm = datamodel;
    }

    @FXML
    private void handleDeleteMovie(ActionEvent event) throws SQLException
    {
        int index = oldMovieList.getSelectionModel().getSelectedIndex();
        dm.deleteMovie(movieList.get(index));
        movieList.remove(movieList.get(index));
    }

    @FXML
    private void handleCancel(ActionEvent event) throws Exception
    {
        for (Movie oldMovy : movieList)
        {
            dm.updateLastView(oldMovy);
        }
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    
    
    
    
    
}
