package edu.umn.csci5801.test;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.umn.csci5801.GRADS;
import edu.umn.csci5801.db.StudentDAO;
import edu.umn.csci5801.session.InvalidUserException;
import edu.umn.csci5801.studentrecord.StudentRecord;

/**
 * Created by trangnguyen on 12/9/13.
 */

public class GRADSTest {

    GRADS grads;
    /**
     * Test setting a User to GPC
     */

    @Before
    public void initGrads() {
        grads = new GRADS();

    }

    @Test
    public void testSetUserID() throws InvalidUserException {
        grads.setUser("12abc");
        Assert.assertSame("12abc", grads.getUser());
    }

    @Test
    public void testSetUserIDNull() {
        try {
            //method supposed to check for null userID
            grads.setUser(null);
            Assert.fail();
        } catch (InvalidUserException i) {

        }
    }


    @Test
    public void testAddNotes() throws Exception{
        grads.addNote("123", "Xum's Note");
        StudentRecord studentRecord = StudentDAO.getStudentByID("StudentRecordFile", "123");
        Assert.assertTrue(studentRecord.getNotes().contains("Xum's Note"));
    }

//    @Test
//    public void testSetUserGPC(){
//        //TODO :  set inputs testID
//        String testID = "";
//        Session session = EasyMock.createMock(Session.class);
//        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.GRADUATE_PROGRAM_COORDINATOR).anyTimes();
//        EasyMock.expect(session.getUserID()).andReturn(testID);
//        EasyMock.replay(session);
//        GRADS access = new GRADS();
//
//        try {
//            access.setUser(session.getUserID());
//
//        } catch (InvalidUserException ex) {
//            fail();
//        }
//    }
//
//    @Test
//    public void testSetUserStudent(){
//        //TODO :  set inputs testID
//        String testID = "";
//        Session session = EasyMock.createMock(Session.class);
//        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.STUDENT).anyTimes();
//        EasyMock.expect(session.getUserID()).andReturn(testID);
//        EasyMock.replay(session);
//        GRADS access = new GRADS();
//        try {
//            access.setUser(session.getUserID());
//        } catch (InvalidUserException ex) {
//            fail();
//        }
//    }
//
//    @Test
//    public void testSetUserInvalidStudent(){
//        //TODO :  set inputs testID
//        String testID = "";
//        Session session = EasyMock.createMock(Session.class);
//        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.STUDENT).anyTimes();
//        EasyMock.expect(session.getUserID()).andReturn(testID);
//        EasyMock.replay(session);
//        GRADS access = new GRADS();
//        try {
//            access.setUser(session.getUserID());
//            fail();
//        } catch (InvalidUserException ex) {
//
//        }
//    }
//
//    @Test
//    public void testSetUserInvalidGPC(){
//        //TODO :  set inputs testID
//        String testID = "";
//        Session session = EasyMock.createMock(Session.class);
//        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.GRADUATE_PROGRAM_COORDINATOR).anyTimes();
//        EasyMock.expect(session.getUserID()).andReturn(testID);
//        EasyMock.replay(session);
//        GRADS access = new GRADS();
//        try {
//            access.setUser(session.getUserID());
//            fail();
//        } catch (InvalidUserException ex) {
//
//        }
//    }
//
//
//
//    /**
//     * Test setting a Get a user
//     */
//    @Test
//    public void testGetUserGPC(){
//        String testID = "";
//        Session session = EasyMock.createMock(Session.class);
//        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.GRADUATE_PROGRAM_COORDINATOR).anyTimes();
//        EasyMock.expect(session.getUserID()).andReturn(testID);
//        EasyMock.replay(session);
//
//        GRADS access = new GRADS();
//        String actual = access.getUser();
//        assertEquals(actual, testID);
//    }
//
//    @Test
//    public void testGetUserStudent(){
//        String testID = "";
//        Session session = EasyMock.createMock(Session.class);
//        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.STUDENT).anyTimes();
//        EasyMock.expect(session.getUserID()).andReturn(testID);
//        EasyMock.replay(session);
//
//        GRADS access = new GRADS();
//        String actual = access.getUser();
//        assertEquals(actual, testID);
//    }
//
//    @Test
//    public void testGetUserInvalidStudent(){
//        String testID = "";
//        Session session = EasyMock.createMock(Session.class);
//        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.STUDENT).anyTimes();
//        EasyMock.expect(session.getUserID()).andReturn(testID);
//        EasyMock.replay(session);
//
//        GRADS access = new GRADS();
//        String actual = access.getUser();
//        assertEquals(actual, testID);
//    }
//
//    @Test
//    public void testGetUserInvalidGPC(){
//        String testID = "";
//        Session session = EasyMock.createMock(Session.class);
//        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.GRADUATE_PROGRAM_COORDINATOR).anyTimes();
//        EasyMock.expect(session.getUserID()).andReturn(testID);
//        EasyMock.replay(session);
//
//        GRADS access = new GRADS();
//        String actual = access.getUser();
//        assertEquals(actual, testID);
//    }
//
//
//
//
//    /**
//     * Checks if the GPC can get a Student's Transcript
//     * @throws Exception
//     */
//    @Test
//    public void testGetTranscript_asGPC() throws AccessDeniedException,DatabaseAccessException,FileNotFoundException {
//        //Todo: add testID
//        String testID = "";
//        Session session = EasyMock.createMock(Session.class);
//        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.GRADUATE_PROGRAM_COORDINATOR).anyTimes();
//        EasyMock.expect(session.getUserID()).andReturn(testID);
//        EasyMock.replay(session);
//
//        GRADS access = new GRADS();
//       //TODO create a expected StudentRecord or can we mock this
//        StudentRecord actual = access.getTranscript(session.getUserID());
//        StudentRecord expected = new StudentRecord();
//        assertEquals(expected, actual);
//    }
//
//
//    @Test
//    public void testGetTranscriptOwn_asStudent() throws AccessDeniedException, DatabaseAccessException, FileNotFoundException {
//        //Todo: add testID
//        String testID = "";
//        Session session = EasyMock.createMock(Session.class);
//        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.STUDENT).anyTimes();
//        EasyMock.expect(session.getUserID()).andReturn(testID);
//        EasyMock.replay(session);
//
//        GRADS access = new GRADS();
//        //TODO create a expected StudentRecord or can we mock this
//        StudentRecord actual = access.getTranscript(session.getUserID());
//        StudentRecord expected = new StudentRecord();
//        assertEquals(expected, actual);
//    }
//
//
//    @Test
//    public void testGetTranscriptOther_asStudent() throws AccessDeniedException,DatabaseAccessException,FileNotFoundException {
//        //Todo: add testID
//        String testID = "";
//        Session session = EasyMock.createMock(Session.class);
//        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.STUDENT).anyTimes();
//        EasyMock.expect(session.getUserID()).andReturn(testID);
//        EasyMock.replay(session);
//
//        GRADS access = new GRADS();
//        try {
//            StudentRecord actual = access.getTranscript(session.getUserID());;
//            fail();
//        }catch (AccessDeniedException ex){
//            //do nothing
//        }
//    }
//
//
//    @Test
//    public void testGetTranscriptStudentNotInDept_asGPCt()throws DatabaseAccessException,FileNotFoundException  {
//        //Todo: add testID
//        String testID = "";
//        Session session = EasyMock.createMock(Session.class);
//        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.STUDENT).anyTimes();
//        EasyMock.expect(session.getUserID()).andReturn(testID);
//        EasyMock.replay(session);
//
//        GRADS access = new GRADS();
//        try {
//            StudentRecord actual = access.getTranscript(session.getUserID());;
//            fail();
//        }catch (AccessDeniedException ex){
//            //do nothing
//        }
//
//    }
//
//    /**
//     *
//     * @throws Exception
//     */
//
//    @Test
//    public void testUpdateTranscript_asStudent() throws AccessDeniedException,FileNotFoundException,DatabaseAccessException{
//        //TODO: set TestID and Expected StudentRecord
//        String testID = "";
//        Session session = EasyMock.createMock(Session.class);
//        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.STUDENT).anyTimes();
//        EasyMock.expect(session.getUserID()).andReturn(testID);
//        EasyMock.replay(session);
//
//        GRADS access = new GRADS();
//        access.updateTranscript(testID,access.getTranscript(testID));
//
//        StudentRecord actual = access.getTranscript(testID);
//        StudentRecord expected = new StudentRecord();
//        assertEquals(actual,expected);
//
//    }
//
//
//    @Test
//    public void testUpdateTranscriptStudentInDept_asGPC() throws AccessDeniedException,FileNotFoundException,DatabaseAccessException{
//        //TODO: set TestID and Expected StudentRecord
//        String testID = "";
//        Session session = EasyMock.createMock(Session.class);
//        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.GRADUATE_PROGRAM_COORDINATOR).anyTimes();
//        EasyMock.expect(session.getUserID()).andReturn(testID);
//        EasyMock.replay(session);
//
//        GRADS access = new GRADS();
//        access.updateTranscript(testID,access.getTranscript(testID));
//
//        StudentRecord actual = access.getTranscript(testID);
//        StudentRecord expected = new StudentRecord();
//        assertEquals(actual,expected);
//
//    }
//
//    @Test
//    public void testUpdateTranscriptStudentNotInDept_asGPC() throws FileNotFoundException,DatabaseAccessException{
//        //TODO: set TestID
//        String testID = "";
//        Session session = EasyMock.createMock(Session.class);
//        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.GRADUATE_PROGRAM_COORDINATOR).anyTimes();
//        EasyMock.expect(session.getUserID()).andReturn(testID);
//        EasyMock.replay(session);
//
//        GRADS access = new GRADS();
//        try {
//            access.updateTranscript(testID,access.getTranscript(testID));
//            fail();
//        } catch (AccessDeniedException ex) {
//            //do nothing
//        }
//    }
//
//    /**
//     * ADD NOTE
//     * @throws FileNotFoundException
//     */
//
//
//    @Test
//    public void testAddNote_asStudent() throws FileNotFoundException,DatabaseAccessException {
//        //TODO set TestID and Note
//        String testID = "";
//        String note   = "";
//        Session session = EasyMock.createMock(Session.class);
//        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.STUDENT).anyTimes();
//        EasyMock.expect(session.getUserID()).andReturn(testID);
//        EasyMock.replay(session);
//
//        GRADS access = new GRADS();
//
//        try {
//            access.addNote(testID,note);
//            fail();
//        } catch (AccessDeniedException e) {
//
//        }
//    }
//
//
//    @Test
//    public void testAddNote_asGPC() throws Exception{
//        //TODO set TestID Note  and Expected StudentRecord
//        String testID = "";
//        String note   = "";
//        Session session = EasyMock.createMock(Session.class);
//        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.GRADUATE_PROGRAM_COORDINATOR).anyTimes();
//        EasyMock.expect(session.getUserID()).andReturn(testID);
//        EasyMock.replay(session);
//
//        GRADS access = new GRADS();
//        access.addNote(testID,note);
//        StudentRecord actual =  access.getTranscript(testID);
//        StudentRecord expected = new StudentRecord();
//
//        assertEquals(actual,expected);
//    }
//
//    /**
//     * Test that the student can get their Progress Summary
//     * @throws Exception
//     */
//    @Test
//    public void testGenerateProgressSummary_asStudent() throws Exception {
//        //TODO: Add inputs testID, Expected Progress Summary
//        String testId = "";
//        Session session = EasyMock.createMock(Session.class);
//        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.STUDENT).anyTimes();
//        EasyMock.expect(session.getUserID()).andReturn(testId);
//        EasyMock.replay(session);
//
//        GRADS access = new GRADS();
//        ProgressSummary actual = access.generateProgressSummary(testId);
//        ProgressSummary expected = new ProgressSummary();
//
//        assertEquals(expected, actual);
//    }
//
//    /**
//     *
//     */
//    @Test
//    public void testGenerateProgressSummaryOutDept_asGPC() throws FileNotFoundException,DatabaseAccessException {
//        //TODO: Add inputs testID, Expected Progress Summary
//        String testId = "";
//        Session session = EasyMock.createMock(Session.class);
//        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.STUDENT).anyTimes();
//        EasyMock.expect(session.getUserID()).andReturn(testId);
//        EasyMock.replay(session);
//
//        GRADS access = new GRADS();
//
//        try {
//            access.generateProgressSummary(testId);
//            fail();
//        } catch (AccessDeniedException ex) {
//            //do nothing
//        }
//    }
//
//    @Test
//    public void testGenerateProgressOther_asStudent() throws FileNotFoundException,DatabaseAccessException {
//        //TODO: Add inputs testID, Expected Progress Summary
//        String testId = "";
//        Session session = EasyMock.createMock(Session.class);
//        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.STUDENT).anyTimes();
//        EasyMock.expect(session.getUserID()).andReturn(testId);
//        EasyMock.replay(session);
//
//        GRADS access = new GRADS();
//
//        try {
//            access.generateProgressSummary(testId);
//            fail();
//        } catch (AccessDeniedException ex) {
//            //do nothing
//        }
//    }
//
//    @Test
//    public void testGenerateProgressSummaryInDept_asGPC() throws Exception {
//        //TODO: Add inputs testID, Expected Progress Summary
//        String testId = "";
//        Session session = EasyMock.createMock(Session.class);
//        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.GRADUATE_PROGRAM_COORDINATOR).anyTimes();
//        EasyMock.expect(session.getUserID()).andReturn(testId);
//        EasyMock.replay(session);
//
//        GRADS access = new GRADS();
//        ProgressSummary actual = access.generateProgressSummary(testId);
//        ProgressSummary expected = new ProgressSummary();
//
//        assertEquals(expected, actual);
//    }
//
//
//
//    @Test
//    public void testSimulateCoursesOwn_asStudent() throws Exception {
//        //TODO inputs: courseTaken list, expected Progress Summary
//        String testID = "";
//        Session session = EasyMock.createMock(Session.class);
//        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.STUDENT).anyTimes();
//        EasyMock.expect(session.getUserID()).andReturn(testID);
//        EasyMock.replay(session);
//
//        GRADS access = new GRADS();
//
//        List<CourseTaken> listCourseTaken = new ArrayList<CourseTaken>();
//        ProgressSummary actual = access.generateProgressSummary(session.getUserID());
//        ProgressSummary expected = new ProgressSummary();
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void testSimulateCoursesInDept_asGPC() throws Exception {
//        //TODO inputs: courseTaken list, expected Progress Summary
//        String testID = "";
//        Session session = EasyMock.createMock(Session.class);
//        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.GRADUATE_PROGRAM_COORDINATOR).anyTimes();
//        EasyMock.expect(session.getUserID()).andReturn(testID);
//        EasyMock.replay(session);
//
//        GRADS access = new GRADS();
//
//        List<CourseTaken> courses = new ArrayList<CourseTaken>();
//        ProgressSummary actual = access.simulateCourses(session.getUserID(), courses);
//        ProgressSummary expected = new ProgressSummary();
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void testSimulateCoursesOutDept_asGPC() throws FileNotFoundException,DatabaseAccessException{
//        //TODO inputs: courseTaken list, expected Progress Summary
//        String testID = "";
//        List<CourseTaken> courses = new ArrayList<CourseTaken>();
//
//        Session session = EasyMock.createMock(Session.class);
//        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.GRADUATE_PROGRAM_COORDINATOR).anyTimes();
//        EasyMock.expect(session.getUserID()).andReturn(testID);
//        EasyMock.replay(session);
//
//        GRADS access = new GRADS();
//
//        try {
//            access.simulateCourses(session.getUserID(),courses);
//            fail();
//        } catch (Exception e) {
//            //do nothing
//        }
//    }
//
//    @Test
//    public void testSimulateCoursesOtherStudent_asStudent() throws FileNotFoundException,DatabaseAccessException{
//        //TODO inputs: courseTaken list, expected Progress Summary
//        String testID = "";
//        List<CourseTaken> courses = new ArrayList<CourseTaken>();
//
//        Session session = EasyMock.createMock(Session.class);
//        EasyMock.expect(session.getCurrentUserType()).andReturn(UserType.STUDENT).anyTimes();
//        EasyMock.expect(session.getUserID()).andReturn(testID);
//        EasyMock.replay(session);
//
//        GRADS access = new GRADS();
//
//        try {
//            access.simulateCourses(session.getUserID(),courses);
//            fail();
//        } catch (Exception e) {
//            //do nothing
//        }
//    }


}
