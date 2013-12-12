package edu.umn.csci5801.session;

import edu.umn.csci5801.db.DatabaseAccessException;
import edu.umn.csci5801.db.UserDAO;


/**
 * Created with IntelliJ IDEA.
 * User: Ben
 * Date: 12/5/13
 * Time: 1:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Session {

    private final String userID;
    private final UserType currentUserType;
    private final Professor professorUser;
    private final Student studentUser;


    public Session(String userID) throws DatabaseAccessException{
        //TODO find user type and set it. Also set professor/student user based on type
        this.userID = userID;
        professorUser = null;
        studentUser = null;

        Users user = UserDAO.getUserByID(userID);
        currentUserType = user.getRole();
    }

    public UserType getCurrentUserType() {
        return currentUserType;
    }

    public Professor getProfessorUser() {
        return professorUser;
    }

    public Student getStudentUser() {
        return studentUser;
    }

    public String getUserID() {
        return userID;
    }

}
