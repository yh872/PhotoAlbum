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
    public Photo(String path){
        this.path = path;
        listofTags = new ArrayList<>();
    }
    public void editCaption(String s){
        caption = s;
    }
    public void addTag(Tag t) throws Exception{
        for (int i = 0; i < listofTags.size(); i++){
            if (t.equals(listofTags.get(i))){
                throw new Exception("Tag already present");
            }
        }
        listofTags.add(t);
    }
    public void AddAlbum(Album a){
        listofAlbums.add(a);
    }
    public void movePhoto(Album a, Album b){
        listofAlbums.remove(a);
        listofAlbums.add(b);
    }
    
    
}
