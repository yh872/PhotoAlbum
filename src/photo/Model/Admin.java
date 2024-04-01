package photo.Model;

import java.io.*;
import java.util.ArrayList;

public class Admin implements Serializable {
    static final long serialVersionUID = 1L;
    public static final String storeDir = "src/photo/Model";
public static final String storeFile = "user.dat";

public static void WritetoFile() throws IOException{
    try {
        FileOutputStream FileStream = new FileOutputStream(storeDir + File.separator + storeFile);
        ObjectOutputStream ObjectStream = new ObjectOutputStream(FileStream);
        ObjectStream.writeObject(listofUsers);
        ObjectStream.close();
        FileStream.close();
    } catch (IOException i) {
        i.printStackTrace();
    }
}

public static void getData() throws IOException{
    try {
        FileInputStream FileStream = new FileInputStream(storeDir + File.separator + storeFile);
        ObjectInputStream ObjectStream = new ObjectInputStream(FileStream);
        listofUsers = (ArrayList<User>) ObjectStream.readObject();
        ObjectStream.close();
        FileStream.close();
    } catch (IOException i) {
        i.printStackTrace();
        return;
    } catch (ClassNotFoundException c) {
        c.printStackTrace();
        return;
    }
}


    public static ArrayList<User> listofUsers = new ArrayList<>();
    public Admin(){
    }
    

    public static void AddUser(String username){
        listofUsers.add(new User(username));
    }
    public static void DeleteUser(String username){
        for (int i = 0; i < listofUsers.size(); i++){
            if (listofUsers.get(i).getUsername().equals(username)){
                listofUsers.remove(i);
                return;
            }
        }
    }
    public static boolean containsUser(String username){
        for (int i = 0; i < listofUsers.size(); i++){
            if (listofUsers.get(i).getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }
    public static User getUser(String username){
        for (int i = 0; i < listofUsers.size(); i++){
            if (listofUsers.get(i).getUsername().equals(username)){
                return listofUsers.get(i);
            }
        }
        return null;
    }


}
