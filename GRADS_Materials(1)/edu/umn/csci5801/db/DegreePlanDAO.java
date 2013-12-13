package edu.umn.csci5801.db;

import edu.umn.csci5801.studentrecord.program.Degree;
import edu.umn.csci5801.studentrecord.program.DegreePlan;
import edu.umn.csci5801.studentrecord.program.DegreeRequirement;
import edu.umn.csci5801.studentrecord.program.Department;
import edu.umn.csci5801.studentrecord.requirements.Milestone;
import edu.umn.csci5801.studentrecord.transcript.Course;
import edu.umn.csci5801.studentrecord.transcript.Grade;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ben
 * Date: 12/12/13
 * Time: 2:01 PM
 * To change this template use File | Settings | File Templates.
 *
 * We decided to hard code the requirements as there was no direction that a new requirements file can be passed
 * in, and there are no controls from the interface to allow for this updating.  Otherwise, we would have created
 * a JSON formatted file that could be updated or swapped.
 */
public class DegreePlanDAO {

    private static List<DegreePlan> getAllDegreePlans() {
        List<DegreePlan> allPlans = new ArrayList<DegreePlan>();

        //TODO

        return allPlans;
    }

    public static DegreePlan getDegreePlanByDepartmentAndDegree(Department dept, Degree degree) {
        List<DegreePlan> allPlans = getAllDegreePlans();
        DegreePlan matchingPlan = new DegreePlan();//TODO: don't actually make a new degree plan, fill in method

        //todo filter out correct degree plan

        return matchingPlan;
    }

    public static DegreePlan getDegreePhDRequirements(){
        DegreeRequirement breadthRequirements;
        DegreeRequirement otherCourseRequirements;
        DegreeRequirement otherGPARequirements;
        DegreeRequirement milestones;
        DegreeRequirement(
                String requirementName,
                List<DegreeRequirement> subRequirements,
                List< Course > applicableCourses,
                List< Milestone > requiredMilestones,
                Double minGPA,
                Boolean isSNAllowed,
                Boolean mustTakeAllCourses,
                Boolean mayRepeatCoursesForCredit,
                Integer minCredits,
                Integer minCourseCount,
                Integer minCourseLevel,
                Grade minCourseGrade);

        DegreePlan phDDegreePlan = new DegreePlan();

        return phDDegreePlan;
    }
}
