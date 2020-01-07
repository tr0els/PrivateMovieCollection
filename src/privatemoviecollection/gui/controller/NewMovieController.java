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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import privatemoviecollection.gui.model.DataModel;

/**
 * FXML Controller class
 *
 * @author Christina
 */
public class NewMovieController implements Initializable
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
    private Button createMovie;
    @FXML
    private TextField categoryInput;

    private DataModel datamodel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
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
    private void handleCreateMovie(ActionEvent event) throws SQLException
    {
        datamodel.createMovie(nameInput.getText(), Integer.parseInt(ratingInput.getText()), fileInput.getText(), Float.parseFloat(imdbInput.getText()));
        Stage stage = (Stage) createMovie.getScene().getWindow();
        stage.close();
    }
    
    public void transfer(DataModel dm)
    {
        datamodel = dm;
    }
    
}
