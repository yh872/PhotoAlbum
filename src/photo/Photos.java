/**
 * Photos Assignment Rutgers CS213 
 * Javafx and FXML project 
 * This is the class with the main method
 * 
 * @author Youssef Hanna and Victoria Permakoff
 * 
 */

package photo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import photo.Model.Admin;


public class Photos extends Application {
    

    /**
     * 
     * sets the stage to login view and loads user data from the admin class
     * 
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/LoginView.fxml"));
        primaryStage.setTitle("Photos App");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        Admin.getData();
    }
    /**
     * 
     * @param args command line arguments
     * launches the program
     */
    public static void main(String[] args) {
        
        launch(args);
    }
}