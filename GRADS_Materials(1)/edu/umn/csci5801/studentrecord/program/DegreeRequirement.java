package edu.umn.csci5801.studentrecord.program;

import edu.umn.csci5801.studentrecord.requirements.Milestone;
import edu.umn.csci5801.studentrecord.transcript.Course;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ben
 * Date: 12/12/13
 * Time: 12:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class DegreeRequirement {

    private List<DegreeRequirement> subRequirements;
    private Double minGPA;
    private boolean isAFAllowed = true;
    private boolean isSNAllowed = true;
    private boolean mustTakeAllCourses = false;
    private boolean mayRepeatCoursesForCredit = false;
    private Integer minCredits;
    private Integer minCourseCount;
    private List<Course> applicableCourses;
    private Integer minCourseLevel;
    private Integer minCourseGrade;
    private List<Milestone> milestones;

    public DegreeRequirement() {

    }
}
