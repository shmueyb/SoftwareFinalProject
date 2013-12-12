package edu.umn.csci5801.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.umn.csci5801.GRADS;
import edu.umn.csci5801.access.AccessDeniedException;
import edu.umn.csci5801.db.DatabaseAccessException;
import edu.umn.csci5801.session.InvalidUserException;
import edu.umn.csci5801.session.Session;
import edu.umn.csci5801.session.UserType;
import edu.umn.csci5801.studentrecord.StudentRecord;
import edu.umn.csci5801.studentrecord.StudentRecordFactory.StudentRecordFactory;
import edu.umn.csci5801.studentrecord.requirements.RequirementCheckResult;
import edu.umn.csci5801.studentrecord.transcript.CourseTaken;
import edu.umn.csci5801.studentrecord.transcript.ProgressSummary;

/**
 * Created by trangnguyen on 12/9/13.
 */

public class GRADSTest {
    private GRADS grads;

    /**
     * Init Grads for usage
     */
    @Before
    public void initGrad(){
        grads = new GRADS();
    }

    /**
     * Test if userID would get set
     * @throws Exception
     */
    @Test
    public void testSetUserValidID() throws InvalidUserException {
        grads.setUser("nguy0621");
        Assert.assertSame("nguy0621", grads.getUser());
    }

    /**
     * Test setting an invalid User
     */
    @Test
    public void testSetUserInvalidUser(){
        try {
            grads.setUser("invalid");
            fail();
        } catch (InvalidUserException e) {

        }
    }

    /**
     * Test with Null setUserID should throw exception
     */
    @Test
    public void testSetUserID_Null() {
        try {
            //method supposed to check for null userID
            grads.setUser(null);
            fail();
        } catch (InvalidUserException ex) {
        }
    }

    /**
     * Test with empty parameter, setUserID should throw exception
     */
    @Test
    public void testSetUserID_Empty() {
        try {
            grads.setUser("");
            fail();
        } catch (InvalidUserException i) {
        }
    }

    /**
     * Test getting the current GPC user
     */
    @Test
    public void testGetUserValidGPC()throws InvalidUserException{
        grads.setUser("smith0001");
        assertEquals( grads.getUser(), "smith0001");
    }
    /**
     * Test getting the current Student user
     */
    @Test
    public void testGetUserValidStudentID() throws InvalidUserException{
        grads.setUser("nguy0621");
        assertEquals( grads.getUser(), "nguy0621");
    }
    /**
     * Test getting the invalid current user
     */
    @Test
    public void testGetUserInvalidUser() {
        try {
            grads.setUser("invalid");
            fail();
        } catch (InvalidUserException ex) {
            //do nothing
        }
    }

    /**
     * Check GPC can get list of Students in their Dept.
     * @throws AccessDeniedException
     * @throws DatabaseAccessException
     * @throws FileNotFoundException
     */

    public void testGetStudentIDs_asGPC() throws Exception {
        //TODO: Grab list of students from Math Dept.
        grads.setUser("smith0001");  /* Math Dept.*/
        List<String> actual  = grads.getStudentIDs();
//        List<String> expected = null;
//        assertEquals(expected, actual);
    }

    /**
     * Checks to make sure student cannot at a list of students in a dept.
     * @throws DatabaseAccessException
     * @throws FileNotFoundException
     */
    public void testGetStudentIDs_AsStudent() throws DatabaseAccessException, FileNotFoundException, InvalidUserException {
        grads.setUser("gayxx067");
        try {
            grads.getTranscript("gayxx067");
            fail();
        } catch (AccessDeniedException ex) {
            //do nothing
        }
    }

    /**
     * Test if getting the correct transcript
     */
    @Test
    public void testGetTranscriptAsStudent() throws Exception {
        //TODO: setUser for access, add user to user.txt
 /* If you want to use your info you created Xum*/
//        StudentRecord XumRecord = grads.getTranscript("3333");
//        Assert.assertSame(StudentRecordFactory.XumRecord(), XumRecord);
        //TODO:if we stick with the database, create LuanRecord();
        grads.setUser("nguy0621");
        StudentRecord actual = grads.getTranscript("nguy0621");
//        StudentRecord expected = StudentRecordFactory.LuanRecord();
//        Assert.assertSame(actual, expected);

    }

