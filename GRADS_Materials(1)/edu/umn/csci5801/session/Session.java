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
    private final User user;


    public Session(String userID) throws DatabaseAccessException{
        this.userID = userID;

        user = UserDAO.getUserByID(userID);
        currentUserType = user.getRole();

        if (currentUserType == UserType.GRADUATE_PROGRAM_COORDINATOR) {
            professorUser = new Professor(user.getFirstName(), user.getLastName(), user.getDepartment());
            studentUser = null;
        } else if (currentUserType == UserType.STUDENT) {
            professorUser = null;
            studentUser = new Student(user.getFirstName(), user.getLastName(), user.getID());
        } else {
            throw new DatabaseAccessException("Invalid user type found for user with ID: " + userID);
        }
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

    public User getUser() {
        return user;
    }

    public String getUserID() {
        return userID;
    }

}
