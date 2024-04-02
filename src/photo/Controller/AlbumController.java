package photo.Controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import photo.Model.Album;

public class AlbumController {
    public static boolean isStock = false;
    public static Album currentAlbum = null;
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
    private Pagination pagination;

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

        @FXML
    public void initialize(){
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

           pagination.setMaxPageIndicatorCount(1);
           pagination.setPageCount(1);

            }
            catch(IOException e){
                e.printStackTrace();
            }
        }

    }
    @FXML
    private void setCaption() {
       
    }

    @FXML
    private void addTag() {
       
    }

    @FXML
    private void deleteTag() {
       
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
    private void addPhoto() {
      
    }

    @FXML
    private void deletePhoto() {
       
    }

    @FXML
    private void copyPhoto() {
        
    }

    @FXML

    private void executeSlideshow(){

    }
    @FXML

    private void displayPhoto(){

    }
}
