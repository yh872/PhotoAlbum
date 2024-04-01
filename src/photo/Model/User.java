package photo.Model;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable{
   
    private String username;
    private ArrayList<Album> listofAlbums;


    public User(String username){
        this.username = username;
        listofAlbums = new ArrayList<>();
       
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String u){
        username = u;
    }
    public ArrayList<Album> getAlbums(){
        return listofAlbums;
    }
    public void addAlbum(String Albumname){
        Album a= new Album(Albumname);
        listofAlbums.add(a);
    }
    public void DeleteAlbum(Album a){
        listofAlbums.remove(a);
    }
    public void addAlbumFromResult(ArrayList<Photo> result, String albumname){
       Album a = new Album(albumname);
       a.listofPhotos = result; 
       listofAlbums.add(a);
    }
}

