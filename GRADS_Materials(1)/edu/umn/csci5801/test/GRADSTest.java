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
    public void testSetUserID()throws Exception{
        grads.setUser("12abc");
        Assert.assertSame("12abc", grads.getUser());
    }

    /**
     * Test setting a valid GPC  user
     */
    @Test
    public void testSetUserGPC(){
        //TODO :  set inputs testID
        String testID = "";
        GRADS access = new GRADS();
        try {
            access.setUser(testID);
        } catch (InvalidUserException e) {
            fail();
        }
    }

    /**
     * Test setting a valid Student user
     */
    @Test
    public void testSetUserStudent(){
        //TODO :  set inputs testID
        String testID = "";
        GRADS access = new GRADS();
        try {
            access.setUser(testID);
        } catch (InvalidUserException e) {
            fail();
        }
    }

    /**
     * Test setting an invalid User
     */
    @Test
    public void testSetUserInvalidUser(){
        //TODO :  set inputs testID
        String testID = "";
        GRADS access = new GRADS();
        try {
            access.setUser(testID);
            fail();
        } catch (InvalidUserException e) {
            //do nothing
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
        } catch (InvalidUserException i) {
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
    public void testGetUserGPC()throws InvalidUserException{
        //TODO :  set inputs testID
        String testID = "";
        GRADS access = new  GRADS();
        access.setUser(testID);

        String actual = access.getUser();
        assertEquals(actual, testID);
    }
    /**
     * Test getting the current Student user
     */
    @Test
    public void testGetUserStudent() throws InvalidUserException{
        //TODO :  set inputs testID
        String testID = "";
        GRADS access = new  GRADS();
        access.setUser(testID);

        String actual = access.getUser();
        assertEquals(actual, testID);
    }
    /**
     * Test getting the invalid current user
     */
    @Test
    public void testGetUserInvalidUser() {
        //TODO :  set inputs testID
        String testID = "";
        GRADS access = new  GRADS();
        try {
            access.setUser(testID);
            fail();
        } catch (InvalidUserException e) {

        }
    }

    /**
     * Check GPC can get list of Students in their Dept.
     * @throws AccessDeniedException
     * @throws DatabaseAccessException
     * @throws FileNotFoundException
     */

    public void testGetStudentIDs_asGPC() throws AccessDeniedException,DatabaseAccessException,FileNotFoundException {
        //Todo: add testID
        String testID = "";
        Session session = EasyMock.createMock(Session.class);
        EasyMock.expect(session.getUserID()).andReturn(testID).anyTimes().anyTimes();
        EasyMock.replay(session);

        GRADS access = new GRADS();
        //TODO create a expected StudentRecord or can we mock this
//        StudentRecord actual = access.getTranscript(session.getUserID());
//        StudentRecord expected = new StudentRecord();
//        assertEquals(expected, actual);
    }

    /**
     * Checks to make sure student cannot at a list of students in a dept.
     * @throws DatabaseAccessException
     * @throws FileNotFoundException
     */
    public void testGetStudentIDs_asStudent() throws DatabaseAccessException,FileNotFoundException {
        //Todo: add testID
        String testID = "";
        Session session = EasyMock.createMock(Session.class);
        EasyMock.expect(session.getUserID()).andReturn(testID).anyTimes();
        EasyMock.replay(session);

        GRADS access = new GRADS();

        try {
            access.getTranscript(session.getUserID());
            fail();
        } catch (AccessDeniedException ex) {
            //do nothing
        }
    }

    /**
     * Test if getting the correct transcript
     */
    @Test
    public void testGetTranscript() throws Exception {
        StudentRecord XumRecord = grads.getTranscript("3333");
        Assert.assertSame(StudentRecordFactory.XumRecord(), XumRecord);
    }

    /**
     * Test if passing in invalid ID
     */
    @Test
    public void testGetTranscript_InvalidID() throws InvalidUserException, AccessDeniedException, DatabaseAccessException{
        try {
            StudentRecord studentRecord = grads.getTranscript("2342342");
            fail();
        } catch (FileNotFoundException f) {

        }
    }

    /**
     * Testing if updateTranscript would swap records
     * @throws Exception
     */
    @Test
    public void testUpdateTranscript() throws Exception {
        grads.updateTranscript("blust013", StudentRecordFactory.SamRecord());
        StudentRecord studentRecord = grads.getTranscript("blust013");
        Assert.assertSame(StudentRecordFactory.SamRecord(), studentRecord);
        }

    /**
     * Testing if updateTranscript would throw the correct Exception for invalid UserID
     * @throws DatabaseAccessException
     * @throws InvalidUserException
     * @throws AccessDeniedException
     */
    @Test
    public void testUpdateTranscript_InvalidID() throws DatabaseAccessException, InvalidUserException, AccessDeniedException{
        try {
            grads.updateTranscript("dummyID", StudentRecordFactory.CatherineRecord());
            fail();
        } catch(FileNotFoundException f) {

        }
    }

    /**
     * Checks if the GPC can get a Student's Transcript
     * @throws Exception
     */
    @Test
    public void testGetTranscript_asGPC() throws AccessDeniedException,DatabaseAccessException,FileNotFoundException {
        //Todo: add testID
        String testID = "";
        Session session = EasyMock.createMock(Session.class);
        EasyMock.expect(session.getUserID()).andReturn(testID).anyTimes();
        EasyMock.replay(session);

        GRADS access = new GRADS();
        //TODO create a expected StudentRecord or can we mock this
//        StudentRecord actual = access.getTranscript(session.getUserID());
//        StudentRecord expected = new StudentRecord();
//        assertEquals(expected, actual);
    }

    /**
     * Checks that a Student can get their transcript
     * @throws AccessDeniedException
     * @throws DatabaseAccessException
     * @throws FileNotFoundException
     */
    @Test
    public void testGetOwnTranscript_asStudent() throws AccessDeniedException, DatabaseAccessException, FileNotFoundException {
        //Todo: add testID
        String testID = "";
        Session session = EasyMock.createMock(Session.class);
        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.STUDENT).anyTimes();
        EasyMock.expect(session.getUserID()).andReturn(testID).anyTimes();
        EasyMock.replay(session);

        GRADS access = new GRADS();
        //TODO create a expected StudentRecord or can we mock this
//        StudentRecord actual = access.getTranscript(session.getUserID());
//        StudentRecord expected = new StudentRecord();
//        assertEquals(expected, actual);
    }

    /**
     * Check that a student cannot get another student's transcript
     * @throws AccessDeniedException
     * @throws DatabaseAccessException
     * @throws FileNotFoundException
     */
    @Test
    public void testGetOtherStudentTranscript_asStudent() throws AccessDeniedException,DatabaseAccessException,FileNotFoundException {
        //Todo: add testID
        String testID = "";
        Session session = EasyMock.createMock(Session.class);
        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.STUDENT).anyTimes();
        EasyMock.expect(session.getUserID()).andReturn(testID).anyTimes();
        EasyMock.replay(session);

        GRADS access = new GRADS();
        try {
            StudentRecord actual = access.getTranscript(session.getUserID());;
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
    public void testGetTranscriptStudentNotInDept_asGPC()throws DatabaseAccessException,FileNotFoundException  {
        //Todo: add testID
        String testID = "";
        Session session = EasyMock.createMock(Session.class);
        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.STUDENT).anyTimes();
        EasyMock.expect(session.getUserID()).andReturn(testID).anyTimes();
        EasyMock.replay(session);

        GRADS access = new GRADS();
        try {
            StudentRecord actual = access.getTranscript(session.getUserID());;
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
    public void testUpdateTranscript_asStudent() throws FileNotFoundException,DatabaseAccessException{
        //TODO: set TestID and Expected StudentRecord
        String testID = "";
        Session session = EasyMock.createMock(Session.class);
        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.STUDENT).anyTimes();
        EasyMock.expect(session.getUserID()).andReturn(testID).anyTimes();
        EasyMock.replay(session);

        GRADS access = new GRADS();
        try {
            access.updateTranscript(testID,access.getTranscript(testID));
        } catch (AccessDeniedException ex) {
            fail();
        }
    }

    /**
     * Checks that a GPC can update a student record for a student in their  dept.
     * @throws AccessDeniedException
     * @throws FileNotFoundException
     * @throws DatabaseAccessException
     */
    @Test
    public void testUpdateTranscriptStudentInDept_asGPC() throws AccessDeniedException,FileNotFoundException,DatabaseAccessException{
        //TODO: set TestID and Expected StudentRecord
        String testID = "";
        Session session = EasyMock.createMock(Session.class);
        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.GRADUATE_PROGRAM_COORDINATOR).anyTimes();
        EasyMock.expect(session.getUserID()).andReturn(testID).anyTimes();
        EasyMock.replay(session);

        GRADS access = new GRADS();
        access.updateTranscript(testID,access.getTranscript(testID));

//        StudentRecord actual = access.getTranscript(testID);
//        StudentRecord expected = new StudentRecord();
//        assertEquals(actual,expected);

    }
    /**
     * Checks that a GPC cannot update a student record for a student no in their dept.
     * @throws FileNotFoundException
     * @throws DatabaseAccessException
     */
    @Test
    public void testUpdateTranscriptStudentNotInDept_asGPC() throws FileNotFoundException,DatabaseAccessException{
        //TODO: set TestID
        String testID = "";
        Session session = EasyMock.createMock(Session.class);
        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.GRADUATE_PROGRAM_COORDINATOR).anyTimes();
        EasyMock.expect(session.getUserID()).andReturn(testID).anyTimes();
        EasyMock.replay(session);

        GRADS access = new GRADS();
        try {
            access.updateTranscript(testID,access.getTranscript(testID));
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
    public void testUpdateTranscript_milestone_asGPC() throws AccessDeniedException,FileNotFoundException,DatabaseAccessException{
        //TODO: set TestID and Expected StudentRecord
        String testID = "";
        Session session = EasyMock.createMock(Session.class);
        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.GRADUATE_PROGRAM_COORDINATOR).anyTimes();
        EasyMock.expect(session.getUserID()).andReturn(testID).anyTimes();
        EasyMock.replay(session);

        GRADS access = new GRADS();
        access.updateTranscript(testID,access.getTranscript(testID));

//        StudentRecord actual = access.getTranscript(testID);
//        StudentRecord expected = new StudentRecord();
//        assertEquals(actual,expected);

    }

    /**
     * Checks that GPC can update a Student's requirement
     * @throws AccessDeniedException
     * @throws FileNotFoundException
     * @throws DatabaseAccessException
     */
    @Test
    public void testUpdateTranscript_requirement_asGPC() throws AccessDeniedException,FileNotFoundException,DatabaseAccessException{
        //TODO: set TestID and Expected StudentRecord
        String testID = "";
        Session session = EasyMock.createMock(Session.class);
        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.GRADUATE_PROGRAM_COORDINATOR).anyTimes();
        EasyMock.expect(session.getUserID()).andReturn(testID).anyTimes();
        EasyMock.replay(session);

        GRADS access = new GRADS();
        access.updateTranscript(testID,access.getTranscript(testID));

//        StudentRecord actual = access.getTranscript(testID);
//        StudentRecord expected = new StudentRecord();
//        assertEquals(actual,expected);

    }

    /**
     * Checks that a GPC can update a student's Grade
     * @throws AccessDeniedException
     * @throws FileNotFoundException
     * @throws DatabaseAccessException
     */
    @Test
    public void testUpdateTranscript_grade_asGPC() throws AccessDeniedException,FileNotFoundException,DatabaseAccessException{
        //TODO: set TestID and Expected StudentRecord
        String testID = "";
        Session session = EasyMock.createMock(Session.class);
        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.GRADUATE_PROGRAM_COORDINATOR).anyTimes();
        EasyMock.expect(session.getUserID()).andReturn(testID).anyTimes();
        EasyMock.replay(session);

        GRADS access = new GRADS();
        access.updateTranscript(testID,access.getTranscript(testID));

