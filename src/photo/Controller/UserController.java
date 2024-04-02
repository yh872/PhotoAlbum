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
    public void openAlbumView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/AlbumView.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Your Album");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        if (user.getUsername().equals("stock")){
            openAlbumView();
        }
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
            userAlbums.remove(albumToDelete);
            Admin.getUser(user.getUsername()).DeleteAlbum(albumToDelete);
            try {
                Admin.WritetoFile();
            } catch (IOException exception) {
                exception.printStackTrace();
            }

            ObservableList<String> albumDataList = FXCollections.observableArrayList();
            for (Album album : userAlbums) {
                String albumData = combineAlbumData(album);
                albumDataList.add(albumData);
            }
            albumListView.setItems(albumDataList);

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
    public void openAlbum(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Open Album");
        dialog.setHeaderText("Enter the name of the album you want to open:");
        dialog.setContentText("Album Name:");

        dialog.showAndWait().ifPresent(albumName -> {
            if (user.containsAlbum(albumName)) {
                try {
                    AlbumController.currentAlbum = user.getAlbum(albumName);
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/AlbumView.fxml"));
                    Parent root = fxmlLoader.load();
                    Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setTitle("Your Album");
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Album Not Found");
                alert.setContentText("The album '" + albumName + "' does not exist.");
                alert.showAndWait();
            }
        });
    }

    @FXML
    public void renameAlbum(ActionEvent e){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Rename Album");
        dialog.setHeaderText("Enter the name of the album to rename:");
        dialog.setContentText("Album Name:");
    
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String oldAlbumName = result.get();
            Album albumToRename = null;
            for (Album album : userAlbums) {
                if (album.albumName.equals(oldAlbumName)) {
                    albumToRename = album;
                    break;
                }
            }
    
            if (albumToRename != null) {
                // Show dialog to get new album name
                TextInputDialog newAlbumNameDialog = new TextInputDialog();
                newAlbumNameDialog.setTitle("Rename Album");
                newAlbumNameDialog.setHeaderText("Enter the new name for the album:");
                newAlbumNameDialog.setContentText("New Album Name:");
    
                Optional<String> newAlbumNameResult = newAlbumNameDialog.showAndWait();
                if (newAlbumNameResult.isPresent()) {
                    String newAlbumName = newAlbumNameResult.get();
                    albumToRename.albumName = newAlbumName;
                    Admin.getUser(user.getUsername()).setAlbumName(albumToRename, newAlbumName);
                    try {
                        Admin.WritetoFile();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
    
                    ObservableList<String> albumDataList = FXCollections.observableArrayList();
                    for (Album album : userAlbums) {
                        String albumData = combineAlbumData(album);
                        albumDataList.add(albumData);
                    }
                    albumListView.setItems(albumDataList);
    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Album Renamed");
                    alert.setHeaderText(null);
                    alert.setContentText("Album \"" + oldAlbumName + "\" has been renamed to \"" + newAlbumName + "\".");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Album \"" + oldAlbumName + "\" not found.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    public void createAlbum(ActionEvent e){
        TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("Create Album");
    dialog.setHeaderText("Enter the name of the new album:");
    dialog.setContentText("Album Name:");

    Optional<String> result = dialog.showAndWait();
    if (result.isPresent()) {
        String albName = result.get();

        // Check if the album already exists
        boolean albumExists = false;
        for (Album album : userAlbums) {
            if (album.albumName.equals(albName)) {
                albumExists = true;
                break;
            }
        }

        if (!albumExists) {
    
            Admin.getUser(user.getUsername()).addAlbum(albName);
                    try {
                        Admin.WritetoFile();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }

            ObservableList<String> albumDataList = FXCollections.observableArrayList();
            for (Album album : userAlbums) {
                String albumData = combineAlbumData(album);
                albumDataList.add(albumData);
            }
            albumListView.setItems(albumDataList);


            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Album Created");
            alert.setHeaderText(null);
            alert.setContentText("Album \"" + albName + "\" has been created.");
            alert.showAndWait();
        } else {
    
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Album \"" + albName + "\" already exists.");
            alert.showAndWait();
        }
    }
        
    }
    

 

}

    