    /**
     * Test if passing in invalid ID
     */
    @Test
    public void testGetTranscriptInvalidID() throws InvalidUserException, DatabaseAccessException, FileNotFoundException {
        try {
            StudentRecord studentRecord = grads.getTranscript("invalid");
        } catch (AccessDeniedException e) {
            //do nothing
        }
    }

//    /**
//     * Testing if updateTranscript would throw the correct Exception for invalid UserID
//     * @throws DatabaseAccessException
//     * @throws InvalidUserException
//     * @throws AccessDeniedException
//     */
//    @Test
//    public void testUpdateTranscript_InvalidID() throws DatabaseAccessException, InvalidUserException, FileNotFoundException {
//        //TODO: Xum, GPC testing invalid ID or Student that is INVALID
//        try {
//            grads.updateTranscript("dummyID", StudentRecordFactory.CatherineRecord());
//            fail();
//        } catch(AccessDeniedException f) {
//            //do nothing
//        }
//    }

    /**
     * Checks if the GPC can get a Student's Transcript
     * @throws Exception
     */
    @Test
    public void testGetTranscript_asGPC() throws AccessDeniedException, DatabaseAccessException, FileNotFoundException, InvalidUserException {
        grads.setUser("tolas9999");
        StudentRecord actual = grads.getTranscript("gayxx067");
       //TODO: create StudentRecordFactor for gayxx067
       // Assert.assertSame(StudentRecordFactory.SamRecord(), actual);
    }

    /**
     * Check that a student cannot get another student's transcript
     * @throws AccessDeniedException
     * @throws DatabaseAccessException
     * @throws FileNotFoundException
     */
    @Test
    public void testGetOtherStudentTranscriptAsStudent() throws AccessDeniedException, DatabaseAccessException, FileNotFoundException, InvalidUserException {
        grads.setUser("nguy0261");
        try {
            grads.getTranscript("gayxx067");
            fail();
        }catch (AccessDeniedException ex){
            //do nothing
        }
    }
    /**
     * Check that a GPC cannot get a transcript of a student not in their dept.
     * @throws DatabaseAccessException
     * @throws FileNotFoundException
     */
    @Test
    public void testGetTranscriptStudentNotInDept_asGPC() throws DatabaseAccessException, FileNotFoundException, InvalidUserException {
        grads.setUser("smith0001");/* Math GPC*/
        try {
            StudentRecord actual = grads.getTranscript("nguy0261");
            fail();
        }catch (AccessDeniedException ex){
            //do nothing
        }
    }

    /**
     * Checks that a student cannot update a student record
     * @throws Exception
     */

    @Test
    public void testUpdateTranscript_AsStudent() throws FileNotFoundException, DatabaseAccessException, InvalidUserException {
        grads.setUser("nguy0621");
        try {
        grads.getTranscript("nguy0621");
            fail();
        }catch (AccessDeniedException ex){
            //do nothing
        }
    }

    /**
     * Checks that a GPC can update a student record for a student in their  dept.
     * @throws AccessDeniedException
     * @throws FileNotFoundException
     * @throws DatabaseAccessException
     */
    @Test
    public void testUpdateTranscriptStudent_InDept_asGPC() throws  FileNotFoundException, DatabaseAccessException, InvalidUserException {
      grads.setUser("tolas9999");
        try {
            grads.updateTranscript("gayxx067",grads.getTranscript("gayxx067"));
        } catch (AccessDeniedException e) {
            fail();
        }
    }

    /**
     * Checks that a GPC cannot update a student record for a student no in their dept.
     * @throws FileNotFoundException
     * @throws DatabaseAccessException
     */
    @Test
    public void testUpdateTranscriptStudent_NotInDept_asGPC() throws FileNotFoundException, DatabaseAccessException, InvalidUserException {
        grads.setUser("smith0001");
        try {
           grads.updateTranscript("gayxx067", grads.getTranscript("gayxx067"));
            fail();
        } catch (AccessDeniedException ex) {
            //do nothing
        }
    }

    /**
     * Checks that a GPC can update a student's milestone
     * @throws AccessDeniedException
     * @throws FileNotFoundException
     * @throws DatabaseAccessException
     */
    @Test
    public void testUpdateTranscript_Milestone() throws Exception{
        grads.setUser("tolas9999");
        grads.updateTranscript("gayxx067",grads.getTranscript("gayxx067"));
        //TODO create StudentRecordFactor for "gayxx067" with an updated Transcript Milestone,add the assert
        StudentRecord actual = grads.getTranscript("gayxx067");
    }