//        StudentRecord actual = access.getTranscript(testID);
//        StudentRecord expected = new StudentRecord();
//        assertEquals(actual,expected);

    }

    /**
     * Checks that a student cannot add a note
     * @throws FileNotFoundException
     */
    @Test
    public void testAddNote() throws Exception {
        grads.addNote("3333", "I am Xum");
        StudentRecord studentRecord = grads.getTranscript("3333");
        Assert.assertTrue(studentRecord.getNotes().contains("I am Xum"));
    }

    /**
     * testing for if added note to the invalidUer
     * @throws DatabaseAccessException
     * @throws AccessDeniedException
     */
    @Test
    public void testAddNote_InvalidRecord() throws DatabaseAccessException, AccessDeniedException {
        try {
            grads.addNote("invalidUser", "anything");
            fail();
        } catch (FileNotFoundException f) {

        }
    }

    /**
     * Testing for null notes
     * @throws FileNotFoundException
     * @throws AccessDeniedException
     */
    @Test
    public void testAddNote_NullNote() throws FileNotFoundException, AccessDeniedException {
        try {
            grads.addNote("3333", null);
            fail();
        } catch (DatabaseAccessException d) {

        }
    }


    @Test
    public void testAddNote_asStudent() throws FileNotFoundException,DatabaseAccessException {
        //TODO set TestID and Note
        String testID = "";
        String note   = "";
        Session session = EasyMock.createMock(Session.class);
        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.STUDENT).anyTimes();
        EasyMock.expect(session.getUserID()).andReturn(testID).anyTimes();
        EasyMock.replay(session);

        GRADS access = new GRADS();

        try {
            access.addNote(testID,note);
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
        //TODO set TestID Note  and Expected StudentRecord
        String testID = "";
        String note   = "";
        Session session = EasyMock.createMock(Session.class);
        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.GRADUATE_PROGRAM_COORDINATOR).anyTimes();
        EasyMock.expect(session.getUserID()).andReturn(testID).anyTimes();
        EasyMock.replay(session);

        GRADS access = new GRADS();
        access.addNote(testID,note);
