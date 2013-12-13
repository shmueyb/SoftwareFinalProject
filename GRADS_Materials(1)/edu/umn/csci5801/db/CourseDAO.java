package edu.umn.csci5801.db;

import edu.umn.csci5801.session.Users;
import edu.umn.csci5801.studentrecord.transcript.Course;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ben
 * Date: 12/8/13
 * Time: 4:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class CourseDAO {
    public static Course getCourseByID(String courseID) throws DatabaseAccessException{
        List<Course> courseList=null;

        courseList= FileAccess.getInstance().getCourseJSON();

        Course currentCourse=null;
        for (Course course: courseList){
            if (courseID.equals(course.getId())){
                currentCourse=course;
            }
        }

        return currentCourse;
    }
}
