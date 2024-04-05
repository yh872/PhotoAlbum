/**
 * Controller class for the user view which displays all of the users albums.
 * This class enables searching by date and tag, adding albums, deleting albums,
 * renaming albums, and viewing a list of current albums 
 * 
 * @author Youssef Hanna
 * 
 */

package photo.Controller;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
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
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import photo.Model.Admin;
import photo.Model.Album;
import photo.Model.Photo;
import photo.Model.Tag;
import photo.Model.User;

public class UserController implements Serializable {
    

    public static User user;


    public static ArrayList<Album> userAlbums = new ArrayList<>();

    /**
     * 
     * public method that allows the log in class to pass the users username to this class
     * @param username string representing the user's username
     */
    public static void setUser(String username){
            user = Admin.getUser(username);
            userAlbums = user.getAlbums();
    }
    /**
     * Helper method to display the albumview fxml file when user opens an album
     */
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
    /**
     * 
     * This helper method turns all important album data into a String to be displayed in a listview
     * @param album an album in the users list of albums
     * @return returns a string which contains the album name, number of photos in the album, and date ranges of the photos
     */
    private String combineAlbumData(Album album) {
        String albumName = album.albumName;
        Integer numPhotos = album.numberOfPhotos();
        String earliestDate = album.earliestDate();
        String latestDate = album.latestDate();
        
        String dateRange = earliestDate + "-" + latestDate;
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
    /**
     * 
     * populates the list view with important information about each of the users albums,
     * including the name, date ranges, and number of photos
     */
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
        from.setEditable(false);
        to.setEditable(false);

        for (int i = 0; i <userAlbums.size(); i++) {
            String albumData = combineAlbumData(userAlbums.get(i));
            albumDataList.add(albumData);
        }

        albumListView.setItems(albumDataList);
    }

