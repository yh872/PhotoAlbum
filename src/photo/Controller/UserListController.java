package photo.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import photo.Model.Admin;
import photo.Model.User;

public class UserListController {
    @FXML
    private ListView<String> listOfUsers;

    @FXML
    public void initialize() {
        if (!Admin.containsUser("stock")){
            Admin.AddUser("stock");
        }
        if (!Admin.containsUser("admin")){
            Admin.AddUser("admin");
        }
        populateList();
    }

    private void populateList() {
        
        ArrayList<String> tempList = new ArrayList<>();
        for (User user : Admin.listofUsers) {
            tempList.add(user.getUsername());
        }
        
        ObservableList<String> observableList = FXCollections.observableArrayList(tempList);
        listOfUsers.setItems(observableList);
    }

    @FXML
    public void back(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/AdminView.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Admin");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

