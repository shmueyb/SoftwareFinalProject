package edu.umn.csci5801.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.umn.csci5801.GRADS;
import edu.umn.csci5801.db.DatabaseAccessException;
import edu.umn.csci5801.session.InvalidUserException;
import edu.umn.csci5801.session.Student;
import edu.umn.csci5801.studentrecord.program.Degree;
import edu.umn.csci5801.studentrecord.program.Department;
import edu.umn.csci5801.studentrecord.requirements.RequirementCheckResult;
import edu.umn.csci5801.studentrecord.transcript.CourseTaken;
import edu.umn.csci5801.studentrecord.transcript.ProgressSummary;
import edu.umn.csci5801.studentrecord.transcript.Semester;
import edu.umn.csci5801.studentrecord.transcript.Term;

/**
 */
public class SimulateCoursesTest {

    private static GRADS grads;

    /**
     * Init Grads for usage
     */
    @Before
    public void initGrad() throws Exception {
        // creating test files
        StudentRecordFactory.instantiateTestDb();
        // init Grads
        if(grads == null){
            grads = new GRADS("GRADS_Materials/Data/TestStudents.txt", "GRADS_Materials/Data/courses.txt", "GRADS_Materials/Data/TestUsers.txt");
        }
    }

    /**
     * test if simulate courses would return the expected progress summary
     */
    @Test
    public void testSimulateCourses() throws Exception {
        ProgressSummary progressSummary = grads.simulateCourses("gayxx067", StudentRecordFactory.GregCoursesTaken());
        for(CourseTaken c : StudentRecordFactory.GregCoursesTaken()) {
            Assert.assertTrue(checkForCourseName(c.getCourse().getName(), progressSummary.getRequirementCheckResults()));
        }
    }

    /**
     * checking to see if a class exists in the list of RequirementCheckResults
     * @param courseName
     * @param results
     * @return
     */
    private boolean checkForCourseName(String courseName, List<RequirementCheckResult> results) {
        for(RequirementCheckResult r : results) {
            if(courseName.equals(r.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * test if simulateCourses would generate grades for simulated courses
     */
    @Test
    public void testSimulateCourses_gradesGenerating() throws Exception {
        ProgressSummary progressSummary = grads.simulateCourses("gayxx067", StudentRecordFactory.GregCoursesTaken());
        for(RequirementCheckResult r : progressSummary.getRequirementCheckResults()) {
            Assert.assertNotNull(r.getDetails().getGPA());
        }
    }

    /**
     * checks that a GPC cannot simulate a progress summary for a student out of their dept.
     * @throws FileNotFoundException
     * @throws DatabaseAccessException
     */
    @Test
    public void testSimulateCourses_OutDept_AsGPC() throws FileNotFoundException, DatabaseAccessException, InvalidUserException {
        //TODO inputs: courseTaken list
        String testID = "smith0002";
        List<CourseTaken> courses = new ArrayList<CourseTaken>();
        grads.setUser(testID);
        try {
            grads.simulateCourses("desil1337",courses);
            fail();
        } catch (Exception ex) {
            //do nothing
        }
    }
    /**
     * Checks that a student cannot simulate another student's progress summary
     * @throws FileNotFoundException
     * @throws DatabaseAccessException
     */
    @Test
    public void testSimulateCourses_OtherStudent_AsStudent() throws FileNotFoundException, DatabaseAccessException, InvalidUserException {

        List<CourseTaken> courses = new ArrayList<CourseTaken>();
        grads.setUser("desil1337");
        try {
            grads.simulateCourses("nguy0261",courses);
            fail();
        } catch (Exception ex) {
            //do nothing
        }
    }
    /**
     * Checks that GPC can simulate a progress summary for a student in their dept.
     * @throws Exception
     */
    @Test
    public void testSimulateCourses_PHD_AsGPC() throws Exception {
        //TODO inputs: courseTaken list, StudentRecordFactor for user new student record,assert
        grads.setUser("tolas9999");
        List<CourseTaken> courses = new ArrayList<CourseTaken>();
        ProgressSummary actual = grads.simulateCourses("nguy0621", courses);
    }

    /**
     * Check that simulate courses takes in account retaken course
     * @throws Exception
     */
    @Test
    public void testSimulateCourses_RetakenCourse_MS_A_AsStudent() throws Exception {
        grads.setUser("gayxx067");
        List<CourseTaken> courses = new ArrayList<CourseTaken>();
        ProgressSummary actual = grads.simulateCourses("gayxx067", courses);

    }

    /**
     * Checks simulate courses takes courses without a grade input
     * @throws Exception
     */
    @Test
    public void testSimulateCourses_NoGradeInput_MS_B_AsStudent() throws Exception {
//        grads.setUser("desil1337");
//        List<CourseTaken> courses = new ArrayList<CourseTaken>();
//
//        ProgressSummary actual = grads.simulateCourses("desil1337", courses);
//
//        assertEquals(new Student("Ian", "De Silva", "desil1337"),actual.getStudent());
//        assertEquals(Department.COMPUTER_SCIENCE,actual.getDepartment());
//        assertEquals(Degree.MS_C, actual.getDegreeSought());
//        assertEquals(new Term(Semester.SPRING, 2011), actual.getTermBegan());
//        assertEquals(StudentRecordFactory.CatherineAdvisors(),actual.getAdvisors());
//        assertEquals(StudentRecordFactory.CatherineCommittee(), actual.getCommittee());
//        assertEquals(StudentRecordFactory.notes(),actual.getNotes());

    }
    /**
     *
     * @throws Exception
     */
    @Test
    public void testSimulateCourses_FailingGradeInput_MS_C_AsStudent() throws Exception {
        grads.setUser("1111");
        List<CourseTaken> courses = new ArrayList<CourseTaken>();
        ProgressSummary actual = grads.simulateCourses("1111", courses);



        assertEquals(new Student("Catherine", "Reed", "1111"),actual.getStudent());
        assertEquals(Department.COMPUTER_SCIENCE,actual.getDepartment());
        assertEquals(Degree.MS_C, actual.getDegreeSought());
        assertEquals(new Term(Semester.SPRING, 2011), actual.getTermBegan());
        assertEquals(StudentRecordFactory.CatherineAdvisors(),actual.getAdvisors());
        assertEquals(StudentRecordFactory.CatherineCommittee(), actual.getCommittee());
        assertEquals(StudentRecordFactory.notes(),actual.getNotes());


    }
}
