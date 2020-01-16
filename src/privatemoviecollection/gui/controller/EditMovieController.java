package privatemoviecollection.gui.controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
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
    
    private DataModel dm;
    private Movie movie;
    private List<Category> allCategories = new ArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
    }
    
    /**
     * Transfers the data from MainView to this view. 
     * @param movie
     * @param datamodel
     */
    public void transfer(Movie movie, DataModel datamodel)
    {
        try 
        {
            dm = datamodel;
            this.movie = movie;
            allCategories = dm.getCategoryList();
            nameInput.setText(movie.getName());
            ratingInput.setText(movie.getRating() + "");
            fileInput.setText(movie.getFilelink());
            imdbInput.setText(movie.getImdb() + "");
            setCategoryMenu();
        } 
        catch (DALException ex)
        {
            DisplayAlert al = new DisplayAlert();
            al.displayAlert(Alert.AlertType.ERROR, "ERROR - Could not retrieve list of all categories from database", ex.getMessage());
        }
    }

    /**
     * Updates movie with the data that the user has changed.
     * Both movie object and database gets updated.
     */
    @FXML
    private void handleUpdateMovie(ActionEvent event) 
    {
            // check if movie name is already taken before proceeding
            List<Movie> tempMovieList = new ArrayList<>();
            tempMovieList.addAll(dm.getAllMovies());
            tempMovieList.remove(movie);

            for (int i = 0; i < tempMovieList.size(); i++)
            {
                if (tempMovieList.get(i).toString().trim().equalsIgnoreCase(nameInput.getText()))
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "That name already exists. Please pick another", ButtonType.OK);
                    alert.showAndWait();
                    return; // exit
                }
            }
            
            // updates movie object
            movie.setName(nameInput.getText());
            movie.setRating(Integer.parseInt(ratingInput.getText()));
            movie.setFilelink(fileInput.getText());
            movie.setImdb(Float.parseFloat(imdbInput.getText()));
            
            // updates the movie objects list of categories to the selected ones
            movie.getCategories().clear();
            for (MenuItem item : menuCategories.getItems())
            {
                CheckMenuItem checkMenuItem = (CheckMenuItem) item;
                if (checkMenuItem.isSelected())
                {
                    int index = menuCategories.getItems().indexOf(checkMenuItem);
                    movie.getCategories().add(allCategories.get(index));
                }
            }
            
            try {
                dm.updateCategoryCatMovie(movie);
                dm.updateMovie(movie);
            } catch (DALException ex)
            {
                DisplayAlert al = new DisplayAlert();
                al.displayAlert(Alert.AlertType.ERROR, "ERROR - Could not update movie data in the database", ex.getMessage());
            }
            
            Stage stage = (Stage) updateMovie.getScene().getWindow();
            stage.close();
    }
    
    /**
     * Sets a lists of all movie categories in the MenuButton, 
     * and checks the categories the movie belongs to. 
     */
    private void setCategoryMenu()
    {
        for (Category menuCategory : allCategories)
        {
            CheckMenuItem checkMenuItem = new CheckMenuItem(menuCategory.toString());
            menuCategories.getItems().add(checkMenuItem);

            if(movie.getCategories().contains(menuCategory))
            {
                checkMenuItem.setSelected(true);
            }
        }
    }
       
    /**
     * Lets the user choose a file from their computer. 
     * @param event 
     */
    @FXML
    private void handleFileChooser(ActionEvent event)
    {
        String path;

        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Movie Files", "*.mp4", "*.mpeg4"));
        File file = fc.showOpenDialog(null);
        path = file.getAbsolutePath();
        path = path.replace("\\", "/");
        fileInput.setText(path);
    }
}