//        StudentRecord actual =  access.getTranscript(testID);
//        StudentRecord expected = new StudentRecord();
//
//        assertEquals(actual,expected);
    }

    /**
     * testing if generatateProgressSummary() would return the correct result
     * @throws Exception
     */
    @Test
    public void testGenerateProgressSummary() throws Exception {
        ProgressSummary progressSummary = grads.generateProgressSummary("2222");
        Assert.assertSame(StudentRecordFactory.notes(), progressSummary.getNotes());
        Assert.assertSame(StudentRecordFactory.BenRecord().getTermBegan(), progressSummary.getTermBegan());
        Assert.assertSame(StudentRecordFactory.BenRecord().getDegreeSought(), progressSummary.getDegreeSought());
        Assert.assertSame(StudentRecordFactory.BenRecord().getStudent(), progressSummary.getStudent());
        Assert.assertSame(StudentRecordFactory.professors(), progressSummary.getAdvisors());
        Assert.assertSame(StudentRecordFactory.BenRecord().getDepartment(), progressSummary.getDepartment());
        Assert.assertSame(StudentRecordFactory.BenRecord().getDepartment(), progressSummary.getRequirementCheckResults());
    }

    /**
     * testing if the method would handle invalid user
     * @throws DatabaseAccessException
     * @throws AccessDeniedException
     */
    @Test
    public void testGenerateProgressSummary_invalidUser() throws DatabaseAccessException, AccessDeniedException {
        try {
            ProgressSummary progressSummary = grads.generateProgressSummary("InvalidUser");
            fail();
        } catch (FileNotFoundException f) {

        }

    }

    /**
     * testing if the method would handle null user
     * @throws DatabaseAccessException
     * @throws AccessDeniedException
     */
    @Test
    public void testGenerateProgressSummary_NullUser() throws FileNotFoundException, AccessDeniedException {
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
    public void testOwnGenerateProgressSummary_asStudent() throws Exception {
        //TODO: Add inputs testID, Expected Progress Summary
        String testId = "";
        Session session = EasyMock.createMock(Session.class);
        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.STUDENT).anyTimes();
        EasyMock.expect(session.getUserID()).andReturn(testId).anyTimes();
        EasyMock.replay(session);

        GRADS access = new GRADS();
        ProgressSummary actual = access.generateProgressSummary(testId);
        ProgressSummary expected = new ProgressSummary();

        assertEquals(expected, actual);
    }

    /**
     * Checks that a GPC cannot generate a progress summary for a student outside of their dept.
     */
    @Test
    public void testGenerateProgressSummaryOutDept_asGPC() throws FileNotFoundException,DatabaseAccessException {
        //TODO: Add inputs testID, Expected Progress Summary
        String testId = "";
        Session session = EasyMock.createMock(Session.class);
        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.STUDENT).anyTimes();
        EasyMock.expect(session.getUserID()).andReturn(testId).anyTimes();
        EasyMock.replay(session);

        GRADS access = new GRADS();

        try {
            access.generateProgressSummary(testId);
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
    public void testGenerateOtherStudentProgress_asStudent() throws FileNotFoundException,DatabaseAccessException {
        //TODO: Add inputs testID, Expected Progress Summary
        String testId = "";
        Session session = EasyMock.createMock(Session.class);
        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.STUDENT).anyTimes();
        EasyMock.expect(session.getUserID()).andReturn(testId).anyTimes();
        EasyMock.replay(session);

        GRADS access = new GRADS();

        try {
            access.generateProgressSummary(testId);
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
    public void testGenerateProgressSummaryInDept_asGPC() throws Exception {
        //TODO: Add inputs testID, Expected Progress Summary
        String testId = "";
        Session session = EasyMock.createMock(Session.class);
        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.GRADUATE_PROGRAM_COORDINATOR).anyTimes();
        EasyMock.expect(session.getUserID()).andReturn(testId).anyTimes();
        EasyMock.replay(session);

        GRADS access = new GRADS();
        ProgressSummary actual = access.generateProgressSummary(testId);
        ProgressSummary expected = new ProgressSummary();

        assertEquals(expected, actual);
    }

    /**
     * test if simulate courses would return the expeccted progress summary
     */
    @Test
    public void testSimulateCourses() throws Exception {
        ProgressSummary progressSummary = grads.simulateCourses("gayxx067", StudentRecordFactory.courseTakens());
        for(CourseTaken c : StudentRecordFactory.courseTakens()) {
            Assert.assertTrue(checkForCourseName(c.getCourse().getName(), progressSummary.getRequirementCheckResults()));
        }
    }

    /**
     * checking to see if a class exists in the list of RequirementCheckResults
     * @param courseName
     * @param results
     * @return
     */
    private boolean checkForCourseName(String courseName, List<RequirementCheckResult> results) {
        for(RequirementCheckResult r : results) {
            if(courseName.equals(r.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * test if simulateCourses would generate grades for simulated courses
     */
    @Test
    public void testSimulateCourses_gradesGenerating() throws Exception {
        ProgressSummary progressSummary = grads.simulateCourses("gayxx067", StudentRecordFactory.courseTakens());
        for(RequirementCheckResult r : progressSummary.getRequirementCheckResults()) {
            Assert.assertNotNull(r.getDetails().getGPA());
        }
    }

    /**
     * Checks that a student can simulate their own progress summary
     * @throws Exception
     */
    @Test
    public void testSimulateCoursesOwn_asStudent() throws Exception {
        //TODO inputs: courseTaken list, expected Progress Summary
        String testID = "";
        Session session = EasyMock.createMock(Session.class);
        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.STUDENT).anyTimes();
        EasyMock.expect(session.getUserID()).andReturn(testID).anyTimes();
        EasyMock.replay(session);

        GRADS access = new GRADS();

        List<CourseTaken> listCourseTaken = new ArrayList<CourseTaken>();
        ProgressSummary actual = access.generateProgressSummary(session.getUserID());
        ProgressSummary expected = new ProgressSummary();
        assertEquals(expected, actual);
    }

    /**
     * Checks that GPC can simulate a progress summary for a student in their dept.
     * @throws Exception
     */
    @Test
    public void testSimulateCoursesInDept_asGPC() throws Exception {
        //TODO inputs: courseTaken list, expected Progress Summary
        String testID = "";
        Session session = EasyMock.createMock(Session.class);
        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.GRADUATE_PROGRAM_COORDINATOR).anyTimes();
        EasyMock.expect(session.getUserID()).andReturn(testID).anyTimes();
        EasyMock.replay(session);

        GRADS access = new GRADS();

        List<CourseTaken> courses = new ArrayList<CourseTaken>();
        ProgressSummary actual = access.simulateCourses(session.getUserID(), courses);
        ProgressSummary expected = new ProgressSummary();

        assertEquals(expected, actual);
    }

    /**
     * checks that a GPC cannot simulate a progress summary for a student out of their dept.
     * @throws FileNotFoundException
     * @throws DatabaseAccessException
     */
    @Test
    public void testSimulateCoursesOutDept_asGPC() throws FileNotFoundException,DatabaseAccessException{
        //TODO inputs: courseTaken list, expected Progress Summary
        String testID = "";
        List<CourseTaken> courses = new ArrayList<CourseTaken>();

        Session session = EasyMock.createMock(Session.class);
        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.GRADUATE_PROGRAM_COORDINATOR).anyTimes();
        EasyMock.expect(session.getUserID()).andReturn(testID).anyTimes();
        EasyMock.replay(session);

        GRADS access = new GRADS();

        try {
            access.simulateCourses(session.getUserID(),courses);
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
    public void testSimulateCoursesOtherStudent_asStudent() throws FileNotFoundException,DatabaseAccessException{
        //TODO inputs: courseTaken list, expected Progress Summary
        String testID = "";
        List<CourseTaken> courses = new ArrayList<CourseTaken>();

        Session session = EasyMock.createMock(Session.class);
        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.STUDENT).anyTimes();
        EasyMock.expect(session.getUserID()).andReturn(testID).anyTimes();
        EasyMock.replay(session);

        GRADS access = new GRADS();

        try {
            access.simulateCourses(session.getUserID(),courses);
            fail();
        } catch (Exception ex) {
            //do nothing
        }
    }

    /**
     * Check that simulate courses takes in account retaken course
     * @throws Exception
     */
    @Test
    public void testSimulateCourses_retakenCourse_asStudent() throws Exception {
        //TODO inputs: courseTaken list, expected Progress Summary
        String testID = "";
        Session session = EasyMock.createMock(Session.class);
        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.STUDENT).anyTimes();
        EasyMock.expect(session.getUserID()).andReturn(testID).anyTimes();
        EasyMock.replay(session);

        GRADS access = new GRADS();

        List<CourseTaken> listCourseTaken = new ArrayList<CourseTaken>();
        ProgressSummary actual = access.generateProgressSummary(session.getUserID());
        ProgressSummary expected = new ProgressSummary();
        assertEquals(expected, actual);
    }

    /**
     * Checks simulate courses takes courses without a grade input
     * @throws Exception
     */
    @Test
    public void testSimulateCourses_noGradeInput_asStudent() throws Exception {
        //TODO inputs: courseTaken list, expected Progress Summary
        String testID = "";
        Session session = EasyMock.createMock(Session.class);
        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.STUDENT).anyTimes();
        EasyMock.expect(session.getUserID()).andReturn(testID).anyTimes();
        EasyMock.replay(session);

        GRADS access = new GRADS();

        List<CourseTaken> listCourseTaken = new ArrayList<CourseTaken>();
        ProgressSummary actual = access.generateProgressSummary(session.getUserID());
        ProgressSummary expected = new ProgressSummary();
        assertEquals(expected, actual);
    }

}
