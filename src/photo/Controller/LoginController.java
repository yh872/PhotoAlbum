package photo.Controller;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
                
            }
            catch (IOException e){
                e.printStackTrace();
            }

        }

        // Here you can implement the login logic
    }
}