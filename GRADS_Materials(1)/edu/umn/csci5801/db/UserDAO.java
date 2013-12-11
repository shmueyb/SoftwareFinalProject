package edu.umn.csci5801.db;

import com.google.gson.JsonObject;
import edu.umn.csci5801.session.Person;
import edu.umn.csci5801.session.Student;
import edu.umn.csci5801.session.User;
import edu.umn.csci5801.session.Users;
import edu.umn.csci5801.studentrecord.program.Department;
import edu.umn.csci5801.studentrecord.transcript.StudentInfo;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ben
 * Date: 12/7/13
 * Time: 10:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserDAO {

    public static Users getUserByID(String userFile, String userID) {
        List<Users> usersList=null;
        try {
            usersList= FileAccess.getUserJSON(userFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Users currentUser=null;
        for (Users user: usersList){
            if (userID.equals(user.getUser().getId())){
                currentUser=user;
            }
        }

        return currentUser;
    }

}
