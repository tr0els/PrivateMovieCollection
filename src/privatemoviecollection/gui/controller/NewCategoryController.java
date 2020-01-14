/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import privatemoviecollection.dal.dalException.DALException;
import privatemoviecollection.gui.model.DataModel;
import privatemoviecollection.gui.utilGUI.DisplayAlert;

/**
 * FXML Controller class
 *
 * @author mads_
 */
public class NewCategoryController implements Initializable
{

    @FXML
    private TextField txtCategoryTitle;
    @FXML
    private Button cancel;
    @FXML
    private Button saveCategory;

    private DataModel dataModel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    /**
     * Closes the window and cancels the creation of the movie.
     */
    @FXML
    private void closeWindow(ActionEvent event)
    {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    /**
     * Adds a new category to the list. Then closes the window.
     */
    @FXML
    private void addNewCategory(ActionEvent event) 
    {
        try
        {
            dataModel = new DataModel();
            dataModel.createCategory(txtCategoryTitle.getText());

            Stage stage = (Stage) saveCategory.getScene().getWindow();
            stage.close();

        } catch (DALException ex)
        {
            DisplayAlert al = new DisplayAlert();
            al.displayAlert(Alert.AlertType.ERROR, "ERROR - Could not Add Category", ex.getMessage());
        }
    }

    /**
     * Transfers datamodel from MainView to this view
     *
     * @param dm
     */
    public void transfer(DataModel dm)
    {
        dataModel = dm;
    }

}
