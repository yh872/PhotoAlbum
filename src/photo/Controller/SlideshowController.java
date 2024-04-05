/**
 * 
 * This class is a controller for slideshow.fxml, the manual slideshow of all the images in an album
 * 
 * @author Youssef Hanna
 */


package photo.Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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

    /**
     * 
     * sets the first image of the slideshow and disables the back button
     * @throws FileNotFoundException
     */
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

 
    /**
     * handles user pressing the back button, sets the image as the previous image, and can potentially
     * enable the go next button
     * @throws FileNotFoundException
     */
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
    /**
     *  handles user pressing the next button, sets the image as the next image, and can potentially
     * enable the go back button
     * @throws FileNotFoundException
     */
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
    /**
     * kills the slideshow window and brings user back to the album screen
     * @param event event of the endslideshow button being clicked
     */
    @FXML
    private void endSlideshow(ActionEvent event){
        try{

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/AlbumView.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.close();
                
            }
            catch (IOException e){
                e.printStackTrace();
            }
        
    }
}
