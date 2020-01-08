/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import privatemoviecollection.gui.model.DataModel;

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

    @FXML
    private void closeWindow(ActionEvent event)
    {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void addNewCategory(ActionEvent event) throws Exception
    {
        try
        {
            dataModel = new DataModel();
            dataModel.createCategory(txtCategoryTitle.getText());

            Stage stage = (Stage) saveCategory.getScene().getWindow();
            stage.close();

        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public void transfer(DataModel dm)
    {
        dataModel = dm;
    }

}
