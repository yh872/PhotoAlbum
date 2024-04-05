package photo.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import photo.Model.Admin;
import photo.Model.Album;
import photo.Model.Photo;
import photo.Model.Tag;

public class AlbumController{
    public static boolean isStock = false;
    public static boolean isSearch = false;
    public static Album currentAlbum = null;
    public static boolean captionClicked = false;
    public static boolean addTagClicked = false;
    public static boolean deleteTagClicked = false;
    public static boolean deletePhotoClicked = false;
    public static boolean copyPhotoClicked = false;
    public static boolean movePhotoClicked = false;
    public static boolean displayPhotoClicked = false;
    @FXML
    private Button caption;

    @FXML
    private Button slideshow;

    @FXML
    private Button displayButton;

    @FXML
    private Button addTagButton;

    @FXML
    private Button deleteTagButton;

    @FXML
   private ChoiceBox<Integer> pageSelector;
   @FXML
   private Label pageNumberlabel;
    @FXML
    private ImageView img1;

    @FXML
    private ImageView img2;

    @FXML
    private Button createAlbumButton;

    @FXML
    private ImageView img3;

    @FXML
    private ImageView img4;

    @FXML
    private ImageView img5;

    @FXML
    private ImageView img6;

    @FXML
    private Label Album_name;

    @FXML
    private Button goBackButton;

    @FXML
    private Button addPhotoButton;

    @FXML
    private Button deletePhotoButton;

    @FXML
    private Button copyPhotoButton;

    @FXML
    private Button movePhotoButton;

    @FXML private Text caption1; @FXML private Text caption2; @FXML private Text caption3;
    @FXML private Text caption4; @FXML private Text caption5; @FXML private Text caption6;

    

