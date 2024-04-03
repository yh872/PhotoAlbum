package photo.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
import javafx.scene.control.ChoiceBox;
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

public class AlbumController{
    public static boolean isStock = false;
    public static Album currentAlbum = null;
    public static boolean captionClicked = false;
    public static boolean addTagClicked = false;
    public static boolean deleteTagClicked = false;
    public static boolean deletePhotoClicked = false;
    public static boolean copyPhotoClicked = false;
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

    @FXML private Text caption1; @FXML private Text caption2; @FXML private Text caption3;
    @FXML private Text caption4; @FXML private Text caption5; @FXML private Text caption6;

    

        @FXML
    public void initialize() throws FileNotFoundException{
        captionClicked = false;
        addTagClicked = false;
         deleteTagClicked = false;
        deletePhotoClicked = false;
      copyPhotoClicked = false;
        ObservableList<Integer> items = FXCollections.observableArrayList();
        items.addAll(1,2,3,4,5,6,7,8,9,10);
        pageSelector.setItems(items);
        pageSelector.setValue(1);
        if (isStock){
            try{
                
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
    }

    @FXML
    private void addTag() {
        captionClicked = false;
        addTagClicked = true;
         deleteTagClicked = false;
        deletePhotoClicked = false;
      copyPhotoClicked = false;
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
/* 
            ImageView[] imageViews = {img1, img2, img3, img4, img5, img6};
            for (ImageView imageView : imageViews) {
                if (imageView.getImage() == null) {
                    // Set the image to the empty ImageView
                    try (InputStream inputStream = new FileInputStream(selectedFile)) {
                        Image image = new Image(inputStream);
                        imageView.setImage(image);
                        break; // Exit the loop after setting the image
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
            }
    */
            // Add the photo to the album
            Photo p = new Photo(selectedFile.getAbsolutePath());
            p.date = calendar;
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
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Delete Photo");
      alert.setHeaderText(null);
      alert.setContentText("Please click on an image to delete it from the album.");
      alert.showAndWait();
    }

    @FXML
    private void copyPhoto() {
        captionClicked = false;
        addTagClicked = false;
         deleteTagClicked = false;
        deletePhotoClicked = false;
      copyPhotoClicked = true;
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Copy photo");
      alert.setHeaderText(null);
      alert.setContentText("Please click on the image you would like to copy to another album.");
      alert.showAndWait();
    }

    @FXML

    private void executeSlideshow(){

    }
    @FXML

    private void displayPhoto(){

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
            dialog.setHeaderText("Enter the caption for Image 1:");
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
       
    }
    @FXML
    public void img2clicked(){
        
    }
    @FXML
    public void img3clicked(){
        
    }
    @FXML
    public void img4clicked(){
        
    }
    @FXML
    public void img5clicked(){
        
    }
    @FXML
    public void img6clicked(){
        
    }
}
