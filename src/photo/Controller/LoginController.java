/**
 * 
 * 
 * Controller class for the login window, which is the first window users see when launching the application
 * 
 * @author Youssef Hanna
 */

package photo.Controller;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import photo.Model.Admin;


public class LoginController {

    @FXML
    private TextField usernameField;
    /**
     * handles the log in button being clicked by getting the inputted string from the 
     * usernameField. if its admin or stock, the user will be directed to their unique views,
     * otherwise the user will be directed to user view
     * @param event
     */
    @FXML
    public void loginButtonClicked(ActionEvent event) {
        String username = usernameField.getText();
        if (username.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a username.");
            alert.showAndWait();
            return;
        }
            if (username.equalsIgnoreCase("admin")){
                try{
            
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/AdminView.fxml"));
                    Parent root = fxmlLoader.load();
                    Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                         stage.setScene(scene);
                        stage.setTitle("Admin");
                        stage.show();
                        return;
                        
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }

            }
            else if (username.equalsIgnoreCase("stock")){
                try{

                    AlbumController.isStock = true;
                    AlbumController.isSearch = false;
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/AlbumView.fxml"));
                    Parent root = fxmlLoader.load();
                    Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                         stage.setScene(scene);
                        stage.setTitle("Stock Album");
                        stage.show();
                        return;
                        
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
            }
            else{
                if (!Admin.containsUser(username))
                { 
                    Alert a = new Alert(AlertType.ERROR);
                    a.setTitle("Error");
                    a.setHeaderText(null);
                    a.setContentText("a user with this username does not exist.");
                    a.showAndWait();
                    return;
                }
                try{
                    UserController.setUser(username);
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/UserView.fxml"));
                    Parent root = fxmlLoader.load();
                    Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
               
                         stage.setScene(scene);
                        stage.setTitle("Welcome to Photos");
                        stage.show();
                        return;
                        
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }

            }

        

    }
}