package edu.umn.csci5801.db;

import edu.umn.csci5801.model.Course;
import edu.umn.csci5801.model.CourseArea;
import edu.umn.csci5801.model.Department;
import edu.umn.csci5801.model.Professor;
import edu.umn.csci5801.model.ProgressSummary;
import edu.umn.csci5801.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for parsing the JSON "database"
 * and converting entries into the equivalent Java Objects
 * or converting objects into database entries
 */
public class Parser {

    private String path;

    public Parser(String path) {
        this.path = path;
    }

    public Course findCourseByName(String courseName) {
        return null;  //TODO: replace with actual code
    }

    public Course findCourseByID(String courseID) {
        return null;  //TODO: replace with actual code
    }

    public Course findCourseByNumCredits(Integer numCredits) {
        return null;  //TODO: replace with actual code
    }

    public Course findCourseByArea(CourseArea courseArea) {
        return null;  //TODO: replace with actual code
    }

    public ProgressSummary findProgressSummarryByStudentID(String studentID) {
        return null;  //TODO: replace with actual code
    }

    public Student findStudentByID(String studentID) {
        return null;  //TODO: replace with actual code
    }

    public Student findStudentByName(String firstName, String lastName) {
        return null;  //TODO: replace with actual code
    }

    public List<Student> findStudentsByDepartment(Department department) {
        return new ArrayList<Student>();   //TODO: replace with actual code
    }

    public List<Student> findStudentsByAdvisor(Professor advisor) {
        return new ArrayList<Student>();   //TODO: replace with actual code
    }

}
