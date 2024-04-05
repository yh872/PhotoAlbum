/**
 * This class represents the admin user
 * It also stores the listofusers arraylist, which is where all of the data is stored
 * 
 * 
 * 
 * 
 * 
 * @author Youssef Hanna
 */


package photo.Model;

import java.io.*;
import java.util.ArrayList;

public class Admin implements Serializable {
    static final long serialVersionUID = 1L;
    public static final String storeDir = "src/photo/Model";
public static final String storeFile = "user.dat";

/**
 * 
 * @serialData Serializes the list of users and each users data
 * 
 * @throws IOException
 */
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
/**
 * reads the serialized data object
 * 
 * @throws IOException
 */
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

    /**
     * The arraylist storing all of the users
     */
    public static ArrayList<User> listofUsers = new ArrayList<>();

   
    
    /**
     * adds a user to the list of users
     * @param username string representing the users name/id
     */
    public static void AddUser(String username){
        listofUsers.add(new User(username));
    }
    /**
     * deletes a user from the list of users
     * @param username string representing the users name/id
     */
    public static void DeleteUser(String username){
        for (int i = 0; i < listofUsers.size(); i++){
            if (listofUsers.get(i).getUsername().equals(username)){
                listofUsers.remove(i);
                return;
            }
        }
    }
    /**
     * 
     * @param username string representing the users name/id
     * @return returns whether a username is in the list of users or not
     */
    public static boolean containsUser(String username){
        for (int i = 0; i < listofUsers.size(); i++){
            if (listofUsers.get(i).getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }
    /**
     * 
     * @param username string representing the users name/id
     * @return returns a user object with the given username
     */
    public static User getUser(String username){
        for (int i = 0; i < listofUsers.size(); i++){
            if (listofUsers.get(i).getUsername().equals(username)){
                return listofUsers.get(i);
            }
        }
        return null;
    }


}
