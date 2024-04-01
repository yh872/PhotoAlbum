package photo.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import photo.Model.Admin;
import photo.Model.Album;
import photo.Model.User;

public class UserController {

    public static User user;


    public static ArrayList<Album> userAlbums = new ArrayList<>();

    public static void setUser(String username){
            user = Admin.getUser(username);
            userAlbums = user.getAlbums();
    }

    private String combineAlbumData(Album album) {
        String albumName = album.albumName;
        Integer numPhotos = album.numberOfPhotos();
        String earliestDate = album.earliestDate();
        String latestDate = album.latestDate();
        
        // Format the dates as needed
        String dateRange = earliestDate + "-" + latestDate;
        
        // Combine album data into a single string
        String combinedData = "Album Name: " + albumName + " | Number of Photos: " + numPhotos + " | Date Range: " + dateRange;
        return combinedData;
    }

    @FXML
    private ListView<String> albumListView;

    ObservableList<String> TagSearchType = FXCollections.observableArrayList("Single Tag Search", "Conjunctive Tag Search", "Disjunctive Tag Search");

    @FXML Button create;

    @FXML Button rename;

    @FXML Button open;

    @FXML Button delete;

    @FXML Button search;

    @FXML TextField searchTarget;


    @FXML Label welcome;

    @FXML Label info;
	
	@FXML DatePicker from;
    @FXML  DatePicker to;

    @FXML ChoiceBox<String> TagSearchBox;
    @FXML
    public void initialize(){
        TagSearchBox.setValue("Single Tag Search");
        TagSearchBox.setItems(TagSearchType);
        ObservableList<String> albumDataList = FXCollections.observableArrayList();
        if (userAlbums == null){
            return;
        }

        for (int i = 0; i <userAlbums.size(); i++) {
            String albumData = combineAlbumData(userAlbums.get(i));
            albumDataList.add(albumData);
        }

        albumListView.setItems(albumDataList);
    }

    
    @FXML
    public void handleChange(ActionEvent e) {
        String selectedOption = TagSearchBox.getValue();
        if ("Single Tag Search".equals(selectedOption)) {
            searchTarget.setPromptText("name=Adam");
        } else if ("Conjunctive Tag Search".equals(selectedOption)) {
            searchTarget.setPromptText("name=Adam,location=Prague");
        } else if ("Disjunctive Tag Search".equals(selectedOption)) {
            searchTarget.setPromptText("name=Adam,location=Prague");
        }
        // Add more conditions as needed for other options
    }
    @FXML
    public void searchTag(ActionEvent e){

    }   

    @FXML
    public void searchDate(ActionEvent e){
        
    }
    @FXML
    public void logout(ActionEvent event){
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
    public void deleteAlbum(ActionEvent e){
         TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("Delete Album");
    dialog.setHeaderText("Enter the name of the album to delete:");
    dialog.setContentText("Album Name:");

    Optional<String> result = dialog.showAndWait();
    if (result.isPresent()) {
        String albumNameToDelete = result.get();
        Album albumToDelete = null;
        for (Album album : userAlbums) {
            if (album.albumName.equals(albumNameToDelete)) {
                albumToDelete = album;
                break;
            }
        }

        if (albumToDelete != null) {
            // Remove the album from the user's albums list
            userAlbums.remove(albumToDelete);
            Admin.getUser(user.getUsername()).DeleteAlbum(albumToDelete);
            try {
                Admin.WritetoFile();
            } catch (IOException exception) {
                exception.printStackTrace();
            }

            // Update the ListView
            ObservableList<String> albumDataList = FXCollections.observableArrayList();
            for (Album album : userAlbums) {
                String albumData = combineAlbumData(album);
                albumDataList.add(albumData);
            }
            albumListView.setItems(albumDataList);

            // Show a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Album Deleted");
            alert.setHeaderText(null);
            alert.setContentText("Album \"" + albumNameToDelete + "\" has been deleted.");
            alert.showAndWait();
        } else {
            // Show an error message if the album doesn't exist
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Album \"" + albumNameToDelete + "\" not found.");
            alert.showAndWait();
        }
    }
    }

    @FXML
    public void openAlbum(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/AlbumView.fxml"));
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
    public void renameAlbum(ActionEvent e){
        
    }

    @FXML
    public void createAlbum(ActionEvent e){

        
    }
    

 

}

    



