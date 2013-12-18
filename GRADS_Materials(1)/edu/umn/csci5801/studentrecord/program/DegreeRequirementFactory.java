package edu.umn.csci5801.studentrecord.program;

import edu.umn.csci5801.studentrecord.requirements.Milestone;
import edu.umn.csci5801.studentrecord.transcript.Course;
import edu.umn.csci5801.studentrecord.transcript.Grade;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ben on 12/17/13.
 */
public class DegreeRequirementFactory {

    private String requirementName;
    private List<DegreeRequirement> subRequirements;
    private List<Course> applicableCourses;
    private List<Milestone> requiredMilestones;
    private Double minGPA;
    private boolean isSNAllowed;
    private boolean mustTakeAllCourses;
    private boolean mayRepeatCoursesForCredit;
    private boolean onlyAllowCoursesThatPassSubReqs;
    private Integer minCredits;
    private Integer minCourseCount;
    private Integer minCourseLevel;
    private Grade minCourseGrade;
    private List<String> courseDeptsToExclude;
    private List<String> courseDeptsToInclude;
    private List<Course> coursesToExclude;

    public DegreeRequirementFactory() {

    }

    public DegreeRequirement make() {
        return new DegreeRequirement(
                requirementName,
                subRequirements,
                applicableCourses,
                requiredMilestones,
                minGPA,
                isSNAllowed,
                mustTakeAllCourses,
                mayRepeatCoursesForCredit,
                onlyAllowCoursesThatPassSubReqs,
                minCredits,
                minCourseCount,
                minCourseLevel,
                minCourseGrade,
                courseDeptsToExclude,
                courseDeptsToInclude,
                coursesToExclude
        );
    }


    public void setRequirementName(String requirementName) {
        this.requirementName = requirementName;
    }

    public void setSubRequirements(List<DegreeRequirement> subRequirements) {
        this.subRequirements = subRequirements;
    }

    public void setApplicableCourses(List<Course> applicableCourses) {
        this.applicableCourses = applicableCourses;
    }

    public void setRequiredMilestones(List<Milestone> requiredMilestones) {
        this.requiredMilestones = requiredMilestones;
    }

    public void setMinGPA(Double minGPA) {
        this.minGPA = minGPA;
    }

    public void setSNAllowed(boolean isSNAllowed) {
        this.isSNAllowed = isSNAllowed;
    }

    public void setMustTakeAllCourses(boolean mustTakeAllCourses) {
        this.mustTakeAllCourses = mustTakeAllCourses;
    }

    public void setMayRepeatCoursesForCredit(boolean mayRepeatCoursesForCredit) {
        this.mayRepeatCoursesForCredit = mayRepeatCoursesForCredit;
    }

    public void setMinCredits(Integer minCredits) {
        this.minCredits = minCredits;
    }

    public void setMinCourseCount(Integer minCourseCount) {
        this.minCourseCount = minCourseCount;
    }

    public void setMinCourseLevel(Integer minCourseLevel) {
        this.minCourseLevel = minCourseLevel;
    }

    public void setMinCourseGrade(Grade minCourseGrade) {
        this.minCourseGrade = minCourseGrade;
    }

    public void setCourseDeptsToExclude(List<String> courseDeptsToExclude) {
        this.courseDeptsToExclude = courseDeptsToExclude;
    }

    public void setCourseDeptsToInclude(List<String> courseDeptsToInclude) {
        this.courseDeptsToInclude = courseDeptsToInclude;
    }

    public void setCoursesToExclude(List<Course> coursesToExclude) {
        this.coursesToExclude = coursesToExclude;
    }

    public void setOnlyAllowCoursesThatPassSubReqs(boolean onlyAllow) {
        onlyAllowCoursesThatPassSubReqs = onlyAllow;
    }




}
