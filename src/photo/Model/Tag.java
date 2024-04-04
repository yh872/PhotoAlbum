package photo.Model;

import java.io.Serializable;

public class Tag implements Serializable{
   private String name;
   private String value;

   public Tag(String name, String value){
    this.name = name;
    this.value = value;
   }
   public String getTag(){
       return name + "=" + value;
    }
    public void setTag(String n, String v){
        name = n; value = v;
    }
public boolean equals(Tag t2){
   return (name == t2.name && value == t2.value);
}
public static boolean isValidTag(String s){
    if (s.isEmpty()) return false;
    if (!s.contains("=")) return false;
    int equalIndex = s.indexOf("=");

    if (equalIndex != s.length()-1 && equalIndex !=0){
        return true;
    }


    return false;
} 
public static String getTagName(String input) {
    int index = input.indexOf('=');
    return input.substring(0, index);
}

public static String getTagValue(String input) {
    int index = input.indexOf('=');
    return input.substring(index + 1);
}
    
}
