package edu.umn.csci5801.db;

import edu.umn.csci5801.session.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ben
 * Date: 12/7/13
 * Time: 10:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserDAO {

    public static User getUserByID(String userID) throws DatabaseAccessException{
        List<User> usersList = FileAccess.getInstance().getUserJSON();

        User currentUser=null;
        for (User user: usersList){
            if (userID.equals(user.getID())){
                currentUser=user;
            }
        }

        if (currentUser == null){
            throw new DatabaseAccessException("User with userID: " + userID + " not found in the database");
        }

        return currentUser;
    }

}
