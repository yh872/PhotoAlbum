package photo.Model;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable{

  private static final long serialVersionUID = 2L;
   
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

    public void setAlbumName(Album a, String s){
        for (Album album : listofAlbums ){
            if (album.equals(a)){
                album.albumName = s;
            }
        }
    }
    public void addAlbumFromResult(ArrayList<Photo> result, String albumname){
       Album a = new Album(albumname);
       a.listofPhotos = result; 
       listofAlbums.add(a);
    }
    public boolean containsAlbum(String a){
        for (Album album: listofAlbums){
            if (a.equals(album.albumName)){
                return true;
            }
        }
        return false;
    }
    public Album getAlbum(String s){
        for (Album a: listofAlbums){
            if (a.albumName.equals(s)){
                return a;
            }
        }
        return null;
    }
}

