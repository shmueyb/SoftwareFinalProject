package edu.umn.csci5801.studentrecord.program;

import edu.umn.csci5801.studentrecord.requirements.MilestoneSet;
import edu.umn.csci5801.studentrecord.requirements.RequirementCheckResult;
import edu.umn.csci5801.studentrecord.transcript.CourseTaken;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Ben Hagaman
 * @author Sam Blustin
 * @author Catherine Reeves
 * @author Xum Giang
 * @author Trang Nguyen
 *
 * DegreePlan.java
 *
 * This class represents a degree plan that students may have.
 */
public class DegreePlan {

    DegreeRequirement breadthRequirements;
    DegreeRequirement otherCourseRequirements;
    DegreeRequirement otherGPARequirements;
    DegreeRequirement milestones;

    Department department;
    Degree degree;

    /**
     * @param breadthRequirements the breadth requirements for the degree plan.
     * @param otherCourseRequirements the other course requirements for the degree plan.
     * @param otherGPARequirements the other GPA requirements for the degree plan.
     * @param milestones the milestones for the degree plan.
     * @param department the department that the degree plan is in.
     * @param degree the degree that the degree plan is for.
     */
    public DegreePlan (
            DegreeRequirement breadthRequirements,
            DegreeRequirement otherCourseRequirements,
            DegreeRequirement otherGPARequirements,
            DegreeRequirement milestones,
            Department department,
            Degree degree) {

        this.breadthRequirements = breadthRequirements;
        this.otherCourseRequirements = otherCourseRequirements;
        this.otherGPARequirements = otherGPARequirements;
        this.milestones = milestones;
        this.department = department;
        this.degree = degree;
    }

    /**
     * Checks if the student passed the degree plan, given the courses taken.
     *
     * @param coursesTaken the courses that the students has taken.
     * @param milestonesPassed milestones that the student has passed.
     * @return true if the student passed all requirements. Otherwise returns false.
     */
    public boolean getIsStudentPassed(List<CourseTaken> coursesTaken, List<MilestoneSet> milestonesPassed) {
        if (! breadthRequirements.checkIsPassed(coursesTaken, milestonesPassed))
            return false;
        if (! otherCourseRequirements.checkIsPassed(coursesTaken, milestonesPassed))
            return false;
        if (! otherGPARequirements.checkIsPassed(coursesTaken, milestonesPassed))
            return false;
        if (! milestones.checkIsPassed(coursesTaken, milestonesPassed))
            return false;

        return true;
    }

    /**
     * Generates and returns a requirementCheckResult list for the courses taken and milestones passed.
     *
     * @param coursesTaken the courses that the student has taken.
     * @param milestonesPassed milestones that the student has passed.
     * @return List of results from the progress check.
     */
    public List<RequirementCheckResult> generateRequirementCheckResults(List<CourseTaken> coursesTaken,
                                                                        List<MilestoneSet> milestonesPassed) {

        List<RequirementCheckResult> requirementCheckResultList = new ArrayList<RequirementCheckResult>();
        List<RequirementCheckResult> resultsToAdd;

        resultsToAdd = breadthRequirements.generateRequirementCheckResults(coursesTaken, milestonesPassed);

        for(RequirementCheckResult result: resultsToAdd) {
            requirementCheckResultList.add(result);
        }

        resultsToAdd = otherCourseRequirements.generateRequirementCheckResults(coursesTaken, milestonesPassed);

        for(RequirementCheckResult result: resultsToAdd) {
            requirementCheckResultList.add(result);
        }

        resultsToAdd = otherGPARequirements.generateRequirementCheckResults(coursesTaken, milestonesPassed);

        for(RequirementCheckResult result: resultsToAdd) {
            requirementCheckResultList.add(result);
        }

        resultsToAdd = milestones.generateRequirementCheckResults(coursesTaken, milestonesPassed);

        for(RequirementCheckResult result: resultsToAdd) {
            requirementCheckResultList.add(result);
        }

        return requirementCheckResultList;
    }

    /**
     * returns the degree that this plan is for.
     * @return the degree
     */
    public Degree getDegree() {
        return degree;
    }

    /**
     * returns the department that this plan belongs to.
     * @return the department.
     */
    public Department getDepartment() {
        return department;
    }


}
