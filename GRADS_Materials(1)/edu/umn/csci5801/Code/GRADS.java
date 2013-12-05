package edu.umn.csci5801.Code;

import java.util.List;

import edu.umn.csci5801.model.CourseTaken;
import edu.umn.csci5801.model.ProgressSummary;
import edu.umn.csci5801.model.StudentRecord;

public class GRADS implements GRADSIntf {

	public GRADS() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setUser(String userId) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public String getUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getStudentIDs() throws Exception {
		// TODO Auto-generated method stub
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
