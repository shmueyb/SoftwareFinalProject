package edu.umn.csci5801.db;

import edu.umn.csci5801.studentrecord.StudentRecord;
import edu.umn.csci5801.studentrecord.program.Department;
import edu.umn.csci5801.studentrecord.transcript.Course;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by UBLUSSA on 12/9/13.
 */
public class FileAccessTest {
    @Test
    public void testGetStudentsJSON() throws Exception {
        List<StudentRecord> studentRecords;
        studentRecords = FileAccess.getStudentsJSON();
        StudentRecord firstStudent=studentRecords.get(0);
        assertEquals(firstStudent.getStudent().getFirstName(), "Luan");
        assertEquals(firstStudent.getDepartment(), Department.COMPUTER_SCIENCE);

        StudentRecord secondStudent=studentRecords.get(1);
        assertEquals(secondStudent.getStudent().getFirstName(), "Greg");
        assertEquals(secondStudent.getDepartment(), null);
    }

    @Test
    public void testWriteStudentsJSON() throws Exception {

    }

    @Test
    public void testGetCourseJSON() throws Exception {
        List<Course> courses;
        courses = FileAccess.getCourseJSON();
        Course firstCourse=courses.get(0);
        assertEquals(firstCourse.getName(), "Operating Systems");
        assertEquals(firstCourse.getNumCredits(), "3");

        Course secondCourse=courses.get(1);
        assertEquals(secondCourse.getId(), "csci5104");
        // Course Area won't be populated?
        //assertEquals(secondCourse.getCourseArea(), CourseArea.ARCHITECTURE_SYSTEMS_SOFTWARE);

    }

    @Test
    public void testGetUserJSON() throws Exception {
       /* List<Session> sessions;
        sessions = FileAccess.getUserJSON();
        Session firstSession=sessions.get(0);
        assertEquals(firstSession.getUser().getId(), "nguy0621");
        assertEquals(firstSession.getRole(), UserType.STUDENT);
        assertEquals(firstSession.getDepartment(), Department.COMPUTER_SCIENCE);*/
    }

    @Test
    public void testGetProgressJSON() throws Exception {

    }

    @Test
    public void testInitialize() throws Exception {

    }

    @Test
    public void testGetInstance() throws Exception {

    }
}