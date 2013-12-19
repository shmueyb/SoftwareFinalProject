package edu.umn.csci5801.db;

import edu.umn.csci5801.session.User;

import java.util.List;

/**
 * @author Ben Hagaman
 * @author Sam Blustin
 * @author Catherine Reeves
 * @author Xum Giang
 * @author Trang Nguyen
 *
 * UserDAO.java
 *
 * This class abstracts the user database information into User objects.
 * There are general methods for interacting with the database.
 */
public class UserDAO {
    /**
     * Returns the user that corresponds with the supplied ID, if they exist in the database.
     *
     * @param userID id of the user we wish to retrieve.
     * @return User with the supplied user ID
     * @throws DatabaseAccessException If the user does not exist, or there was a problem querying the database.
     */
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
