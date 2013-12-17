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
     * This class is used to check if the user of the system has the privileges to perform particular actions.
     *
     * @param session the session containing information on the current user.
     */
    public AccessController(Session session) {
        currentSession = session;
    }


    /**
     * Checks if the current user has access to view the supplied student's record.
     *
     * @param studentID of the student whose record the user wishes to view.
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
        } else {
            throw new AccessDeniedException("User " + currentSession.getUserID() + " has an invalid or missing user type");
        }

    }


    /**
     * Checks if the current user has permission to generate a list of student IDs. Throws an exception
     * if they do not.
     *
     * @throws AccessDeniedException if the user is not a GPC
     */
    public void checkUserCanGetListOfStudentIDs() throws AccessDeniedException{
        if (currentSession.getCurrentUserType() != UserType.GRADUATE_PROGRAM_COORDINATOR) {
            throw new AccessDeniedException("User " + currentSession.getUserID() + " is not a GPC," +
                    "and therefore does not have access to generate a list of student IDs");
        }
    }


    /**
     * Checks if the current user has access to edit the given student's record. Throws an exception if they do
     * not have permission.
     *
     * @param studentID of the student whose record the user wishes to change
     * @throws AccessDeniedException if the user does not have permission to edit the student's record.
     */
    public void checkUserCanEditRecord(String studentID) throws AccessDeniedException, DatabaseAccessException{
        if (currentSession.getCurrentUserType() == UserType.GRADUATE_PROGRAM_COORDINATOR) {
            Department gpcDept = currentSession.getProfessorUser().getDepartment();
            Department studentDepartment = StudentDAO.getStudentByID(studentID).getDepartment();

            if (! gpcDept.equals(studentDepartment)) {
                throw new AccessDeniedException("The current user is not in the correct department to edit the record for" +
                        "the student with ID: " + studentID + ".\nGPC Department: " + gpcDept + "\nStudent Department: "
                        + studentDepartment);
            }

        } else if (currentSession.getCurrentUserType() == UserType.STUDENT) {
            throw new AccessDeniedException("Only GPCs may edit student records. The current user is a student");
        } else {
            throw new AccessDeniedException("User " + currentSession.getUserID() + " has an invalid user type.  The user type" +
                    "is: " + currentSession.getCurrentUserType());
        }
    }
}
