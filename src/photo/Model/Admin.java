package photo.Model;

import java.io.*;
import java.util.ArrayList;

public class Admin implements Serializable {
    static final long serialVersionUID = 1L;
    public static ArrayList<User> listofUsers = new ArrayList<>();
    public Admin(){
    }
    

    public void AddUser(User u){
        listofUsers.add(u);
    }
    public void DeleteUser(User u){
        listofUsers.remove(u);
    }


}
