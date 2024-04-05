//Youssef Hanna and Victoria Permakoff

package photo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import photo.Model.Admin;


public class Photos extends Application {
    

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/LoginView.fxml"));
        primaryStage.setTitle("Photos App");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        Admin.getData();
    }

    public static void main(String[] args) {
        
        launch(args);
    }
}