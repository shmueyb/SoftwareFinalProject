package edu.umn.csci5801.studentrecord;

import edu.umn.csci5801.db.DatabaseAccessException;
import edu.umn.csci5801.db.StudentDAO;
import edu.umn.csci5801.studentrecord.program.Department;
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
    public static StudentRecord getTranscript(String studentID) throws DatabaseAccessException, FileNotFoundException {
        return StudentDAO.getStudentByID(studentID);
    }

    /**
     *
     * @param studentID
     * @return
     * @throws DatabaseAccessException
     */
    public static ProgressSummary generateProgressSummary(String studentID) throws DatabaseAccessException{
        return simulateCourses(studentID, new ArrayList<CourseTaken>());
    }

    /**
     *
     * @param studentID
     * @param courses
     * @return
     * @throws DatabaseAccessException
     */
    public static ProgressSummary simulateCourses(String studentID,
                                                  List<CourseTaken> courses)  throws DatabaseAccessException{
        ProgressSummary summary = new ProgressSummary();

        //TODO: fill in

        return null;
    }

    /**
     *
     * @param studentID
     * @param transcript
     * @throws DatabaseAccessException
     */
    public static void updateStudentRecord(String studentID, StudentRecord transcript) throws DatabaseAccessException{
        StudentDAO.updateStudentRecord(studentID, transcript);
    }

    /**
     *
     * @param studentID
     * @param note
     * @throws DatabaseAccessException
     */
    public static void addNote(String studentID, String note) throws DatabaseAccessException, FileNotFoundException {
        StudentRecord studentRecord = StudentDAO.getStudentByID(studentID);
        List<String> notes = studentRecord.getNotes();
        notes.add(note);
        studentRecord.setNotes(notes);

        updateStudentRecord(studentID, studentRecord);
    }

    /**
     *
     * @param gpcDepartment
     * @return
     * @throws DatabaseAccessException
     */
    public static List<String> getStudentIDsByDepartment(Department gpcDepartment) throws DatabaseAccessException, FileNotFoundException {

        final List<StudentRecord> studentList;
        final List<String> idList = new ArrayList<String>();

        studentList = StudentDAO.getStudentsByDepartment(gpcDepartment);

        for(StudentRecord studentDAO: studentList) {
            idList.add(studentDAO.getStudent().getId());
        }

        return idList;
    }
}
