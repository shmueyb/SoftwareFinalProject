package edu.umn.csci5801.WhiteBoxTest.dbTesting;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.umn.csci5801.GRADS;
import edu.umn.csci5801.db.CourseDAO;
import edu.umn.csci5801.db.DatabaseAccessException;
import edu.umn.csci5801.test.StudentRecordFactory;
import edu.umn.csci5801.studentrecord.transcript.Course;
import edu.umn.csci5801.studentrecord.transcript.CourseArea;


public class CourseDAOTest {
    private static GRADS grads;

    /**
     * Init Grads for usage
     */
    @BeforeClass
    public static void initGrad() throws Exception {
        // creating test files
        StudentRecordFactory.instantiateTestDb();
        // init Grads
        grads = new GRADS("GRADS_Materials/Data/TestStudents.txt", "GRADS_Materials/Data/courses.txt", "GRADS_Materials/Data/TestUsers.txt");

    }

    /**
     * Checks that a student cannot add a note
     * @throws java.io.FileNotFoundException
     */
    @Test
    public void testCourseDAO() throws Exception {
        Course course = CourseDAO.getCourseByID("csci5103");
        Assert.assertSame(course.getCourseArea(), CourseArea.ARCHITECTURE_SYSTEMS_SOFTWARE);
        Assert.assertEquals(course.getName(), "Operating Systems");
        Assert.assertEquals(course.getId(), "csci5103");
        Assert.assertEquals(course.getNumCredits(), "3");
    }

    @Test
    public void testCourseDAO_null() {
        try {
            Course course = CourseDAO.getCourseByID(null);
            Assert.assertNull(course);
        } catch (DatabaseAccessException e) {
            Assert.fail();
        }
    }


    @Test
    public void testCourseDAO_Empty() {
        try {
            Course course = CourseDAO.getCourseByID("");
            Assert.assertNull(course);
        } catch (DatabaseAccessException e) {
            Assert.fail();
        }
    }

    @Test
    public void testCourseDAO_Invalid() {
        try {
            Course course = CourseDAO.getCourseByID("Invalid");
            Assert.assertNull(course);
        } catch (DatabaseAccessException e) {
            Assert.fail();
        }
    }

}
