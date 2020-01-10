/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui.controller;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import privatemoviecollection.be.Movie;
import privatemoviecollection.gui.model.DataModel;

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
    private TextField categoryInput;
    @FXML
    private Button updateMovie;

    private Movie movie;
    private DataModel dm;

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
        //fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("*.mp4"));
        File file = fc.showOpenDialog(null);
        path = file.getAbsolutePath();
        path = path.replace("\\", "/");
        fileInput.setText(path);
    }

    @FXML
    private void handleUpdateMovie(ActionEvent event) throws SQLException, Exception
    {
        
        for (int i = 0; i < dm.getAllMovies().size(); i++)
        {

            if (dm.getAllMovies().get(i).toString().trim().equalsIgnoreCase(nameInput.getText()))
            {
                Alert alert = new Alert(Alert.AlertType.WARNING, "That name already exists in the database. Please pick another", ButtonType.OK);
                alert.showAndWait();            
                Stage stage = (Stage) updateMovie.getScene().getWindow();
                stage.show();
                break; 
                
            } else
            {
                movie.setName(nameInput.getText());
                movie.setRating(Integer.parseInt(ratingInput.getText()));
                movie.setFilelink(fileInput.getText());
                movie.setImdb(Float.parseFloat(imdbInput.getText()));
                dm.updateMovie(movie);
                Stage stage = (Stage) updateMovie.getScene().getWindow();
                stage.close();
            }
        }

    }

    public void transfer(Movie movie, DataModel datamodel)
    {
        nameInput.setText(movie.getName());
        ratingInput.setText(movie.getRating() + "");
        fileInput.setText(movie.getFilelink());
        imdbInput.setText(movie.getImdb() + "");

        this.movie = movie;
        dm = datamodel;
    }
}
