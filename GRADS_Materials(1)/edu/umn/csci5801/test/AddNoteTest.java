package edu.umn.csci5801.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import edu.umn.csci5801.GRADS;
import edu.umn.csci5801.access.AccessDeniedException;
import edu.umn.csci5801.db.DatabaseAccessException;
import edu.umn.csci5801.session.InvalidUserException;
import edu.umn.csci5801.studentrecord.StudentRecord;
import edu.umn.csci5801.studentrecord.StudentRecordFactory.StudentRecordFactory;

/**
 * Created by trangnguyen on 12/12/13.
 */
public class AddNoteTest {
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
     * Checks that a student cannot add a note
     * @throws FileNotFoundException
     */
    @Test
    public void testAddNote() throws Exception {
        //TODO: Student Record Factory for gayxx067 with the updated Note,and the assert
        grads.setUser("tolas9999");
        grads.addNote("gayxx067", "I am Xum");
        StudentRecord studentRecord = grads.getTranscript("gayxx067");
        assertTrue(studentRecord.getNotes().contains("I am Xum"));
        assertEquals(studentRecord, StudentRecordFactory.GregRecord());
    }

    /**
     * testing for if added note to the invalidUer
     * @throws DatabaseAccessException
     * @throws AccessDeniedException
     */
    @Test
    public void testAddNote_InvalidRecord() throws  FileNotFoundException, AccessDeniedException, InvalidUserException {
        try {
            grads.setUser("smith0001");
        } catch (DatabaseAccessException e) {
            e.printStackTrace();
        }
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
        try {
            grads.setUser("tolas9999");
        } catch (DatabaseAccessException e) {
            e.printStackTrace();
        }
        try {
            grads.addNote("gayxx067", null);
            fail();
        } catch (DatabaseAccessException d) {

        }
    }


    @Test
    public void testAddNote_AsStudent() throws FileNotFoundException, DatabaseAccessException, InvalidUserException {
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
     *
     */
    @Test
    public void testAddNote_asGPC() throws Exception{
        grads.setUser("tolas9999");
        grads.addNote("gayxx067","test note");
        StudentRecord actual =  grads.getTranscript("gayxx067");
        assertEquals(actual,StudentRecordFactory.GregRecord());

    }

}
