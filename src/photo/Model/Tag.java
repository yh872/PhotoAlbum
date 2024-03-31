package photo.Model;

public class Tag{
   private String name;
   private String value;

   public Tag(String name, String value){
    this.name = name;
    this.value = value;
   }
   public String[] getTag(){
        String[] tag = new String[2];
        tag[0] = name; tag[1] = value;
        return tag;
    }
    public void setTag(String n, String v){
        name = n; value = v;
    }
public boolean equals(Tag t2){
   return (name == t2.name && value == t2.value);
}

    
}
