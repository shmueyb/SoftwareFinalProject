package edu.umn.csci5801.studentrecord.program;

import edu.umn.csci5801.studentrecord.requirements.Milestone;
import edu.umn.csci5801.studentrecord.requirements.MilestoneSet;
import edu.umn.csci5801.studentrecord.requirements.RequirementCheckResult;
import edu.umn.csci5801.studentrecord.transcript.CheckResultDetails;
import edu.umn.csci5801.studentrecord.transcript.Course;
import edu.umn.csci5801.studentrecord.transcript.CourseTaken;
import edu.umn.csci5801.studentrecord.transcript.Grade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ben Hagaman
 * @author Sam Blustin
 * @author Catherine Reeves
 * @author Xum Giang
 * @author Trang Nguyen
 *
 * DegreeRequirement.java
 *
 * This class represents a degree requirement that may be part of a DegreePlan, or another requirement..
 */
public class DegreeRequirement {

    private final String requirementName;
    private final List<DegreeRequirement> subRequirements;
    private final List<Course> applicableCourses;
    private final List<Milestone> requiredMilestones;
    private final Double minGPA;
    private final boolean isSNAllowed;
    private final boolean mustTakeAllCourses;
    private final boolean mayRepeatCoursesForCredit;
    private final boolean onlyAllowCoursesThatPassSubReqs;
    private final Integer minCredits;
    private final Integer minCourseCount;
    private final Integer minCourseLevel;
    private final Grade minCourseGrade;
    private final List<String> courseDeptsToExclude;
    private final List<String> courseDeptsToInclude;
    private final List<Course> coursesToExclude;

    private List<String> errorLog = new ArrayList<String>();

    /**
     * Class which represents a set of degree requirements. May contain a list of
     * sub-requirements of the same class.
     *
     * @param requirementName name of the requirement
     * @param subRequirements list of subrequirements with their own restrictions
     * @param applicableCourses courses that are applicable to this requirement
     * @param requiredMilestones milestones that must be passed for this requirement to pass
     * @param minGPA minimum GPA for this requirement
     * @param isSNAllowed this requirement does not disallow S/N grades
     * @param mustTakeAllCourses true if all courses in applicableCourses must be taken and passed
     * @param mayRepeatCoursesForCredit true if courses will count if they are repeated
     * @param onlyAllowCoursesThatPassSubReqs true if we only allow courses that pass sub requirements to apply
     * @param minCredits minimum number of credits required
     * @param minCourseCount minimum number of courses required
     * @param minCourseLevel minimum course level that counts (eg. 5000 allows only courses >= 5000)
     * @param minCourseGrade minimum grade allowed for a course to count
     * @param courseDeptsToExclude course departments we don't want to count towards this requirement
     * @param courseDeptsToInclude the only course departments we will allow to count towards this requirement
     * @param coursesToExclude courses that don't count towards this requirement
     */
    public DegreeRequirement(
            String requirementName,
            List<DegreeRequirement> subRequirements,
            List<Course> applicableCourses,
            List<Milestone> requiredMilestones,
            Double minGPA,
            Boolean isSNAllowed,
            Boolean mustTakeAllCourses,
            Boolean mayRepeatCoursesForCredit,
            Boolean onlyAllowCoursesThatPassSubReqs,
            Integer minCredits,
            Integer minCourseCount,
            Integer minCourseLevel,
            Grade minCourseGrade,
            List<String> courseDeptsToExclude,
            List<String> courseDeptsToInclude,
            List<Course> coursesToExclude) {

        this.requirementName = requirementName == null ? "Requirement" : requirementName;
        this.subRequirements = subRequirements == null ? new ArrayList<DegreeRequirement>() : subRequirements;
        this.applicableCourses = applicableCourses == null? new ArrayList<Course>() : applicableCourses;
        this.requiredMilestones = requiredMilestones == null ? new ArrayList<Milestone>() : requiredMilestones;

        this.isSNAllowed = (isSNAllowed == null) ? true : isSNAllowed;
        this.mustTakeAllCourses = (mustTakeAllCourses == null) ? false : mustTakeAllCourses;
        this.mayRepeatCoursesForCredit = (mayRepeatCoursesForCredit == null) ? false : mayRepeatCoursesForCredit;
        this.onlyAllowCoursesThatPassSubReqs = (onlyAllowCoursesThatPassSubReqs == null) ? false : onlyAllowCoursesThatPassSubReqs;

        this.minGPA = (minGPA == null) ? 0.0 : minGPA;
        this.minCredits = (minCredits == null) ? 0 : minCredits;
        this.minCourseCount = (minCourseCount == null) ? 0 : minCourseCount;
        this.minCourseLevel = (minCourseLevel == null) ? 0 : minCourseLevel;
        this.minCourseGrade = (minCourseGrade == null) ? Grade.C : minCourseGrade;

        this.courseDeptsToExclude = (courseDeptsToExclude == null) ? new ArrayList<String>() : courseDeptsToExclude;
        this.courseDeptsToInclude = (courseDeptsToInclude == null) ? new ArrayList<String>() : courseDeptsToInclude;

        this.coursesToExclude = (coursesToExclude == null) ? new ArrayList<Course>() : coursesToExclude;
    }