        @FXML
    public void initialize() throws FileNotFoundException{

        captionClicked = false;
        addTagClicked = false;
         deleteTagClicked = false;
        deletePhotoClicked = false;
      copyPhotoClicked = false;
      movePhotoClicked = false;
      displayPhotoClicked = false;
      createAlbumButton.setDisable(true);
      createAlbumButton.setVisible(false);
        ObservableList<Integer> items = FXCollections.observableArrayList();
        items.addAll(1,2,3,4,5,6,7,8,9,10);
        pageSelector.setItems(items);
        pageSelector.setValue(1);

        if (isSearch){
            createAlbumButton.setDisable(false);
            createAlbumButton.setVisible(true);
            addPhotoButton.setDisable(true);
            addPhotoButton.setVisible(false);
           deleteTagButton.setDisable(true);
           deleteTagButton.setVisible(false);
           addTagButton.setDisable(true);
           addTagButton.setVisible(false);
           deletePhotoButton.setDisable(true);
           deletePhotoButton.setVisible(false);
           caption.setDisable(true);
           caption.setVisible(false);
           copyPhotoButton.setDisable(true);
           copyPhotoButton.setVisible(false);
           movePhotoButton.setDisable(true);
           movePhotoButton.setVisible(false);
           Album_name.setVisible(false);
           displayButton.setVisible(false);
           displayButton.setDisable(true);
           slideshow.setVisible(false);
           slideshow.setDisable(true);

        changePage();


        }
        if (isStock){
            Album_name.setText("Stock Album");
            try{
            currentAlbum = new Album("stock");
            currentAlbum.addPhoto(new Photo("data/manStockphoto.jpg"));
            currentAlbum.addPhoto(new Photo("data/DanceStockPhoto.jpg"));
            currentAlbum.addPhoto(new Photo("data/FamilyStockPhoto.jpg"));
            currentAlbum.addPhoto(new Photo("data/IslandStockPhoto.jpg"));
            currentAlbum.addPhoto(new Photo("data/catStockPhoto.jpg"));
            currentAlbum.addPhoto(new Photo("data/basketballStock.jpg"));
            for (Photo p: currentAlbum.getAllPhotos()){
                File file = new File(p.path);
                Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(file.lastModified());
            calendar.set(Calendar.MILLISECOND, 0);
            p.date = calendar;
            }
           

            InputStream stream1 = new FileInputStream("data/manStockphoto.jpg"); 
            InputStream stream2 = new FileInputStream("data/DanceStockPhoto.jpg");
            InputStream stream3 = new FileInputStream("data/FamilyStockPhoto.jpg");
            InputStream stream4 = new FileInputStream("data/IslandStockPhoto.jpg");
            InputStream stream5 = new FileInputStream("data/catStockPhoto.jpg");
            InputStream stream6 = new FileInputStream("data/basketballStock.jpg");
            

            Image image1 = new Image(stream1);
            
            Image image2 = new Image(stream2);
            Image image3 = new Image(stream3);
            Image image4 = new Image(stream4);
            Image image5 = new Image(stream5);
            Image image6 = new Image(stream6);
            

            img1.setImage(image1);
            
            img2.setImage(image2);
            img3.setImage(image3);
            img4.setImage(image4);
            img5.setImage(image5);
            img6.setImage(image6);
            
          
           addPhotoButton.setDisable(true);
           deleteTagButton.setDisable(true);
           addTagButton.setDisable(true);
           deletePhotoButton.setDisable(true);
           caption.setDisable(true);
           copyPhotoButton.setDisable(true);
           pageSelector.setDisable(true);
           movePhotoButton.setDisable(true);

           pageSelector.setVisible(false);
           pageNumberlabel.setVisible(false);

           caption1.setText("Business man");
           caption2.setText("Hip Hop dancers");
           caption3.setText("A happy family");
           caption4.setText("Tropical island");
           caption5.setText("A curious cat");
           caption6.setText("Basketball player");
        

            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        else{
            if (currentAlbum.numberOfPhotos() == null){
                return;
            }
            if (currentAlbum.numberOfPhotos() == 0){
                return;
            }
            int numPhotos = currentAlbum.numberOfPhotos();
            if (numPhotos>=1){
                String path1 = currentAlbum.getAllPhotos().get(0).path;
                InputStream stream1 = new FileInputStream(path1);
                Image p1 = new Image(stream1);
                img1.setImage(p1);
            }
            if (numPhotos>=2){
                String path2 = currentAlbum.getAllPhotos().get(1).path;
                InputStream stream2 = new FileInputStream(path2);
                Image p2 = new Image(stream2);
                img2.setImage(p2);
            }
            if (numPhotos>=3){
                String path3 = currentAlbum.getAllPhotos().get(2).path;
                InputStream stream3 = new FileInputStream(path3);
                Image p3 = new Image(stream3);
                img3.setImage(p3);
            }

            if (numPhotos>=4){
                String path4 = currentAlbum.getAllPhotos().get(3).path;
                InputStream stream4 = new FileInputStream(path4);
                Image p4 = new Image(stream4);
                img4.setImage(p4);
            }
            if (numPhotos>=5){
                String path5 = currentAlbum.getAllPhotos().get(4).path;
                InputStream stream5 = new FileInputStream(path5);
                Image p5 = new Image(stream5);
                img5.setImage(p5);
            }
            if (numPhotos>=6){
                String path6 = currentAlbum.getAllPhotos().get(5).path;
                InputStream stream6 = new FileInputStream(path6);
                Image p6 = new Image(stream6);
                img6.setImage(p6);
            }
           
            
        }

    }
    @FXML
    private void createAlbum(ActionEvent event){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Album Name");
        dialog.setHeaderText("Enter the name of your new album:");
        dialog.setContentText("Album Name: ");
        dialog.showAndWait().ifPresent(name -> {
            if (name.isEmpty()){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("name is empty.");
                alert.showAndWait();
                return;

            }
            for (Album a :UserController.user.getAlbums()){
                if (a.albumName.equals(name)){
                    Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("album name taken.");
                alert.showAndWait();
                return;
                }
            }
            UserController.user.addAlbum(name);
            for (Album a: UserController.user.getAlbums()){
                if (a.albumName.equals(name)){
                    for (Photo p: currentAlbum.getAllPhotos()){
                        a.addPhoto(p);
                    }
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Album succesfully created");
                    alert.setHeaderText(null);
                    alert.setContentText("Your album has been succesfully created and added to your album list");
                    alert.showAndWait();

                    try{
                        Admin.WritetoFile();
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                    try{

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/UserView.fxml"));
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
            }


        });

    }

    @FXML
    private void setCaption() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Add Caption");
        alert.setHeaderText(null);
        alert.setContentText("Please click on an image to add or edit the caption.");
        alert.showAndWait();
        captionClicked = true;
        addTagClicked = false;
         deleteTagClicked = false;
        deletePhotoClicked = false;
      copyPhotoClicked = false;
      movePhotoClicked = false;
      displayPhotoClicked = false;
    }

    @FXML
    private void addTag() {
        captionClicked = false;
        addTagClicked = true;
         deleteTagClicked = false;
        deletePhotoClicked = false;
      copyPhotoClicked = false;
      movePhotoClicked = false;
      displayPhotoClicked = false;
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Add Tag");
      alert.setHeaderText(null);
      alert.setContentText("Please click on an image to add a tag to the image");
      alert.showAndWait();
    }

    @FXML
    private void deleteTag() {
        captionClicked = false;
        addTagClicked = false;
         deleteTagClicked = true;
        deletePhotoClicked = false;
      copyPhotoClicked = false;
      movePhotoClicked = false;
      displayPhotoClicked = false;

      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Delete Tag");
      alert.setHeaderText(null);
      alert.setContentText("Please click on an image to delete a tag from the image");
      alert.showAndWait();
       
    }

    @FXML
    private void goBack(ActionEvent event) {
        if (isStock){
            isStock = false;
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
        else{

            try{

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/UserView.fxml"));
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
       
    }

    @FXML
    private void addPhoto(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Photo to Add");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.bmp", "*.gif", "*.jpg", "*.jpeg", "*.png")
        );
        File selectedFile = fileChooser.showOpenDialog(((Node) e.getSource()).getScene().getWindow());
        
        if (selectedFile != null) {
            // Iterate through the ImageView nodes to find the closest empty ImageView
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(selectedFile.lastModified());
            // Set milliseconds to zero
            calendar.set(Calendar.MILLISECOND, 0);

            // Add the photo to the album
            Photo p = new Photo(selectedFile.getAbsolutePath());
            p.date = calendar;
            for (Photo p1: currentAlbum.getAllPhotos()){
                if (p1.path.equals(p.path)){
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("This Photo is already in the album.");
                    alert.showAndWait();
                    return;

                }
            }
            currentAlbum.addPhoto(p);

            try {
                Admin.WritetoFile();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            changePage();
        }
    }

    @FXML
    private void deletePhoto() {
        captionClicked = false;
        addTagClicked = false;
         deleteTagClicked = false;
        deletePhotoClicked = true;
      copyPhotoClicked = false;
      movePhotoClicked = false;
      displayPhotoClicked = false;
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Delete Photo");
      alert.setHeaderText(null);
      alert.setContentText("Please click on an image to delete it from the album.");
      alert.showAndWait();
    }

    @FXML
    private void copyPhoto() {
        if (UserController.user.getAlbums().size() <2){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No other album found.");
            alert.showAndWait();
            return;
        }
        captionClicked = false;
        addTagClicked = false;
         deleteTagClicked = false;
        deletePhotoClicked = false;
      copyPhotoClicked = true;
      movePhotoClicked = false;
      displayPhotoClicked = false;
      
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Copy photo");
      alert.setHeaderText(null);
      alert.setContentText("Please click on the image you would like to copy to another album.");
      alert.showAndWait();
    }

    @FXML
    private void movePhoto() {
        if (UserController.user.getAlbums().size() <2){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No other album found.");
            alert.showAndWait();
            return;
        }
        captionClicked = false;
        addTagClicked = false;
         deleteTagClicked = false;
        deletePhotoClicked = false;
      copyPhotoClicked = false;
      movePhotoClicked = true;
      displayPhotoClicked = false;
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Move photo");
      alert.setHeaderText(null);
      alert.setContentText("Please click on the image you would like to move to another album.");
      alert.showAndWait();

    }

    @FXML

    private void executeSlideshow(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/Slideshow.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Slideshow");
            stage.show();
            }
            catch (IOException e){
                e.printStackTrace();
            }


    }
    @FXML

    private void displayPhoto(){
        if (currentAlbum.getAllPhotos().isEmpty()){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No Photos in this album.");
            alert.showAndWait();
            return;
        }
        captionClicked = false;
        addTagClicked = false;
         deleteTagClicked = false;
        deletePhotoClicked = false;
      copyPhotoClicked = false;
      movePhotoClicked = false;
      displayPhotoClicked = true;
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Display photo");
      alert.setHeaderText(null);
      alert.setContentText("Please click on the image you would like to display.");
      alert.showAndWait();
    }

    @FXML
    private void changePage(){
        if (isStock){
            return;
        }
        
        img1.setImage(null); img2.setImage(null); img3.setImage(null); img4.setImage(null); img5.setImage(null); img6.setImage(null);
        caption1.setText("");caption2.setText("");caption3.setText("");caption4.setText("");caption5.setText("");caption6.setText("");
        
       int curPage = pageSelector.getValue();

       if (currentAlbum.numberOfPhotos() == null){
        return;
    }
    if (currentAlbum.numberOfPhotos() == 0){
        return;
    }
    int photoCount = currentAlbum.numberOfPhotos();
    int currentIndex = 0;
    int numPhotos = 0;

        switch (curPage){
             case(1):
             currentIndex = 0;
             numPhotos = numPhotosonPage(currentIndex, photoCount);
             try {
                loadImageHelper(numPhotos, currentIndex);
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
             case (2):
             currentIndex = 6;
             numPhotos = numPhotosonPage(currentIndex, photoCount);
             try {
                loadImageHelper(numPhotos, currentIndex);
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
             case (3):
             currentIndex = 12;
             numPhotos = numPhotosonPage(currentIndex, photoCount);
             try {
                loadImageHelper(numPhotos, currentIndex);
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
 
             case (4):
             currentIndex = 18;
             numPhotos = numPhotosonPage(currentIndex, photoCount);
             try {
                loadImageHelper(numPhotos, currentIndex);
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
            
            case(5):
            currentIndex = 24;
             numPhotos = numPhotosonPage(currentIndex, photoCount);
             try {
                loadImageHelper(numPhotos, currentIndex);
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;

             case (6):
             currentIndex = 30;
             numPhotos = numPhotosonPage(currentIndex, photoCount);
             try {
                loadImageHelper(numPhotos, currentIndex);
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;

             case (7):
             currentIndex = 36;
             numPhotos = numPhotosonPage(currentIndex, photoCount);
             try {
                loadImageHelper(numPhotos, currentIndex);
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;

             case (8):
             currentIndex = 42;
             numPhotos = numPhotosonPage(currentIndex, photoCount);
             try {
                loadImageHelper(numPhotos, currentIndex);
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
             case(9):
             currentIndex =48;
             numPhotos = numPhotosonPage(currentIndex, photoCount);
             try {
                loadImageHelper(numPhotos, currentIndex);
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;

             case (10):
             currentIndex = 54;
             numPhotos = numPhotosonPage(currentIndex, photoCount);
             try {
                loadImageHelper(numPhotos, currentIndex);
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
            default:
            break;
            
        }
    
    }
    private int numPhotosonPage(int startIndex, int photoCount){

        if (startIndex >= photoCount){
            return -1;
        }
        return Math.min(6, photoCount -startIndex);

    }
    private void loadImageHelper(int numPhotos, int startIndex) throws IOException{
        if (numPhotos>=1){
            String path1 = currentAlbum.getAllPhotos().get(startIndex).path;
            InputStream stream1 = new FileInputStream(path1);
            Image p1 = new Image(stream1);
            img1.setImage(p1);
            String cap1 = currentAlbum.getAllPhotos().get(startIndex).caption;
            caption1.setText(cap1);

        }
        if (numPhotos>=2){
            String path2 = currentAlbum.getAllPhotos().get(startIndex +1).path;
            InputStream stream2 = new FileInputStream(path2);
            Image p2 = new Image(stream2);
            img2.setImage(p2);
            String cap2 = currentAlbum.getAllPhotos().get(startIndex + 1).caption;
            caption2.setText(cap2);
        }
        if (numPhotos>=3){
            String path3 = currentAlbum.getAllPhotos().get(startIndex +2).path;
            InputStream stream3 = new FileInputStream(path3);
            Image p3 = new Image(stream3);
            img3.setImage(p3);
            String cap3 = currentAlbum.getAllPhotos().get(startIndex+2).caption;
            caption3.setText(cap3);
        }

        if (numPhotos>=4){
            String path4 = currentAlbum.getAllPhotos().get(startIndex +3).path;
            InputStream stream4 = new FileInputStream(path4);
            Image p4 = new Image(stream4);
            img4.setImage(p4);
            String cap4 = currentAlbum.getAllPhotos().get(startIndex +3).caption;
            caption4.setText(cap4);
        }
        if (numPhotos>=5){
            String path5 = currentAlbum.getAllPhotos().get(startIndex +4).path;
            InputStream stream5 = new FileInputStream(path5);
            Image p5 = new Image(stream5);
            img5.setImage(p5);
            String cap5 = currentAlbum.getAllPhotos().get(startIndex + 4).caption;
            caption5.setText(cap5);
        }
        if (numPhotos>=6){
            String path6 = currentAlbum.getAllPhotos().get(startIndex +5).path;
            InputStream stream6 = new FileInputStream(path6);
            Image p6 = new Image(stream6);
            img6.setImage(p6);
            String cap6 = currentAlbum.getAllPhotos().get(startIndex + 5).caption;
            caption6.setText(cap6);
        }
    }
    @FXML
    public void img1clicked(){
        if (img1.getImage().equals(null)){
            return;
         }
        if (captionClicked) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add Caption");
            dialog.setHeaderText("Enter the caption for this image: ");
            dialog.setContentText("Caption:");
    
            dialog.showAndWait().ifPresent(caption -> {
                Integer curPage = pageSelector.getValue(); 
                int index = 0;
                if (curPage ==1) index =0; if (curPage ==2) index =6; if (curPage ==3) index =12; if (curPage ==4) index =18;
                if (curPage ==5) index =24; if (curPage ==6) index =30; if (curPage ==7) index =36; if (curPage ==8) index =42;
                if (curPage ==9) index =48; if (curPage ==10) index =54;

                currentAlbum.getAllPhotos().get(index).editCaption(caption);
                try {
                    Admin.WritetoFile();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                changePage();
                captionClicked = false;
            });
        }
        else if (addTagClicked){
            ArrayList<String> choices = new ArrayList<>();
    
            choices.add("name");
            choices.add("location");
            Integer curPage = pageSelector.getValue(); 
                int index = 0;
                if (curPage ==1) index =0; if (curPage ==2) index =6; if (curPage ==3) index =12; if (curPage ==4) index =18;
                if (curPage ==5) index =24; if (curPage ==6) index =30; if (curPage ==7) index =36; if (curPage ==8) index =42;
                if (curPage ==9) index =48; if (curPage ==10) index =54;
                for (Tag temp : currentAlbum.getAllPhotos().get(index).listofTags ){
                    
                if (!(Tag.getTagName(temp.getTag()).equalsIgnoreCase("location") || Tag.getTagName(temp.getTag()).equalsIgnoreCase("name") ))
                    choices.add(Tag.getTagName(temp.getTag()));
                }
                choices.add("new");
                ChoiceDialog<String> dialog = new ChoiceDialog<>("name", choices);
                dialog.setTitle("Add Tag");
                dialog.setHeaderText("Choose a tag type or select 'new' to add a new tag type");
                dialog.setContentText("Type:");
                dialog.showAndWait().ifPresent(type ->{
                    if (type.equals("new")){

                        TextInputDialog d = new TextInputDialog();
                        d.setTitle("Add Tag");
                        d.setHeaderText("Enter a new type");
                        d.setContentText("Tag Type:");
                        d.showAndWait().ifPresent(n ->{
                            if (n.isBlank()){
                                Alert successAlert = new Alert(Alert.AlertType.ERROR);
                                successAlert.setTitle("Error");
                                successAlert.setHeaderText(null);
                                successAlert.setContentText("No Tag Type entered.");
                                successAlert.showAndWait();
                                return;

                            }

                            if (!choices.contains(n)){
                                choices.add(n);
                                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                                successAlert.setTitle("Success");
                                successAlert.setHeaderText(null);
                                successAlert.setContentText("Tag Type has been added.");
                                successAlert.showAndWait();
                                TextInputDialog dial = new TextInputDialog();
                                dial.setTitle("Add Tag");
                                dial.setHeaderText("Enter a Value");
                                dial.setContentText("Tag Value:");
                                dial.showAndWait().ifPresent(v ->{
                                    if (v.isBlank()){
                                        Alert fail = new Alert(Alert.AlertType.ERROR);
                                        fail.setTitle("Error");
                                        fail.setHeaderText(null);
                                        fail.setContentText("No Tag Type entered.");
                                        fail.showAndWait();
                                     return;

                                    }
                                    Tag tag1 = new Tag(n, v);
                                    int i = 0;
                            if (curPage ==1) i =0; if (curPage ==2) i =6; if (curPage ==3) i =12; if (curPage ==4) i =18;
                            if (curPage ==5) i =24; if (curPage ==6) i =30; if (curPage ==7) i =36; if (curPage ==8) i =42;
                            if (curPage ==9) i =48; if (curPage ==10) i =54;
                            currentAlbum.getAllPhotos().get(i).listofTags.add(tag1);
                            Alert sAl = new Alert(Alert.AlertType.INFORMATION);
                            sAl.setTitle("Success");
                            sAl.setHeaderText(null);
                            sAl.setContentText("Tag has been successfully added.");
                            sAl.showAndWait();
                            addTagClicked = false;
                            try {
                                Admin.WritetoFile();
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                            return;

                                });


                            }
                            else{
                                Alert successAlert = new Alert(Alert.AlertType.ERROR);
                                successAlert.setTitle("Error");
                                successAlert.setHeaderText(null);
                                successAlert.setContentText("Tag Type already in list");
                                successAlert.showAndWait();
                                return;
                            }
                           

                        });

                    }
                    else{
                    TextInputDialog d = new TextInputDialog();
                    d.setTitle("Add Tag");
                    d.setHeaderText("Enter a Value");
                    d.setContentText("Tag Value:");
                    d.showAndWait().ifPresent(value ->{
                        if (value.isBlank()){
                            Alert successAlert = new Alert(Alert.AlertType.ERROR);
                                successAlert.setTitle("Error");
                                successAlert.setHeaderText(null);
                                successAlert.setContentText("No Tag Value entered.");
                                successAlert.showAndWait();
                                return;
                        }
                        Tag temptag = new Tag(type, value);
                        int i = 0;
                if (curPage ==1) i =0; if (curPage ==2) i =6; if (curPage ==3) i =12; if (curPage ==4) i =18;
                if (curPage ==5) i =24; if (curPage ==6) i =30; if (curPage ==7) i =36; if (curPage ==8) i =42;
                if (curPage ==9) i =48; if (curPage ==10) i =54;
                        for (Tag temp : currentAlbum.getAllPhotos().get(i).listofTags ){
                            if (temptag.getTag().equals(temp.getTag())){
                                Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Duplicate Tag");
                    alert.setHeaderText(null);
                    alert.setContentText("Tag Already exists for this image.");
                    alert.showAndWait();
                    return;
                            }
                    }

                    for (Tag temp : currentAlbum.getAllPhotos().get(i).listofTags ){
                        if (Tag.getTagName(temp.getTag()).equals("location")){ 
                            currentAlbum.getAllPhotos().get(i).listofTags.remove(temp);
                            break;
                        };
                    }
                    currentAlbum.getAllPhotos().get(i).listofTags.add(temptag);
                  
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Success");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Tag has been successfully added.");
                    successAlert.showAndWait();
                    addTagClicked = false;
                    try {
                        Admin.WritetoFile();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }

                    });
                }
                    
                });
        }

        else if (deleteTagClicked){
            ArrayList<String> choices = new ArrayList<>();

            Integer curPage = pageSelector.getValue(); 
            int index = 0;
            if (curPage ==1) index =0; if (curPage ==2) index =6; if (curPage ==3) index =12; if (curPage ==4) index =18;
            if (curPage ==5) index =24; if (curPage ==6) index =30; if (curPage ==7) index =36; if (curPage ==8) index =42;
            if (curPage ==9) index =48; if (curPage ==10) index =54;
            for (Tag temp : currentAlbum.getAllPhotos().get(index).listofTags ){
                if (!Tag.getTagValue(temp.getTag()).equals("")){ choices.add(temp.getTag());}
                
                }
            if (choices.size() ==0){
                Alert successAlert = new Alert(Alert.AlertType.ERROR);
                successAlert.setTitle("Error");
                successAlert.setHeaderText(null);
                successAlert.setContentText("This photo does not have any tags");
                successAlert.showAndWait();
                return;

            }
            ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
            dialog.setTitle("Delete Tag");
            dialog.setHeaderText("Choose a tag to delete.");
            dialog.setContentText("Tag: ");
            dialog.showAndWait().ifPresent(type ->{
                int a = 0;
                if (curPage ==1) a =0; if (curPage ==2) a =6; if (curPage ==3) a =12; if (curPage ==4) a =18;
            if (curPage ==5) a =24; if (curPage ==6) a =30; if (curPage ==7) a =36; if (curPage ==8) a =42;
            if (curPage ==9) a =48; if (curPage ==10) a =54;
                for (Tag temp : currentAlbum.getAllPhotos().get(a).listofTags){
                    if (temp.getTag().equals(type)){
                        currentAlbum.getAllPhotos().get(a).listofTags.remove(temp);
                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                        successAlert.setTitle("Sucess");
                        successAlert.setHeaderText(null);
                        successAlert.setContentText("Tag has been succesfully removed.");
                        successAlert.showAndWait();
                        deleteTagClicked = false;

                        try {
                            Admin.WritetoFile();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        return;

                    }

                }
                Alert successAlert = new Alert(Alert.AlertType.ERROR);
                successAlert.setTitle("Error");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Tag not found.");
                successAlert.showAndWait();

            });

        }
        else if (deletePhotoClicked){
            
        
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("Delete Photo");
            confirmationAlert.setContentText("Are you sure you want to delete this photo?");
            confirmationAlert.showAndWait().ifPresent(result -> {
                if (result == ButtonType.OK) {  
                    Integer curPage = pageSelector.getValue(); 
                    int index = 0;
                    if (curPage ==1) index =0; if (curPage ==2) index =6; if (curPage ==3) index =12; if (curPage ==4) index =18;
                    if (curPage ==5) index =24; if (curPage ==6) index =30; if (curPage ==7) index =36; if (curPage ==8) index =42;
                    if (curPage ==9) index =48; if (curPage ==10) index =54;
                    currentAlbum.getAllPhotos().remove(index);
                    changePage();
                    try {
                        Admin.WritetoFile();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    deletePhotoClicked = false;
                } else {
                    deletePhotoClicked = false;
                    return;
                }
            });
        }

        else if (copyPhotoClicked){
            ArrayList<String> choices = new ArrayList<>();
            for (Album a : UserController.user.getAlbums()){
                if (a.albumName != currentAlbum.albumName)   choices.add(a.albumName);
            }
            ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
            dialog.setTitle("Copy Photo");
            dialog.setHeaderText("Choose an Album to copy this photo to.");
            dialog.setContentText("Album: ");
            dialog.showAndWait().ifPresent(name ->{
          
                

                Integer curPage = pageSelector.getValue(); 
                    int index = 0;
                    if (curPage ==1) index =0; if (curPage ==2) index =6; if (curPage ==3) index =12; if (curPage ==4) index =18;
                    if (curPage ==5) index =24; if (curPage ==6) index =30; if (curPage ==7) index =36; if (curPage ==8) index =42;
                    if (curPage ==9) index =48; if (curPage ==10) index =54;
                    for (Album a: UserController.user.getAlbums()){
                        if (a.albumName.equals(name)){
                            for ( Photo p: a.getAllPhotos() ){
                                if (p.path.equals(currentAlbum.getAllPhotos().get(index).path)){

                                    Alert alert = new Alert(AlertType.ERROR);
                                      alert.setTitle("Error");
                                      alert.setHeaderText(null);
                                          alert.setContentText("Photo is already present in that album.");
                                         alert.showAndWait();
                                         return;
                                }
                            }
                        }
                    }
                    for (Album a : UserController.user.getAlbums()){
                        if (a.albumName.equals(name)){
                            a.getAllPhotos().add(currentAlbum.getAllPhotos().get(index));
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Success");
                              alert.setHeaderText(null);
                               alert.setContentText("Photo has succesfully been copied to " + a.albumName + ".") ;
                               alert.showAndWait();
                               copyPhotoClicked = false;
                               try {
                                Admin.WritetoFile();
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                            return;

                        }
                    }
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Album not found");
                    alert.setHeaderText(null);
                    alert.setContentText("no album with this name found.");
                    alert.showAndWait();
                    copyPhotoClicked = false;


            });
           
        }
        else if (movePhotoClicked){
            ArrayList<String> choices = new ArrayList<>();
            for (Album a : UserController.user.getAlbums()){
                if (a.albumName != currentAlbum.albumName)   choices.add(a.albumName);
            }
            ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
            dialog.setTitle("Move Photo");
            dialog.setHeaderText("Choose an Album to move this photo to.");
            dialog.setContentText("Album: ");
            dialog.showAndWait().ifPresent(name ->{
                Integer curPage = pageSelector.getValue(); 
                    int index = 0;
                    if (curPage ==1) index =0; if (curPage ==2) index =6; if (curPage ==3) index =12; if (curPage ==4) index =18;
                    if (curPage ==5) index =24; if (curPage ==6) index =30; if (curPage ==7) index =36; if (curPage ==8) index =42;
                    if (curPage ==9) index =48; if (curPage ==10) index =54;
                    for (Album a: UserController.user.getAlbums()){
                        if (a.albumName.equals(name)){
                            for ( Photo p: a.getAllPhotos() ){
                                if (p.path.equals(currentAlbum.getAllPhotos().get(index).path)){

                                    Alert alert = new Alert(AlertType.ERROR);
                                      alert.setTitle("Error");
                                      alert.setHeaderText(null);
                                          alert.setContentText("Photo is already present in that album.");
                                         alert.showAndWait();
                                         return;
                                }
                            }
                        }
                    }
                    for (Album a : UserController.user.getAlbums()){
                        if (a.albumName.equals(name)){
                            a.getAllPhotos().add(currentAlbum.getAllPhotos().get(index));
                            currentAlbum.getAllPhotos().remove(index);

                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Success");
                              alert.setHeaderText(null);
                               alert.setContentText("Photo has succesfully been moved to " + a.albumName + ".") ;
                               alert.showAndWait();
                               movePhotoClicked = false;
                               try {
                                Admin.WritetoFile();
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                            changePage();
                            return;

                        }
                    }
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Album not found");
                    alert.setHeaderText(null);
                    alert.setContentText("no album with this name found.");
                    alert.showAndWait();
                    movePhotoClicked = false;


            });

        }
        else if (displayPhotoClicked){
            Integer curPage = pageSelector.getValue(); 
            int index = 0;
            if (curPage ==1) index =0; if (curPage ==2) index =6; if (curPage ==3) index =12; if (curPage ==4) index =18;
            if (curPage ==5) index =24; if (curPage ==6) index =30; if (curPage ==7) index =36; if (curPage ==8) index =42;
            if (curPage ==9) index =48; if (curPage ==10) index =54;
            Photo tempPhoto = currentAlbum.getAllPhotos().get(index);
            PhotoController.DisplayPhoto = tempPhoto;
            try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/PhotoView.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Photo View");
            stage.show();
            displayPhotoClicked = false;
            }
            catch (IOException e){
                e.printStackTrace();
            }

        }
       
    }
    @FXML
    public void img2clicked(){
        if (img2.getImage().equals(null)){
            return;
         }
        if (captionClicked) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add Caption");
            dialog.setHeaderText("Enter the caption for this image: ");
            dialog.setContentText("Caption:");
    
            dialog.showAndWait().ifPresent(caption -> {
                Integer curPage = pageSelector.getValue(); 
                int index = 0;
                if (curPage ==1) index =1; if (curPage ==2) index =7; if (curPage ==3) index =13; if (curPage ==4) index =19;
                if (curPage ==5) index =25; if (curPage ==6) index =31; if (curPage ==7) index =37; if (curPage ==8) index =43;
                if (curPage ==9) index =49; if (curPage ==10) index =55;

                currentAlbum.getAllPhotos().get(index).editCaption(caption);
                try {
                    Admin.WritetoFile();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                changePage();
                captionClicked = false;
            });
        }
        else if (addTagClicked){
            ArrayList<String> choices = new ArrayList<>();
    
            choices.add("name");
            choices.add("location");
            Integer curPage = pageSelector.getValue(); 
            int index = 0;
            if (curPage ==1) index =1; if (curPage ==2) index =7; if (curPage ==3) index =13; if (curPage ==4) index =19;
            if (curPage ==5) index =25; if (curPage ==6) index =31; if (curPage ==7) index =37; if (curPage ==8) index =43;
            if (curPage ==9) index =49; if (curPage ==10) index =55;
                for (Tag temp : currentAlbum.getAllPhotos().get(index).listofTags ){
                if (!(Tag.getTagName(temp.getTag()).equalsIgnoreCase("location") || Tag.getTagName(temp.getTag()).equalsIgnoreCase("name") ))
                    choices.add(Tag.getTagName(temp.getTag()));
                }
                choices.add("new");
                ChoiceDialog<String> dialog = new ChoiceDialog<>("name", choices);
                dialog.setTitle("Add Tag");
                dialog.setHeaderText("Choose a tag type or select 'new' to add a new tag type");
                dialog.setContentText("Type:");
                dialog.showAndWait().ifPresent(type ->{
                    if (type.equals("new")){

                        TextInputDialog d = new TextInputDialog();
                        d.setTitle("Add Tag");
                        d.setHeaderText("Enter a new type");
                        d.setContentText("Tag Type:");
                        d.showAndWait().ifPresent(n ->{
                            if (n.isBlank()){
                                Alert successAlert = new Alert(Alert.AlertType.ERROR);
                                successAlert.setTitle("Error");
                                successAlert.setHeaderText(null);
                                successAlert.setContentText("No Tag Type entered.");
                                successAlert.showAndWait();
                                return;

                            }

                            if (!choices.contains(n)){
                                choices.add(n);
                                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                                successAlert.setTitle("Success");
                                successAlert.setHeaderText(null);
                                successAlert.setContentText("Tag Type has been added.");
                                successAlert.showAndWait();
                                TextInputDialog dial = new TextInputDialog();
                                dial.setTitle("Add Tag");
                                dial.setHeaderText("Enter a Value");
                                dial.setContentText("Tag Value:");
                                dial.showAndWait().ifPresent(v ->{
                                    if (v.isBlank()){
                                        Alert fail = new Alert(Alert.AlertType.ERROR);
                                        fail.setTitle("Error");
                                        fail.setHeaderText(null);
                                        fail.setContentText("No Tag Type entered.");
                                        fail.showAndWait();
                                     return;

                                    }
                                    Tag tag1 = new Tag(n, v);
                                    int i = 0;
                            if (curPage ==1) i =1; if (curPage ==2) i =7; if (curPage ==3) i =13; if (curPage ==4) i =19;
                            if (curPage ==5) i =25; if (curPage ==6) i =31; if (curPage ==7) i =37; if (curPage ==8) i =43;
                            if (curPage ==9) i =49; if (curPage ==10) i =55;
                            currentAlbum.getAllPhotos().get(i).listofTags.add(tag1);
                            Alert sAl = new Alert(Alert.AlertType.INFORMATION);
                            sAl.setTitle("Success");
                            sAl.setHeaderText(null);
                            sAl.setContentText("Tag has been successfully added.");
                            sAl.showAndWait();
                            addTagClicked = false;
                            try {
                                Admin.WritetoFile();
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                            return;

                                });


                            }
                            else{
                                Alert successAlert = new Alert(Alert.AlertType.ERROR);
                                successAlert.setTitle("Error");
                                successAlert.setHeaderText(null);
                                successAlert.setContentText("Tag Type already in list");
                                successAlert.showAndWait();
                                return;
                            }
                           

                        });

                    }
                    else{
                    TextInputDialog d = new TextInputDialog();
                    d.setTitle("Add Tag");
                    d.setHeaderText("Enter a Value");
                    d.setContentText("Tag Value:");
                    d.showAndWait().ifPresent(value ->{
                        if (value.isBlank()){
                            Alert fail = new Alert(Alert.AlertType.ERROR);
                            fail.setTitle("Error");
                            fail.setHeaderText(null);
                            fail.setContentText("No Tag Type entered.");
                            fail.showAndWait();
                         return;

                        }
                        
                        Tag temptag = new Tag(type, value);
                        int i = 0;
                        if (curPage ==1) i =1; if (curPage ==2) i =7; if (curPage ==3) i =13; if (curPage ==4) i =19;
                        if (curPage ==5) i =25; if (curPage ==6) i =31; if (curPage ==7) i =37; if (curPage ==8) i =43;
                        if (curPage ==9) i =49; if (curPage ==10) i =55;
                        for (Tag temp : currentAlbum.getAllPhotos().get(i).listofTags ){
                            if (temptag.getTag().equals(temp.getTag())){
                                Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Duplicate Tag");
                    alert.setHeaderText(null);
                    alert.setContentText("Tag Already exists for this image.");
                    alert.showAndWait();
                    return;
                            }
                    }

                    for (Tag temp : currentAlbum.getAllPhotos().get(i).listofTags ){
                        if (Tag.getTagName(temp.getTag()).equals("location")){ 
                            currentAlbum.getAllPhotos().get(i).listofTags.remove(temp);
                            break;
                        };
                    }
                    currentAlbum.getAllPhotos().get(i).listofTags.add(temptag);
                  
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Success");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Tag has been successfully added.");
                    successAlert.showAndWait();
                    addTagClicked = false;
                    try {
                        Admin.WritetoFile();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }

                    });
                }
                    
                });
        }

        else if (deleteTagClicked){
            ArrayList<String> choices = new ArrayList<>();

            Integer curPage = pageSelector.getValue(); 
            int index = 0;
            if (curPage ==1) index =1; if (curPage ==2) index =7; if (curPage ==3) index =13; if (curPage ==4) index =19;
            if (curPage ==5) index =25; if (curPage ==6) index =31; if (curPage ==7) index =37; if (curPage ==8) index =43;
            if (curPage ==9) index =49; if (curPage ==10) index =55;
            for (Tag temp : currentAlbum.getAllPhotos().get(index).listofTags ){
                    choices.add(temp.getTag());
                }
            if (choices.size() ==0){
                Alert successAlert = new Alert(Alert.AlertType.ERROR);
                successAlert.setTitle("Error");
                successAlert.setHeaderText(null);
                successAlert.setContentText("This photo does not have any tags");
                successAlert.showAndWait();
                return;

            }
            ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
            dialog.setTitle("Delete Tag");
            dialog.setHeaderText("Choose a tag to delete.");
            dialog.setContentText("Tag: ");
            dialog.showAndWait().ifPresent(type ->{
                int a = 0;
                if (curPage ==1) a =1; if (curPage ==2) a =7; if (curPage ==3) a =13; if (curPage ==4) a =19;
            if (curPage ==5) a =25; if (curPage ==6) a =31; if (curPage ==7) a =37; if (curPage ==8) a =43;
            if (curPage ==9) a =49; if (curPage ==10) a =55;
                for (Tag temp : currentAlbum.getAllPhotos().get(a).listofTags){
                    if (temp.getTag().equals(type)){
                        currentAlbum.getAllPhotos().get(a).listofTags.remove(temp);
                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                        successAlert.setTitle("Sucess");
                        successAlert.setHeaderText(null);
                        successAlert.setContentText("Tag has been succesfully removed.");
                        successAlert.showAndWait();
                        deleteTagClicked = false;

                        try {
                            Admin.WritetoFile();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        return;

                    }

                }
                Alert successAlert = new Alert(Alert.AlertType.ERROR);
                successAlert.setTitle("Error");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Tag not found.");
                successAlert.showAndWait();

            });

            
        }
        else if (deletePhotoClicked){
            
        
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("Delete Photo");
            confirmationAlert.setContentText("Are you sure you want to delete this photo?");
            confirmationAlert.showAndWait().ifPresent(result -> {
                if (result == ButtonType.OK) {  
                    Integer curPage = pageSelector.getValue(); 
                    int index = 0;
                    if (curPage ==1) index =1; if (curPage ==2) index =7; if (curPage ==3) index =13; if (curPage ==4) index =19;
                    if (curPage ==5) index =25; if (curPage ==6) index =31; if (curPage ==7) index =37; if (curPage ==8) index =43;
                    if (curPage ==9) index =49; if (curPage ==10) index =55;
                    currentAlbum.getAllPhotos().remove(index);
                    changePage();
                    try {
                        Admin.WritetoFile();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    deletePhotoClicked = false;
                } else {
                    deletePhotoClicked = false;
                    return;
                }
            });
        }

        else if (copyPhotoClicked){
            ArrayList<String> choices = new ArrayList<>();
            for (Album a : UserController.user.getAlbums()){
                if (a.albumName != currentAlbum.albumName)   choices.add(a.albumName);
            }
            ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
            dialog.setTitle("Copy Photo");
            dialog.setHeaderText("Choose an Album to copy this photo to.");
            dialog.setContentText("Album: ");
            dialog.showAndWait().ifPresent(name ->{
          
                

                Integer curPage = pageSelector.getValue(); 
                int index = 0;
                if (curPage ==1) index =1; if (curPage ==2) index =7; if (curPage ==3) index =13; if (curPage ==4) index =19;
                if (curPage ==5) index =25; if (curPage ==6) index =31; if (curPage ==7) index =37; if (curPage ==8) index =43;
                if (curPage ==9) index =49; if (curPage ==10) index =55;
                    for (Album a: UserController.user.getAlbums()){
                        if (a.albumName.equals(name)){
                            for ( Photo p: a.getAllPhotos() ){
                                if (p.path.equals(currentAlbum.getAllPhotos().get(index).path)){

                                    Alert alert = new Alert(AlertType.ERROR);
                                      alert.setTitle("Error");
                                      alert.setHeaderText(null);
                                          alert.setContentText("Photo is already present in that album.");
                                         alert.showAndWait();
                                         return;
                                }
                            }
                        }
                    }
                    for (Album a : UserController.user.getAlbums()){
                        if (a.albumName.equals(name)){
                            a.getAllPhotos().add(currentAlbum.getAllPhotos().get(index));
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Success");
                              alert.setHeaderText(null);
                               alert.setContentText("Photo has succesfully been copied to " + a.albumName + ".") ;
                               alert.showAndWait();
                               copyPhotoClicked = false;
                               try {
                                Admin.WritetoFile();
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                            return;

                        }
                    }
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Album not found");
                    alert.setHeaderText(null);
                    alert.setContentText("no album with this name found.");
                    alert.showAndWait();
                    copyPhotoClicked = false;


            });
           
        }
        else if (movePhotoClicked){
            ArrayList<String> choices = new ArrayList<>();
            for (Album a : UserController.user.getAlbums()){
                if (a.albumName != currentAlbum.albumName)   choices.add(a.albumName);
            }
            ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
            dialog.setTitle("Move Photo");
            dialog.setHeaderText("Choose an Album to move this photo to.");
            dialog.setContentText("Album: ");
            dialog.showAndWait().ifPresent(name ->{
                Integer curPage = pageSelector.getValue(); 
                int index = 0;
                if (curPage ==1) index =1; if (curPage ==2) index =7; if (curPage ==3) index =13; if (curPage ==4) index =19;
                if (curPage ==5) index =25; if (curPage ==6) index =31; if (curPage ==7) index =37; if (curPage ==8) index =43;
                if (curPage ==9) index =49; if (curPage ==10) index =55;
                    for (Album a: UserController.user.getAlbums()){
                        if (a.albumName.equals(name)){
                            for ( Photo p: a.getAllPhotos() ){
                                if (p.path.equals(currentAlbum.getAllPhotos().get(index).path)){

                                    Alert alert = new Alert(AlertType.ERROR);
                                      alert.setTitle("Error");
                                      alert.setHeaderText(null);
                                          alert.setContentText("Photo is already present in that album.");
                                         alert.showAndWait();
                                         return;
                                }
                            }
                        }
                    }
                    for (Album a : UserController.user.getAlbums()){
                        if (a.albumName.equals(name)){
                            a.getAllPhotos().add(currentAlbum.getAllPhotos().get(index));
                            currentAlbum.getAllPhotos().remove(index);

                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Success");
                              alert.setHeaderText(null);
                               alert.setContentText("Photo has succesfully been moved to " + a.albumName + ".") ;
                               alert.showAndWait();
                               movePhotoClicked = false;
                               try {
                                Admin.WritetoFile();
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                            changePage();
                            return;

                        }
                    }
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Album not found");
                    alert.setHeaderText(null);
                    alert.setContentText("no album with this name found.");
                    alert.showAndWait();
                    movePhotoClicked = false;


            });

        }
        else if (displayPhotoClicked){
            Integer curPage = pageSelector.getValue(); 
            int index = 0;
            if (curPage ==1) index =1; if (curPage ==2) index =7; if (curPage ==3) index =13; if (curPage ==4) index =19;
            if (curPage ==5) index =25; if (curPage ==6) index =31; if (curPage ==7) index =37; if (curPage ==8) index =43;
            if (curPage ==9) index =49; if (curPage ==10) index =55;
            Photo tempPhoto = currentAlbum.getAllPhotos().get(index);
            PhotoController.DisplayPhoto = tempPhoto;
            try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/PhotoView.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Photo View");
            stage.show();
            displayPhotoClicked = false;
            }
            catch (IOException e){
                e.printStackTrace();
            }

        }
        
    }
    @FXML
    public void img3clicked(){
        if (img3.getImage().equals(null)){
            return;
         }
        if (captionClicked) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add Caption");
            dialog.setHeaderText("Enter the caption for this image: ");
            dialog.setContentText("Caption:");
    
            dialog.showAndWait().ifPresent(caption -> {
                Integer curPage = pageSelector.getValue(); 
                int index = 0;
                if (curPage ==1) index =2; if (curPage ==2) index =8; if (curPage ==3) index =14; if (curPage ==4) index =20;
                if (curPage ==5) index =26; if (curPage ==6) index =32; if (curPage ==7) index =38; if (curPage ==8) index =44;
                if (curPage ==9) index =50; if (curPage ==10) index =56;

                currentAlbum.getAllPhotos().get(index).editCaption(caption);
                try {
                    Admin.WritetoFile();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                changePage();
                captionClicked = false;
            });
        }
        else if (addTagClicked){
            ArrayList<String> choices = new ArrayList<>();
    
            choices.add("name");
            choices.add("location");
            Integer curPage = pageSelector.getValue(); 
            int index = 0;
            if (curPage ==1) index =2; if (curPage ==2) index =8; if (curPage ==3) index =14; if (curPage ==4) index =20;
            if (curPage ==5) index =26; if (curPage ==6) index =32; if (curPage ==7) index =38; if (curPage ==8) index =44;
            if (curPage ==9) index =50; if (curPage ==10) index =56;

                for (Tag temp : currentAlbum.getAllPhotos().get(index).listofTags ){
                if (!(Tag.getTagName(temp.getTag()).equalsIgnoreCase("location") || Tag.getTagName(temp.getTag()).equalsIgnoreCase("name") ))
                    choices.add(Tag.getTagName(temp.getTag()));
                }
                choices.add("new");
                ChoiceDialog<String> dialog = new ChoiceDialog<>("name", choices);
                dialog.setTitle("Add Tag");
                dialog.setHeaderText("Choose a tag type or select 'new' to add a new tag type");
                dialog.setContentText("Type:");
                dialog.showAndWait().ifPresent(type ->{
                    if (type.equals("new")){

                        TextInputDialog d = new TextInputDialog();
                        d.setTitle("Add Tag");
                        d.setHeaderText("Enter a new type");
                        d.setContentText("Tag Type:");
                        d.showAndWait().ifPresent(n ->{
                            if (n.isBlank()){
                                Alert successAlert = new Alert(Alert.AlertType.ERROR);
                                successAlert.setTitle("Error");
                                successAlert.setHeaderText(null);
                                successAlert.setContentText("No Tag Type entered.");
                                successAlert.showAndWait();
                                return;

                            }

                            if (!choices.contains(n)){
                                choices.add(n);
                                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                                successAlert.setTitle("Success");
                                successAlert.setHeaderText(null);
                                successAlert.setContentText("Tag Type has been added.");
                                successAlert.showAndWait();
                                TextInputDialog dial = new TextInputDialog();
                                dial.setTitle("Add Tag");
                                dial.setHeaderText("Enter a Value");
                                dial.setContentText("Tag Value:");
                                dial.showAndWait().ifPresent(v ->{
                                    if (v.isBlank()){
                                        Alert fail = new Alert(Alert.AlertType.ERROR);
                                        fail.setTitle("Error");
                                        fail.setHeaderText(null);
                                        fail.setContentText("No Tag Type entered.");
                                        fail.showAndWait();
                                     return;

                                    }
                                    Tag tag1 = new Tag(n, v);
                                    int i = 0;
                if (curPage ==1) i =2; if (curPage ==2) i =8; if (curPage ==3) i =14; if (curPage ==4) i =20;
                if (curPage ==5) i =26; if (curPage ==6) i =32; if (curPage ==7) i =38; if (curPage ==8) i =44;
                if (curPage ==9) i =50; if (curPage ==10) i =56;

                            currentAlbum.getAllPhotos().get(i).listofTags.add(tag1);
                            Alert sAl = new Alert(Alert.AlertType.INFORMATION);
                            sAl.setTitle("Success");
                            sAl.setHeaderText(null);
                            sAl.setContentText("Tag has been successfully added.");
                            sAl.showAndWait();
                            addTagClicked = false;
                            try {
                                Admin.WritetoFile();
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                            return;

                                });


                            }
                            else{
                                Alert successAlert = new Alert(Alert.AlertType.ERROR);
                                successAlert.setTitle("Error");
                                successAlert.setHeaderText(null);
                                successAlert.setContentText("Tag Type already in list");
                                successAlert.showAndWait();
                                return;
                            }
                           

                        });

                    }
                    else{
                    TextInputDialog d = new TextInputDialog();
                    d.setTitle("Add Tag");
                    d.setHeaderText("Enter a Value");
                    d.setContentText("Tag Value:");
                    d.showAndWait().ifPresent(value ->{
                        if (value.isBlank()){
                            Alert fail = new Alert(Alert.AlertType.ERROR);
                            fail.setTitle("Error");
                            fail.setHeaderText(null);
                            fail.setContentText("No Tag Type entered.");
                            fail.showAndWait();
                         return;

                        }
                        
                        Tag temptag = new Tag(type, value);
                        int i = 0;
                        if (curPage ==1) i =2; if (curPage ==2) i =8; if (curPage ==3) i =14; if (curPage ==4) i =20;
                        if (curPage ==5) i =26; if (curPage ==6) i =32; if (curPage ==7) i =38; if (curPage ==8) i =44;
                        if (curPage ==9) i =50; if (curPage ==10) i =56;
                        for (Tag temp : currentAlbum.getAllPhotos().get(i).listofTags ){
                            if (temptag.getTag().equals(temp.getTag())){
                                Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Duplicate Tag");
                    alert.setHeaderText(null);
                    alert.setContentText("Tag Already exists for this image.");
                    alert.showAndWait();
                    return;
                            }
                    }

                    for (Tag temp : currentAlbum.getAllPhotos().get(i).listofTags ){
                        if (Tag.getTagName(temp.getTag()).equals("location")){ 
                            currentAlbum.getAllPhotos().get(i).listofTags.remove(temp);
                            break;
                        };
                    }
                    currentAlbum.getAllPhotos().get(i).listofTags.add(temptag);
                  
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Success");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Tag has been successfully added.");
                    successAlert.showAndWait();
                    addTagClicked = false;
                    try {
                        Admin.WritetoFile();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }

                    });
                }
                    
                });
        }

        else if (deleteTagClicked){
            ArrayList<String> choices = new ArrayList<>();

            Integer curPage = pageSelector.getValue(); 
            int index = 0;
            if (curPage ==1) index =2; if (curPage ==2) index =8; if (curPage ==3) index =14; if (curPage ==4) index =20;
            if (curPage ==5) index =26; if (curPage ==6) index =32; if (curPage ==7) index =38; if (curPage ==8) index =44;
            if (curPage ==9) index =50; if (curPage ==10) index =56;
            for (Tag temp : currentAlbum.getAllPhotos().get(index).listofTags ){
                    choices.add(temp.getTag());
                }
            if (choices.size() ==0){
                Alert successAlert = new Alert(Alert.AlertType.ERROR);
                successAlert.setTitle("Error");
                successAlert.setHeaderText(null);
                successAlert.setContentText("This photo does not have any tags");
                successAlert.showAndWait();
                return;

            }
            ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
            dialog.setTitle("Delete Tag");
            dialog.setHeaderText("Choose a tag to delete.");
            dialog.setContentText("Tag: ");
            dialog.showAndWait().ifPresent(type ->{
                int a = 0;
                if (curPage ==1) a =2; if (curPage ==2) a =8; if (curPage ==3) a =14; if (curPage ==4) a =20;
            if (curPage ==5) a =26; if (curPage ==6) a =32; if (curPage ==7) a =38; if (curPage ==8) a =44;
            if (curPage ==9) a =50; if (curPage ==10) a =56;
                for (Tag temp : currentAlbum.getAllPhotos().get(a).listofTags){
                    if (temp.getTag().equals(type)){
                        currentAlbum.getAllPhotos().get(a).listofTags.remove(temp);
                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                        successAlert.setTitle("Sucess");
                        successAlert.setHeaderText(null);
                        successAlert.setContentText("Tag has been succesfully removed.");
                        successAlert.showAndWait();
                        deleteTagClicked = false;

                        try {
                            Admin.WritetoFile();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        return;

                    }

                }
                Alert successAlert = new Alert(Alert.AlertType.ERROR);
                successAlert.setTitle("Error");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Tag not found.");
                successAlert.showAndWait();

            });

            
        }
        else if (deletePhotoClicked){
            
        
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("Delete Photo");
            confirmationAlert.setContentText("Are you sure you want to delete this photo?");
            confirmationAlert.showAndWait().ifPresent(result -> {
                if (result == ButtonType.OK) {  
                    Integer curPage = pageSelector.getValue(); 
                    int index = 0;
            if (curPage ==1) index =2; if (curPage ==2) index =8; if (curPage ==3) index =14; if (curPage ==4) index =20;
            if (curPage ==5) index =26; if (curPage ==6) index =32; if (curPage ==7) index =38; if (curPage ==8) index =44;
            if (curPage ==9) index =50; if (curPage ==10) index =56;
                    currentAlbum.getAllPhotos().remove(index);
                    changePage();
                    try {
                        Admin.WritetoFile();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    deletePhotoClicked = false;
                } else {
                    deletePhotoClicked = false;
                    return;
                }
            });
        }

        else if (copyPhotoClicked){
            ArrayList<String> choices = new ArrayList<>();
            for (Album a : UserController.user.getAlbums()){
                if (a.albumName != currentAlbum.albumName)   choices.add(a.albumName);
            }
            ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
            dialog.setTitle("Copy Photo");
            dialog.setHeaderText("Choose an Album to copy this photo to.");
            dialog.setContentText("Album: ");
            dialog.showAndWait().ifPresent(name ->{
          
                

                Integer curPage = pageSelector.getValue(); 
                int index = 0;
                if (curPage ==1) index =2; if (curPage ==2) index =8; if (curPage ==3) index =14; if (curPage ==4) index =20;
                if (curPage ==5) index =26; if (curPage ==6) index =32; if (curPage ==7) index =38; if (curPage ==8) index =44;
                if (curPage ==9) index =50; if (curPage ==10) index =56;
                    for (Album a: UserController.user.getAlbums()){
                        if (a.albumName.equals(name)){
                            for ( Photo p: a.getAllPhotos() ){
                                if (p.path.equals(currentAlbum.getAllPhotos().get(index).path)){

                                    Alert alert = new Alert(AlertType.ERROR);
                                      alert.setTitle("Error");
                                      alert.setHeaderText(null);
                                          alert.setContentText("Photo is already present in that album.");
                                         alert.showAndWait();
                                         return;
                                }
                            }
                        }
                    }
                    for (Album a : UserController.user.getAlbums()){
                        if (a.albumName.equals(name)){
                            a.getAllPhotos().add(currentAlbum.getAllPhotos().get(index));
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Success");
                              alert.setHeaderText(null);
                               alert.setContentText("Photo has succesfully been copied to " + a.albumName + ".") ;
                               alert.showAndWait();
                               copyPhotoClicked = false;
                               try {
                                Admin.WritetoFile();
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                            return;

                        }
                    }
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Album not found");
                    alert.setHeaderText(null);
                    alert.setContentText("no album with this name found.");
                    alert.showAndWait();
                    copyPhotoClicked = false;


            });
           
        }
        else if (movePhotoClicked){
            ArrayList<String> choices = new ArrayList<>();
            for (Album a : UserController.user.getAlbums()){
                if (a.albumName != currentAlbum.albumName)   choices.add(a.albumName);
            }
            ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
            dialog.setTitle("Move Photo");
            dialog.setHeaderText("Choose an Album to move this photo to.");
            dialog.setContentText("Album: ");
            dialog.showAndWait().ifPresent(name ->{
                Integer curPage = pageSelector.getValue(); 
                int index = 0;
                if (curPage ==1) index =2; if (curPage ==2) index =8; if (curPage ==3) index =14; if (curPage ==4) index =20;
                if (curPage ==5) index =26; if (curPage ==6) index =32; if (curPage ==7) index =38; if (curPage ==8) index =44;
                if (curPage ==9) index =50; if (curPage ==10) index =56;
                    for (Album a: UserController.user.getAlbums()){
                        if (a.albumName.equals(name)){
                            for ( Photo p: a.getAllPhotos() ){
                                if (p.path.equals(currentAlbum.getAllPhotos().get(index).path)){

                                    Alert alert = new Alert(AlertType.ERROR);
                                      alert.setTitle("Error");
                                      alert.setHeaderText(null);
                                          alert.setContentText("Photo is already present in that album.");
                                         alert.showAndWait();
                                         return;
                                }
                            }
                        }
                    }
                    for (Album a : UserController.user.getAlbums()){
                        if (a.albumName.equals(name)){
                            a.getAllPhotos().add(currentAlbum.getAllPhotos().get(index));
                            currentAlbum.getAllPhotos().remove(index);

                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Success");
                              alert.setHeaderText(null);
                               alert.setContentText("Photo has succesfully been moved to " + a.albumName + ".") ;
                               alert.showAndWait();
                               movePhotoClicked = false;
                               try {
                                Admin.WritetoFile();
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                            changePage();
                            return;

                        }
                    }
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Album not found");
                    alert.setHeaderText(null);
                    alert.setContentText("no album with this name found.");
                    alert.showAndWait();
                    movePhotoClicked = false;


            });

        }
        else if (displayPhotoClicked){
            Integer curPage = pageSelector.getValue(); 
            int index = 0;
            if (curPage ==1) index =2; if (curPage ==2) index =8; if (curPage ==3) index =14; if (curPage ==4) index =20;
            if (curPage ==5) index =26; if (curPage ==6) index =32; if (curPage ==7) index =38; if (curPage ==8) index =44;
            if (curPage ==9) index =50; if (curPage ==10) index =56;
            Photo tempPhoto = currentAlbum.getAllPhotos().get(index);
            PhotoController.DisplayPhoto = tempPhoto;
            try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/PhotoView.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Photo View");
            stage.show();
            displayPhotoClicked = false;
            }
            catch (IOException e){
                e.printStackTrace();
            }

        }
        
    }
    @FXML
    public void img4clicked(){
        if (img4.getImage().equals(null)){
            return;
         }
        if (captionClicked) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add Caption");
            dialog.setHeaderText("Enter the caption for this image: ");
            dialog.setContentText("Caption:");
    
            dialog.showAndWait().ifPresent(caption -> {
                Integer curPage = pageSelector.getValue(); 
                int index = 0;
                if (curPage ==1) index =3; if (curPage ==2) index =9; if (curPage ==3) index =15; if (curPage ==4) index =21;
                if (curPage ==5) index =27; if (curPage ==6) index =33; if (curPage ==7) index =39; if (curPage ==8) index =45;
                if (curPage ==9) index =51; if (curPage ==10) index =57;

                currentAlbum.getAllPhotos().get(index).editCaption(caption);
                try {
                    Admin.WritetoFile();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                changePage();
                captionClicked = false;
            });
        }
        else if (addTagClicked){
            ArrayList<String> choices = new ArrayList<>();
    
            choices.add("name");
            choices.add("location");
            Integer curPage = pageSelector.getValue(); 
            int index = 0;
            if (curPage ==1) index =3; if (curPage ==2) index =9; if (curPage ==3) index =15; if (curPage ==4) index =21;
            if (curPage ==5) index =27; if (curPage ==6) index =33; if (curPage ==7) index =39; if (curPage ==8) index =45;
            if (curPage ==9) index =51; if (curPage ==10) index =57;
                for (Tag temp : currentAlbum.getAllPhotos().get(index).listofTags ){
                if (!(Tag.getTagName(temp.getTag()).equalsIgnoreCase("location") || Tag.getTagName(temp.getTag()).equalsIgnoreCase("name") ))
                    choices.add(Tag.getTagName(temp.getTag()));
                }
                choices.add("new");
                ChoiceDialog<String> dialog = new ChoiceDialog<>("name", choices);
                dialog.setTitle("Add Tag");
                dialog.setHeaderText("Choose a tag type or select 'new' to add a new tag type");
                dialog.setContentText("Type:");
                dialog.showAndWait().ifPresent(type ->{
                    if (type.equals("new")){

                        TextInputDialog d = new TextInputDialog();
                        d.setTitle("Add Tag");
                        d.setHeaderText("Enter a new type");
                        d.setContentText("Tag Type:");
                        d.showAndWait().ifPresent(n ->{
                            if (n.isBlank()){
                                Alert successAlert = new Alert(Alert.AlertType.ERROR);
                                successAlert.setTitle("Error");
                                successAlert.setHeaderText(null);
                                successAlert.setContentText("No Tag Type entered.");
                                successAlert.showAndWait();
                                return;

                            }

                            if (!choices.contains(n)){
                                choices.add(n);
                                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                                successAlert.setTitle("Success");
                                successAlert.setHeaderText(null);
                                successAlert.setContentText("Tag Type has been added.");
                                successAlert.showAndWait();
                                TextInputDialog dial = new TextInputDialog();
                                dial.setTitle("Add Tag");
                                dial.setHeaderText("Enter a Value");
                                dial.setContentText("Tag Value:");
                                dial.showAndWait().ifPresent(v ->{
                                    if (v.isBlank()){
                                        Alert fail = new Alert(Alert.AlertType.ERROR);
                                        fail.setTitle("Error");
                                        fail.setHeaderText(null);
                                        fail.setContentText("No Tag Type entered.");
                                        fail.showAndWait();
                                     return;

                                    }
                                    Tag tag1 = new Tag(n, v);
                                    int i = 0;
                if (curPage ==1) i =3; if (curPage ==2) i =9; if (curPage ==3) i =15; if (curPage ==4) i =21;
                if (curPage ==5) i =27; if (curPage ==6) i =33; if (curPage ==7) i =39; if (curPage ==8) i =45;
                if (curPage ==9) i =51; if (curPage ==10) i =57;
                            currentAlbum.getAllPhotos().get(i).listofTags.add(tag1);
                            Alert sAl = new Alert(Alert.AlertType.INFORMATION);
                            sAl.setTitle("Success");
                            sAl.setHeaderText(null);
                            sAl.setContentText("Tag has been successfully added.");
                            sAl.showAndWait();
                            addTagClicked = false;
                            try {
                                Admin.WritetoFile();
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                            return;

                                });


                            }
                            else{
                                Alert successAlert = new Alert(Alert.AlertType.ERROR);
                                successAlert.setTitle("Error");
                                successAlert.setHeaderText(null);
                                successAlert.setContentText("Tag Type already in list");
                                successAlert.showAndWait();
                                return;
                            }
                           

                        });

                    }
                    else{
                    TextInputDialog d = new TextInputDialog();
                    d.setTitle("Add Tag");
                    d.setHeaderText("Enter a Value");
                    d.setContentText("Tag Value:");
                    d.showAndWait().ifPresent(value ->{
                        if (value.isBlank()){
                            Alert fail = new Alert(Alert.AlertType.ERROR);
                            fail.setTitle("Error");
                            fail.setHeaderText(null);
                            fail.setContentText("No Tag Type entered.");
                            fail.showAndWait();
                         return;

                        }
                        
                        Tag temptag = new Tag(type, value);
                        int i = 0;
                if (curPage ==1) i =3; if (curPage ==2) i =9; if (curPage ==3) i =15; if (curPage ==4) i =21;
                if (curPage ==5) i =27; if (curPage ==6) i =33; if (curPage ==7) i =39; if (curPage ==8) i =45;
                if (curPage ==9) i =51; if (curPage ==10) i =57;
                        for (Tag temp : currentAlbum.getAllPhotos().get(i).listofTags ){
                            if (temptag.getTag().equals(temp.getTag())){
                                Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Duplicate Tag");
                    alert.setHeaderText(null);
                    alert.setContentText("Tag Already exists for this image.");
                    alert.showAndWait();
                    return;
                            }
                    }

                    for (Tag temp : currentAlbum.getAllPhotos().get(i).listofTags ){
                        if (Tag.getTagName(temp.getTag()).equals("location")){ 
                            currentAlbum.getAllPhotos().get(i).listofTags.remove(temp);
                            break;
                        };
                    }
                    currentAlbum.getAllPhotos().get(i).listofTags.add(temptag);
                  
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Success");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Tag has been successfully added.");
                    successAlert.showAndWait();
                    addTagClicked = false;
                    try {
                        Admin.WritetoFile();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }

                    });
                }
                    
                });
        }

        else if (deleteTagClicked){
            ArrayList<String> choices = new ArrayList<>();

            Integer curPage = pageSelector.getValue(); 
            int index = 0;
            if (curPage ==1) index =3; if (curPage ==2) index =9; if (curPage ==3) index =15; if (curPage ==4) index =21;
            if (curPage ==5) index =27; if (curPage ==6) index =33; if (curPage ==7) index =39; if (curPage ==8) index =45;
            if (curPage ==9) index =51; if (curPage ==10) index =57;
            for (Tag temp : currentAlbum.getAllPhotos().get(index).listofTags ){
                    choices.add(temp.getTag());
                }
            if (choices.size() ==0){
                Alert successAlert = new Alert(Alert.AlertType.ERROR);
                successAlert.setTitle("Error");
                successAlert.setHeaderText(null);
                successAlert.setContentText("This photo does not have any tags");
                successAlert.showAndWait();
                return;

            }
            ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
            dialog.setTitle("Delete Tag");
            dialog.setHeaderText("Choose a tag to delete.");
            dialog.setContentText("Tag: ");
            dialog.showAndWait().ifPresent(type ->{
                int a = 0;
                if (curPage ==1) a =3; if (curPage ==2) a =9; if (curPage ==3) a =15; if (curPage ==4) a =21;
            if (curPage ==5) a =27; if (curPage ==6) a =33; if (curPage ==7) a =39; if (curPage ==8) a =45;
            if (curPage ==9) a =51; if (curPage ==10) a =57;
                for (Tag temp : currentAlbum.getAllPhotos().get(a).listofTags){
                    if (temp.getTag().equals(type)){
                        currentAlbum.getAllPhotos().get(a).listofTags.remove(temp);
                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                        successAlert.setTitle("Sucess");
                        successAlert.setHeaderText(null);
                        successAlert.setContentText("Tag has been succesfully removed.");
                        successAlert.showAndWait();
                        deleteTagClicked = false;

                        try {
                            Admin.WritetoFile();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        return;

                    }

                }
                Alert successAlert = new Alert(Alert.AlertType.ERROR);
                successAlert.setTitle("Error");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Tag not found.");
                successAlert.showAndWait();

            });

            
        }
        else if (deletePhotoClicked){
            
        
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("Delete Photo");
            confirmationAlert.setContentText("Are you sure you want to delete this photo?");
            confirmationAlert.showAndWait().ifPresent(result -> {
                if (result == ButtonType.OK) {  
                    Integer curPage = pageSelector.getValue(); 
                    int index = 0;
                    if (curPage ==1) index =3; if (curPage ==2) index =9; if (curPage ==3) index =15; if (curPage ==4) index =21;
                    if (curPage ==5) index =27; if (curPage ==6) index =33; if (curPage ==7) index =39; if (curPage ==8) index =45;
                    if (curPage ==9) index =51; if (curPage ==10) index =57;
                    currentAlbum.getAllPhotos().remove(index);
                    changePage();
                    try {
                        Admin.WritetoFile();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    deletePhotoClicked = false;
                } else {
                    deletePhotoClicked = false;
                    return;
                }
            });
        }

        else if (copyPhotoClicked){
            ArrayList<String> choices = new ArrayList<>();
            for (Album a : UserController.user.getAlbums()){
                if (a.albumName != currentAlbum.albumName)   choices.add(a.albumName);
            }
            ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
            dialog.setTitle("Copy Photo");
            dialog.setHeaderText("Choose an Album to copy this photo to.");
            dialog.setContentText("Album: ");
            dialog.showAndWait().ifPresent(name ->{
          
                

                Integer curPage = pageSelector.getValue(); 
                int index = 0;
                if (curPage ==1) index =3; if (curPage ==2) index =9; if (curPage ==3) index =15; if (curPage ==4) index =21;
                if (curPage ==5) index =27; if (curPage ==6) index =33; if (curPage ==7) index =39; if (curPage ==8) index =45;
                if (curPage ==9) index =51; if (curPage ==10) index =57;
                    for (Album a: UserController.user.getAlbums()){
                        if (a.albumName.equals(name)){
                            for ( Photo p: a.getAllPhotos() ){
                                if (p.path.equals(currentAlbum.getAllPhotos().get(index).path)){

                                    Alert alert = new Alert(AlertType.ERROR);
                                      alert.setTitle("Error");
                                      alert.setHeaderText(null);
                                          alert.setContentText("Photo is already present in that album.");
                                         alert.showAndWait();
                                         return;
                                }
                            }
                        }
                    }
                    for (Album a : UserController.user.getAlbums()){
                        if (a.albumName.equals(name)){
                            a.getAllPhotos().add(currentAlbum.getAllPhotos().get(index));
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Success");
                              alert.setHeaderText(null);
                               alert.setContentText("Photo has succesfully been copied to " + a.albumName + ".") ;
                               alert.showAndWait();
                               copyPhotoClicked = false;
                               try {
                                Admin.WritetoFile();
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                            return;

                        }
                    }
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Album not found");
                    alert.setHeaderText(null);
                    alert.setContentText("no album with this name found.");
                    alert.showAndWait();
                    copyPhotoClicked = false;


            });
           
        }
        else if (movePhotoClicked){
            ArrayList<String> choices = new ArrayList<>();
            for (Album a : UserController.user.getAlbums()){
                if (a.albumName != currentAlbum.albumName)   choices.add(a.albumName);
            }
            ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
            dialog.setTitle("Move Photo");
            dialog.setHeaderText("Choose an Album to move this photo to.");
            dialog.setContentText("Album: ");
            dialog.showAndWait().ifPresent(name ->{
                Integer curPage = pageSelector.getValue(); 
                int index = 0;
                if (curPage ==1) index =3; if (curPage ==2) index =9; if (curPage ==3) index =15; if (curPage ==4) index =21;
                if (curPage ==5) index =27; if (curPage ==6) index =33; if (curPage ==7) index =39; if (curPage ==8) index =45;
                if (curPage ==9) index =51; if (curPage ==10) index =57;
                    for (Album a: UserController.user.getAlbums()){
                        if (a.albumName.equals(name)){
                            for ( Photo p: a.getAllPhotos() ){
                                if (p.path.equals(currentAlbum.getAllPhotos().get(index).path)){

                                    Alert alert = new Alert(AlertType.ERROR);
                                      alert.setTitle("Error");
                                      alert.setHeaderText(null);
                                          alert.setContentText("Photo is already present in that album.");
                                         alert.showAndWait();
                                         return;
                                }
                            }
                        }
                    }
                    for (Album a : UserController.user.getAlbums()){
                        if (a.albumName.equals(name)){
                            a.getAllPhotos().add(currentAlbum.getAllPhotos().get(index));
                            currentAlbum.getAllPhotos().remove(index);

                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Success");
                              alert.setHeaderText(null);
                               alert.setContentText("Photo has succesfully been moved to " + a.albumName + ".") ;
                               alert.showAndWait();
                               movePhotoClicked = false;
                               try {
                                Admin.WritetoFile();
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                            changePage();
                            return;

                        }
                    }
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Album not found");
                    alert.setHeaderText(null);
                    alert.setContentText("no album with this name found.");
                    alert.showAndWait();
                    movePhotoClicked = false;


            });

        }
        else if (displayPhotoClicked){
            Integer curPage = pageSelector.getValue(); 
            int index = 0;
            if (curPage ==1) index =3; if (curPage ==2) index =9; if (curPage ==3) index =15; if (curPage ==4) index =21;
            if (curPage ==5) index =27; if (curPage ==6) index =33; if (curPage ==7) index =39; if (curPage ==8) index =45;
            if (curPage ==9) index =51; if (curPage ==10) index =57;
            Photo tempPhoto = currentAlbum.getAllPhotos().get(index);
            PhotoController.DisplayPhoto = tempPhoto;
            try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/PhotoView.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Photo View");
            stage.show();
            displayPhotoClicked = false;
            }
            catch (IOException e){
                e.printStackTrace();
            }

        }
        
    }
    @FXML
    public void img5clicked(){
        if (img5.getImage().equals(null)){
            return;
         }
        if (captionClicked) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add Caption");
            dialog.setHeaderText("Enter the caption for this image: ");
            dialog.setContentText("Caption:");
    
            dialog.showAndWait().ifPresent(caption -> {
                Integer curPage = pageSelector.getValue(); 
                int index = 0;
                if (curPage ==1) index =4; if (curPage ==2) index =10; if (curPage ==3) index =16; if (curPage ==4) index =22;
                if (curPage ==5) index =28; if (curPage ==6) index =34; if (curPage ==7) index =40; if (curPage ==8) index =46;
                if (curPage ==9) index =52; if (curPage ==10) index =58;

                currentAlbum.getAllPhotos().get(index).editCaption(caption);
                try {
                    Admin.WritetoFile();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                changePage();
                captionClicked = false;
            });
        }
        else if (addTagClicked){
            ArrayList<String> choices = new ArrayList<>();
    
            choices.add("name");
            choices.add("location");
            Integer curPage = pageSelector.getValue(); 
                int index = 0;
                if (curPage ==1) index =4; if (curPage ==2) index =10; if (curPage ==3) index =16; if (curPage ==4) index =22;
                if (curPage ==5) index =28; if (curPage ==6) index =34; if (curPage ==7) index =40; if (curPage ==8) index =46;
                if (curPage ==9) index =52; if (curPage ==10) index =58;
                for (Tag temp : currentAlbum.getAllPhotos().get(index).listofTags ){
                if (!(Tag.getTagName(temp.getTag()).equalsIgnoreCase("location") || Tag.getTagName(temp.getTag()).equalsIgnoreCase("name") ))
                    choices.add(Tag.getTagName(temp.getTag()));
                }
                choices.add("new");
                ChoiceDialog<String> dialog = new ChoiceDialog<>("name", choices);
                dialog.setTitle("Add Tag");
                dialog.setHeaderText("Choose a tag type or select 'new' to add a new tag type");
                dialog.setContentText("Type:");
                dialog.showAndWait().ifPresent(type ->{
                    if (type.equals("new")){

                        TextInputDialog d = new TextInputDialog();
                        d.setTitle("Add Tag");
                        d.setHeaderText("Enter a new type");
                        d.setContentText("Tag Type:");
                        d.showAndWait().ifPresent(n ->{
                            if (n.isBlank()){
                                Alert successAlert = new Alert(Alert.AlertType.ERROR);
                                successAlert.setTitle("Error");
                                successAlert.setHeaderText(null);
                                successAlert.setContentText("No Tag Type entered.");
                                successAlert.showAndWait();
                                return;

                            }

                            if (!choices.contains(n)){
                                choices.add(n);
                                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                                successAlert.setTitle("Success");
                                successAlert.setHeaderText(null);
                                successAlert.setContentText("Tag Type has been added.");
                                successAlert.showAndWait();
                                TextInputDialog dial = new TextInputDialog();
                                dial.setTitle("Add Tag");
                                dial.setHeaderText("Enter a Value");
                                dial.setContentText("Tag Value:");
                                dial.showAndWait().ifPresent(v ->{
                                    if (v.isBlank()){
                                        Alert fail = new Alert(Alert.AlertType.ERROR);
                                        fail.setTitle("Error");
                                        fail.setHeaderText(null);
                                        fail.setContentText("No Tag Type entered.");
                                        fail.showAndWait();
                                     return;

                                    }
                                    Tag tag1 = new Tag(n, v);
                                    int i = 0;
                                    if (curPage ==1) i =4; if (curPage ==2) i =10; if (curPage ==3) i =16; if (curPage ==4) i =22;
                                    if (curPage ==5) i =28; if (curPage ==6) i =34; if (curPage ==7) i =40; if (curPage ==8) i =46;
                                    if (curPage ==9) i =52; if (curPage ==10) i =58;
                            currentAlbum.getAllPhotos().get(i).listofTags.add(tag1);
                            Alert sAl = new Alert(Alert.AlertType.INFORMATION);
                            sAl.setTitle("Success");
                            sAl.setHeaderText(null);
                            sAl.setContentText("Tag has been successfully added.");
                            sAl.showAndWait();
                            addTagClicked = false;
                            try {
                                Admin.WritetoFile();
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                            return;

                                });


                            }
                            else{
                                Alert successAlert = new Alert(Alert.AlertType.ERROR);
                                successAlert.setTitle("Error");
                                successAlert.setHeaderText(null);
                                successAlert.setContentText("Tag Type already in list");
                                successAlert.showAndWait();
                                return;
                            }
                           

                        });

                    }
                    else{
                    TextInputDialog d = new TextInputDialog();
                    d.setTitle("Add Tag");
                    d.setHeaderText("Enter a Value");
                    d.setContentText("Tag Value:");
                    d.showAndWait().ifPresent(value ->{
                        if (value.isBlank()){
                            Alert fail = new Alert(Alert.AlertType.ERROR);
                            fail.setTitle("Error");
                            fail.setHeaderText(null);
                            fail.setContentText("No Tag Type entered.");
                            fail.showAndWait();
                         return;

                        }
                        
                        Tag temptag = new Tag(type, value);
                        int i = 0;
                        if (curPage ==1) i =4; if (curPage ==2) i =10; if (curPage ==3) i =16; if (curPage ==4) i =22;
                        if (curPage ==5) i =28; if (curPage ==6) i =34; if (curPage ==7) i =40; if (curPage ==8) i =46;
                        if (curPage ==9) i =52; if (curPage ==10) i =58;
                        for (Tag temp : currentAlbum.getAllPhotos().get(i).listofTags ){
                            if (temptag.getTag().equals(temp.getTag())){
                                Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Duplicate Tag");
                    alert.setHeaderText(null);
                    alert.setContentText("Tag Already exists for this image.");
                    alert.showAndWait();
                    return;
                            }
                    }

                    for (Tag temp : currentAlbum.getAllPhotos().get(i).listofTags ){
                        if (Tag.getTagName(temp.getTag()).equals("location")){ 
                            currentAlbum.getAllPhotos().get(i).listofTags.remove(temp);
                            break;
                        };
                    }
                    currentAlbum.getAllPhotos().get(i).listofTags.add(temptag);
                  
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Success");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Tag has been successfully added.");
                    successAlert.showAndWait();
                    addTagClicked = false;
                    try {
                        Admin.WritetoFile();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }

                    });
                }
                    
                });
        }

        else if (deleteTagClicked){
            ArrayList<String> choices = new ArrayList<>();

            Integer curPage = pageSelector.getValue(); 
            int index = 0;
            if (curPage ==1) index =4; if (curPage ==2) index =10; if (curPage ==3) index =16; if (curPage ==4) index =22;
            if (curPage ==5) index =28; if (curPage ==6) index =34; if (curPage ==7) index =40; if (curPage ==8) index =46;
            if (curPage ==9) index =52; if (curPage ==10) index =58;
            for (Tag temp : currentAlbum.getAllPhotos().get(index).listofTags ){
                    choices.add(temp.getTag());
                }
            if (choices.size() ==0){
                Alert successAlert = new Alert(Alert.AlertType.ERROR);
                successAlert.setTitle("Error");
                successAlert.setHeaderText(null);
                successAlert.setContentText("This photo does not have any tags");
                successAlert.showAndWait();
                return;

            }
            ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
            dialog.setTitle("Delete Tag");
            dialog.setHeaderText("Choose a tag to delete.");
            dialog.setContentText("Tag: ");
            dialog.showAndWait().ifPresent(type ->{
                int a = 0;
                if (curPage ==1) a =4; if (curPage ==2) a =10; if (curPage ==3) a =16; if (curPage ==4) a =22;
            if (curPage ==5) a =28; if (curPage ==6) a =34; if (curPage ==7) a =40; if (curPage ==8) a =46;
            if (curPage ==9) a =52; if (curPage ==10) a =58;
                for (Tag temp : currentAlbum.getAllPhotos().get(a).listofTags){
                    if (temp.getTag().equals(type)){
                        currentAlbum.getAllPhotos().get(a).listofTags.remove(temp);
                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                        successAlert.setTitle("Sucess");
                        successAlert.setHeaderText(null);
                        successAlert.setContentText("Tag has been succesfully removed.");
                        successAlert.showAndWait();
                        deleteTagClicked = false;

                        try {
                            Admin.WritetoFile();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        return;

                    }

                }
                Alert successAlert = new Alert(Alert.AlertType.ERROR);
                successAlert.setTitle("Error");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Tag not found.");
                successAlert.showAndWait();

            });

        }
        else if (deletePhotoClicked){
            
        
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("Delete Photo");
            confirmationAlert.setContentText("Are you sure you want to delete this photo?");
            confirmationAlert.showAndWait().ifPresent(result -> {
                if (result == ButtonType.OK) {  
                    Integer curPage = pageSelector.getValue(); 
                    int index = 0;
                    if (curPage ==1) index =4; if (curPage ==2) index =10; if (curPage ==3) index =16; if (curPage ==4) index =22;
                    if (curPage ==5) index =28; if (curPage ==6) index =34; if (curPage ==7) index =40; if (curPage ==8) index =46;
                    if (curPage ==9) index =52; if (curPage ==10) index =58;
                    currentAlbum.getAllPhotos().remove(index);
                    changePage();
                    try {
                        Admin.WritetoFile();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    deletePhotoClicked = false;
                } else {
                    deletePhotoClicked = false;
                    return;
                }
            });
        }

        else if (copyPhotoClicked){
            ArrayList<String> choices = new ArrayList<>();
            for (Album a : UserController.user.getAlbums()){
                if (a.albumName != currentAlbum.albumName)   choices.add(a.albumName);
            }
            ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
            dialog.setTitle("Copy Photo");
            dialog.setHeaderText("Choose an Album to copy this photo to.");
            dialog.setContentText("Album: ");
            dialog.showAndWait().ifPresent(name ->{
          
                

                Integer curPage = pageSelector.getValue(); 
                    int index = 0;
                    if (curPage ==1) index =4; if (curPage ==2) index =10; if (curPage ==3) index =16; if (curPage ==4) index =22;
                    if (curPage ==5) index =28; if (curPage ==6) index =34; if (curPage ==7) index =40; if (curPage ==8) index =46;
                    if (curPage ==9) index =52; if (curPage ==10) index =58;
                    for (Album a: UserController.user.getAlbums()){
                        if (a.albumName.equals(name)){
                            for ( Photo p: a.getAllPhotos() ){
                                if (p.path.equals(currentAlbum.getAllPhotos().get(index).path)){

                                    Alert alert = new Alert(AlertType.ERROR);
                                      alert.setTitle("Error");
                                      alert.setHeaderText(null);
                                          alert.setContentText("Photo is already present in that album.");
                                         alert.showAndWait();
                                         return;
                                }
                            }
                        }
                    }
                    for (Album a : UserController.user.getAlbums()){
                        if (a.albumName.equals(name)){
                            a.getAllPhotos().add(currentAlbum.getAllPhotos().get(index));
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Success");
                              alert.setHeaderText(null);
                               alert.setContentText("Photo has succesfully been copied to " + a.albumName + ".") ;
                               alert.showAndWait();
                               copyPhotoClicked = false;
                               try {
                                Admin.WritetoFile();
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                            return;

                        }
                    }
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Album not found");
                    alert.setHeaderText(null);
                    alert.setContentText("no album with this name found.");
                    alert.showAndWait();
                    copyPhotoClicked = false;


            });
           
        }
        else if (movePhotoClicked){
            ArrayList<String> choices = new ArrayList<>();
            for (Album a : UserController.user.getAlbums()){
                if (a.albumName != currentAlbum.albumName)   choices.add(a.albumName);
            }
            ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
            dialog.setTitle("Move Photo");
            dialog.setHeaderText("Choose an Album to move this photo to.");
            dialog.setContentText("Album: ");
            dialog.showAndWait().ifPresent(name ->{
                Integer curPage = pageSelector.getValue(); 
                    int index = 0;
                    if (curPage ==1) index =4; if (curPage ==2) index =10; if (curPage ==3) index =16; if (curPage ==4) index =22;
                    if (curPage ==5) index =28; if (curPage ==6) index =34; if (curPage ==7) index =40; if (curPage ==8) index =46;
                    if (curPage ==9) index =52; if (curPage ==10) index =58;
                    for (Album a: UserController.user.getAlbums()){
                        if (a.albumName.equals(name)){
                            for ( Photo p: a.getAllPhotos() ){
                                if (p.path.equals(currentAlbum.getAllPhotos().get(index).path)){

                                    Alert alert = new Alert(AlertType.ERROR);
                                      alert.setTitle("Error");
                                      alert.setHeaderText(null);
                                          alert.setContentText("Photo is already present in that album.");
                                         alert.showAndWait();
                                         return;
                                }
                            }
                        }
                    }
                    for (Album a : UserController.user.getAlbums()){
                        if (a.albumName.equals(name)){
                            a.getAllPhotos().add(currentAlbum.getAllPhotos().get(index));
                            currentAlbum.getAllPhotos().remove(index);

                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Success");
                              alert.setHeaderText(null);
                               alert.setContentText("Photo has succesfully been moved to " + a.albumName + ".") ;
                               alert.showAndWait();
                               movePhotoClicked = false;
                               try {
                                Admin.WritetoFile();
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                            changePage();
                            return;

                        }
                    }
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Album not found");
                    alert.setHeaderText(null);
                    alert.setContentText("no album with this name found.");
                    alert.showAndWait();
                    movePhotoClicked = false;


            });

        }
        else if (displayPhotoClicked){
            Integer curPage = pageSelector.getValue(); 
            int index = 0;
            if (curPage ==1) index =4; if (curPage ==2) index =10; if (curPage ==3) index =16; if (curPage ==4) index =22;
            if (curPage ==5) index =28; if (curPage ==6) index =34; if (curPage ==7) index =40; if (curPage ==8) index =46;
            if (curPage ==9) index =52; if (curPage ==10) index =58;
            Photo tempPhoto = currentAlbum.getAllPhotos().get(index);
            PhotoController.DisplayPhoto = tempPhoto;
            try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/PhotoView.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Photo View");
            stage.show();
            displayPhotoClicked = false;
            }
            catch (IOException e){
                e.printStackTrace();
            }

        }
        
    }
    @FXML
    public void img6clicked(){
        if (img6.getImage().equals(null)){
            return;
         }
        if (captionClicked) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add Caption");
            dialog.setHeaderText("Enter the caption for this image: ");
            dialog.setContentText("Caption:");
    
            dialog.showAndWait().ifPresent(caption -> {
                Integer curPage = pageSelector.getValue(); 
                int index = 0;
                if (curPage ==1) index =5; if (curPage ==2) index =11; if (curPage ==3) index =17; if (curPage ==4) index =23;
                if (curPage ==5) index =29; if (curPage ==6) index =35; if (curPage ==7) index =41; if (curPage ==8) index =47;
                if (curPage ==9) index =53; if (curPage ==10) index =59;

                currentAlbum.getAllPhotos().get(index).editCaption(caption);
                try {
                    Admin.WritetoFile();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                changePage();
                captionClicked = false;
            });
        }
        else if (addTagClicked){
            ArrayList<String> choices = new ArrayList<>();
    
            choices.add("name");
            choices.add("location");
            Integer curPage = pageSelector.getValue(); 
                int index = 0;
                if (curPage ==1) index =5; if (curPage ==2) index =11; if (curPage ==3) index =17; if (curPage ==4) index =23;
                if (curPage ==5) index =29; if (curPage ==6) index =35; if (curPage ==7) index =41; if (curPage ==8) index =47;
                if (curPage ==9) index =53; if (curPage ==10) index =59;
                for (Tag temp : currentAlbum.getAllPhotos().get(index).listofTags ){
                if (!(Tag.getTagName(temp.getTag()).equalsIgnoreCase("location") || Tag.getTagName(temp.getTag()).equalsIgnoreCase("name") ))
                    choices.add(Tag.getTagName(temp.getTag()));
                }
                choices.add("new");
                ChoiceDialog<String> dialog = new ChoiceDialog<>("name", choices);
                dialog.setTitle("Add Tag");
                dialog.setHeaderText("Choose a tag type or select 'new' to add a new tag type");
                dialog.setContentText("Type:");
                dialog.showAndWait().ifPresent(type ->{
                    if (type.equals("new")){

                        TextInputDialog d = new TextInputDialog();
                        d.setTitle("Add Tag");
                        d.setHeaderText("Enter a new type");
                        d.setContentText("Tag Type:");
                        d.showAndWait().ifPresent(n ->{
                            if (n.isBlank()){
                                Alert successAlert = new Alert(Alert.AlertType.ERROR);
                                successAlert.setTitle("Error");
                                successAlert.setHeaderText(null);
                                successAlert.setContentText("No Tag Type entered.");
                                successAlert.showAndWait();
                                return;

                            }

                            if (!choices.contains(n)){
                                choices.add(n);
                                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                                successAlert.setTitle("Success");
                                successAlert.setHeaderText(null);
                                successAlert.setContentText("Tag Type has been added.");
                                successAlert.showAndWait();
                                TextInputDialog dial = new TextInputDialog();
                                dial.setTitle("Add Tag");
                                dial.setHeaderText("Enter a Value");
                                dial.setContentText("Tag Value:");
                                dial.showAndWait().ifPresent(v ->{
                                    if (v.isBlank()){
                                        Alert fail = new Alert(Alert.AlertType.ERROR);
                                        fail.setTitle("Error");
                                        fail.setHeaderText(null);
                                        fail.setContentText("No Tag Type entered.");
                                        fail.showAndWait();
                                     return;

                                    }
                                    Tag tag1 = new Tag(n, v);
                                    int i = 0;
                                    if (curPage ==1) i =5; if (curPage ==2) i =11; if (curPage ==3) i =17; if (curPage ==4) i =23;
                                    if (curPage ==5) i =29; if (curPage ==6) i =35; if (curPage ==7) i =41; if (curPage ==8) i =47;
                                    if (curPage ==9) i =53; if (curPage ==10) i =59;
                            currentAlbum.getAllPhotos().get(i).listofTags.add(tag1);
                            Alert sAl = new Alert(Alert.AlertType.INFORMATION);
                            sAl.setTitle("Success");
                            sAl.setHeaderText(null);
                            sAl.setContentText("Tag has been successfully added.");
                            sAl.showAndWait();
                            addTagClicked = false;
                            try {
                                Admin.WritetoFile();
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                            return;

                                });


                            }
                            else{
                                Alert successAlert = new Alert(Alert.AlertType.ERROR);
                                successAlert.setTitle("Error");
                                successAlert.setHeaderText(null);
                                successAlert.setContentText("Tag Type already in list");
                                successAlert.showAndWait();
                                return;
                            }
                           

                        });

                    }
                    else{
                    TextInputDialog d = new TextInputDialog();
                    d.setTitle("Add Tag");
                    d.setHeaderText("Enter a Value");
                    d.setContentText("Tag Value:");
                    d.showAndWait().ifPresent(value ->{
                        if (value.isBlank()){
                            Alert fail = new Alert(Alert.AlertType.ERROR);
                            fail.setTitle("Error");
                            fail.setHeaderText(null);
                            fail.setContentText("No Tag Type entered.");
                            fail.showAndWait();
                         return;

                        }
                        
                        Tag temptag = new Tag(type, value);
                        int i = 0;
                        if (curPage ==1) i =5; if (curPage ==2) i =11; if (curPage ==3) i =17; if (curPage ==4) i =23;
                        if (curPage ==5) i =29; if (curPage ==6) i =35; if (curPage ==7) i =41; if (curPage ==8) i =47;
                        if (curPage ==9) i =53; if (curPage ==10) i =59;
                        for (Tag temp : currentAlbum.getAllPhotos().get(i).listofTags ){
                            if (temptag.getTag().equals(temp.getTag())){
                                Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Duplicate Tag");
                    alert.setHeaderText(null);
                    alert.setContentText("Tag Already exists for this image.");
                    alert.showAndWait();
                    return;
                            }
                    }

                    for (Tag temp : currentAlbum.getAllPhotos().get(i).listofTags ){
                        if (Tag.getTagName(temp.getTag()).equals("location")){ 
                            currentAlbum.getAllPhotos().get(i).listofTags.remove(temp);
                            break;
                        };
                    }
                    currentAlbum.getAllPhotos().get(i).listofTags.add(temptag);
                  
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Success");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Tag has been successfully added.");
                    successAlert.showAndWait();
                    addTagClicked = false;
                    try {
                        Admin.WritetoFile();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }

                    });
                }
                    
                });
        }

        else if (deleteTagClicked){
            ArrayList<String> choices = new ArrayList<>();

            Integer curPage = pageSelector.getValue(); 
            int index = 0;
            if (curPage ==1) index =5; if (curPage ==2) index =11; if (curPage ==3) index =17; if (curPage ==4) index =23;
            if (curPage ==5) index =29; if (curPage ==6) index =35; if (curPage ==7) index =41; if (curPage ==8) index =47;
            if (curPage ==9) index =53; if (curPage ==10) index =59;
            for (Tag temp : currentAlbum.getAllPhotos().get(index).listofTags ){
                    choices.add(temp.getTag());
                }
            if (choices.size() ==0){
                Alert successAlert = new Alert(Alert.AlertType.ERROR);
                successAlert.setTitle("Error");
                successAlert.setHeaderText(null);
                successAlert.setContentText("This photo does not have any tags");
                successAlert.showAndWait();
                return;

            }
            ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
            dialog.setTitle("Delete Tag");
            dialog.setHeaderText("Choose a tag to delete.");
            dialog.setContentText("Tag: ");
            dialog.showAndWait().ifPresent(type ->{
                int a = 0;
                if (curPage ==1) a =5; if (curPage ==2) a =11; if (curPage ==3) a =17; if (curPage ==4) a =23;
            if (curPage ==5) a =29; if (curPage ==6) a =35; if (curPage ==7) a =41; if (curPage ==8) a =47;
            if (curPage ==9) a =53; if (curPage ==10) a =59;
                for (Tag temp : currentAlbum.getAllPhotos().get(a).listofTags){
                    if (temp.getTag().equals(type)){
                        currentAlbum.getAllPhotos().get(a).listofTags.remove(temp);
                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                        successAlert.setTitle("Sucess");
                        successAlert.setHeaderText(null);
                        successAlert.setContentText("Tag has been succesfully removed.");
                        successAlert.showAndWait();
                        deleteTagClicked = false;

                        try {
                            Admin.WritetoFile();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        return;

                    }

                }
                Alert successAlert = new Alert(Alert.AlertType.ERROR);
                successAlert.setTitle("Error");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Tag not found.");
                successAlert.showAndWait();

            });

        }
        else if (deletePhotoClicked){
            
        
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("Delete Photo");
            confirmationAlert.setContentText("Are you sure you want to delete this photo?");
            confirmationAlert.showAndWait().ifPresent(result -> {
                if (result == ButtonType.OK) {  
                    Integer curPage = pageSelector.getValue(); 
                    int index = 0;
                    if (curPage ==1) index =5; if (curPage ==2) index =11; if (curPage ==3) index =17; if (curPage ==4) index =23;
                    if (curPage ==5) index =29; if (curPage ==6) index =35; if (curPage ==7) index =41; if (curPage ==8) index =47;
                    if (curPage ==9) index =53; if (curPage ==10) index =59;
                    currentAlbum.getAllPhotos().remove(index);
                    changePage();
                    try {
                        Admin.WritetoFile();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    deletePhotoClicked = false;
                } else {
                    deletePhotoClicked = false;
                    return;
                }
            });
        }

        else if (copyPhotoClicked){
            ArrayList<String> choices = new ArrayList<>();
            for (Album a : UserController.user.getAlbums()){
                if (a.albumName != currentAlbum.albumName)   choices.add(a.albumName);
            }
            ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
            dialog.setTitle("Copy Photo");
            dialog.setHeaderText("Choose an Album to copy this photo to.");
            dialog.setContentText("Album: ");
            dialog.showAndWait().ifPresent(name ->{
          
                

                Integer curPage = pageSelector.getValue(); 
                    int index = 0;
                    if (curPage ==1) index =5; if (curPage ==2) index =11; if (curPage ==3) index =17; if (curPage ==4) index =23;
                    if (curPage ==5) index =29; if (curPage ==6) index =35; if (curPage ==7) index =41; if (curPage ==8) index =47;
                    if (curPage ==9) index =53; if (curPage ==10) index =59;
                    for (Album a: UserController.user.getAlbums()){
                        if (a.albumName.equals(name)){
                            for ( Photo p: a.getAllPhotos() ){
                                if (p.path.equals(currentAlbum.getAllPhotos().get(index).path)){

                                    Alert alert = new Alert(AlertType.ERROR);
                                      alert.setTitle("Error");
                                      alert.setHeaderText(null);
                                          alert.setContentText("Photo is already present in that album.");
                                         alert.showAndWait();
                                         return;
                                }
                            }
                        }
                    }
                    for (Album a : UserController.user.getAlbums()){
                        if (a.albumName.equals(name)){
                            a.getAllPhotos().add(currentAlbum.getAllPhotos().get(index));
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Success");
                              alert.setHeaderText(null);
                               alert.setContentText("Photo has succesfully been copied to " + a.albumName + ".") ;
                               alert.showAndWait();
                               copyPhotoClicked = false;
                               try {
                                Admin.WritetoFile();
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                            return;

                        }
                    }
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Album not found");
                    alert.setHeaderText(null);
                    alert.setContentText("no album with this name found.");
                    alert.showAndWait();
                    copyPhotoClicked = false;


            });
           
        }
        else if (movePhotoClicked){
            ArrayList<String> choices = new ArrayList<>();
            for (Album a : UserController.user.getAlbums()){
                if (a.albumName != currentAlbum.albumName)   choices.add(a.albumName);
            }
            ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
            dialog.setTitle("Move Photo");
            dialog.setHeaderText("Choose an Album to move this photo to.");
            dialog.setContentText("Album: ");
            dialog.showAndWait().ifPresent(name ->{
                Integer curPage = pageSelector.getValue(); 
                    int index = 0;
                    if (curPage ==1) index =5; if (curPage ==2) index =11; if (curPage ==3) index =17; if (curPage ==4) index =23;
                if (curPage ==5) index =29; if (curPage ==6) index =35; if (curPage ==7) index =41; if (curPage ==8) index =47;
                if (curPage ==9) index =53; if (curPage ==10) index =59;
                    for (Album a: UserController.user.getAlbums()){
                        if (a.albumName.equals(name)){
                            for ( Photo p: a.getAllPhotos() ){
                                if (p.path.equals(currentAlbum.getAllPhotos().get(index).path)){

                                    Alert alert = new Alert(AlertType.ERROR);
                                      alert.setTitle("Error");
                                      alert.setHeaderText(null);
                                          alert.setContentText("Photo is already present in that album.");
                                         alert.showAndWait();
                                         return;
                                }
                            }
                        }
                    }
                    for (Album a : UserController.user.getAlbums()){
                        if (a.albumName.equals(name)){
                            a.getAllPhotos().add(currentAlbum.getAllPhotos().get(index));
                            currentAlbum.getAllPhotos().remove(index);

                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Success");
                              alert.setHeaderText(null);
                               alert.setContentText("Photo has succesfully been moved to " + a.albumName + ".") ;
                               alert.showAndWait();
                               movePhotoClicked = false;
                               try {
                                Admin.WritetoFile();
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                            changePage();
                            return;

                        }
                    }
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Album not found");
                    alert.setHeaderText(null);
                    alert.setContentText("no album with this name found.");
                    alert.showAndWait();
                    movePhotoClicked = false;


            });

        }
        else if (displayPhotoClicked){
            Integer curPage = pageSelector.getValue(); 
            int index = 0;
            if (curPage ==1) index =5; if (curPage ==2) index =11; if (curPage ==3) index =17; if (curPage ==4) index =23;
            if (curPage ==5) index =29; if (curPage ==6) index =35; if (curPage ==7) index =41; if (curPage ==8) index =47;
            if (curPage ==9) index =53; if (curPage ==10) index =59;
            Photo tempPhoto = currentAlbum.getAllPhotos().get(index);
            PhotoController.DisplayPhoto = tempPhoto;
            try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/PhotoView.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Photo View");
            stage.show();
            displayPhotoClicked = false;
            }
            catch (IOException e){
                e.printStackTrace();
            }

        }
    }
}
