/**
 * This class represents the Album 
 * Stores information about the users album, including the list of photos and the name of the album
 * 
 * 
 * 
 * 
 * 
 * @author Youssef Hanna
 */

package photo.Model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Album implements Serializable{
    public String albumName;
    ArrayList<Photo> listofPhotos = new ArrayList<>();

    /**
     * 
     * 
     * @param albumName the name of the album
     * creates a new album
     */
    public Album(String albumName){
        this.albumName = albumName;
    }
    /**
     * 
     * @param n the new name of the album
     * renames the album
     */
    public void renameAlbum(String n){
        albumName = n;

    }
    /**
     * 
     * @return returns the amount of photos in the album
     */
    public Integer numberOfPhotos(){
        return listofPhotos.size();
    }
    /**
     * 
     * @param p photo to be added to the album
     * adds a photo to the album
     */
    public void addPhoto(Photo p){
        listofPhotos.add(p);
    }
    /**
     * 
     * @param p photo to be removed from the album
     * removes a photo from the album
     */
    public void removePhoto(Photo p){
        listofPhotos.remove(p);
    }
   /**
    * 
    * @return returns the list of photos in the album
    */
    public ArrayList<Photo> getAllPhotos(){
        return listofPhotos;
    }
    /**
     * 
     * @return returns the earliest date of all the photos in the album
     * 
     */
    public String earliestDate(){
        if (listofPhotos.isEmpty()) return "";
        Calendar curMin = listofPhotos.get(0).date;
        for (Photo p: listofPhotos){
            if (p.date.before(curMin)){
                curMin = p.date;
            }
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
        String formattedDate = dateFormat.format(curMin.getTime());

        return formattedDate;
    }
    /**
     * 
     * @return returns the latest date of all the photos in the album
     */

    public String latestDate(){
        if (listofPhotos.isEmpty()) return "";
        Calendar curMin = listofPhotos.get(0).date;
        for (Photo p: listofPhotos){
            if (p.date.after(curMin)){
                curMin = p.date;
            }
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
        String formattedDate = dateFormat.format(curMin.getTime());

        return formattedDate;
    }
}

   
