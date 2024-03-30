package photo.Controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class StockImageController implements Initializable {

    @FXML
    private ImageView imageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            // Load the image using FileInputStream
            InputStream inputStream = new FileInputStream("/Users/youssefhanna/Downloads/manStockphoto.jpg");
            Image image = new Image(inputStream);

            // Set the image to the ImageView
            imageView.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

