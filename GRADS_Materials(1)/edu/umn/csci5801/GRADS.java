package edu.umn.csci5801;

import java.util.List;

import edu.umn.csci5801.access.AccessController;
import edu.umn.csci5801.db.Parser;
import edu.umn.csci5801.model.CourseTaken;
import edu.umn.csci5801.model.ProgressSummary;
import edu.umn.csci5801.model.StudentRecord;
import edu.umn.csci5801.session.Session;

public class GRADS implements GRADSIntf {

    private AccessController accessController;
    private Session currentSession;

	public GRADS() {
		// TODO Auto-generated constructor stub
        AccessController accessController = new AccessController();
	}

	@Override
	public void setUser(String userID) throws Exception {
        currentSession = new Session(userID);
		// TODO Auto-generated method stub

	}

	@Override
	public String getUser() {
		return currentSession.getUserID();
	}

	@Override
	public List<String> getStudentIDs() throws Exception {
		Parser parser = new Parser("Data/");

        //TODO: check that they have permission using access controller

        parser.findStudentsByAdvisor(currentSession.getProfessorUser());
		return null;
	}

	@Override
	public StudentRecord getTranscript(String userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateTranscript(String userId, StudentRecord transcript)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void addNote(String userId, String note) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public ProgressSummary generateProgressSummary(String userId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProgressSummary simulateCourses(String userId,
			List<CourseTaken> courses) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
