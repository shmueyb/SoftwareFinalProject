package edu.umn.csci5801.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


import edu.umn.csci5801.studentrecord.transcript.*;
import org.junit.Before;
import org.junit.Test;

import edu.umn.csci5801.GRADS;
import edu.umn.csci5801.access.AccessDeniedException;
import edu.umn.csci5801.db.DatabaseAccessException;
import edu.umn.csci5801.session.InvalidUserException;
import edu.umn.csci5801.session.Student;
import edu.umn.csci5801.studentrecord.program.Degree;
import edu.umn.csci5801.studentrecord.program.Department;
import edu.umn.csci5801.studentrecord.requirements.Milestone;
import edu.umn.csci5801.studentrecord.requirements.MilestoneSet;
import edu.umn.csci5801.studentrecord.requirements.RequirementCheckResult;

/**
 */
public class GenerateProgressSummaryTest {
    private static GRADS grads;

    /**
     * Init Grads for usage
     */
    @Before
    public void initGrad() throws Exception {
        // creating test files
        StudentRecordFactory.instantiateTestDb();
        // init Grads
        if(grads == null){
            grads = new GRADS("GRADS_Materials/Data/TestStudents.txt", "GRADS_Materials/Data/courses.txt", "GRADS_Materials/Data/TestUsers.txt");
        }
    }

    private CheckResultDetails newCheckResultDetails(float gpa, List<CourseTaken> courses,List<String> other){
        CheckResultDetails details = new CheckResultDetails();
        details.setGPA(gpa);
        details.setCourses(courses);
        details.setOther(other);
        return details;
    }

