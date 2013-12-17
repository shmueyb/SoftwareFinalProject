package edu.umn.csci5801.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.umn.csci5801.GRADS;
import edu.umn.csci5801.access.AccessDeniedException;
import edu.umn.csci5801.db.DatabaseAccessException;
import edu.umn.csci5801.session.InvalidUserException;
import edu.umn.csci5801.session.Student;
import edu.umn.csci5801.studentrecord.StudentRecordFactory.StudentRecordFactory;
import edu.umn.csci5801.studentrecord.program.Degree;
import edu.umn.csci5801.studentrecord.program.Department;
import edu.umn.csci5801.studentrecord.requirements.Milestone;
import edu.umn.csci5801.studentrecord.requirements.MilestoneSet;
import edu.umn.csci5801.studentrecord.requirements.RequirementCheckResult;
import edu.umn.csci5801.studentrecord.transcript.ProgressSummary;
import edu.umn.csci5801.studentrecord.transcript.Semester;
import edu.umn.csci5801.studentrecord.transcript.Term;

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

    /**
     * testing if generatateProgressSummary() would return the correct result
     * @throws Exception
     */
    @Test
    public void testGenerateProgressSummary_PHDStudent() throws Exception {
        grads.setUser("nguy0261");
        ProgressSummary progressSummary = grads.generateProgressSummary("nguy0261");

    }

    /**
     * testing if generatateProgressSummary() would return the correct result
     * @throws Exception
     */
    @Test
    public void testGenerateProgressSummary_MS_A() throws Exception {
        grads.setUser("gayxx067");
        List<RequirementCheckResult> requirements = new ArrayList<RequirementCheckResult>();
        List<MilestoneSet>  milestones = new ArrayList<MilestoneSet>();


        requirements.add(new RequirementCheckResult("BREADTH_REQUIREMENT_MS_A"));
        requirements.add(new RequirementCheckResult("THESIS_MS_A"));
        requirements.add(new RequirementCheckResult("COLLOQUIUM"));
        requirements.add(new RequirementCheckResult("OUT_OF_DEPARTMENT"));
        requirements.add(new RequirementCheckResult("INTRO_TO_RESEARCH"));
        requirements.add(new RequirementCheckResult("TOTAL_CREDITS"));
        requirements.add(new RequirementCheckResult("OVERALL_GPA_PHD"));
        requirements.add(new RequirementCheckResult("BREADTH_REQUIREMENT_PHD"));


        requirements.add(new RequirementCheckResult("PRELIM_COMMITTEE_APPOINTED"));
        requirements.add(new RequirementCheckResult("WRITTEN_PE_SUBMITTED"));
        requirements.add(new RequirementCheckResult("WRITTEN_PE_APPROVED"));
        requirements.add(new RequirementCheckResult("ORAL_PE_PASSED"));
        requirements.add(new RequirementCheckResult("DPF_SUBMITTED"));
        requirements.add(new RequirementCheckResult("DPF_APPROVED"));

        requirements.add(new RequirementCheckResult("THESIS_COMMITTEE_APPOINTED"));
        requirements.add(new RequirementCheckResult("PROPOSAL_PASSED"));
        requirements.add(new RequirementCheckResult("GRADUATION_PACKET_REQUESTED"));
        requirements.add(new RequirementCheckResult("THESIS_SUBMITTED"));
        requirements.add(new RequirementCheckResult("THESIS_APPROVED"));
        requirements.add(new RequirementCheckResult("DEFENSE_PASSED"));


        Term term = new Term(Semester.SPRING,2009);
        milestones.add(new MilestoneSet(Milestone.THESIS_APPROVED,new Term(Semester.FALL,2014)));

        ProgressSummary actual =  grads.generateProgressSummary("gayxx067");
        assertEquals(new Student("Gregory", "Gay", "gayxx067"),actual.getStudent());
        assertEquals(Department.COMPUTER_SCIENCE,actual.getDepartment());
        assertEquals(Degree.MS_A, actual.getDegreeSought());
        assertEquals(new Term(Semester.SPRING, 2009), actual.getTermBegan());
        assertEquals(StudentRecordFactory.GregAdvisors(),actual.getAdvisors());
        assertEquals(StudentRecordFactory.GregCommittee(), actual.getCommittee());
        assertEquals(StudentRecordFactory.notes(),actual.getNotes());
        assertEquals(requirements,actual.getRequirementCheckResults());

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
     * testing if the method would handle invalid user
     * @throws DatabaseAccessException
     * @throws AccessDeniedException
     */
    @Test
    public void testGenerateProgressSummary_InvalidUser() throws DatabaseAccessException, AccessDeniedException, InvalidUserException, FileNotFoundException {
        grads.setUser("invalid user");
        try {
            ProgressSummary progressSummary = grads.generateProgressSummary("InvalidUser");
            fail();
        } catch (AccessDeniedException ex) {

        }

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
     * @throws Exception
     */
    @Test
    public void testGenerateProgressSummary_InDept_AsGPC() throws FileNotFoundException, DatabaseAccessException, InvalidUserException {
        //TODO: Add inputs testID, Expected Progress Summary
        grads.setUser("tolas9999");
        try {
            ProgressSummary actual = grads.generateProgressSummary("nguy0261");
        } catch (AccessDeniedException e) {

        }
    }

}
