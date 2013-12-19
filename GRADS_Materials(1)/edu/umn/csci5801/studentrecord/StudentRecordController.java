package edu.umn.csci5801.studentrecord;

import edu.umn.csci5801.db.DatabaseAccessException;
import edu.umn.csci5801.db.DegreePlanDAO;
import edu.umn.csci5801.db.StudentDAO;
import edu.umn.csci5801.session.Professor;
import edu.umn.csci5801.session.Student;
import edu.umn.csci5801.studentrecord.program.Degree;
import edu.umn.csci5801.studentrecord.program.DegreePlan;
import edu.umn.csci5801.studentrecord.program.Department;
import edu.umn.csci5801.studentrecord.requirements.MilestoneSet;
import edu.umn.csci5801.studentrecord.requirements.RequirementCheckResult;
import edu.umn.csci5801.studentrecord.transcript.CourseTaken;
import edu.umn.csci5801.studentrecord.transcript.Grade;
import edu.umn.csci5801.studentrecord.transcript.ProgressSummary;
import edu.umn.csci5801.studentrecord.transcript.Term;

import java.util.ArrayList;
import java.util.List;

/**
 * Authors: Ben Hagaman, Sam Blustin, Catherine Reeves, Xum Giang, Trang Nguyen
 *
 * StudentRecordController handles requests specific to student records, including progress
 * summary generation.
 */
public class StudentRecordController {
    /**
     * Returns the StudentRecord for the given student ID.
     *
     * @param studentID id of the student we wish to retrieve the record for.
     * @return the student record for the given student.
     * @throws DatabaseAccessException if access to the database failed
     */
    public static StudentRecord getTranscript(String studentID)
            throws DatabaseAccessException {

        return StudentDAO.getStudentByID(studentID);
    }

    /**
     * Generates the current progress summary for the given student.
     *
     * @param studentID the ID of the student, for which we want to generate a progress summary.
     * @return ProgressSummary for the given student.
     * @throws DatabaseAccessException if access to the database failed.
     */
    public static ProgressSummary generateProgressSummary(String studentID)
            throws DatabaseAccessException {
        return simulateCourses(studentID, new ArrayList<CourseTaken>());
    }

    /**
     * Generates a progress summary based upon the simulation of the student taking the supplied courses.
     *
     * @param studentID ID of the student, for which we wish to generate a progress summary.
     * @param coursesToAddForSimulation the courses we'd like to include, in addition to the student's
     *                                  actually taken courses, in the generated progress summary.
     * @return ProgressSummary resulting from the simulation of taking the supplied courses.
     * @throws DatabaseAccessException if access to the databases failed.
     */
    public static ProgressSummary simulateCourses(String studentID, List<CourseTaken> coursesToAddForSimulation)
            throws DatabaseAccessException {
        ProgressSummary summary = new ProgressSummary();

        StudentRecord studentRecord = StudentDAO.getStudentByID(studentID);

        Student student = studentRecord.getStudent();
        summary.setStudent(student);

        Department dept = studentRecord.getDepartment();
        summary.setDepartment(dept);

        Degree degree = studentRecord.getDegreeSought();
        summary.setDegreeSought(degree);

        Term termBegan = studentRecord.getTermBegan();
        summary.setTermBegan(termBegan);

        List<Professor> advisors = studentRecord.getAdvisors();
        summary.setAdvisors(advisors);

        List<Professor> committee = studentRecord.getCommittee();
        summary.setCommittee(committee);

        List<String> notes = studentRecord.getNotes();
        summary.setNotes(notes);

        DegreePlan degreePlan = DegreePlanDAO.getDegreePlanByDepartmentAndDegree(dept, degree);
        List<CourseTaken> coursesToSimulate = studentRecord.getCoursesTaken();
        for (CourseTaken courseToAdd: coursesToAddForSimulation) {
            coursesToSimulate.add(courseToAdd);
        }

        List<RequirementCheckResult> results = degreePlan.generateRequirementCheckResults(
                coursesToSimulate,
                studentRecord.getMilestonesSet()
        );
        summary.setRequirementCheckResults(results);


        return summary;
    }

    /**
     * Replaces the given student's record with the one supplied.
     *
     * @param studentID the ID of the student, for whom we want to overwrite the student record.
     * @param record the student record we wish to update to.
     * @throws DatabaseAccessException if access to the database failed.
     */
    public static void updateStudentRecord(String studentID, StudentRecord record) throws DatabaseAccessException {
        StudentDAO.updateStudentRecord(studentID, record);
    }

    /**
     * Adds a note to the given student's record.
     *
     * @param studentID the ID of the student whose record we wish to add a note to.
     * @param note the note we wish to append on the student record.
     * @throws DatabaseAccessException if access to the database failed.
     */
    public static void addNote(String studentID, String note) throws DatabaseAccessException {
        if (note == null) {
            return;
        }

        StudentRecord studentRecord = StudentDAO.getStudentByID(studentID);
        List<String> notes = studentRecord.getNotes();
        notes.add(note);
        studentRecord.setNotes(notes);

        updateStudentRecord(studentID, studentRecord);
    }

    /**
     * Returns the IDs of students in the given department.
     *
     * @param department the department that we wish to get a list of students for.
     * @return List of student IDs of students in the given department.
     * @throws DatabaseAccessException if access to the database failed.
     */
    public static List<String> getStudentIDsByDepartment(Department department) throws DatabaseAccessException {

        final List<StudentRecord> studentList;
        final List<String> idList = new ArrayList<String>();

        studentList = StudentDAO.getStudentsByDepartment(department);

        for(StudentRecord studentDAO: studentList) {
            idList.add(studentDAO.getStudent().getId());
        }

        return idList;
    }
}
