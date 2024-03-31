package photo.Model;

import java.util.ArrayList;
import java.util.Calendar;

public class Album {
    String albumName;
    Photo thumbnail;
    ArrayList<Photo> listofPhotos = new ArrayList<>();

    public Album(String albumName){
        this.albumName = albumName;
    }
    public void renameAlbum(String n){
        albumName = n;

    }
    public int numberOfPhotos(){
        return listofPhotos.size();
    }
    public void addPhoto(Photo p){
        listofPhotos.add(p);
    }
    public void removePhoto(Photo p){
        listofPhotos.remove(p);
    }
    public Photo getThumbnail(){
        return thumbnail;
    }
    public void setThumbnail(Photo p){
        if (!listofPhotos.contains(p)){
            return;
        }
        thumbnail = p;
    }
    public ArrayList<Photo> getAllPhotos(){
        return listofPhotos;
    }
    public ArrayList<Photo> SearchbyDate(Calendar date1, Calendar date2){
        ArrayList<Photo> result = new ArrayList<>();
        for (int i = 0; i < listofPhotos.size(); i++){
            if (date1.get(Calendar.YEAR) <= listofPhotos.get(i).date.get(Calendar.YEAR) &&
            date2.get(Calendar.YEAR) >= listofPhotos.get(i).date.get(Calendar.YEAR) &&
            date1.get(Calendar.MONTH) <= listofPhotos.get(i).date.get(Calendar.MONTH) && 
            date2.get(Calendar.MONTH) >= listofPhotos.get(i).date.get(Calendar.MONTH) && 
            date1.get(Calendar.DAY_OF_MONTH) <= listofPhotos.get(i).date.get(Calendar.DAY_OF_MONTH) && 
            date2.get(Calendar.DAY_OF_MONTH) >= listofPhotos.get(i).date.get(Calendar.DAY_OF_MONTH)){
                result.add(listofPhotos.get(i));

            }
            
        }
        return result;
    }
    public ArrayList<Photo> SearchbyOneTag(Tag t){
        ArrayList<Photo> result = new ArrayList<>();
        for (int i = 0; i < listofPhotos.size(); i++){
            ArrayList<Tag> temp = listofPhotos.get(i).listofTags;
            if (temp.contains(t)){
                result.add(listofPhotos.get(i));
            }
        }
        return result;

    }
    public ArrayList<Photo> SearchbyTwoTagAND(Tag t1, Tag t2){
        ArrayList<Photo> result = new ArrayList<>();
        for (int i = 0; i < listofPhotos.size(); i++){
            ArrayList<Tag> temp = listofPhotos.get(i).listofTags;
            if (temp.contains(t1) && temp.contains(t2)){
                result.add(listofPhotos.get(i));
            }
        }
        return result;
    }
    public ArrayList<Photo> SearchbyTwoTagsOR(Tag t1, Tag t2){
        ArrayList<Photo> result = new ArrayList<>();
        for (int i = 0; i < listofPhotos.size(); i++){
            ArrayList<Tag> temp = listofPhotos.get(i).listofTags;
            if (temp.contains(t1) || temp.contains(t2)){
                result.add(listofPhotos.get(i));
            }
        }
        return result;
    }
}

   
