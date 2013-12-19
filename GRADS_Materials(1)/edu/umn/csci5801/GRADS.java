package edu.umn.csci5801;

import java.util.List;

import edu.umn.csci5801.access.AccessController;
import edu.umn.csci5801.access.AccessDeniedException;
import edu.umn.csci5801.db.DatabaseAccessException;
import edu.umn.csci5801.db.FileAccess;
import edu.umn.csci5801.db.UserDAO;
import edu.umn.csci5801.session.InvalidUserException;
import edu.umn.csci5801.studentrecord.StudentRecordController;
import edu.umn.csci5801.studentrecord.program.Department;
import edu.umn.csci5801.studentrecord.transcript.CourseTaken;
import edu.umn.csci5801.studentrecord.transcript.ProgressSummary;
import edu.umn.csci5801.studentrecord.StudentRecord;
import edu.umn.csci5801.session.Session;

public class GRADS implements GRADSIntf {

    private Session currentSession;
    private AccessController access;

    /**
     * Creates a GRADS(Graduation Requirement Assessment Data System) instance. GRADS
     * is a program which allows users to see student records, and check on student progress.
     *
     * Students are able to check their own progress, and simulate the effect of taking particular
     * courses on their student record and progress.
     *
     * In addition, GPCs are able to access this program to do anything a student can do, as well as
     * add notes to a student's record, or make changes to a student's record.
     *
     * @param studentsFilePath path to the students JSON database
     * @param coursesFilePath path to the courses JSON database
     * @param usersFilePath path to the users JSON database
     * @throws DatabaseAccessException if the databases file locations were invalid, or some other problem came up
     *             in initializing the database files.
     */
    public GRADS(String studentsFilePath, String coursesFilePath, String usersFilePath) throws DatabaseAccessException{
            FileAccess.initialize(studentsFilePath, coursesFilePath, usersFilePath);
    }

    /**
     * Sets the current user of the GRADS system.
     *
     * @param userID id of the current user.
     * @throws InvalidUserException if there was a problem with the given userID
     */
    @Override
    public void setUser(String userID) throws InvalidUserException, DatabaseAccessException {
        currentSession = new Session(userID);
        access = new AccessController(currentSession);
    }

    /**
     * returns the userID of the current user.
     *
     * @return current user's userID
     */
    @Override
    public String getUser() {
        return currentSession.getUserID();
    }

    /**
     * Fetches a list of student IDs corresponding to students that the
     * current user (GPC only) has access to
     *
     * @return List of strings of student IDs
     * @throws Exception if the current user is not a GPC
     */
    @Override
    public List<String> getStudentIDs() throws Exception {

        access.checkUserCanGetListOfStudentIDs();
        Department gpcDepartment = UserDAO.getUserByID(currentSession.getUserID()).getDepartment();

        return StudentRecordController.getStudentIDsByDepartment(gpcDepartment);
    }

    /**
     * retrieves the transcript corresponding to the given studentID.
     *
     * @param studentID  the identifier of the student, for which we want a transcript.
     * @return StudentRecord corresponding to the userID
     * @throws AccessDeniedException if the current user does not have access to the desired record.
     * @throws DatabaseAccessException if the data could not be retrieved from the database
     */
    @Override
    public StudentRecord getTranscript(String studentID)
            throws AccessDeniedException, DatabaseAccessException {

        access.checkUserCanAccessStudentRecord(studentID);
        return StudentRecordController.getTranscript(studentID);
    }

    /**
     * Updates the StudentRecord in the database for the supplied userID
     *
     * @param studentID the ID of the student, whose student record we want to overwrite.
     * @param transcript the new student record
     * @throws AccessDeniedException if the current user does not have access to the desired record.
     * @throws DatabaseAccessException if the data could not be updated in the database
     */
    @Override
    public void updateTranscript(String studentID, StudentRecord transcript)
            throws AccessDeniedException,DatabaseAccessException {

        access.checkUserCanEditRecord(studentID);
        StudentRecordController.updateStudentRecord(studentID, transcript);
    }

    /**
     * adds a new note to the student record corresponding to the studentID
     *
     * @param studentID the student ID to add a new note for.
     * @param note the note to append
     * @throws AccessDeniedException if the current user does not have access to
     *      add notes to the student's record or is not a GPC.
     * @throws DatabaseAccessException if the note failed being added to the database
     */
    @Override
    public void addNote(String studentID, String note)
            throws AccessDeniedException, DatabaseAccessException{

        access.checkUserCanEditRecord(studentID);
        StudentRecordController.addNote(studentID, note);
    }

    /**
     * generates and returns a progress summary for the given student
     *
     * @param studentID the student to generate the record for.
     * @return the current progress summary for the given student
     * @throws AccessDeniedException if the current user does not have permission
     *      to access the student's record.
     * @throws DatabaseAccessException if the data could not be retrieved from the database
     */
    @Override
    public ProgressSummary generateProgressSummary(String studentID)
            throws AccessDeniedException, DatabaseAccessException {

        access.checkUserCanAccessStudentRecord(studentID);
        return StudentRecordController.generateProgressSummary(studentID);
    }

    /**
     * Returns the ProgressSummary that would result from the given student
     * taking the supplied courses.
     *
     * @param studentID the student to generate the record for.
     * @param courses a list of the prospective courses.
     * @return ProgressSummary, what the student's record would look like after taking and passing
     *      the supplied list of courses.
     * @throws AccessDeniedException if the current user does not have permission
     *      to access the student's record.
     * @throws DatabaseAccessException if the data could not be retrieved from the database
     */
    @Override
    public ProgressSummary simulateCourses(String studentID, List<CourseTaken> courses)
            throws AccessDeniedException, DatabaseAccessException {

        access.checkUserCanAccessStudentRecord(studentID);
        return StudentRecordController.simulateCourses(studentID, courses);
    }
}
