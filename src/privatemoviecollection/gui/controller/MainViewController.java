/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import privatemoviecollection.gui.model.DataModel;

/**
 * FXML Controller class
 *
 * @author Christina
 */
public class MainViewController implements Initializable
{

    @FXML
    private TableView<?> categoryTable;
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
    @FXML
    private Button editRatingButton;

    private DataModel dataModel;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        dataModel = new DataModel();
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
    private void handleNewMovie(ActionEvent event)
    {
    }

    @FXML
    private void handleDeleteMovie(ActionEvent event)
    {
    }

    @FXML
    private void handleEditRating(ActionEvent event)
    {
    }
    
}