    /**
     * Checks that GPC can update a Student's requirement
     * @throws AccessDeniedException
     * @throws FileNotFoundException
     * @throws DatabaseAccessException
     */
    @Test
    public void testUpdateTranscript_Requirement() throws Exception {
        grads.setUser("tolas9999");
        grads.updateTranscript("gayxx067", grads.getTranscript("gayxx067"));
        //TODO create StudentRecordFactor for "gayxx067" with an updated Transcript Requirement,add the assert
        StudentRecord actual = grads.getTranscript("gayxx067");
    }

    /**
     * Checks that a GPC can update a student's Grade
     * @throws AccessDeniedException
     * @throws FileNotFoundException
     * @throws DatabaseAccessException
     */
    @Test
    public void testUpdateTranscript_Grade() throws Exception{
        grads.setUser("tolas9999");
        grads.updateTranscript("nguy0261", grads.getTranscript("nguy0261"));
        //TODO create StudentRecordFactor for "gayxx067" with an updated Transcript GRADE,add the assert
        StudentRecord actual = grads.getTranscript("nguy0261");
    }
    /**
     * Checks that a student cannot add a note
     * @throws FileNotFoundException
     */
    @Test
    public void testAddNote() throws Exception {
        //TODO: Student Record Factory for gayxx067 with the updated Note,and the assert
        grads.setUser("tolas9999");
        grads.addNote("gayxx067", "I am Xum");
        StudentRecord studentRecord = grads.getTranscript("gayxx067");
    }

    /**
     * testing for if added note to the invalidUer
     * @throws DatabaseAccessException
     * @throws AccessDeniedException
     */
    @Test
    public void testAddNote_InvalidRecord() throws  FileNotFoundException, AccessDeniedException, InvalidUserException {
        grads.setUser("smith0001");
        try {
            grads.addNote("invalidUser", "anything");
            fail();
        } catch (DatabaseAccessException ex) {

        }
    }

    /**
     * Testing for null notes
     * @throws FileNotFoundException
     * @throws AccessDeniedException
     */
    @Test
    public void testAddNote_NullNote() throws FileNotFoundException, AccessDeniedException, InvalidUserException {
        grads.setUser("tolas9999");
        try {
            grads.addNote("gayxx067", null);
            fail();
        } catch (DatabaseAccessException d) {

        }
    }


    @Test
    public void testAddNote_AsStudent() throws FileNotFoundException, DatabaseAccessException, InvalidUserException {
        //TODO set TestID and Note
        grads.setUser("nguy0621");
        try {
            grads.addNote("nguy0621","note");
            fail();
        } catch (AccessDeniedException e) {

        }
    }

    /**
     * Checks that a GPC can add a note
     * @throws Exception
     */
    @Test
    public void testAddNote_asGPC() throws Exception{
        //TODO: Student Record Factory for gayxx067 for updated note , asserts
        grads.setUser("tolas9999");
        grads.addNote("gayxx067","test note");

        StudentRecord actual =  grads.getTranscript("gayxx067");

    }

