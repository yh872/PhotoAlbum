package photo.Controller;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import photo.Model.Admin;
import photo.Model.Album;
import photo.Model.User;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    public void loginButtonClicked(ActionEvent event) {
        String username = usernameField.getText();
        if (username.equalsIgnoreCase("stock")){
            try{
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/StockPhotos.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
                 stage.setScene(scene);
                stage.setTitle("Stock Photos");
                stage.show();
                return;
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
           else if (username.equalsIgnoreCase("admin")){
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
            else{
                boolean userExists = false;
                User currentUser = new User(username);
                for (int i = 0; i < Admin.listofUsers.size(); i++){
                    if (Admin.listofUsers.get(i).getUsername().equals(username)){
                        userExists = true;
                         currentUser = Admin.listofUsers.get(i);

                    }
                }
                if (userExists){
                


                }
                else{
                    Admin.listofUsers.add(currentUser);
                }
            }

        

    }
}