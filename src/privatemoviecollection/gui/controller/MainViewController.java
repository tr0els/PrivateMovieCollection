/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import privatemoviecollection.gui.model.DataModel;

/**
 * FXML Controller class
 *
 * @author Christina
 */
public class MainViewController implements Initializable
{

    @FXML
    private TableView<?> movieTable;
    @FXML
    private TextField searchField;
    @FXML
    private ListView<?> categoryFilter;
    @FXML
    private ComboBox<?> comboFilterRating;
    @FXML
    private Button playMovieButton;
    @FXML
    private Button showAllButton;
    @FXML
    private Button newCategoryButton;
    @FXML
    private Button deleteCategoryButton;
    @FXML
    private Button newMovieButton;
    @FXML
    private Button deleteMovieButton;

    private DataModel dataModel;
    @FXML
    private Button editMovieButton;
    @FXML
    private Button editCategoryButton;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
            dataModel = new DataModel();
        } catch (IOException ex)
        {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void handleSearch(KeyEvent event)
    {
//        String input = searchField.getText();
//        ObservableList<Movie> result = dataModel.getSearchResult(input);
//        movieTable.setItems(result);
    }

    @FXML
    private void handlePlayMovie(ActionEvent event)
    {
    }

    @FXML
    private void handleShowAll(ActionEvent event)
    {
    }

    @FXML
    private void handleNewCategory(ActionEvent event)
    {
    }

    @FXML
    private void handleDeleteCategory(ActionEvent event)
    {
    }

    @FXML
    private void handleNewMovie(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/privatemoviecollection/gui/view/NewMovie.fxml"));
        Parent root = loader.load();

        NewMovieController newMovieController = loader.getController();
        newMovieController.transfer(dataModel);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void handleDeleteMovie(ActionEvent event)
    {
    }

    @FXML
    private void handleEditMovie(ActionEvent event)
    {
    }

    @FXML
    private void handleEditCategory(ActionEvent event)
    {
    }
    
}
