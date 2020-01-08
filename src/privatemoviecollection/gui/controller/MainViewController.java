/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import privatemoviecollection.be.Category;
import privatemoviecollection.be.Movie;
import privatemoviecollection.gui.model.DataModel;

/**
 * FXML Controller class
 *
 * @author Christina
 */
public class MainViewController implements Initializable
{

    @FXML
    private TableView<Movie> movieTable;
    @FXML
    private TableColumn<Movie, String> movieName;
    @FXML
    private TableColumn<Movie, String> movieCategory;
    @FXML
    private TableColumn<Movie, Integer> movieRating;
    @FXML
    private TableColumn<Movie, Float> movieRatingIMDB;
    @FXML
    private TextField searchField;
    @FXML
    private ListView<Category> categoryFilter;
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
            setAllMovies();
            setAllCategories();
        } catch (Exception ex)
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
    private void handleNewCategory(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/privatemoviecollection/gui/view/NewCategory.fxml"));
        Parent root = loader.load();

        NewCategoryController newCategoryController = loader.getController();
        newCategoryController.transfer(dataModel);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
        setAllCategories();
    }

    @FXML
    private void handleDeleteCategory(ActionEvent event) throws Exception
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("A Deletion Confirmation");
        alert.setHeaderText("Are you sure you want to delete:");
        alert.setContentText(categoryFilter.getSelectionModel().getSelectedItem() + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {

            dataModel.setChosenCategory(categoryFilter.getSelectionModel().getSelectedItem());
            dataModel.deleteCategory(dataModel.getChosenCategory());

        } else
        {
            alert.close();
        }
    }
    
    @FXML
    private void handleEditCategory(ActionEvent event)
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
    
    private void setAllMovies() {
        
        // initialize the columns
        movieName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        //movieCategory.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        movieRating.setCellValueFactory(cellData -> cellData.getValue().ratingProperty());
        movieRatingIMDB.setCellValueFactory(cellData -> cellData.getValue().imdbProperty());

        // add data to the table
        movieTable.setItems(dataModel.getAllMovies());
    }

    private void setAllCategories()
    {
        try
        {
            categoryFilter.setItems(dataModel.getCategoryList());
        } catch (Exception ex)
        {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
