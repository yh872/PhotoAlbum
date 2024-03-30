package photo.Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminController {

   
@FXML
public void logout(ActionEvent event){
    try{
            
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/LoginView.fxml"));
        Parent root = fxmlLoader.load();
       Stage stage =  (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
             stage.setScene(scene);
            stage.setTitle("Photos App");
            stage.show();
            
        }
        catch (IOException e){
            e.printStackTrace();
        }
    
}
@FXML 
public void DisplayUserList(ActionEvent event){
    try{
            
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/UserList.fxml"));
        Parent root = fxmlLoader.load();
       Stage stage =  (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
             stage.setScene(scene);
            stage.setTitle("User List");
            stage.show();
            
        }
        catch (IOException e){
            e.printStackTrace();
        }
}
}
