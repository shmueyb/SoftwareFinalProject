package edu.umn.csci5801.db;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.umn.csci5801.studentrecord.StudentRecord;
import edu.umn.csci5801.studentrecord.program.Department;

import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ben
 * Date: 12/8/13
 * Time: 2:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class StudentDAO {

    public static void updateStudentRecord(String StudentID, StudentRecord record) {
        //TODO: get all students, find this one, replace with updated version, convert to JSON, save using FileAccess.java
    }

    public static List<StudentRecord> getAllStudents() throws DatabaseAccessException, FileNotFoundException {
        List<StudentRecord> studentList;
        Gson gson = new Gson();
        studentList = FileAccess.getStudentsJSON();

        Type type = new TypeToken<ArrayList<StudentRecord>>(){}.getType();

        //studentList = gson.fromJson(jsonString, type);

        return studentList;
    }

    public static List<StudentRecord> getStudentsByDepartment(Department dept) throws DatabaseAccessException, FileNotFoundException {
        List<StudentRecord> fullStudentList= getAllStudents();
        List<StudentRecord> filteredStudentList = new ArrayList<StudentRecord>();

        for(StudentRecord student: fullStudentList) {
            if (student.getDepartment() == dept) {
                filteredStudentList.add(student);
            }
        }

        return filteredStudentList;
    }

    public static StudentRecord getStudentByID(String studentID) throws DatabaseAccessException, FileNotFoundException {
        List<StudentRecord> fullStudentList = getAllStudents();

        for (StudentRecord studentRecord: fullStudentList) {
            if (studentRecord.getStudent().getId().equals(studentID)) {
                return studentRecord;
            }
        }

        throw new DatabaseAccessException("Student record not found for " + studentID);
    }
}
