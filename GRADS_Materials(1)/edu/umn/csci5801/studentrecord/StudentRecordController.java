package edu.umn.csci5801.studentrecord;

import edu.umn.csci5801.db.DatabaseAccessException;
import edu.umn.csci5801.db.StudentDAO;
import edu.umn.csci5801.studentrecord.program.Department;
import edu.umn.csci5801.studentrecord.requirements.MilestoneSet;
import edu.umn.csci5801.studentrecord.requirements.RequirementCheckResult;
import edu.umn.csci5801.studentrecord.transcript.CourseTaken;
import edu.umn.csci5801.studentrecord.transcript.ProgressSummary;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ben
 * Date: 12/8/13
 * Time: 3:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class StudentRecordController {
    /**
     *
     * @param studentID
     * @return
     * @throws DatabaseAccessException
     */
    public static StudentRecord getTranscript(String studentRecordsFile, String studentID) throws DatabaseAccessException, FileNotFoundException {
        return StudentDAO.getStudentByID(studentRecordsFile, studentID);
    }

    /**
     *
     * @param studentID
     * @return
     * @throws DatabaseAccessException
     */
    public static ProgressSummary generateProgressSummary(String studentRecordsFile, String studentID) throws DatabaseAccessException, FileNotFoundException {
        return simulateCourses(studentRecordsFile, studentID, new ArrayList<CourseTaken>());
    }

    /**
     *
     * @param studentID
     * @param courses
     * @return
     * @throws DatabaseAccessException
     */
    public static ProgressSummary simulateCourses(String studentRecordsFile, String studentID,
                                                  List<CourseTaken> courses) throws DatabaseAccessException, FileNotFoundException {
        ProgressSummary summary = new ProgressSummary();

        //TODO: fill in
        StudentRecord record = StudentDAO.getStudentByID(studentRecordsFile,studentID);
        List<RequirementCheckResult> requirementCheckResultsList = null;
        courses.addAll(record.getCoursesTaken());

        List <MilestoneSet> milestonesSet = record.getMilestonesSet();
        summary.setStudent(record.getStudent());
        summary.setDegreeSought(record.getDegreeSought());
        summary.setAdvisors(record.getAdvisors());
        summary.setCommittee(record.getCommittee());
        summary.setNotes(record.getNotes());
        summary.setDepartment(record.getDepartment());
        summary.setTermBegan(record.getTermBegan());
        boolean passed = true;
        double totalGradePoints=0;
        double totalCredits =0;
        for(CourseTaken course : courses){
            String courseName = course.getCourse().getName();

            if(!(course.getGrade().equals("S"))||(course.getGrade().equals("N"))){

                double grade = course.getGrade().numericValue();
                double credits = Double.parseDouble(course.getCourse().getNumCredits());

                totalGradePoints+= grade;
                totalCredits+= credits;

                if(grade< 1.667){

                    passed = false;
                }

            }
            RequirementCheckResult requirementCheckResult = new RequirementCheckResult(courseName, passed);
            requirementCheckResultsList.add(requirementCheckResult );
        }

        double totalGPA = totalGradePoints/totalCredits;

        summary.setRequirementCheckResults(requirementCheckResultsList);

        return null;
    }

    /**
     *
     * @param studentID
     * @param transcript
     * @throws DatabaseAccessException
     */
    public static void updateStudentRecord(String studentID, StudentRecord transcript, String studentRecordsFile) throws DatabaseAccessException, FileNotFoundException {
        StudentDAO.updateStudentRecord(studentID, transcript, studentRecordsFile);
    }

    /**
     *
     * @param studentID
     * @param note
     * @throws DatabaseAccessException
     */
    public static void addNote(String studentRecordsFile, String studentID, String note) throws DatabaseAccessException, FileNotFoundException {
        StudentRecord studentRecord = StudentDAO.getStudentByID(studentRecordsFile, studentID);
        List<String> notes = studentRecord.getNotes();
        notes.add(note);
        studentRecord.setNotes(notes);

        updateStudentRecord(studentID, studentRecord, studentRecordsFile);
    }

    /**
     *
     * @param gpcDepartment
     * @return
     * @throws DatabaseAccessException
     */
    public static List<String> getStudentIDsByDepartment(String studentRecordFile, Department gpcDepartment) throws DatabaseAccessException, FileNotFoundException {

        final List<StudentRecord> studentList;
        final List<String> idList = new ArrayList<String>();

        studentList = StudentDAO.getStudentsByDepartment(studentRecordFile, gpcDepartment);

        for(StudentRecord studentDAO: studentList) {
            idList.add(studentDAO.getStudent().getId());
        }

        return idList;
    }
}
