/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui.utilGUI;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author zilot
 */
public class DisplayAlert
{
    //util class to display catched Exception 
  public void displayAlert(AlertType type, String title, String message )
  {
      Alert alert = new Alert(type);
      alert.setTitle(title);
      alert.setContentText(message);
      alert.showAndWait();
      
  }
    
}
