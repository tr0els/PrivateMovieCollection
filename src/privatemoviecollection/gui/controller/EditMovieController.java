/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui.controller;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
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
public class EditMovieController implements Initializable
{

    @FXML
    private TextField fileInput;
    @FXML
    private Button fileChooser;
    @FXML
    private TextField nameInput;
    @FXML
    private TextField ratingInput;
    @FXML
    private TextField imdbInput;
    @FXML
    private Button updateMovie;
    @FXML
    private MenuButton menuCategories;
    
    private Movie movie;
    private DataModel dm;
    private List<Category> categoryList;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

    }

    @FXML
    private void handleFileChooser(ActionEvent event)
    {
        String path;

        FileChooser fc = new FileChooser();
        //"HUSK AT DECOMMENT!!!!" fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Movie Files", "*.mp4", "*.mpeg4"));
        File file = fc.showOpenDialog(null);
        path = file.getAbsolutePath();
        path = path.replace("\\", "/");
        fileInput.setText(path);
    }

    /**
     * Adds all movies in the database to an arraylist.
     * Removes the selected movie from the arraylist so it isn't checked if the name equals itself.
     * Loops through every movie in the arraylist 
     * and checks if the nameinput equals a name already in the list 
     * so two movies can't have the same name. 
     * Then updates the parameters of the selected movie that the user has changed.
     */
    @FXML
    private void handleUpdateMovie(ActionEvent event) throws DALException
    {

        try
        {
            List<Movie> tempMovieList = new ArrayList<>();
            tempMovieList.addAll(dm.getAllMovies());
            tempMovieList.remove(movie);

            for (int i = 0; i < tempMovieList.size(); i++)
            {
                if (tempMovieList.get(i).toString().trim().equalsIgnoreCase(nameInput.getText()))
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "That name already exists in the database. Please pick another", ButtonType.OK);
                    alert.showAndWait();
                    return;
                }
            }
            movie.setName(nameInput.getText());
            movie.setRating(Integer.parseInt(ratingInput.getText()));
            movie.setFilelink(fileInput.getText());
            movie.setImdb(Float.parseFloat(imdbInput.getText()));
            
            dm.updateMovie(movie);
            Stage stage = (Stage) updateMovie.getScene().getWindow();
            stage.close();
        } catch (DALException ex)
        {
            DisplayAlert dp = new DisplayAlert();
            dp.displayAlert(AlertType.ERROR, "ERROR - kan ikke håndtere efterspørgslen", ex.getMessage());
        }
    }

    public void transfer(Movie currentMovie, DataModel datamodel)
    /**
     * Transfers the data from MainView to this view. 
     * @param movie
     * @param datamodel
     */
    public void transfer(Movie movie, DataModel datamodel)
    {
        movie = currentMovie;
        
        nameInput.setText(movie.getName());
        ratingInput.setText(movie.getRating() + "");
        fileInput.setText(movie.getFilelink());
        imdbInput.setText(movie.getImdb() + "");
        
        dm = datamodel;
    }
    
    public void categoryMenu(List<Category> list)
    {
        categoryList = list;
        for (Category category : categoryList)
        {
            CheckMenuItem checkMenuItem = new CheckMenuItem(category.getName());
            menuCategories.getItems().add(checkMenuItem);
            
            for (Category movieCategory : movie.getCategories())
            {
                if (category.getName().equals(movieCategory.getName()))
                {
                    checkMenuItem.setSelected(true);
                }
            }
            
        }
    }
}
