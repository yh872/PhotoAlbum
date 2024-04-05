/**
 * 
 * 
 * 
 * @author Youssef Hanna
 */

package photo.Model;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable{

    static final long serialVersionUID = 2L;
    private String username;
    private ArrayList<Album> listofAlbums;

    public ArrayList<String> tagNames;



    /**
     * 
     * @param username the string representing the user's username
     * creates a new user object
     */
    public User(String username){
        this.username = username;
        listofAlbums = new ArrayList<>();
        tagNames = new ArrayList<>();

       
    }
    /**
     * 
     * @return returns the users username
     */
    public String getUsername(){
        return username;
    }
    /**
     * 
     * 
     * @param u username that will be set as the users new username
     */
    public void setUsername(String u){
        username = u;
    }
    /**
     * 
     * @return get all the albums belonging to this user
     */
    public ArrayList<Album> getAlbums(){
        return listofAlbums;
    }
    /**
     * 
     * @param Albumname name of the new album
     * create a new album and add it to the users albums
     */
    public void addAlbum(String Albumname){
        Album a= new Album(Albumname);
        listofAlbums.add(a);
    }
    /**
     * 
     * @param a album to be deleted
     * deletes the album from the list of albums
     */
    public void DeleteAlbum(Album a){
        listofAlbums.remove(a);
    }
    /**
     * 
     * @param a album to be name-changed
     * @param s new name of the album
     * changes the name of one of the users albums
     */
    public void setAlbumName(Album a, String s){
        for (Album album : listofAlbums ){
            if (album.equals(a)){
                album.albumName = s;
            }
        }
    }
    /**
     * 
     * @param a an album name 
     * @return returns if an album with that name exists in the users album list
     */
    public boolean containsAlbum(String a){
        for (Album album: listofAlbums){
            if (a.equals(album.albumName)){
                return true;
            }
        }
        return false;
    }
    /**
     * 
     * @param s name of the album
     * @return the album object with that album name
     */
    public Album getAlbum(String s){
        for (Album a: listofAlbums){
            if (a.albumName.equals(s)){
                return a;
            }
        }
        return null;
    }
}

