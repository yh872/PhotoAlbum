package photo.Controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import photo.Model.Admin;
import photo.Model.User;

public class AdminController{

    @FXML
    public void logout(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/LoginView.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Photos App");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void DisplayUserList(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/UserList.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("User List");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void getDeletedUsername(ActionEvent event) {
        // Create a text input dialog
        TextField userInput = new TextField();
        userInput.setPromptText("Enter username to delete");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete User");
        alert.setHeaderText("Enter Username");
        alert.getDialogPane().setContent(userInput);

        // Wait for user input
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                String username = userInput.getText();
                if (Admin.containsUser(username)){
                    Admin.DeleteUser(username);
                    try {
	                    Admin.WritetoFile();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("User Deleted");
                successAlert.setHeaderText(null);
                successAlert.setContentText("User '" + username + "' successfully deleted.");
                successAlert.showAndWait();

                }
                else{
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("User Not Found");
                    errorAlert.setHeaderText(null);
                    errorAlert.setContentText("User '" + username + "' does not exist.");
                    errorAlert.showAndWait();
                }
            }
        });
    }

    @FXML
    public void AddNewUser(ActionEvent event) {
        // Create a text input dialog
        TextField userInput = new TextField();
        userInput.setPromptText("Enter new username");
    
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Create New User");
        alert.setHeaderText("Enter Username");
        alert.getDialogPane().setContent(userInput);
    
        // Wait for user input
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                String username = userInput.getText();
                if (Admin.containsUser(username)) {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Username Taken");
                    errorAlert.setHeaderText(null);
                    errorAlert.setContentText("Username '" + username + "' is already taken. Please choose another username.");
                    errorAlert.showAndWait();
                } else {
                    Admin.listofUsers.add(new User(username));
                    try {
	                    Admin.WritetoFile();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("User Created");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("User '" + username + "' successfully created.");
                    successAlert.showAndWait();
                }
            }
        });
    }
}
