package edu.umn.csci5801.session;

import edu.umn.csci5801.db.DatabaseAccessException;
import edu.umn.csci5801.db.UserDAO;

/**
 * @author Ben Hagaman
 * @author Sam Blustin
 * @author Catherine Reeves
 * @author Xum Giang
 * @author Trang Nguyen
 *
 * Session.java
 *
 * This class is used to keep track of current user information.
 */
public class Session {

    private final String userID;
    private final UserType currentUserType;
    private final Professor professorUser;
    private final Student studentUser;
    private final User user;

    /**
     * Constructor sets up the user information in the session class to correspond with the supplied userID
     *
     * @param userID current user of the GRADS system.
     * @throws DatabaseAccessException If there was a problem in accessing the database for the given userID.
     */
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

    /**
     * Returns the type of user that the current user is
     * @return GPC or STUDENT.
     */
    public UserType getCurrentUserType() {
        return currentUserType;
    }

    /**
     * Gets the professor representation of the current user, if they are a GPC. Otherwise returns null
     * @return Professor if the user is a GPC, otherwise null.
     */
    public Professor getProfessorUser() {
        return professorUser;
    }

    /**
     * Gets the student representation of the current user, if they are a Student. Otherwise returns null
     * @return Student if the user is a Student, otherwise null.
     */
    public Student getStudentUser() {
        return studentUser;
    }

    /**
     * return the current user.
     * @return current User of the GRADS system
     */
    public User getUser() {
        return user;
    }

    /**
     * returns the userID of the current user
     * @return userID of the current user.
     */
    public String getUserID() {
        return userID;
    }

}