    /**
     * updates the prompt text when the tag search type is changed so the user knows the expected
     * format of the string representing his search
     * @param e the event of the user changing the type of tag search
     */
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
    }

    private boolean isValiddoubleTag(String s){
        if (!s.contains(",")){
            return false;
        }
        if (s.contains(" ")){
            return false;
        }
        String[] parts = s.split(",");
        if (parts.length ==1){
            return false;
        }
        if (parts[1].length() <3){
            return false;
        }

        for (String part: parts){
            if (!Tag.isValidTag(part)){
                return false;
            }
        }
        return true;

    }
    /**
     * 
     * searches all of the users albums for the inputted tag and creates a temporary album to be 
     * displayed. the user can then choose to create an album of the search results
     * @param e the event of the search tag button being clicked
     */
    @FXML
    public void searchTag(ActionEvent e){

        String searchType = TagSearchBox.getValue();
        String tags = searchTarget.getText();
        Album temp = new Album("tempalbum" + Math.random());

        if (searchType.equals("Single Tag Search")){
            if (!Tag.isValidTag(tags)){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid input. Make sure your input is the form 'name=value'.");
                alert.showAndWait();
                return;

            }
            for (Album tempAlb: user.getAlbums()){
                for (Photo p: tempAlb.getAllPhotos()){
                    for (Tag t: p.listofTags){
                        if (t.getTag().equals(tags) && !temp.getAllPhotos().contains(p)){
                            temp.addPhoto(p);
                        }
                    }
                }
            }
            if (temp.getAllPhotos().size() == 0){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No photos found with this tag.");
                alert.showAndWait();
                return;

            }
            else{
                AlbumController.isSearch = true;
                AlbumController.currentAlbum = temp;
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/AlbumView.fxml"));
                    Parent root = fxmlLoader.load();
                    Stage stage = (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setTitle("Search results");
                    stage.show();
                } catch (IOException excp) {
                    excp.printStackTrace();
                }


            }

           

        }

        else if (searchType.equals("Conjunctive Tag Search")){
            if (!isValiddoubleTag(tags)){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid input. Make sure your input is the form 'name=value,name=value'.");
                alert.showAndWait();
                return;


            }
            String parts[] = tags.split(",");
             String tag1 = parts[0];
             String tag2 = parts[1];
             boolean tag1found = false;
             boolean tag2found = false;
             for (Album tempAlb: user.getAlbums()){
                for (Photo p: tempAlb.getAllPhotos()){
                    for (Tag t: p.listofTags){
                        if (t.getTag().equals(tag1)){
                            tag1found = true;
                        }
                        if (t.getTag().equals(tag2)){
                            tag2found = true;
                        }
                    }
                    if (tag1found && tag2found && !temp.getAllPhotos().contains(p)){
                        temp.addPhoto(p);
                    }
                    tag1found=false;
                    tag2found=false;
                }
            }
            if (temp.getAllPhotos().size() == 0){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No photos found with these tag.");
                alert.showAndWait();
                return;

            }
            else{
                AlbumController.isSearch = true;
                AlbumController.currentAlbum = temp;
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/AlbumView.fxml"));
                    Parent root = fxmlLoader.load();
                    Stage stage = (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setTitle("Search results");
                    stage.show();
                } catch (IOException excp) {
                    excp.printStackTrace();
                }

            }

        }

        else if ((searchType.equals("Disjunctive Tag Search"))){
            if (!isValiddoubleTag(tags)){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid input. Make sure your input is the form 'name=value,name=value'.");
                alert.showAndWait();
                return;


            }
            String parts[] = tags.split(",");
             String tag1 = parts[0];
             String tag2 = parts[1];
             boolean tag1found = false;
             boolean tag2found = false;
             for (Album tempAlb: user.getAlbums()){
                for (Photo p: tempAlb.getAllPhotos()){
                    for (Tag t: p.listofTags){
                        if (t.getTag().equals(tag1)){
                            tag1found = true;
                        }
                        if (t.getTag().equals(tag2)){
                            tag2found = true;
                        }
                    }
                    if ((tag1found || tag2found) && !temp.getAllPhotos().contains(p)){
                        temp.addPhoto(p);
                    }
                    tag1found=false;
                    tag2found=false;
                }
            }
            if (temp.getAllPhotos().size() == 0){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No photos found with these tag.");
                alert.showAndWait();
                return;

            }
            else{
                AlbumController.isSearch = true;
                AlbumController.currentAlbum = temp;
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/AlbumView.fxml"));
                    Parent root = fxmlLoader.load();
                    Stage stage = (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setTitle("Search results");
                    stage.show();
                } catch (IOException excp) {
                    excp.printStackTrace();
                }

            }

        }
    }   

    
    /**
     * 
     *  searches all of the users albums for photos within the specificed date range and creates a temporary album to be displayed. 
     * the user can then choose to create an album of the search results
     * 
     * @param e the event of the search date button being clicked
     */
    @FXML
    public void searchDate(ActionEvent e){
        if (from.getValue() == null || to.getValue() == null){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("date field(s) are not filled out.");
            alert.showAndWait();
            return;
        }
        LocalDate fromDateValue = from.getValue();
        LocalDate toDateValue = to.getValue();
        if (toDateValue.isBefore(fromDateValue)){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid date range.");
            alert.showAndWait();
            return;
        }
        Calendar fromCal = Calendar.getInstance();
        fromCal.clear(); 
        fromCal.set(Calendar.MILLISECOND, 0);
        fromCal.set(Calendar.SECOND, 0);
        fromCal.set(Calendar.MINUTE, 0);
        fromCal.set(Calendar.HOUR_OF_DAY, 0);
        fromCal.set(Calendar.YEAR, fromDateValue.getYear());
        fromCal.set(Calendar.MONTH, fromDateValue.getMonthValue() - 1); 
        fromCal.set(Calendar.DAY_OF_MONTH, fromDateValue.getDayOfMonth());

        Calendar toCal = Calendar.getInstance();
        toCal.clear(); 
        toCal.set(Calendar.MILLISECOND, 0);
        toCal.set(Calendar.SECOND, 0);
        toCal.set(Calendar.MINUTE, 0);
        toCal.set(Calendar.HOUR_OF_DAY, 0);

        toCal.set(Calendar.YEAR, toDateValue.getYear());
        toCal.set(Calendar.MONTH, toDateValue.getMonthValue() - 1); 
        toCal.set(Calendar.DAY_OF_MONTH, toDateValue.getDayOfMonth());
        Album temp = new Album("tempalbum" + Math.random());
        for (Album tempAlb: user.getAlbums()){
            for (Photo p: tempAlb.getAllPhotos()){
                p.date.set(Calendar.MILLISECOND, 0);
                p.date.set(Calendar.SECOND, 0);
                p.date.set(Calendar.MINUTE, 0);
                p.date.set(Calendar.HOUR_OF_DAY, 0);
                if (fromCal.getTimeInMillis() <= p.date.getTimeInMillis() && p.date.getTimeInMillis() <= toCal.getTimeInMillis() && !temp.getAllPhotos().contains(p) ){
                    
                    temp.addPhoto(p);
                }
                if (temp.getAllPhotos().size() >= 60){
                    break;
                }
                
            }
            if (temp.getAllPhotos().size() >= 60){
                break;
            }
        }
        if (temp.getAllPhotos().size() == 0){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No photos in this date range");
            alert.showAndWait();
            return;

        }
        else{
            AlbumController.isSearch = true;
            AlbumController.currentAlbum = temp;
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/AlbumView.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Search results");
                stage.show();
            } catch (IOException excp) {
                excp.printStackTrace();
            }

        }
    }
    /**
     * logs the user out and changes the user view back to the log in page
     * @param event the event of the logout button being clicked
     */
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
    /**
     * 
     * deletes an album from the users listofalbums arraylist
     * @param e the event of the delete album button being pressed
     */
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Album \"" + albumNameToDelete + "\" not found.");
            alert.showAndWait();
        }
    }
    }
    /**
     * 
     * changes the stage to the albumview fxml file, displaying all ofthe photos and their captions in the album 
     * and allowing users to make changes to their album
     * @param event the event that the open album button has been clicked
     */
    @FXML
    public void openAlbum(ActionEvent event) {
        AlbumController.isSearch = false;
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
    /**
     * 
     * starts a dialog with the user to get the album they would like to rename and
     * lets user set a new name for the chosen album
     *
     * @param e the event that the create album button has been clicked
     */
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
    /**
     * adds a new album to the users list of albums,
     * starts a dialog with the user to get the album name then displays the new album
     * in the listview of albums
     * @param e the event that the create album button has been clicked
     */
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

    



