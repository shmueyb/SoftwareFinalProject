package edu.umn.csci5801;

import java.util.ArrayList;
import java.util.List;

import edu.umn.csci5801.access.AccessController;
import edu.umn.csci5801.db.DatabaseAccessException;
import edu.umn.csci5801.db.FileAccess;
import edu.umn.csci5801.db.StudentDAO;
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

	public GRADS() {
        String databaseLocation = "Grads_Materials/Data/";

        try {
            FileAccess.initialize(databaseLocation);
        } catch (DatabaseAccessException e) {
            //do nothing, this will not fail on the first call
        }
    }

    /**
     * Sets the current user of the GRADS system.
     *
     * @param userID id of the current user.
     * @throws InvalidUserException if there was a problem with the given userID
     */
	@Override
	public void setUser(String userID) throws InvalidUserException {
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
        Department gpcDepartment = currentSession.getProfessorUser().getDepartment();

        return StudentRecordController.getStudentIDsByDepartment(gpcDepartment);
	}

    /**
     * retrieves the transcript corresponding to the given studentID.
     * @param studentID  the identifier of the student, for which we want a transcript.
     * @return StudentRecord corresponding to the userID
     * @throws Exception if the data could not be retrieved, or if the current user
     *      does not have access to the desired record.
     */
	@Override
	public StudentRecord getTranscript(String studentID) throws Exception {

        access.checkUserCanAccessStudentRecord(studentID);
        return StudentRecordController.getTranscript(studentID);
	}

    /**
     * Updates the StudentRecord in the database for the supplied userID
     *
     * @param studentID the ID of the student, whose student record we want to overwrite.
     * @param transcript the new student record
     * @throws Exception if the user does not have access, or updating failed
     */
	@Override
	public void updateTranscript(String studentID, StudentRecord transcript) throws Exception {

        access.checkUserCanEditRecord(studentID);
        StudentRecordController.updateStudentRecord(studentID, transcript);
	}

    /**
     * adds a new note to the student record corresponding to the studentID
     *
     * @param studentID the student ID to add a new note for.
     * @param note the note to append
     * @throws Exception if the user does not have access or adding the note failed
     */
	@Override
	public void addNote(String studentID, String note) throws Exception {

        access.checkUserCanEditRecord(studentID);
        StudentRecordController.addNote(studentID, note);
	}

    /**
     * generates and returns a progress summary for the given student
     *
     * @param studentID the student to generate the record for.
     * @return
     * @throws Exception
     */
	@Override
	public ProgressSummary generateProgressSummary(String studentID) throws Exception {

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
     * @throws Exception the user does not have permission to do this, or generating the
     *      summary failed for the given reason.
     */
	@Override
	public ProgressSummary simulateCourses(String studentID, List<CourseTaken> courses) throws Exception {
        access.checkUserCanAccessStudentRecord(studentID);
        return StudentRecordController.simulateCourses(studentID, courses);
	}

}
