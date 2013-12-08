package edu.umn.csci5801.session;

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


    public Session(String userID) {
        //TODO find user type and set it. Also set professor/student user based on type
        this.userID = userID;
        currentUserType = null;
        professorUser = null;
        studentUser = null;
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
