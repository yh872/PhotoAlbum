package photo.Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SlideshowController {

    private static int curIndex = 0;
    @FXML
    private ImageView image;
    @FXML
    private Button endSlideshowButton;
    @FXML
    private Button backButton;
    @FXML
    private Button nextButton;


    @FXML
    private void initialize() throws FileNotFoundException {
        curIndex = 0;
        String path = AlbumController.currentAlbum.getAllPhotos().get(0).path;
      
        InputStream stream = new FileInputStream(path);
        Image img = new Image(stream);
        image.setImage(img);
        backButton.setDisable(true);
        if (AlbumController.currentAlbum.getAllPhotos().size() ==1){
            nextButton.setDisable(true);
        }
    
    }

    @FXML
    private void goBack() throws FileNotFoundException{
        curIndex--;
        if (curIndex == 0){
            backButton.setDisable(true);

        }
        nextButton.setDisable(false);
        String path = AlbumController.currentAlbum.getAllPhotos().get(curIndex).path;
      
        InputStream stream = new FileInputStream(path);
        Image img = new Image(stream);
        image.setImage(img);

       


    }
    @FXML
    private void goNext() throws FileNotFoundException{
        curIndex++; 
        if (curIndex + 1 == AlbumController.currentAlbum.getAllPhotos().size()){
            nextButton.setDisable(true);
        }
        if (curIndex >0){
            backButton.setDisable(false);
        }
        String path = AlbumController.currentAlbum.getAllPhotos().get(curIndex).path;
      
        InputStream stream = new FileInputStream(path);
        Image img = new Image(stream);
        image.setImage(img);

        
    }
    @FXML
    private void endSlideshow(ActionEvent event){
        try{

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/AlbumView.fxml"));
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
