package edu.umn.csci5801.db;

import com.google.gson.JsonObject;
import edu.umn.csci5801.session.Person;
import edu.umn.csci5801.session.Student;
import edu.umn.csci5801.studentrecord.program.Department;
import edu.umn.csci5801.studentrecord.transcript.StudentInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ben
 * Date: 12/7/13
 * Time: 10:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserDAO {

    public static Person getUserByID(String userID) {
        Person user = new Student(); //TODO: this should be replaced with the actual correct type;

        //TODO: fill in method

        return user;
    }

    public static List<StudentInfo> getStudentsByDepartment(Department department) {
        List<StudentInfo> studentList = new ArrayList<StudentInfo>();

        //TODO: fill in method

        return studentList;
    }
}
