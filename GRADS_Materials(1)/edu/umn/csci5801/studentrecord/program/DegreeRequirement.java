package edu.umn.csci5801.studentrecord.program;

import edu.umn.csci5801.studentrecord.requirements.Milestone;
import edu.umn.csci5801.studentrecord.requirements.MilestoneSet;
import edu.umn.csci5801.studentrecord.transcript.Course;
import edu.umn.csci5801.studentrecord.transcript.CourseTaken;
import edu.umn.csci5801.studentrecord.transcript.Grade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ben
 * Date: 12/12/13
 * Time: 12:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class DegreeRequirement {

    private final List<DegreeRequirement> subRequirements;
    private final List<Course> applicableCourses;
    private final List<Milestone> requiredMilestones;
    private final Double minGPA;
    private final boolean isSNAllowed;
    private final boolean mustTakeAllCourses;
    private final boolean mayRepeatCoursesForCredit;
    private final Integer minCredits;
    private final Integer minCourseCount;
    private final Integer minCourseLevel;
    private final Grade minCourseGrade;

    public DegreeRequirement(
            List<DegreeRequirement> subRequirements,
            List<Course> applicableCourses,
            List<Milestone> requiredMilestones,
            Double minGPA,
            Boolean isSNAllowed,
            Boolean mustTakeAllCourses,
            Boolean mayRepeatCoursesForCredit,
            Integer minCredits,
            Integer minCourseCount,
            Integer minCourseLevel,
            Grade minCourseGrade) {

        this.subRequirements = subRequirements;
        this.applicableCourses = applicableCourses;
        this.requiredMilestones = requiredMilestones;

        this.minGPA = (minGPA == null) ? 0.0 : minGPA;
        this.isSNAllowed = (isSNAllowed == null) ? true : isSNAllowed;
        this.mustTakeAllCourses = (mustTakeAllCourses == null) ? false : mustTakeAllCourses;
        this.mayRepeatCoursesForCredit = (mayRepeatCoursesForCredit == null) ? false : mayRepeatCoursesForCredit;
        this.minCredits = (minCredits == null) ? 0 : minCredits;
        this.minCourseCount = (minCourseCount == null) ? 0 : minCourseCount;
        this.minCourseLevel = (minCourseLevel == null) ? 0 : minCourseLevel;
        this.minCourseGrade = (minCourseGrade == null) ? Grade.C : minCourseGrade;
    }


    protected List<Course> getAllApplicableCourses() {
        List<Course> allApplicableCourses = new ArrayList<Course>();
        Map<String, Course> courseOverlapChecker = new HashMap<String, Course>();

        for(Course course: applicableCourses) {
            if (! courseOverlapChecker.containsKey(course.getId())) {
                allApplicableCourses.add(course);
                courseOverlapChecker.put(course.getId(),  null);
            }
        }

        for (DegreeRequirement subRequirement: subRequirements) {
            for (Course course: subRequirement.getAllApplicableCourses()) {

                if (! courseOverlapChecker.containsKey(course.getId())) {
                    allApplicableCourses.add(course);
                }
            }
        }

        return allApplicableCourses;
    }

    public boolean checkIsPassed(List<CourseTaken> coursesTaken, List<MilestoneSet> milestonesPassed)
            throws NumberFormatException{
        for (DegreeRequirement subRequirement: subRequirements) {
            if (! subRequirement.checkIsPassed(coursesTaken, milestonesPassed))
                return false;
        }

        if (coursesTaken == null) {
            if (minCredits != 0)
                return false;
            if (minCourseCount != 0)
                return false;
            if (mustTakeAllCourses)
                return false;
        } else {
            if (! checkCoursesPassRequirement(coursesTaken))
                return false;
        }

        if (milestonesPassed == null) {
            if (! (requiredMilestones == null || requiredMilestones.isEmpty()))
                return false;
        } else {
            if (! checkMilestonesPassRequirement(milestonesPassed))
                return false;
        }

        return true;
    }

    private boolean checkMilestonesPassRequirement(List<MilestoneSet> milestonesPassed) {
        if (milestonesPassed == null)
            return false;

        Map<String, MilestoneSet> passedMilestoneMap = new HashMap<String, MilestoneSet>();

        for (MilestoneSet milestoneSet: milestonesPassed) {
            passedMilestoneMap.put(milestoneSet.getMilestone().name(), milestoneSet);
        }

        for (Milestone requiredMilestone : requiredMilestones) {
            if (! passedMilestoneMap.containsKey(requiredMilestone.name()))
                return false;
        }

        return true;
    }

    private boolean checkCoursesPassRequirement(List<CourseTaken> coursesTaken) {
        if (coursesTaken == null)
            return false;

        List<CourseTaken> acceptedCourses = new ArrayList<CourseTaken>();

        for (CourseTaken courseTaken: coursesTaken) {
            acceptedCourses.add(courseTaken);
        }


        if (! isSNAllowed) {
            acceptedCourses = filterOutSN(acceptedCourses);
        }

        if (minCourseLevel > 0) {
            acceptedCourses = filterOutByMinCourseLevel(acceptedCourses);
        }

        if (minCourseGrade.numericValue() <= 0) {
            acceptedCourses = filterOutByMinCourseGrade(acceptedCourses);
        }

        if (! mayRepeatCoursesForCredit) {
            acceptedCourses = filterOutDuplicateCourses(acceptedCourses);
        }


        if (minCourseCount > 0) {
            if (acceptedCourses.size() < minCourseCount)
                return false;
        }

        if (minCredits > 0){
            if (getNumberOfCredits(acceptedCourses) < minCredits)
                return false;
        }

        if (minGPA > 0) {
            if (calculateGPA(acceptedCourses) < minGPA)
                return false;
        }

        if (mustTakeAllCourses) {
            if (! getContainsAllApplicableCourses(acceptedCourses))
                return false;
        }

        return true;
    }

    private List<CourseTaken> filterOutSN(List<CourseTaken> coursesTaken) {
        List<CourseTaken> coursesToReturn = new ArrayList<CourseTaken>();

        for(CourseTaken courseTaken: coursesTaken) {
            if (! (courseTaken.getGrade().equals(Grade.S) || courseTaken.getGrade().equals(Grade.N))) {
                coursesToReturn.add(courseTaken);
            }
        }

        return coursesToReturn;
    }

    private List<CourseTaken> filterOutByMinCourseLevel(List<CourseTaken> coursesTaken) {
        ArrayList<CourseTaken> coursesToReturn = new ArrayList<CourseTaken>();

        for (CourseTaken courseTaken: coursesTaken) {
            if (new Integer(courseTaken.getCourse().getId()) >= minCourseLevel) {
                coursesToReturn.add(courseTaken);
            }
        }

        return coursesToReturn;
    }

    private List<CourseTaken> filterOutByMinCourseGrade(List<CourseTaken> coursesTaken) {
        ArrayList<CourseTaken> coursesToReturn = new ArrayList<CourseTaken>();

        for (CourseTaken courseTaken: coursesTaken) {
            if (courseTaken.getGrade().numericValue() >= minCourseGrade.numericValue()) {
                coursesToReturn.add(courseTaken);
            }
        }

        return coursesToReturn;
    }

    private List<CourseTaken> filterOutDuplicateCourses(List<CourseTaken> coursesTaken) {
        ArrayList<String> allCourseIDs = new ArrayList<String>();
        ArrayList<String> repeatedCourseIDs = new ArrayList<String>();
        ArrayList<CourseTaken> coursesToReturn = new ArrayList<CourseTaken>();
        Map<String, CourseTaken> acceptedCourses = new HashMap<String, CourseTaken>();

        for (CourseTaken courseTaken: coursesTaken) {
            String courseID = courseTaken.getCourse().getId();
            if (acceptedCourses.containsKey(courseID)) {
                repeatedCourseIDs.add(courseID);
                acceptedCourses.remove(courseID);
            } else {
                allCourseIDs.add(courseID);
            }
        }


        for (String courseID: repeatedCourseIDs) {
            CourseTaken courseToKeep = null;

            for(CourseTaken courseTaken: coursesTaken) {
                if (courseTaken.getCourse().getId().equals(courseID)) {
                    if (courseToKeep == null)
                        courseToKeep = courseTaken;

                    if (courseToKeep.getGrade().numericValue() < courseTaken.getGrade().numericValue()) {
                        courseToKeep = courseTaken;
                    }
                }
            }

            acceptedCourses.put(courseID, courseToKeep);
        }

        for (String courseID: allCourseIDs) {
            coursesToReturn.add(acceptedCourses.get(courseID));
        }

        return coursesToReturn;
    }

    private int getNumberOfCredits(List<CourseTaken> coursesTaken) throws NumberFormatException{
        int totalCredits = 0;

        for(CourseTaken courseTaken: coursesTaken) {
            totalCredits += new Integer(courseTaken.getCourse().getNumCredits());
        }

        return totalCredits;
    }

    private Double calculateGPA(List<CourseTaken> coursesTaken) throws NumberFormatException{
        Double undividedGPATotal = 0.0;
        int creditTotal = 0;

        for (CourseTaken courseTaken: coursesTaken) {
            int courseCredits = new Integer(courseTaken.getCourse().getNumCredits());
            creditTotal += courseCredits;
            undividedGPATotal += courseTaken.getGrade().numericValue() * courseCredits;
        }

        return undividedGPATotal / ((double) creditTotal);
    }

    private boolean getContainsAllApplicableCourses(List<CourseTaken> coursesTaken) {
        Map<String, CourseTaken> coursesTakenMap = new HashMap<String, CourseTaken>();

        for (CourseTaken courseTaken: coursesTaken) {
            if (! coursesTakenMap.containsKey(courseTaken.getCourse().getId()))
                coursesTakenMap.put(courseTaken.getCourse().getId(), courseTaken);
        }

        for (Course course: applicableCourses) {
            if (! coursesTakenMap.containsKey(course.getId()))
                return false;
        }

        return true;
    }

}
