package edu.umn.csci5801.access;

import edu.umn.csci5801.db.DatabaseAccessException;
import edu.umn.csci5801.session.UserType;
import edu.umn.csci5801.session.Session;
import edu.umn.csci5801.studentrecord.program.Department;
import edu.umn.csci5801.db.StudentDAO;


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
    public void checkUserCanAccessStudentRecord(String studentID) throws AccessDeniedException, DatabaseAccessException {

        if (currentSession.getCurrentUserType() == UserType.STUDENT) {
            if(!(currentSession.getUserID().equals(studentID))){
                throw new AccessDeniedException("User " + currentSession.getUserID() + " is not a GPC," +
                    "and therefore does not have access to" + studentID + "'s record.");
            }
        }
        else if(currentSession.getCurrentUserType() == UserType.GRADUATE_PROGRAM_COORDINATOR){
            Department department = currentSession.getProfessorUser().getDepartment();
            if((department != StudentDAO.getStudentByID(studentID).getDepartment())){
                throw new AccessDeniedException("User " + currentSession.getUserID() + " does not have access to" +
                        studentID + "'s record because the student is outside of their department.");
            }
        }

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
            throw new AccessDeniedException("User " + currentSession.getUserID() + " cannot access notes because they are not of type " +
                    currentSession.getCurrentUserType());
        } else {
            throw new AccessDeniedException("User " + currentSession.getUserID() + " has an invalid user type.  The user type" +
                    "is: " + currentSession.getCurrentUserType());
        }
    }
}
