/**
 * This class represents a Photo 
 * Stores the path to photos on Users machine, tags, date of photo, and a caption.
 * 
 * 
 * 
 * 
 * 
 * @author Youssef Hanna
 */


package photo.Model;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.*;
public class Photo implements Serializable{
    public ArrayList<Album> listofAlbums;
    public ArrayList<Tag> listofTags;
    public Calendar date;
    public String path;
   public  String caption = "";
   /**
    * creates a new photo object
    * @param path path to the photo on the users machine
    */
    public Photo(String path){
        this.path = path;
        listofTags = new ArrayList<>();
    }
    /**
     * 
     * @param s the string tha the caption will be set to
     * sets the caption of the photo
     */
    public void editCaption(String s){
        caption = s;
    }
    /**
     * 
     * @param t represents tag being added to the listoftags arraylist
     * @throws Exception if the tag is already in  listoftags
     */
    public void addTag(Tag t) throws Exception{
        for (int i = 0; i < listofTags.size(); i++){
            if (t.equals(listofTags.get(i))){
                throw new Exception("Tag already present");
            }
        }
        listofTags.add(t);
    }
    /**
     * adds an album associated with the photo
     * @param a represents an album object
     */
    public void AddAlbum(Album a){
        listofAlbums.add(a);
    }
    /**
     * 
     * @param a current album the photo is associated with
     * @param b album the photo will be associated with
     * removes the photo from one album and adds it to another
     */
    public void movePhoto(Album a, Album b){
        listofAlbums.remove(a);
        listofAlbums.add(b);
    }
    
    
}
