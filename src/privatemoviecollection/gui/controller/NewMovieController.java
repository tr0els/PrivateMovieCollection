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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import privatemoviecollection.be.Movie;
import privatemoviecollection.be.Category;
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

    private DataModel datamodel;

    @FXML
    private MenuButton chooseCategory;

    List<Category> categoryList;

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
        ArrayList<Integer> idList = new ArrayList<Integer>();
        for (MenuItem item : chooseCategory.getItems())
        {
            CheckMenuItem checkMenuItem = (CheckMenuItem) item;
            if (checkMenuItem.isSelected())
            {
                int index = chooseCategory.getItems().indexOf(checkMenuItem);
                idList.add(categoryList.get(index).getId());
            }
        }

        for (int i = 0; i < datamodel.getAllMovies().size(); i++)
        {
            nameInput.getText();
            if (datamodel.getAllMovies().get(i).toString().trim().equalsIgnoreCase(nameInput.getText()))
            {
                Alert alert = new Alert(Alert.AlertType.WARNING, "That name already exists in the database. Please pick another", ButtonType.OK);
                alert.showAndWait();
                break; 
                
            } else
            {
                datamodel.createMovie(nameInput.getText(), Integer.parseInt(ratingInput.getText()), fileInput.getText(), Float.parseFloat(imdbInput.getText()), idList);
                Stage stage = (Stage) createMovie.getScene().getWindow();
                stage.close();
                break; 
            }
        }

    }

    public void transfer(DataModel dm)
    {
        datamodel = dm;
    }

    public void categoryMenu(List<Category> list)
    {
        categoryList = list;
        for (Category category : categoryList)
        {
            CheckMenuItem checkMenuItem = new CheckMenuItem(category.getName());
            chooseCategory.getItems().add(checkMenuItem);
        }

    }

}