    /**
     * testing if generatateProgressSummary() would return the correct result
     * @throws Exception
     */
    @Test
    public void testGenerateProgressSummary_PHDStudent() throws Exception {
        grads.setUser("nguy0621");
        ProgressSummary progressSummary = grads.generateProgressSummary("nguy0621");

        // Create a parallel Progress Summary
        assertEquals(new Student("Luan", "Nguyen", "nguy0621"), progressSummary.getStudent());
        assertEquals(Department.COMPUTER_SCIENCE, progressSummary.getDepartment());
        assertEquals(Degree.PHD, progressSummary.getDegreeSought());
        assertEquals(new Term(Semester.SPRING, 2008), progressSummary.getTermBegan());
        assertEquals(StudentRecordFactory.LuanAdvisors(),progressSummary.getAdvisors());
        assertEquals(StudentRecordFactory.LuanCommittee(), progressSummary.getCommittee());
        assertEquals(StudentRecordFactory.notes(), progressSummary.getNotes());
        List<RequirementCheckResult> requirementCheckResultsList = progressSummary.getRequirementCheckResults();
        List<String> others = new ArrayList<String>();
        List<String> errorM = new ArrayList<String>();
        // 0
        CheckResultDetails details = newCheckResultDetails((float) 4.0, StudentRecordFactory.LuanCoursesTaken(), others);
        RequirementCheckResult requirementCheckResult = new RequirementCheckResult("Theory and Algorithms",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(0).getName(), requirementCheckResult.getName());
//        assertEquals(requirementCheckResultsList.get(0).isPassed(), requirementCheckResult.isPassed());
//        assertEquals(requirementCheckResultsList.get(0).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 1
        details = newCheckResultDetails((float) 3.0, StudentRecordFactory.LuanCoursesTaken(), others);
        requirementCheckResult = new RequirementCheckResult("Architecture, Systems, and Software",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        // Name, GPA, Pass
        assertEquals(requirementCheckResultsList.get(1).getName(), requirementCheckResult.getName());
//        assertEquals(requirementCheckResultsList.get(1).isPassed(), requirementCheckResult.isPassed());
//        assertEquals(requirementCheckResultsList.get(1).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 2
        details = newCheckResultDetails((float) 0.0, StudentRecordFactory.LuanCoursesTaken(), others);
        requirementCheckResult = new RequirementCheckResult("Applications",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(2).getName(), requirementCheckResult.getName());
//        assertEquals(requirementCheckResultsList.get(2).isPassed(), requirementCheckResult.isPassed());
//        assertEquals(requirementCheckResultsList.get(2).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 3
        details = newCheckResultDetails((float) 3.6666667, StudentRecordFactory.LuanCoursesTaken(), others);
        requirementCheckResult = new RequirementCheckResult("Breadth Requirement",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(3).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(3).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(3).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 4
        details = newCheckResultDetails((float) 0.0, StudentRecordFactory.LuanCoursesTaken(), others);
        requirementCheckResult = new RequirementCheckResult("Thesis PHD",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(4).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(4).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(4).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 5
        details = newCheckResultDetails((float) 0.0, StudentRecordFactory.LuanCoursesTaken(), others);
        requirementCheckResult = new RequirementCheckResult("Colloquium",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(5).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(5).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(5).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 6
        details = newCheckResultDetails((float) 0.0, StudentRecordFactory.LuanCoursesTaken(), others);
        requirementCheckResult = new RequirementCheckResult("Out of Department",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(6).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(6).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(6).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 7
        details = newCheckResultDetails((float) 0.0, StudentRecordFactory.LuanCoursesTaken(), others);
        requirementCheckResult = new RequirementCheckResult("Introduction to Research",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(7).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(7).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(7).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 8
        details = newCheckResultDetails((float) 3.6666667, StudentRecordFactory.LuanCoursesTaken(), others);
        requirementCheckResult = new RequirementCheckResult("16 csci Credits",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(8).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(8).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(8).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 9
        details = newCheckResultDetails((float) 3.6666667, StudentRecordFactory.LuanCoursesTaken(), others);
        requirementCheckResult = new RequirementCheckResult("Total Credits",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(9).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(9).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(9).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 10
        details = newCheckResultDetails((float) 3.6666667, StudentRecordFactory.LuanCoursesTaken(), others);
        requirementCheckResult = new RequirementCheckResult("Other Course Requirements",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(10).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(10).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(10).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 11
        details = newCheckResultDetails((float) 3.6666667, StudentRecordFactory.LuanCoursesTaken(), others);
        requirementCheckResult = new RequirementCheckResult("Overall GPA",true,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(11).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(11).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(11).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 12
        details = newCheckResultDetails((float) 3.6666667, StudentRecordFactory.LuanCoursesTaken(), others);
        requirementCheckResult = new RequirementCheckResult("In Program GPA",true,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(12).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(12).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(12).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 13
        details = newCheckResultDetails((float) 3.6666667, StudentRecordFactory.LuanCoursesTaken(), others);
        requirementCheckResult = new RequirementCheckResult("Other GPA Requirements",true,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(13).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(13).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(13).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 14
        details = newCheckResultDetails((float) 0.0, StudentRecordFactory.LuanCoursesTaken(), others);
        requirementCheckResult = new RequirementCheckResult("Milestones",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(14).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(14).isPassed(), requirementCheckResult.isPassed());

    }

    /**
     * testing if generatateProgressSummary() would return the correct result
     * @throws Exception
     */
    @Test
    public void testGenerateProgressSummary_PHDStudent_SomeRequirementsCompleted() throws Exception {
        grads.setUser("phd333");
        ProgressSummary progressSummary = grads.generateProgressSummary("phd333");

        // Create a parallel Progress Summary
        assertEquals(new Student("PHD", "Student", "phd333"), progressSummary.getStudent());
        assertEquals(Department.COMPUTER_SCIENCE, progressSummary.getDepartment());
        assertEquals(Degree.PHD, progressSummary.getDegreeSought());
        assertEquals(new Term(Semester.SPRING, 2008), progressSummary.getTermBegan());
        assertEquals(StudentRecordFactory.LuanAdvisors(),progressSummary.getAdvisors());
        assertEquals(StudentRecordFactory.LuanCommittee(), progressSummary.getCommittee());
        assertEquals(StudentRecordFactory.notes(), progressSummary.getNotes());
        List<RequirementCheckResult> requirementCheckResultsList = progressSummary.getRequirementCheckResults();
        List<String> others = new ArrayList<String>();
        List<String> errorM = new ArrayList<String>();
        List<CourseTaken> courseTaken= StudentRecordFactory.PHDCoursesTaken();
        // 0
        CheckResultDetails details = newCheckResultDetails((float) 3.5, StudentRecordFactory.LuanCoursesTaken(), others);
        RequirementCheckResult requirementCheckResult = new RequirementCheckResult("Theory and Algorithms",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(0).getName(), requirementCheckResult.getName());
//        assertEquals(requirementCheckResultsList.get(0).isPassed(), requirementCheckResult.isPassed());
        //assertEquals(requirementCheckResultsList.get(0).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 1
        details = newCheckResultDetails((float) 3.5, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Architecture, Systems, and Software",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        // Name, GPA, Pass
        assertEquals(requirementCheckResultsList.get(1).getName(), requirementCheckResult.getName());
//        assertEquals(requirementCheckResultsList.get(1).isPassed(), requirementCheckResult.isPassed());
        //assertEquals(requirementCheckResultsList.get(1).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 2
        details = newCheckResultDetails((float) 4.0, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Applications",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(2).getName(), requirementCheckResult.getName());
//        assertEquals(requirementCheckResultsList.get(2).isPassed(), requirementCheckResult.isPassed());
        //assertEquals(requirementCheckResultsList.get(2).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 3
        details = newCheckResultDetails((float) 3.6, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Breadth Requirement",true,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(3).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(3).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(3).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 4
        details = newCheckResultDetails((float) 0.0, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Thesis PHD",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(4).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(4).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(4).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 5
        details = newCheckResultDetails((float) 4.0, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Colloquium",true,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(5).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(5).isPassed(), requirementCheckResult.isPassed());

        // 6
        details = newCheckResultDetails((float) 0.0, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Out of Department",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(6).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(6).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(6).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 7
        details = newCheckResultDetails((float) 4.0, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Introduction to Research",true,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(7).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(7).isPassed(), requirementCheckResult.isPassed());

        // 8
        details = newCheckResultDetails((float) 3.6, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("16 csci Credits",true,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(8).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(8).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(8).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 9
        details = newCheckResultDetails((float) 3.6, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Total Credits",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(9).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(9).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(9).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 10
        details = newCheckResultDetails((float) 3.6, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Other Course Requirements",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(10).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(10).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(10).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 11
        details = newCheckResultDetails((float) 3.6, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Overall GPA",true,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(11).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(11).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(11).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 12
        details = newCheckResultDetails((float) 3.6, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("In Program GPA",true,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(12).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(12).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(12).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 13
        details = newCheckResultDetails((float) 3.6, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Other GPA Requirements",true,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(13).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(13).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(13).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 14
        details = newCheckResultDetails((float) 0.0, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Milestones",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(14).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(14).isPassed(), requirementCheckResult.isPassed());

    }

    /**
     * testing if generatateProgressSummary() would return the correct result
     * @throws Exception
     */
    @Test
    public void testGenerateProgressSummary_MS_A() throws Exception {
        grads.setUser("gayxx067");
        ProgressSummary progressSummary = grads.generateProgressSummary("gayxx067");

        // Create a parallel Progress Summary
        assertEquals(new Student("Gregory", "Gay", "gayxx067").getId(), progressSummary.getStudent().getId());
        assertEquals(Department.COMPUTER_SCIENCE, progressSummary.getDepartment());
        assertEquals(Degree.MS_A, progressSummary.getDegreeSought());
        assertEquals(new Term(Semester.SPRING, 2009), progressSummary.getTermBegan());
        assertEquals(StudentRecordFactory.GregAdvisors(),progressSummary.getAdvisors());
        assertEquals(StudentRecordFactory.GregCommittee(), progressSummary.getCommittee());
        assertEquals(StudentRecordFactory.notes(), progressSummary.getNotes());
        List<RequirementCheckResult> requirementCheckResultsList = progressSummary.getRequirementCheckResults();
        List<String> others = new ArrayList<String>();
        List<String> errorM = new ArrayList<String>();
        List<CourseTaken> courseTaken= StudentRecordFactory.GregCoursesTaken();
        // 0
        CheckResultDetails details = newCheckResultDetails((float) 0.0, courseTaken, others);
        RequirementCheckResult requirementCheckResult = new RequirementCheckResult("Theory and Algorithms",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(0).getName(), requirementCheckResult.getName());
//        assertEquals(requirementCheckResultsList.get(0).isPassed(), requirementCheckResult.isPassed());
        //assertEquals(requirementCheckResultsList.get(0).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 1
        details = newCheckResultDetails((float) 3.5, courseTaken , others);
        requirementCheckResult = new RequirementCheckResult("Architecture, Systems, and Software",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        // Name, GPA, Pass
        assertEquals(requirementCheckResultsList.get(1).getName(), requirementCheckResult.getName());
//        assertEquals(requirementCheckResultsList.get(1).isPassed(), requirementCheckResult.isPassed());
        //assertEquals(requirementCheckResultsList.get(1).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 2
        details = newCheckResultDetails((float) 2.0, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Applications",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(2).getName(), requirementCheckResult.getName());
//        assertEquals(requirementCheckResultsList.get(2).isPassed(), requirementCheckResult.isPassed());
        //assertEquals(requirementCheckResultsList.get(2).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 3
        details = newCheckResultDetails((float) 3.0, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Breadth Requirement",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(3).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(3).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(3).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 4
        details = newCheckResultDetails((float) 0.0, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Thesis MS",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(4).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(4).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(4).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 5
        details = newCheckResultDetails((float) 0.0, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Colloquium",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(5).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(5).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(5).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 6
        details = newCheckResultDetails((float) 0.0, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("PHD Level Courses",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(6).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(6).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(6).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 7
        details = newCheckResultDetails((float) 3.0, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Total Credits",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(7).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(7).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(7).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 8
        details = newCheckResultDetails((float) 3.0, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("16 csci Credits",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(8).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(8).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(8).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 9
        details = newCheckResultDetails((float) 3.0, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("22 Course Credits",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(9).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(9).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(9).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 10
        details = newCheckResultDetails((float) 3.0, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Course Credits",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(10).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(10).isPassed(), requirementCheckResult.isPassed());
        // 11
        details = newCheckResultDetails((float) 3.0, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Other Course Requirements",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(11).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(11).isPassed(), requirementCheckResult.isPassed());
        // 12
        details = newCheckResultDetails((float) 3.0, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Overall GPA",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(12).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(12).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(12).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 13
        details = newCheckResultDetails((float) 3.0, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("In Program GPA",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(13).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(13).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(13).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 14
        details = newCheckResultDetails((float) 3.0, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Other GPA Requirements",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(14).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(14).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(14).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 15
        details = newCheckResultDetails((float) 0.0, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Milestones",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(15).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(15).isPassed(), requirementCheckResult.isPassed());


    }

    /**
     * testing if generatateProgressSummary() would return the correct result
     * @throws Exception
     */
    @Test
    public void testGenerateProgressSummary_MS_B() throws Exception {
        grads.setUser("desil1337");
        ProgressSummary progressSummary = grads.generateProgressSummary("desil1337");

        // Create a parallel Progress Summary
        assertEquals(new Student("Ian", "De Silva", "desil1337"), progressSummary.getStudent());
        assertEquals(Department.COMPUTER_SCIENCE, progressSummary.getDepartment());
        assertEquals(Degree.MS_B, progressSummary.getDegreeSought());
        assertEquals(new Term(Semester.SPRING, 2010), progressSummary.getTermBegan());
        assertEquals(StudentRecordFactory.IanAdvisors(),progressSummary.getAdvisors());
        assertEquals(StudentRecordFactory.IanCommittee(), progressSummary.getCommittee());
        assertEquals(StudentRecordFactory.notes(), progressSummary.getNotes());
        List<RequirementCheckResult> requirementCheckResultsList = progressSummary.getRequirementCheckResults();
        List<String> others = new ArrayList<String>();
        List<String> errorM = new ArrayList<String>();
        List<CourseTaken> courseTaken= StudentRecordFactory.IanCoursesTaken();
        // 0
        CheckResultDetails details = newCheckResultDetails((float) 4.0, courseTaken, others);
        RequirementCheckResult requirementCheckResult = new RequirementCheckResult("Theory and Algorithms",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(0).getName(), requirementCheckResult.getName());
//        assertEquals(requirementCheckResultsList.get(0).isPassed(), requirementCheckResult.isPassed());
//        assertEquals(requirementCheckResultsList.get(0).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 1
        details = newCheckResultDetails((float) 3.0, courseTaken , others);
        requirementCheckResult = new RequirementCheckResult("Architecture, Systems, and Software",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        // Name, GPA, Pass
        assertEquals(requirementCheckResultsList.get(1).getName(), requirementCheckResult.getName());
//        assertEquals(requirementCheckResultsList.get(1).isPassed(), requirementCheckResult.isPassed());
//        assertEquals(requirementCheckResultsList.get(1).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 2
        details = newCheckResultDetails((float) 3.0, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Applications",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(2).getName(), requirementCheckResult.getName());
//        assertEquals(requirementCheckResultsList.get(2).isPassed(), requirementCheckResult.isPassed());
//        assertEquals(requirementCheckResultsList.get(2).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 3
        details = newCheckResultDetails((float) 3.3333333, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Breadth Requirement",true,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(3).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(3).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(3).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 4
        details = newCheckResultDetails((float) 4.0, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Plan B Project",true,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(4).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(4).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(4).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 5
        details = newCheckResultDetails((float) 4.0, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Colloquium",true,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(5).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(5).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(5).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 6
        details = newCheckResultDetails((float) 0.0, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("PHD Level Courses",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(6).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(6).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(6).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 7
        details = newCheckResultDetails((float) 3.5384615, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("16 csci Credits",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(7).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(7).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(7).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 8
        details = newCheckResultDetails((float) 3.5384615, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("31 Course Credits",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(8).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(8).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(8).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 9
        details = newCheckResultDetails((float) 3.5384615, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Total Credits",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(9).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(9).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(9).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 10
        details = newCheckResultDetails((float) 3.5384615, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Other Course Requirements",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(10).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(10).isPassed(), requirementCheckResult.isPassed());
        // 11
        details = newCheckResultDetails((float) 3.5384615, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Overall GPA",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(11).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(11).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(11).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 12
        details = newCheckResultDetails((float) 3.5384615, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("In Program GPA",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(12).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(12).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(12).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 13
        details = newCheckResultDetails((float) 3.5384615, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Other GPA Requirements",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(13).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(13).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(13).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 14
        details = newCheckResultDetails((float) 0.0, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Milestones",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(14).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(14).isPassed(), requirementCheckResult.isPassed());


    }

    /**
     * testing if generatateProgressSummary() would return the correct result
     * @throws Exception
     */
    @Test
    public void testGenerateProgressSummary_MS_C() throws Exception {
        grads.setUser("1111");
        ProgressSummary progressSummary = grads.generateProgressSummary("1111");

        // Create a parallel Progress Summary
        assertEquals(new Student("Catherine", "Reed", "1111"), progressSummary.getStudent());
        assertEquals(Department.COMPUTER_SCIENCE, progressSummary.getDepartment());
        assertEquals(Degree.MS_C, progressSummary.getDegreeSought());
        assertEquals(new Term(Semester.SPRING, 2011), progressSummary.getTermBegan());
        assertEquals(StudentRecordFactory.CatherineAdvisors(),progressSummary.getAdvisors());
        assertEquals(StudentRecordFactory.CatherineCommittee(), progressSummary.getCommittee());
        assertEquals(StudentRecordFactory.notes(), progressSummary.getNotes());
        List<RequirementCheckResult> requirementCheckResultsList = progressSummary.getRequirementCheckResults();
        List<String> others = new ArrayList<String>();
        List<String> errorM = new ArrayList<String>();
        List<CourseTaken> courseTaken= StudentRecordFactory.CatherineCoursesTaken();
        // 0  Take all Theory Class
        CheckResultDetails details = newCheckResultDetails((float) 3.5, courseTaken, others);
        RequirementCheckResult requirementCheckResult = new RequirementCheckResult("Theory and Algorithms",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(0).getName(), requirementCheckResult.getName());
//        assertEquals(requirementCheckResultsList.get(0).isPassed(), requirementCheckResult.isPassed());
        //assertEquals(requirementCheckResultsList.get(0).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 1
        details = newCheckResultDetails((float) 0.0, courseTaken , others);
        requirementCheckResult = new RequirementCheckResult("Architecture, Systems, and Software",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        // Name, GPA, Pass
        assertEquals(requirementCheckResultsList.get(1).getName(), requirementCheckResult.getName());
//        assertEquals(requirementCheckResultsList.get(1).isPassed(), requirementCheckResult.isPassed());
        //assertEquals(requirementCheckResultsList.get(1).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 2
        details = newCheckResultDetails((float) 4.0, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Applications",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(2).getName(), requirementCheckResult.getName());
//        assertEquals(requirementCheckResultsList.get(2).isPassed(), requirementCheckResult.isPassed());
        //assertEquals(requirementCheckResultsList.get(2).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 3  //Only calculate for top class in each category
        details = newCheckResultDetails((float) 4.0, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Breadth Requirement",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(3).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(3).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(3).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 4
        details = newCheckResultDetails((float) 0.0, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Colloquium",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(5).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(5).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(5).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 5
        details = newCheckResultDetails((float) 0.0, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("PHD Level Courses - Plan C",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(5).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(5).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(5).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 6
        details = newCheckResultDetails((float) 3.6666667, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("16 csci Credits",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(6).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(6).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(6).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 7
        details = newCheckResultDetails((float) 3.6666667, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("31 Course Credits",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(7).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(7).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(7).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 8
        details = newCheckResultDetails((float) 3.6666667, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Total Credits",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(8).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(8).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(8).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 9
        details = newCheckResultDetails((float) 3.6666667, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Other Course Requirements",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(9).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(9).isPassed(), requirementCheckResult.isPassed());
        // 10
        details = newCheckResultDetails((float) 3.5384615, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Overall GPA",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(10).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(10).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(10).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 11
        details = newCheckResultDetails((float) 3.6666667, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("In Program GPA",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(11).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(11).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(11).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 12
        details = newCheckResultDetails((float) 3.6666667, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Other GPA Requirements",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(12).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(12).isPassed(), requirementCheckResult.isPassed());
        assertEquals(requirementCheckResultsList.get(12).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
        // 13
        details = newCheckResultDetails((float) 0.0, courseTaken, others);
        requirementCheckResult = new RequirementCheckResult("Milestones",false,details);
        requirementCheckResult.setErrorMsgs(errorM);
        assertEquals(requirementCheckResultsList.get(13).getName(), requirementCheckResult.getName());
        assertEquals(requirementCheckResultsList.get(13).isPassed(), requirementCheckResult.isPassed());

    }
    /* checking to see if a class exists in the list of RequirementCheckResults
     * @param courseName
     * @param results
     * @return
     */
    private boolean checkForCourseName(String courseName, List< RequirementCheckResult > results) {
        for(RequirementCheckResult r : results) {
            if(courseName.equals(r.getName())) {
                return true;
            }
        }
        return false;

    }


    /**
     * testing if the method would handle InvalidUser
     * @throws DatabaseAccessException
     * @throws AccessDeniedException
     */
    @Test
    public void testGenerateProgressSummary_InvalidStudent_AsGPC() throws FileNotFoundException, AccessDeniedException, InvalidUserException {
        try {
            grads.setUser("smith0001");
        } catch (DatabaseAccessException e) {
            e.printStackTrace();
        }
        try {
            ProgressSummary progressSummary = grads.generateProgressSummary("InvalidUser");
            fail();
        } catch ( DatabaseAccessException d) {

        }
    }

    /**
     * Test that the student can get their Progress Summary
     * @throws Exception
     */
    @Test
    public void testGenerateProgressSummary_Own_AsStudent() throws InvalidUserException, FileNotFoundException, DatabaseAccessException {
        grads.setUser("gayxx067");
        try {
            ProgressSummary actual = grads.generateProgressSummary("gayxx067");
        } catch (AccessDeniedException e) {
            fail();
        }
    }

    /**
     * Checks that a GPC cannot generate a progress summary for a student outside of their dept.
     */
    @Test
    public void testGenerateProgressSummary_OutDept_AsGPC() throws FileNotFoundException, DatabaseAccessException, InvalidUserException {
        grads.setUser("smith0001");
        try {
            grads.generateProgressSummary("nguy0621");
            fail();
        } catch (AccessDeniedException ex) {
            //do nothing
        }
    }

    /**
     * Checks that student cannot generate a progress summary for another student
     * @throws FileNotFoundException
     * @throws DatabaseAccessException
     */
    @Test
    public void testGenerateProgress_OtherStudent_AsStudent() throws FileNotFoundException, DatabaseAccessException, InvalidUserException {
        //TODO: Add inputs testID, Expected Progress Summary;
        grads.setUser("gayxx067");
        try {
            grads.generateProgressSummary("nguy0261");
            fail();
        } catch (AccessDeniedException ex) {
            //do nothing
        }
    }

    /**
     * Checks that GPC can generate a progress summary for a student in their dept.
     * @throws AccessDeniedException 
     * @throws Exception
     */
    @Test
    public void testGenerateProgressSummary_InDept_AsGPC() throws FileNotFoundException, DatabaseAccessException, InvalidUserException, AccessDeniedException {
        //TODO: Add inputs testID, Expected Progress Summary
        grads.setUser("tolas9999");

        try {
            ProgressSummary actual = grads.generateProgressSummary("nguy0621");
            // Create a parallel Progress Summary
            assertEquals(new Student("Luan", "Nguyen", "nguy0621"), actual.getStudent());
            assertEquals(Department.COMPUTER_SCIENCE, actual.getDepartment());
            assertEquals(Degree.PHD, actual.getDegreeSought());
            assertEquals(new Term(Semester.SPRING, 2008), actual.getTermBegan());
            assertEquals(StudentRecordFactory.LuanAdvisors(), actual.getAdvisors());
            assertEquals(StudentRecordFactory.LuanCommittee(), actual.getCommittee());
            assertEquals(StudentRecordFactory.notes(), actual.getNotes());
            List<RequirementCheckResult> requirementCheckResultsList = actual.getRequirementCheckResults();
            List<String> others = new ArrayList<String>();
            List<String> errorM = new ArrayList<String>();
            // 0
            CheckResultDetails details = newCheckResultDetails((float) 4.0, StudentRecordFactory.LuanCoursesTaken(), others);
            RequirementCheckResult requirementCheckResult = new RequirementCheckResult("Theory and Algorithms",false,details);
            requirementCheckResult.setErrorMsgs(errorM);
            assertEquals(requirementCheckResultsList.get(0).getName(), requirementCheckResult.getName());
//            assertEquals(requirementCheckResultsList.get(0).isPassed(), requirementCheckResult.isPassed());
//            assertEquals(requirementCheckResultsList.get(0).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
            // 1
            details = newCheckResultDetails((float) 3.0, StudentRecordFactory.LuanCoursesTaken(), others);
            requirementCheckResult = new RequirementCheckResult("Architecture, Systems, and Software",false,details);
            requirementCheckResult.setErrorMsgs(errorM);
            // Name, GPA, Pass
            assertEquals(requirementCheckResultsList.get(1).getName(), requirementCheckResult.getName());
//            assertEquals(requirementCheckResultsList.get(1).isPassed(), requirementCheckResult.isPassed());
//            assertEquals(requirementCheckResultsList.get(1).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
            // 2
            details = newCheckResultDetails((float) 0.0, StudentRecordFactory.LuanCoursesTaken(), others);
            requirementCheckResult = new RequirementCheckResult("Applications",false,details);
            requirementCheckResult.setErrorMsgs(errorM);
            assertEquals(requirementCheckResultsList.get(2).getName(), requirementCheckResult.getName());
//            assertEquals(requirementCheckResultsList.get(2).isPassed(), requirementCheckResult.isPassed());
//            assertEquals(requirementCheckResultsList.get(2).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
            // 3
            details = newCheckResultDetails((float) 3.6666667, StudentRecordFactory.LuanCoursesTaken(), others);
            requirementCheckResult = new RequirementCheckResult("Breadth Requirement",false,details);
            requirementCheckResult.setErrorMsgs(errorM);
            assertEquals(requirementCheckResultsList.get(3).getName(), requirementCheckResult.getName());
            assertEquals(requirementCheckResultsList.get(3).isPassed(), requirementCheckResult.isPassed());
            assertEquals(requirementCheckResultsList.get(3).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
            // 4
            details = newCheckResultDetails((float) 0.0, StudentRecordFactory.LuanCoursesTaken(), others);
            requirementCheckResult = new RequirementCheckResult("Thesis PHD",false,details);
            requirementCheckResult.setErrorMsgs(errorM);
            assertEquals(requirementCheckResultsList.get(4).getName(), requirementCheckResult.getName());
            assertEquals(requirementCheckResultsList.get(4).isPassed(), requirementCheckResult.isPassed());
            assertEquals(requirementCheckResultsList.get(4).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
            // 5
            details = newCheckResultDetails((float) 0.0, StudentRecordFactory.LuanCoursesTaken(), others);
            requirementCheckResult = new RequirementCheckResult("Colloquium",false,details);
            requirementCheckResult.setErrorMsgs(errorM);
            assertEquals(requirementCheckResultsList.get(5).getName(), requirementCheckResult.getName());
            assertEquals(requirementCheckResultsList.get(5).isPassed(), requirementCheckResult.isPassed());
            assertEquals(requirementCheckResultsList.get(5).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
            // 6
            details = newCheckResultDetails((float) 0.0, StudentRecordFactory.LuanCoursesTaken(), others);
            requirementCheckResult = new RequirementCheckResult("Out of Department",false,details);
            requirementCheckResult.setErrorMsgs(errorM);
            assertEquals(requirementCheckResultsList.get(6).getName(), requirementCheckResult.getName());
            assertEquals(requirementCheckResultsList.get(6).isPassed(), requirementCheckResult.isPassed());
            assertEquals(requirementCheckResultsList.get(6).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
            // 7
            details = newCheckResultDetails((float) 0.0, StudentRecordFactory.LuanCoursesTaken(), others);
            requirementCheckResult = new RequirementCheckResult("Introduction to Research",false,details);
            requirementCheckResult.setErrorMsgs(errorM);
            assertEquals(requirementCheckResultsList.get(7).getName(), requirementCheckResult.getName());
            assertEquals(requirementCheckResultsList.get(7).isPassed(), requirementCheckResult.isPassed());
            assertEquals(requirementCheckResultsList.get(7).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
            // 8
            details = newCheckResultDetails((float) 3.6666667, StudentRecordFactory.LuanCoursesTaken(), others);
            requirementCheckResult = new RequirementCheckResult("16 csci Credits",false,details);
            requirementCheckResult.setErrorMsgs(errorM);
            assertEquals(requirementCheckResultsList.get(8).getName(), requirementCheckResult.getName());
            assertEquals(requirementCheckResultsList.get(8).isPassed(), requirementCheckResult.isPassed());
            assertEquals(requirementCheckResultsList.get(8).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
            // 9
            details = newCheckResultDetails((float) 3.6666667, StudentRecordFactory.LuanCoursesTaken(), others);
            requirementCheckResult = new RequirementCheckResult("Total Credits",false,details);
            requirementCheckResult.setErrorMsgs(errorM);
            assertEquals(requirementCheckResultsList.get(9).getName(), requirementCheckResult.getName());
            assertEquals(requirementCheckResultsList.get(9).isPassed(), requirementCheckResult.isPassed());
            assertEquals(requirementCheckResultsList.get(9).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
            // 10
            details = newCheckResultDetails((float) 3.6666667, StudentRecordFactory.LuanCoursesTaken(), others);
            requirementCheckResult = new RequirementCheckResult("Other Course Requirements",false,details);
            requirementCheckResult.setErrorMsgs(errorM);
            assertEquals(requirementCheckResultsList.get(10).getName(), requirementCheckResult.getName());
            assertEquals(requirementCheckResultsList.get(10).isPassed(), requirementCheckResult.isPassed());
            assertEquals(requirementCheckResultsList.get(10).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
            // 11
            details = newCheckResultDetails((float) 3.6666667, StudentRecordFactory.LuanCoursesTaken(), others);
            requirementCheckResult = new RequirementCheckResult("Overall GPA",true,details);
            requirementCheckResult.setErrorMsgs(errorM);
            assertEquals(requirementCheckResultsList.get(11).getName(), requirementCheckResult.getName());
            assertEquals(requirementCheckResultsList.get(11).isPassed(), requirementCheckResult.isPassed());
            assertEquals(requirementCheckResultsList.get(11).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
            // 12
            details = newCheckResultDetails((float) 3.6666667, StudentRecordFactory.LuanCoursesTaken(), others);
            requirementCheckResult = new RequirementCheckResult("In Program GPA",true,details);
            requirementCheckResult.setErrorMsgs(errorM);
            assertEquals(requirementCheckResultsList.get(12).getName(), requirementCheckResult.getName());
            assertEquals(requirementCheckResultsList.get(12).isPassed(), requirementCheckResult.isPassed());
            assertEquals(requirementCheckResultsList.get(12).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
            // 13
            details = newCheckResultDetails((float) 3.6666667, StudentRecordFactory.LuanCoursesTaken(), others);
            requirementCheckResult = new RequirementCheckResult("Other GPA Requirements",true,details);
            requirementCheckResult.setErrorMsgs(errorM);
            assertEquals(requirementCheckResultsList.get(13).getName(), requirementCheckResult.getName());
            assertEquals(requirementCheckResultsList.get(13).isPassed(), requirementCheckResult.isPassed());
            assertEquals(requirementCheckResultsList.get(13).getDetails().getGPA(), requirementCheckResult.getDetails().getGPA());
            // 14
            details = newCheckResultDetails((float) 0.0, StudentRecordFactory.LuanCoursesTaken(), others);
            requirementCheckResult = new RequirementCheckResult("Milestones",false,details);
            requirementCheckResult.setErrorMsgs(errorM);
            assertEquals(requirementCheckResultsList.get(14).getName(), requirementCheckResult.getName());
            assertEquals(requirementCheckResultsList.get(14).isPassed(), requirementCheckResult.isPassed());
        } catch (AccessDeniedException e) {

        }

    }

}
