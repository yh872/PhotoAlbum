package photo.Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import photo.Model.Photo;
import photo.Model.Tag;

public class PhotoController {
    public static Photo DisplayPhoto = null;

    @FXML 
    private ImageView image;
    @FXML 
    private Text date;
    @FXML 
    private Text caption;
    @FXML
    private ListView<String> tags;
  

    @FXML
    private void initialize() throws FileNotFoundException{
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String formattedDate = sdf.format(DisplayPhoto.date.getTime());
        date.setText(formattedDate);
        String path = DisplayPhoto.path;
        if (AlbumController.isStock){
            if (path.equals("data/manStockphoto.jpg")){
                caption.setText("Business man");
            }
            else if (path.equals("data/basketballStock.jpg")){
                caption.setText("Basketball player");
            }
            else if (path.equals("data/catStockPhoto.jpg")){
                caption.setText("A curious cat");
            }
            else if (path.equals("data/FamilyStockPhoto.jpg")){
                caption.setText("A happy family");
            }
            else if (path.equals("data/DanceStockPhoto.jpg")){
                caption.setText("Hip Hop dancers");
            }
            else if (path.equals("data/IslandStockPhoto.jpg")){
                caption.setText("Tropical island");
         
        }
    }
        InputStream stream = new FileInputStream(path);
        Image img = new Image(stream);
        image.setImage(img);
        if (AlbumController.isStock){
            return;
        }
       caption.setText(DisplayPhoto.caption);
       ObservableList<String> items = FXCollections.observableArrayList();
       for (Tag t: DisplayPhoto.listofTags){
        if (Tag.getTagValue(t.getTag()).isEmpty()){
            continue;

        }
        items.add(t.getTag());
       }
       tags.setItems(items);

    }

    @FXML
    private void goBack(ActionEvent event){
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
