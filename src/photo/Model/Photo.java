package photo.Model;

import java.util.ArrayList;
import java.util.*;
public class Photo{
    ArrayList<Album> listofAlbums;
    public ArrayList<Tag> listofTags;
    Calendar date;
    String caption;
    public Photo(Calendar date, String caption){
        this.date = date;
        date.set(Calendar.MILLISECOND, 0);
        this.caption = caption;
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
