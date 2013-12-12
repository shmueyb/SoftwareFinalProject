package edu.umn.csci5801.db;

import edu.umn.csci5801.GRADS;
import edu.umn.csci5801.session.UserType;
import edu.umn.csci5801.session.Users;
import edu.umn.csci5801.studentrecord.StudentRecord;
import edu.umn.csci5801.studentrecord.program.Department;
import edu.umn.csci5801.studentrecord.transcript.Course;
import edu.umn.csci5801.studentrecord.transcript.CourseArea;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by UBLUSSA on 12/9/13.
 */
public class FileAccessTest {
    @Test
    public void testGetStudentsJSON() throws Exception {
        GRADS grads = new GRADS("GRADS_Materials/Data/students.txt", "GRADS_Materials/Data/courses.txt", "GRADS_Materials/Data/users.txt");
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
        GRADS grads = new GRADS("GRADS_Materials/Data/students.txt", "GRADS_Materials/Data/courses.txt", "GRADS_Materials/Data/users.txt");
        List<StudentRecord> studentRecords;
        studentRecords = FileAccess.getStudentsJSON();
        // Set ID of first user to a different ID
        studentRecords.get(0).getStudent().setId("blust013");
        FileAccess.writeStudentsJSON(studentRecords);
        // Check files have been writen to
        studentRecords = FileAccess.getStudentsJSON();
        StudentRecord firstStudent=studentRecords.get(0);
        assertEquals(firstStudent.getStudent().getId(), "blust013");
        assertEquals(firstStudent.getDepartment(), Department.COMPUTER_SCIENCE);

        //reset the old value
        studentRecords.get(0).getStudent().setId("nguy0621");
        FileAccess.writeStudentsJSON(studentRecords);
    }

    @Test
    public void testGetCourseJSON() throws Exception {
        GRADS grads = new GRADS("GRADS_Materials/Data/students.txt", "GRADS_Materials/Data/courses.txt", "GRADS_Materials/Data/users.txt");
        List<Course> courses;
        courses = FileAccess.getCourseJSON();
        Course firstCourse=courses.get(0);
        assertEquals(firstCourse.getName(), "Operating Systems");
        assertEquals(firstCourse.getNumCredits(), "3");

        Course secondCourse=courses.get(1);
        assertEquals(secondCourse.getId(), "csci5104");
        assertEquals(secondCourse.getCourseArea(), CourseArea.ARCHITECTURE_SYSTEMS_SOFTWARE);

    }

    @Test
    public void testGetUserJSON() throws Exception {
        GRADS grads = new GRADS("GRADS_Materials/Data/students.txt", "GRADS_Materials/Data/courses.txt", "GRADS_Materials/Data/users.txt");
        List<Users> users;
        users = FileAccess.getUserJSON();
        Users firstSession=users.get(0);
        assertEquals(firstSession.getUser().getId(), "nguy0621");
        assertEquals(firstSession.getRole(), UserType.STUDENT);
        assertEquals(firstSession.getDepartment(), Department.COMPUTER_SCIENCE);
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
