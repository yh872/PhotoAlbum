/**
 * This class represents a tag 
 * Tag is an object with a name and a value, and is usually represented as a string in the format 'name=value'
 * 
 * 
 * 
 * 
 * 
 * @author Youssef Hanna
 */


package photo.Model;

import java.io.Serializable;

public class Tag implements Serializable{
   private String name;
   private String value;
    /**
     * creates a new tag object
     * @param name the name of the tag
     * @param value the value of the tag
     */
   public Tag(String name, String value){
    this.name = name;
    this.value = value;
   }
   /**
    * 
    * @return returns the tag in a string format
    */
   public String getTag(){
       return name + "=" + value;
    }
    /**
     * 
     * @param n name of the tag
     * @param v value of the tag
     * changes the name and value of the tag
     */
    public void setTag(String n, String v){
        name = n; value = v;
    }
    /**
     * 
     * @param t2 a tag to be compared with the tag object
     * @return returns if the two tags are equal or not
     */
public boolean equals(Tag t2){
   return (name == t2.name && value == t2.value);
}
/**
 * 
 * @param s a string that represents a potential tag
 * @return returns if the string is in the correct tag format
 */
public static boolean isValidTag(String s){
    if (s.isEmpty()) return false;
    if (!s.contains("=")) return false;
    int equalIndex = s.indexOf("=");

    if (equalIndex != s.length()-1 && equalIndex !=0){
        return true;
    }


    return false;
} 
/**
 * 
 * @param input A string representing a tag
 * @return returns the name of the tag
 */
public static String getTagName(String input) {
    int index = input.indexOf('=');
    return input.substring(0, index);
}
/**
 * 
 * @param input A string representing a tag
 * @return returns the name of the tag
 */
public static String getTagValue(String input) {
    int index = input.indexOf('=');
    return input.substring(index + 1);
}
    
}
