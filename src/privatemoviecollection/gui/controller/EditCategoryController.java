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
import privatemoviecollection.be.Category;
import privatemoviecollection.dal.dalException.DALException;
import privatemoviecollection.gui.model.DataModel;
import privatemoviecollection.gui.utilGUI.DisplayAlert;

/**
 * FXML Controller class
 *
 * @author mads_
 */
public class EditCategoryController implements Initializable
{
    
    @FXML
    private TextField txtCategoryTitle;
    @FXML
    private Button cancel;
    @FXML
    private Button saveCategory;

    private DataModel dataModel;
    private Category category; 

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
    private void addNewCategory(ActionEvent event) throws DALException
    {   try{
        handleEditCategory(); 
        Stage stage = (Stage) saveCategory.getScene().getWindow();
        stage.close();}
    catch (DALException ex)
    {
        DisplayAlert al = new DisplayAlert();
        al.displayAlert(Alert.AlertType.ERROR, "ERROR - kunne ikke håndtere efterspørgslen", ex.getMessage());
    }
    }
    
    public void transferCategory(Category cg)
    {   
        category = cg;
        txtCategoryTitle.setText(cg.getName());
    }
    
    public void transferDatamodel(DataModel model)
    {
        dataModel = model;
    }
    
    public void handleEditCategory() throws DALException 
    {  try{
        category.setName(txtCategoryTitle.getText());
        dataModel.updateCategory(category);}
    catch (DALException ex)
    {
        DisplayAlert al = new DisplayAlert();
        al.displayAlert(Alert.AlertType.ERROR, "ERROR - kunne ikke håndtere efterspørgslen", ex.getMessage());
    }
    }
    
}
