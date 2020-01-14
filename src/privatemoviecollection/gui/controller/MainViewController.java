/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import privatemoviecollection.be.Category;
import privatemoviecollection.be.Movie;
import privatemoviecollection.dal.dalException.DALException;
import privatemoviecollection.gui.model.DataModel;
import privatemoviecollection.gui.utilGUI.DisplayAlert;

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
    private TableColumn<Movie, List<Category>> movieCategory;
    @FXML
    private TableColumn<Movie, Integer> movieRating;
    @FXML
    private TableColumn<Movie, Float> movieRatingIMDB;
    @FXML
    private TextField searchField;
    @FXML
    private ListView<Category> categoryFilter;
    @FXML
    private ComboBox<String> comboFilterRating;
    @FXML
    private Button playMovieButton;
    @FXML
    private Button clearFilter;
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

    //laver listen til comboFilterRating med tal fra 1-10

    ObservableList<String> comboList = FXCollections.observableArrayList("Filter by rating","1","2","3","4","5","6","7","8","9","10");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
            comboFilterRating.setItems(comboList);
            dataModel = new DataModel();
            setAllMovies();
            setAllCategories();

            if (!dataModel.timeSinceLastview().isEmpty())
            {
                alertOldMovies();
            }
        } catch (DALException ex)
        {
            DisplayAlert al = new DisplayAlert();
            al.displayAlert(Alert.AlertType.ERROR, "Kunne ikke hente dine filer", ex.getMessage());
        }
    }

    @FXML
    private void handleSearch(KeyEvent event)
    {
        try
        {
            String input = searchField.getText();
            ObservableList<Movie> result = dataModel.getSearchResult(input);
            movieTable.setItems(result);
        } catch (DALException ex)
        {
            DisplayAlert al = new DisplayAlert();
            al.displayAlert(Alert.AlertType.ERROR, "Kunne ikke håndtere din efterspørgsel", ex.getMessage());
        }
    }

    /*
    *Handel playmovie via systemdefault mediaplayer 
    */
    @FXML
    private void handlePlayMovie(ActionEvent event)
    {
        try{
       
            String s = movieTable.getSelectionModel().getSelectedItem().getFilelink();
            File f = new File(s);
            Desktop d = Desktop.getDesktop();
            d.open(f);

            Movie mo = movieTable.getSelectionModel().getSelectedItem();
            dataModel.updateLastView(mo);

        } catch (DALException ex1)
        {
            DisplayAlert al = new DisplayAlert();
            al.displayAlert(Alert.AlertType.ERROR, "Kunne ikke hente dine filer", ex1.getMessage());
        } catch (IOException ex)

        {
            DisplayAlert al = new DisplayAlert();
            al.displayAlert(Alert.AlertType.ERROR, "Kunne ikke åbne mediaplayer", ex.getMessage());
        }
    }
    /*
    *Sets allmovies, allcategories and clears searchfiled and combobox
    */
    @FXML
    private void handleClearFilter(ActionEvent event)
    {
        
        setAllMovies();
        setAllCategories();
        searchField.clear();       
        comboFilterRating.getSelectionModel().clearAndSelect(0);

    }
  

    /**
     * Opens the NewCategory controller. Transfers the datamodel
     * and updates the list of categories. 
     */
    @FXML
    private void handleNewCategory(ActionEvent event)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/privatemoviecollection/gui/view/NewCategory.fxml"));
            Parent root = loader.load();

            NewCategoryController newCategoryController = loader.getController();
            newCategoryController.transfer(dataModel);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
            setAllCategories();
        } catch (IOException ex)
        {
            DisplayAlert al = new DisplayAlert();
            al.displayAlert(Alert.AlertType.ERROR, "ERROR - kunne ikke åbne NewCategory", ex.getMessage());
        }
    }

    /**
     * Deletes the category selected from the list. Opens a confirmation alert
     * for the user to confirm the action.
     */
    @FXML
    private void handleDeleteCategory(ActionEvent event)
    {
        try
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
        } catch (DALException ex)
        {
            DisplayAlert al = new DisplayAlert();
            al.displayAlert(Alert.AlertType.ERROR, "ERROR - kunne ikke håndtere efterspørgslen", ex.getMessage());
        }
    }

    /**
     * Opens the EditCategory view and transfers the selected category as well
     * as the datamodel.
     */
    @FXML
    private void handleEditCategory(ActionEvent event)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/privatemoviecollection/gui/view/EditCategory.fxml"));
            Parent root = loader.load();

            if (categoryFilter.getSelectionModel().getSelectedItem() != null)
            {
                EditCategoryController EditCategoryController = loader.getController();
                EditCategoryController.transferCategory(categoryFilter.getSelectionModel().getSelectedItem());
                EditCategoryController.transferDatamodel(dataModel);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }
        } catch (IOException ex)
        {
            DisplayAlert al = new DisplayAlert();
            al.displayAlert(Alert.AlertType.ERROR, "ERROR - Håndtere efterspørgslen", ex.getMessage());
        }
    }

    @FXML
    private void handleNewMovie(ActionEvent event)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/privatemoviecollection/gui/view/NewMovie.fxml"));
            Parent root = loader.load();

            NewMovieController newMovieController = loader.getController();
            newMovieController.transfer(dataModel);
            newMovieController.categoryMenu(dataModel.getCategoryList());

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (DALException | IOException ex)
        {
            DisplayAlert al = new DisplayAlert();
            al.displayAlert(Alert.AlertType.ERROR, "ERROR - kunne ikke håndtere efterspørgslen", ex.getMessage());
        }
    }

    @FXML
    private void handleDeleteMovie(ActionEvent event)
    {
        try
        {
            String name = movieTable.getSelectionModel().getSelectedItem().getName();

            if (movieTable.getSelectionModel().getSelectedItem() != null)
            {
                int input = JOptionPane.showConfirmDialog(null, "Permanently delete " + name + " from the list?", "Select an Option...",
                        JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

                // 0=yes, 1=no.
                if (input == JOptionPane.YES_OPTION)
                {
                    dataModel.deleteMovie(movieTable.getSelectionModel().getSelectedItem());
                }
            }
        } catch (DALException ex)
        {
            DisplayAlert al = new DisplayAlert();
            al.displayAlert(Alert.AlertType.ERROR, "ERROR - kunne ikke håndtere efterspørgslen", ex.getMessage());
        }
    }

    @FXML
    private void handleEditMovie(ActionEvent event)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/privatemoviecollection/gui/view/EditMovie.fxml"));
            Parent root = loader.load();

            EditMovieController editmoviecontroller = loader.getController();
            editmoviecontroller.transfer(movieTable.getSelectionModel().getSelectedItem(), dataModel);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex)
        {
            DisplayAlert al = new DisplayAlert();
            al.displayAlert(Alert.AlertType.ERROR, "ERROR - Fejl i out- eller indput", ex.getMessage());
        }
    }

    @FXML
    private void filterByRating(ActionEvent event)
    {

    }

    private void setAllMovies()
    {

        movieName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        movieCategory.setCellValueFactory(new PropertyValueFactory<>("categories"));
        movieCategory.setCellFactory(col -> new TableCell<Movie, List<Category>>()
        {
            @Override
            public void updateItem(List<Category> categories, boolean empty)
            {
                super.updateItem(categories, empty);
                if (empty || categories.isEmpty())
                {
                    setText(null);
                } else
                {
                    String text = "";
                    for (Category c : categories)
                    {
                        text = text + c.getName() + ", ";
                    }
                    text = text.replaceAll(", $", "");
                    text.trim();
                    setText(text);
                }
            }
        });

        /*   
        // custom rendering of the time table cell
        movieCategory.setCellFactory(column -> new TableCell<Movie, List<Category>>() {

            @Override
            protected void updateItem(List<Category> item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setText(null);
                } else if(!item.isEmpty()) {
                    String text = "";
                    for (Category c : item) {
                        text = text + c.getName() + ", ";
                    }
                    text = text.replaceAll(", $", "");
                    text.trim();
                    setText(text);
                } else {
                    setText("list is empty!");
                }
            }
        });
         */
        movieRating.setCellValueFactory(cellData -> cellData.getValue().ratingProperty());
        movieRatingIMDB.setCellValueFactory(cellData -> cellData.getValue().imdbProperty());

        // add data to the table
        movieTable.setItems(dataModel.getAllMovies());
    }

    /**
     * Sets all the categories in the listview
     */
    private void setAllCategories()
    {
        try
        {
            categoryFilter.setItems(dataModel.getCategoryList());
        } catch (DALException ex)
        {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void alertOldMovies()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/privatemoviecollection/gui/view/AlertOldMovies.fxml"));
            Parent root = loader.load();

            AlertOldMoviesController alertOldMoviesController = loader.getController();
            alertOldMoviesController.transfer(dataModel);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setAlwaysOnTop(true);
            stage.show();

        } catch (DALException | IOException ex)
        {
            DisplayAlert al = new DisplayAlert();
            al.displayAlert(Alert.AlertType.ERROR, "ERROR - kunne ikke håndtere efterspørgslen", ex.getMessage());
        }
    }
}
