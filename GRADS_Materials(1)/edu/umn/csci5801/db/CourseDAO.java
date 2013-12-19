package edu.umn.csci5801.db;


import edu.umn.csci5801.studentrecord.transcript.Course;

import java.util.List;

/**
 * @author Ben Hagaman
 * @author Sam Blustin
 * @author Catherine Reeves
 * @author Xum Giang
 * @author Trang Nguyen
 *
 * CourseDAO.java
 *
 * This class abstracts the course database information into Course objects.
 * There are general methods for interacting with the database.
 */
public class CourseDAO {

    /**
     * Retrieves the course object in the course database corresponding to a courseID, if it exists.
     *
     * @param courseID the courseID of the course we wish to retrieve.
     * @return the Course we wish to retrieve.
     * @throws DatabaseAccessException If the course is not found,
     *         or there is a problem with accessing the database.
     */
    public static Course getCourseByID(String courseID) throws DatabaseAccessException{
        List<Course> courseList;

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
