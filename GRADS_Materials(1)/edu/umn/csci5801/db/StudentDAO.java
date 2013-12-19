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
 * @author Ben Hagaman
 * @author Sam Blustin
 * @author Catherine Reeves
 * @author Xum Giang
 * @author Trang Nguyen
 *
 * StudentDAO.java
 *
 * This class abstracts the students database information into Student objects.
 * There are general methods for interacting with the database.
 */
public class StudentDAO {

    /**
     * Updates the student record of the given student to the record given in the database.
     *
     * @param studentID the ID of the student, for whom we wish to update the student record.
     * @param record the record we wish to update to.
     * @throws DatabaseAccessException if updating the database failed.
     */
    public static void updateStudentRecord(String studentID, StudentRecord record) throws DatabaseAccessException {
        List<StudentRecord> allStudents = getAllStudents();

        for (int counter=0; counter < allStudents.size(); counter++){

            if (studentID.equals(allStudents.get(counter).getStudent().getId())){
                allStudents.remove(counter);
                allStudents.add(counter,record);
            }
        }
        FileAccess.getInstance().writeStudentsJSON(allStudents);
    }

    /**
     * Returns the list of all students in the students database.
     *
     * @return List of all students
     * @throws DatabaseAccessException if there was an error in querying the database.
     */
    public static List<StudentRecord> getAllStudents() throws DatabaseAccessException {
        List<StudentRecord> studentList;
        studentList = FileAccess.getInstance().getStudentsJSON();

        return studentList;
    }

    /**
     * Returns all students in the supplied department.
     *
     * @param dept the department, for which we wish to generate a list of all students.
     * @return List of students in the given department.
     * @throws DatabaseAccessException if there was an error in querying the database.
     */
    public static List<StudentRecord> getStudentsByDepartment(Department dept) throws DatabaseAccessException {
        List<StudentRecord> fullStudentList= getAllStudents();
        List<StudentRecord> filteredStudentList = new ArrayList<StudentRecord>();

        for(StudentRecord student: fullStudentList) {
            if (student.getDepartment() == dept) {
                filteredStudentList.add(student);
            }
        }

        return filteredStudentList;
    }

    /**
     * Returns the student with the supplied ID, if they exist in the database.
     *
     * @param studentID the ID of the student we wish to retrieve the record for.
     * @return StudentRecord for the given student id.
     * @throws DatabaseAccessException if there was an error in querying the databse.
     */
    public static StudentRecord getStudentByID(String studentID) throws DatabaseAccessException {
        List<StudentRecord> fullStudentList = getAllStudents();

        for (StudentRecord studentRecord: fullStudentList) {
            if (studentRecord.getStudent().getId().equals(studentID)) {
                return studentRecord;
            }
        }

        throw new DatabaseAccessException("Student record not found for " + studentID);
    }
}