    /**
     * Returns all courses in this requirement and all subrequirements. Discludes duplicates.
     *
     * @return List of Courses that apply to this requirement.
     */
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

    /**
     * Returns a list of courses that met this requirement, and all sub-requirements
     *
     * @return List of CourseTaken objects which meet this requirement and sub-requirements
     */
    protected List<CourseTaken> getPassingCourses(List<CourseTaken> coursesTaken) {

        List<CourseTaken> acceptedCourses = new ArrayList<CourseTaken>();
        Map<Integer, CourseTaken> overlapChecker = new HashMap<Integer, CourseTaken>();

        if (onlyAllowCoursesThatPassSubReqs) {
            for (DegreeRequirement subRequirement: subRequirements) {
                for(CourseTaken courseTaken: subRequirement.getPassingCourses(coursesTaken)) {
                    if(! overlapChecker.containsKey(courseTaken.hashCode())){
                        acceptedCourses.add(courseTaken);
                        overlapChecker.put(courseTaken.hashCode(), courseTaken);
                    }
                }
            }
        } else {
            acceptedCourses = coursesTaken;
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

        if (coursesToExclude != null && coursesToExclude.size() > 0) {
            acceptedCourses = filterOutCoursesToExclude(acceptedCourses);
        }

        if (courseDeptsToExclude != null && courseDeptsToExclude.size() > 0) {
            acceptedCourses = filterOutExcludedDepts(acceptedCourses);
        }

        if (courseDeptsToInclude != null && courseDeptsToInclude.size() > 0) {
            acceptedCourses = filterByIncludedDepts(acceptedCourses);
        }

        if (applicableCourses != null && applicableCourses.size() > 0) {
            acceptedCourses = filterToApplicableCourses(acceptedCourses);
        }

        return acceptedCourses;
    }

    /**
     * Returns the minimum courses that this requirement needs to pass.
     * @param originalList
     * @return
     */
    protected List<CourseTaken> getMinimumNecessaryCourses(List<CourseTaken> originalList) {
        //TODO
        return originalList;
    }

    /**
     * Generates a list of results depending on if the requirement, and subrequirements are passed.
     *
     * @param coursesTaken courses that the student has taken.
     * @param milestonesPassed milestones that the student has passed.
     * @return the list of results from the check.
     */
    public List<RequirementCheckResult> generateRequirementCheckResults(
            List<CourseTaken> coursesTaken,
            List<MilestoneSet> milestonesPassed) {

        List<RequirementCheckResult> requirementCheckResultList = new ArrayList<RequirementCheckResult>();

        for (DegreeRequirement requirement: subRequirements) {
            List<RequirementCheckResult> subResults =
                    requirement.generateRequirementCheckResults(coursesTaken, milestonesPassed);

            for(RequirementCheckResult result: subResults) {
                requirementCheckResultList.add(result);
            }
        }

        List<CourseTaken> applicableCourses = coursesTaken;

        if (! mayRepeatCoursesForCredit) {
            applicableCourses = filterOutDuplicateCourses(applicableCourses);
        }

        applicableCourses = getPassingCourses(applicableCourses);

        CheckResultDetails checkResultDetails = new CheckResultDetails();
        checkResultDetails.setGPA(new Float(calculateGPA(applicableCourses)));
        checkResultDetails.setCourses(applicableCourses);
        checkResultDetails.setOther(new ArrayList<String>());


        RequirementCheckResult resultForThisRequirement = new RequirementCheckResult(
                requirementName,
                checkIsPassed(getPassingCourses(applicableCourses), milestonesPassed),
                checkResultDetails);
        resultForThisRequirement.setErrorMsgs(errorLog);

        requirementCheckResultList.add(resultForThisRequirement);

        return requirementCheckResultList;
    }

    /**
     * returns true if the required milestones and other requirements are all passed by the student
     *
     * @param coursesTaken courses that have been taken by the student
     * @param milestonesPassed milestones that the student has passed
     * @return true if all of the required courses are taken, and they pass all requirements, and the
     *      student has passed all necessary milestones.
     * @throws NumberFormatException if one of the courses that have been taken (or passed as a course
     *      to simulate taking) has an invalid
     */
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

    /**
     * returns true if all required milestones are passed
     *
     * @param milestonesPassed list of milestones the student has passed
     *
     * @return true if all required milestones are passed.
     */
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

    /**
     * returns true if the courses taken meet all course requirements for this requirement
     *
     * @param coursesTaken list of courses taken by the student
     * @return true if the course requirements are met
     */
    private boolean checkCoursesPassRequirement(List<CourseTaken> coursesTaken) {

        List<CourseTaken> acceptedCourses = new ArrayList<CourseTaken>();

        if (onlyAllowCoursesThatPassSubReqs) {
            acceptedCourses = getPassingCourses(coursesTaken);
        } else {
            for (CourseTaken courseTaken: coursesTaken) {
                acceptedCourses.add(courseTaken);
            }
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

        if (coursesToExclude != null && coursesToExclude.size() > 0) {
            acceptedCourses = filterOutCoursesToExclude(acceptedCourses);
        }

        if (courseDeptsToExclude != null && courseDeptsToExclude.size() > 0) {
            acceptedCourses = filterOutExcludedDepts(acceptedCourses);
        }

        if (courseDeptsToInclude != null && courseDeptsToInclude.size() > 0) {
            acceptedCourses = filterByIncludedDepts(acceptedCourses);
        }

        if (applicableCourses != null && applicableCourses.size() > 0) {
            acceptedCourses = filterToApplicableCourses(acceptedCourses);
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

    /**
     * returns a filtered list, which filters out S/N courses from the passed in list
     *
     * @param originalList courses we wish to apply the filter to
     * @return the filtered list
     */
    private List<CourseTaken> filterOutSN(List<CourseTaken> originalList) {
        List<CourseTaken> coursesToReturn = new ArrayList<CourseTaken>();

        for(CourseTaken courseTaken: originalList) {
            if (! (courseTaken.getGrade().equals(Grade.S) || courseTaken.getGrade().equals(Grade.N))) {
                coursesToReturn.add(courseTaken);
            }
        }

        return coursesToReturn;
    }

    /**
     * returns a list of courses taken, with courses that don't meet the minimum
     * course level requirement out.
     *
     * @param originalList courses we wish to filter from
     * @return the filtered list
     */
    private List<CourseTaken> filterOutByMinCourseLevel(List<CourseTaken> originalList) {
        ArrayList<CourseTaken> coursesToReturn = new ArrayList<CourseTaken>();

        for (CourseTaken courseTaken: originalList) {
            String courseID = courseTaken.getCourse().getId();
            String courseNum = courseID.substring(courseID.length()-4);

            if (new Integer(courseNum) >= minCourseLevel) {
                coursesToReturn.add(courseTaken);
            }
        }

        return coursesToReturn;
    }

    /**
     * returns a list of courses taken, with courses that don't meet the minimum course
     * grade requirement out.
     *
     * @param originalList courses we wish to filter from
     * @return the filtered list
     */
    private List<CourseTaken> filterOutByMinCourseGrade(List<CourseTaken> originalList) {
        ArrayList<CourseTaken> coursesToReturn = new ArrayList<CourseTaken>();

        for (CourseTaken courseTaken: originalList) {
            if (courseTaken.getGrade().numericValue() >= minCourseGrade.numericValue()
                    && courseTaken.getGrade().numericValue() > 0) {

                coursesToReturn.add(courseTaken);
            }

            if (isSNAllowed && courseTaken.getGrade().equals(Grade.S)) {
                coursesToReturn.add(courseTaken);
            }
        }

        return coursesToReturn;
    }

    /**
     * returns a list of courses taken, with courses that are duplicates filtered out
     *
     * @param originalList courses we wish to filter from
     * @return the filtered list
     */
    private List<CourseTaken> filterOutDuplicateCourses(List<CourseTaken> originalList) {
        ArrayList<String> allCourseIDs = new ArrayList<String>();
        ArrayList<String> repeatedCourseIDs = new ArrayList<String>();
        ArrayList<CourseTaken> coursesToReturn = new ArrayList<CourseTaken>();
        Map<String, CourseTaken> acceptedCourses = new HashMap<String, CourseTaken>();

        for (CourseTaken courseTaken: originalList) {
            String courseID = courseTaken.getCourse().getId();
            if (acceptedCourses.containsKey(courseID)) {
                repeatedCourseIDs.add(courseID);
                acceptedCourses.remove(courseID);
            } else {
                acceptedCourses.put(courseID, courseTaken);
                allCourseIDs.add(courseID);
            }
        }


        for (String courseID: repeatedCourseIDs) {
            CourseTaken courseToKeep = null;

            for(CourseTaken courseTaken: originalList) {
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

    /**
     * Returns a list with the courses to exclude filtered out.
     *
     * @param originalList courses to filter from.
     * @return list of filtered courses.
     */
    private List<CourseTaken> filterOutCoursesToExclude(List<CourseTaken> originalList) {
        List<CourseTaken> coursesToReturn = new ArrayList<CourseTaken>();
        Map<String, Course> coursesToExcludeMap = new HashMap<String, Course>();

        for(Course course: coursesToExclude) {
            coursesToExcludeMap.put(course.getId(), course);
        }

        for(CourseTaken courseTaken: originalList) {
            if (! coursesToExcludeMap.containsKey(courseTaken.getCourse().getId())) {
                coursesToReturn.add(courseTaken);
            }
        }

        return coursesToReturn;
    }

    /**
     * Filters out courses with excluded departments.
     *
     * @param originalList the original list of courses.
     * @return the filtered list.
     */
    private List<CourseTaken> filterOutExcludedDepts(List<CourseTaken> originalList) {
        List<CourseTaken> coursesToReturn = new ArrayList<CourseTaken>();
        Map<String, String> deptsToExcludeMap = new HashMap<String, String>();

        for(String dept: courseDeptsToExclude) {
            deptsToExcludeMap.put(dept, null);
        }

        for(CourseTaken courseTaken: originalList) {
            String courseID = courseTaken.getCourse().getId();

            if (! deptsToExcludeMap.containsKey(courseID.substring(0, courseID.length()-4))) {
                coursesToReturn.add(courseTaken);
            }
        }

        return coursesToReturn;
    }

    /**
     * returns a list of courses, filtered to only include the included departments.
     *
     * @param originalList the original list of courses.
     * @return the filtered list of courses.
     */
    private List<CourseTaken> filterByIncludedDepts(List<CourseTaken> originalList) {
        List<CourseTaken> coursesToReturn = new ArrayList<CourseTaken>();
        Map<String, String> deptsToIncludeMap = new HashMap<String, String>();

        for(String dept: courseDeptsToInclude) {
            deptsToIncludeMap.put(dept, null);
        }

        for(CourseTaken courseTaken: originalList) {
            String courseID = courseTaken.getCourse().getId();

            if (deptsToIncludeMap.containsKey(courseID.substring(0, courseID.length()-4))) {
                coursesToReturn.add(courseTaken);
            }
        }

        return coursesToReturn;
    }

    /**
     * Returns a list of courses, filtered to only include courses from the applicable courses list.
     *
     * @param originalList the original list of courses.
     * @return the filtered list of courses.
     */
    private List<CourseTaken> filterToApplicableCourses(List<CourseTaken> originalList) {
        List<CourseTaken> coursesToReturn = new ArrayList<CourseTaken>();
        Map<String, Course> courseMap = new HashMap<String, Course>();

        for(Course course: applicableCourses) {
            courseMap.put(course.getId(), course);
        }

        for(CourseTaken courseTaken: originalList) {
            if (courseMap.containsKey(courseTaken.getCourse().getId()))
                coursesToReturn.add(courseTaken);
        }

        return coursesToReturn;
    }

    /**
     * returns the number of credits in the passed-in courses.
     *
     * @param coursesTaken courses we wish to sum the total credits of
     * @return sum of credits in coursesTaken
     * @throws NumberFormatException if one of the courses has an invalid number of credits (eg. a range 1-3)
     */
    private int getNumberOfCredits(List<CourseTaken> coursesTaken) throws NumberFormatException{
        int totalCredits = 0;

        for(CourseTaken courseTaken: coursesTaken) {
            totalCredits += new Integer(courseTaken.getCourse().getNumCredits());
        }

        return totalCredits;
    }

    /**
     * calculates the total gpa of the courses passed in
     *
     * @param coursesTaken courses that we wish to calculate the GPA of
     * @return the GPA calculated from all of the courses in the passed-in list
     * @throws NumberFormatException if one of the courses has an invalid number of credits (eg. a range 1-3)
     */
    private Double calculateGPA(List<CourseTaken> coursesTaken) throws NumberFormatException{

        List<CourseTaken> applicableCourses = getPassingCourses(coursesTaken);

        if (minCourseCount > 0) {
            applicableCourses = getBestCoursesForMinCount(coursesTaken);
        } else if (minCredits > 0) {
            applicableCourses = getBestCoursesForMinCredits(coursesTaken);
        }


        Double undividedGPATotal = 0.0;
        int creditTotal = 0;

        for (CourseTaken courseTaken: applicableCourses) {
            if (! (courseTaken.getGrade().equals(Grade.N)
                    || courseTaken.getGrade().equals(Grade.S)
                    || courseTaken.getGrade().equals(Grade._))) {

                int courseCredits = new Integer(courseTaken.getCourse().getNumCredits());
                creditTotal += courseCredits;
                undividedGPATotal += courseTaken.getGrade().numericValue() * courseCredits;
            }
        }

        return (creditTotal == 0) ? 0.0 : undividedGPATotal / ((double) creditTotal);
    }

    /**
     * returns true if every single course in this requirement has been taken
     *
     * @param coursesTaken list that we want to check the completeness of
     * @return true of coursesTaken contains all contents in applicableCourses
     */
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

    /**
     * Returns the best courses for the minimum required count.
     *
     * @param coursesTaken list of courses taken by the student.
     * @return List filtered to only include the minimum number of the best courses.
     */
    private List<CourseTaken> getBestCoursesForMinCount(List<CourseTaken> coursesTaken) {
        List<CourseTaken> AList = new ArrayList<CourseTaken>();
        List<CourseTaken> BList = new ArrayList<CourseTaken>();
        List<CourseTaken> CList = new ArrayList<CourseTaken>();
        List<CourseTaken> DList = new ArrayList<CourseTaken>();
        List<CourseTaken> FList = new ArrayList<CourseTaken>();
        List<CourseTaken> SList = new ArrayList<CourseTaken>();
        List<CourseTaken> _List = new ArrayList<CourseTaken>();

        List<CourseTaken> listToReturn = new ArrayList<CourseTaken>();

        for(CourseTaken courseTaken: coursesTaken) {
            if (courseTaken.getGrade().equals(Grade.A)){
                AList.add(courseTaken);
            } else if (courseTaken.getGrade().equals(Grade.B)){
                BList.add(courseTaken);
            } else if (courseTaken.getGrade().equals(Grade.C)){
                CList.add(courseTaken);
            } else if (courseTaken.getGrade().equals(Grade.D)){
                DList.add(courseTaken);
            } else if (courseTaken.getGrade().equals(Grade.F)){
                FList.add(courseTaken);
            }  else if (courseTaken.getGrade().equals(Grade.S)){
                SList.add(courseTaken);
            } else if (courseTaken.getGrade().equals(Grade._)){
                _List.add(courseTaken);
            }
        }

        for (int n=0; n < minCourseCount; n++) {
            if (AList.size() > 0) {
                listToReturn.add(AList.remove(0));
            } else if (BList.size() > 0) {
                listToReturn.add(BList.remove(0));
            } else if (CList.size() > 0) {
                listToReturn.add(CList.remove(0));
            } else if (SList.size() > 0) {
                listToReturn.add(SList.remove(0));
            } else if (_List.size() > 0) {
                listToReturn.add(_List.remove(0));
            } else if (DList.size() > 0) {
                listToReturn.add(DList.remove(0));
            } else if (FList.size() > 0) {
                listToReturn.add(FList.remove(0));
            } else {
                break;
            }
        }

        return listToReturn;
    }

    /**
     * Returns the best courses for the minimum required credits.
     *
     * @param coursesTaken list of courses taken by the student.
     * @return List filtered to only include the minimum number of the best courses.
     */
    private List<CourseTaken> getBestCoursesForMinCredits(List<CourseTaken> coursesTaken) {
        List<CourseTaken> AList = new ArrayList<CourseTaken>();
        List<CourseTaken> BList = new ArrayList<CourseTaken>();
        List<CourseTaken> CList = new ArrayList<CourseTaken>();
        List<CourseTaken> DList = new ArrayList<CourseTaken>();
        List<CourseTaken> FList = new ArrayList<CourseTaken>();
        List<CourseTaken> SList = new ArrayList<CourseTaken>();
        List<CourseTaken> _List = new ArrayList<CourseTaken>();

        List<CourseTaken> listToReturn = new ArrayList<CourseTaken>();

        for(CourseTaken courseTaken: coursesTaken) {
            if (courseTaken.getGrade().equals(Grade.A)){
                AList.add(courseTaken);
            } else if (courseTaken.getGrade().equals(Grade.B)){
                BList.add(courseTaken);
            } else if (courseTaken.getGrade().equals(Grade.C)){
                CList.add(courseTaken);
            } else if (courseTaken.getGrade().equals(Grade.D)){
                DList.add(courseTaken);
            } else if (courseTaken.getGrade().equals(Grade.F)){
                FList.add(courseTaken);
            }  else if (courseTaken.getGrade().equals(Grade.S)){
                SList.add(courseTaken);
            } else if (courseTaken.getGrade().equals(Grade._)){
                _List.add(courseTaken);
            }
        }

        int n = 0;

        while (n < minCredits) {
            CourseTaken courseToAdd;
            if (AList.size() > 0) {
                courseToAdd = AList.remove(0);
            } else if (BList.size() > 0) {
                courseToAdd = BList.remove(0);
            } else if (CList.size() > 0) {
                courseToAdd = CList.remove(0);
            } else if (SList.size() > 0) {
                courseToAdd = SList.remove(0);
            } else if (_List.size() > 0) {
                courseToAdd = _List.remove(0);
            } else if (DList.size() > 0) {
                courseToAdd = DList.remove(0);
            } else if (FList.size() > 0) {
                courseToAdd = FList.remove(0);
            } else {
                break;
            }
            listToReturn.add(courseToAdd);
            n += new Integer(courseToAdd.getCourse().getNumCredits());
        }

        return listToReturn;
    }



}
