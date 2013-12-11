package edu.umn.csci5801.db;

import edu.umn.csci5801.GRADS;
import edu.umn.csci5801.studentrecord.StudentRecord;
import edu.umn.csci5801.studentrecord.program.Department;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by UBLUSSA on 12/10/13.
 */
public class StudentDAOTest {
    @Test
    public void testUpdateStudentRecord() throws Exception {
        GRADS grads = new GRADS("GRADS_Materials/Data/students.txt", "GRADS_Materials/Data/courses.txt", "GRADS_Materials/Data/users.txt");
        List<StudentRecord> studentRecords;
        studentRecords = StudentDAO.getAllStudents(grads.getStudentRecords());
        studentRecords.get(0).getStudent().setFirstName("Sammy");
        StudentDAO.updateStudentRecord("nguy0621", studentRecords.get(0), grads.getStudentRecords());
        studentRecords = StudentDAO.getAllStudents(grads.getStudentRecords());
        StudentRecord firstStudent=studentRecords.get(0);
        assertEquals(firstStudent.getStudent().getFirstName(), "Sammy");
         //reset
        studentRecords.get(0).getStudent().setFirstName("Luan");
        StudentDAO.updateStudentRecord("nguy0621", studentRecords.get(0), grads.getStudentRecords() );

    }

    @Test
    public void testGetAllStudents() throws Exception {
        GRADS grads = new GRADS("GRADS_Materials/Data/students.txt", "GRADS_Materials/Data/courses.txt", "GRADS_Materials/Data/users.txt");
        List<StudentRecord> studentRecords;
        studentRecords = StudentDAO.getAllStudents(grads.getStudentRecords());
        StudentRecord firstStudent=studentRecords.get(0);
        assertEquals(firstStudent.getStudent().getFirstName(), "Luan");
        assertEquals(firstStudent.getDepartment(), Department.COMPUTER_SCIENCE);

        StudentRecord secondStudent=studentRecords.get(1);
        assertEquals(secondStudent.getStudent().getFirstName(), "Greg");
        assertEquals(secondStudent.getDepartment(), null);
    }

    @Test
    public void testGetStudentsByDepartment() throws Exception {
        GRADS grads = new GRADS("GRADS_Materials/Data/students.txt", "GRADS_Materials/Data/courses.txt", "GRADS_Materials/Data/users.txt");
        List<StudentRecord> studentRecordsInDepartment;
        studentRecordsInDepartment = StudentDAO.getStudentsByDepartment(grads.getStudentRecords(), Department.COMPUTER_SCIENCE);
        StudentRecord firstStudent=studentRecordsInDepartment.get(0);
        assertEquals(firstStudent.getStudent().getFirstName(), "Luan");
        assertEquals(firstStudent.getDepartment(), Department.COMPUTER_SCIENCE);
        assertEquals(1,studentRecordsInDepartment.size());
    }

    @Test
    public void testGetStudentByID() throws Exception {
        GRADS grads = new GRADS("GRADS_Materials/Data/students.txt", "GRADS_Materials/Data/courses.txt", "GRADS_Materials/Data/users.txt");
        StudentRecord student = StudentDAO.getStudentByID(grads.getStudentRecords(), "blust013" );
        assertEquals(student.getStudent().getFirstName(), "Sam");
        assertEquals(student.getDepartment(), Department.MATH);

    }
}
