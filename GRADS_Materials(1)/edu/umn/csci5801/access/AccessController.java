package edu.umn.csci5801.access;

import edu.umn.csci5801.session.Session;
import edu.umn.csci5801.session.UserType;

/**
 * Created with IntelliJ IDEA.
 * User: Ben
 * Date: 12/5/13
 * Time: 12:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccessController {

    private Session currentSession;

    /**
     *
     * @param session
     */
    public AccessController(Session session) {
        currentSession = session;
    }


    /**
     *
     * @param studentID
     */
    public void checkUserCanAccessStudentRecord(String studentID) {
        //TODO: perform check
    }


    /**
     *
     * @throws AccessDeniedException
     */
    public void checkUserCanGetListOfStudentIDs() throws AccessDeniedException{
        if (currentSession.getCurrentUserType() != UserType.GRADUATE_PROGRAM_COORDINATOR) {
            throw new AccessDeniedException("User " + currentSession.getUserID() + " is not a GPC," +
                    "and therefore does not have access to generate a list of student IDs");
        }
    }


    /**
     *
     * @param studentID
     * @throws AccessDeniedException
     */
    public void checkUserCanEditRecord(String studentID) throws AccessDeniedException{
        if (currentSession.getCurrentUserType() == UserType.GRADUATE_PROGRAM_COORDINATOR) {
            //TODO: perform check on student's dept

        } else if (currentSession.getCurrentUserType() == UserType.STUDENT) {
            //TODO: throw exception if student ID != user ID
        } else {
            throw new AccessDeniedException("User " + currentSession.getUserID() + "has an invalid user type");
        }
    }
}
