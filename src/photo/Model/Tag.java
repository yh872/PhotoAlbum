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

    
}
