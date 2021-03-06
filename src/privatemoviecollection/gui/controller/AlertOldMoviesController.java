/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import privatemoviecollection.be.Movie;
import privatemoviecollection.dal.dalException.DALException;
import privatemoviecollection.gui.model.DataModel;
import privatemoviecollection.gui.utilGUI.DisplayAlert;

/**
 * FXML Controller class
 *
 * @author Christina
 */
public class AlertOldMoviesController implements Initializable
{
    @FXML
    private ListView<Movie> oldMovieList;
    @FXML
    private Button deleteOldMovieButton;
    @FXML
    private Button cancelButton;
    
    private ObservableList<Movie> movieList;
    private DataModel dm;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
    }    
    
    /**
     * Gets a list of movies that the user hasn't seen in more than 2 years, 
     * and that has a personal rating under 6. 
     * These movies are put into a list and set into a listview. 
     * @throws DALException 
     */
    public void getListOfMovies() throws DALException 
    {   try{
        movieList.addAll(dm.timeSinceLastview());      
        oldMovieList.setItems(movieList);}
    
        catch (DALException ex)
        {
            DisplayAlert al = new DisplayAlert();
            al.displayAlert(Alert.AlertType.ERROR, "ERROR - kunne ikke håndtere efterspørgslen", ex.getMessage());
        }
    
    }
    
    /**
     * Transfers the datamodel and the movielist to the controller. 
     * @param datamodel
     * @throws DALException 
     */
    public void transfer(DataModel datamodel) throws DALException 
    {
        dm = datamodel;
        movieList = FXCollections.observableArrayList();
        
        getListOfMovies();
        
    }
    
    /**
     * Deletes a movie from the listview, and from the mainView. 
     * @param event 
     */
    @FXML
    private void handleDeleteMovie(ActionEvent event) 
    {   try{
        int index = oldMovieList.getSelectionModel().getSelectedIndex();
        dm.deleteMovie(movieList.get(index));
        movieList.remove(index);}
    catch (DALException ex)
            {
            DisplayAlert al = new DisplayAlert();
            al.displayAlert(Alert.AlertType.ERROR, "ERROR - kunne ikke håndtere efterspørgslen", ex.getMessage());
            }

    }

    /**
     * Closes the window, and updates the lastview of the movies, 
     * that are still in the list. 
     * @param event 
     */
    @FXML
    private void handleCancel(ActionEvent event)  
    { try{
        for (Movie oldMovy : movieList)
        {
            dm.updateLastView(oldMovy);
        }
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();}
    catch (DALException ex)
            {
            DisplayAlert al = new DisplayAlert();
            al.displayAlert(Alert.AlertType.ERROR, "ERROR - kunne ikke håndtere efterspørgslen", ex.getMessage());
            }
    }
}