    /**
     * testing if generatateProgressSummary() would return the correct result
     * @throws Exception
     */
    @Test
    public void testGenerateProgressSummary() throws Exception {
        //TODO Add tmrw
//        ProgressSummary progressSummary = grads.generateProgressSummary("2222");
//        Assert.assertSame(StudentRecordFactory.notes(), progressSummary.getNotes());
//        Assert.assertSame(StudentRecordFactory.BenRecord().getTermBegan(), progressSummary.getTermBegan());
//        Assert.assertSame(StudentRecordFactory.BenRecord().getDegreeSought(), progressSummary.getDegreeSought());
//        Assert.assertSame(StudentRecordFactory.BenRecord().getStudent(), progressSummary.getStudent());
//        Assert.assertSame(StudentRecordFactory.professors(), progressSummary.getAdvisors());
//        Assert.assertSame(StudentRecordFactory.BenRecord().getDepartment(), progressSummary.getDepartment());
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
        grads.setUser("smith0001");
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

    //TODO:Still confused about these, discuss tmrw
//    /**
//     * test if simulate courses would return the expected progress summary
//     */
//    @Test
//    public void testSimulateCourses() throws Exception {
//        ProgressSummary progressSummary = grads.simulateCourses("gayxx067", StudentRecordFactory.courseTakens());
//        for(CourseTaken c : StudentRecordFactory.courseTakens()) {
//            Assert.assertTrue(checkForCourseName(c.getCourse().getName(), progressSummary.getRequirementCheckResults()));
//        }
//    }
//
//    /**
//     * checking to see if a class exists in the list of RequirementCheckResults
//     * @param courseName
//     * @param results
//     * @return
//     */
//    private boolean checkForCourseName(String courseName, List<RequirementCheckResult> results) {
//        for(RequirementCheckResult r : results) {
//            if(courseName.equals(r.getName())) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    /**
//     * test if simulateCourses would generate grades for simulated courses
//     */
//    @Test
//    public void testSimulateCourses_gradesGenerating() throws Exception {
//        ProgressSummary progressSummary = grads.simulateCourses("gayxx067", StudentRecordFactory.courseTakens());
//        for(RequirementCheckResult r : progressSummary.getRequirementCheckResults()) {
//            Assert.assertNotNull(r.getDetails().getGPA());
//        }
//    }


    /**
     * checks that a GPC cannot simulate a progress summary for a student out of their dept.
     * @throws FileNotFoundException
     * @throws DatabaseAccessException
     */
    @Test
    public void testSimulateCoursesOutDept_AsGPC() throws FileNotFoundException, DatabaseAccessException, InvalidUserException {
        //TODO inputs: courseTaken list
        String testID = "smith0002";
        List<CourseTaken> courses = new ArrayList<CourseTaken>();
        grads.setUser(testID);
        try {
            grads.simulateCourses("desil1337",courses);
            fail();
        } catch (Exception ex) {
            //do nothing
        }
    }

    /**
     * Checks that a student cannot simulate another student's progress summary
     * @throws FileNotFoundException
     * @throws DatabaseAccessException
     */
    @Test
    public void testSimulateCourses_OtherStudent_AsStudent() throws FileNotFoundException, DatabaseAccessException, InvalidUserException {

        List<CourseTaken> courses = new ArrayList<CourseTaken>();
        grads.setUser("desil1337");
        try {
            grads.simulateCourses("nguy0261",courses);
            fail();
        } catch (Exception ex) {
            //do nothing
        }
    }

    //TODO: Create Users and Student Record for each type of degree,PHD= LUAN, MS_A MS_B MS_C needs records,and users

//
//    /**
//     * Checks that GPC can simulate a progress summary for a student in their dept.
//     * @throws Exception
//     */
//    @Test
//    public void testSimulateCourses_PHD_AsGPC() throws Exception {
//        //TODO inputs: courseTaken list, StudentRecordFactor for user new student record,assert
//        grads.setUser("tolas9999");
//        List<CourseTaken> courses = new ArrayList<CourseTaken>();
//        ProgressSummary actual = grads.simulateCourses("gayxx067", courses);
//    }
//
//    /**
//     * Check that simulate courses takes in account retaken course
//     * @throws Exception
//     */
//    @Test
//    public void testSimulateCourses_RetakenCourse_MS_A_AsStudent() throws Exception {
//        //TODO inputs: courseTaken list, expected Factory Progress Summary, assert
//        grads.setUser("gayxx067");
//        List<CourseTaken> courses = new ArrayList<CourseTaken>();
//        ProgressSummary actual = grads.simulateCourses("gayxx067", courses);
//
//    }
//
//    /**
//     * Checks simulate courses takes courses without a grade input
//     * @throws Exception
//     */
//    @Test
//    public void testSimulateCourses_NoGradeInput_MS_B_AsStudent() throws Exception {
//        //TODO inputs: courseTaken list, expected Progress Summary
//        grads.setUser("gayxx067");
//        List<CourseTaken> courses = new ArrayList<CourseTaken>();
//        ProgressSummary actual = grads.simulateCourses("gayxx067", courses);
//
//    }
//
//    /**
//     *
//     * @throws Exception
//     */
//    @Test
//    public void testSimulateCourses_FailingGradeInput_MS_C_AsStudent() throws Exception {
//        //TODO inputs: courseTaken list, expected Progress Summary
//        grads.setUser("gayxx067");
//        List<CourseTaken> courses = new ArrayList<CourseTaken>();
//        ProgressSummary actual = grads.simulateCourses("gayxx067", courses);
//
//    }
}
