package edu.umn.csci5801.studentrecord.program;

import edu.umn.csci5801.studentrecord.requirements.MilestoneSet;
import edu.umn.csci5801.studentrecord.requirements.RequirementCheckResult;
import edu.umn.csci5801.studentrecord.transcript.CourseTaken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ben
 * Date: 12/12/13
 * Time: 12:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class DegreePlan {

    DegreeRequirement breadthRequirements;
    DegreeRequirement otherCourseRequirements;
    DegreeRequirement otherGPARequirements;
    DegreeRequirement milestones;

    Department department;
    Degree degree;

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

    public Degree getDegree() {
        return degree;
    }

    public Department getDepartment() {
        return department;
    }


}
