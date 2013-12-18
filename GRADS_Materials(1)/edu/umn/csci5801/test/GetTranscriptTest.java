package edu.umn.csci5801.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.umn.csci5801.GRADS;
import edu.umn.csci5801.access.AccessDeniedException;
import edu.umn.csci5801.db.DatabaseAccessException;
import edu.umn.csci5801.session.InvalidUserException;
import edu.umn.csci5801.studentrecord.StudentRecord;

/**
 */
public class GetTranscriptTest {
    private static GRADS grads;

    /**
     * Init Grads for usage
     */
    @BeforeClass
    public static void initGrad() throws Exception {
        // creating test files
        StudentRecordFactory.instantiateTestDb();
        grads = new GRADS("GRADS_Materials/Data/TestStudents.txt", "GRADS_Materials/Data/courses.txt", "GRADS_Materials/Data/TestUsers.txt");

    }

    /**
     * Checks if the GPC can get a Student's Transcript
     * @throws Exception
     */
    @Test
    public void testGetTranscript_AsGPC() throws AccessDeniedException, DatabaseAccessException, FileNotFoundException, InvalidUserException {
        grads.setUser("tolas9999");
        StudentRecord actual = grads.getTranscript("gayxx067");
        assertEquals(actual,StudentRecordFactory.GregRecord());
    }

    /**
     * Checks if the GPC can get a Student's Transcript
     * @throws Exception
     */
    @Test
    public void testGetTranscript_AsStudentC() throws AccessDeniedException, DatabaseAccessException, FileNotFoundException, InvalidUserException {
        grads.setUser("gayxx067");
        StudentRecord actual = grads.getTranscript("gayxx067");
        assertEquals(actual,StudentRecordFactory.GregRecord());
    }

    /**
     * Check that a student cannot get another student's transcript
     * @throws AccessDeniedException
     * @throws DatabaseAccessException
     * @throws FileNotFoundException
     */
    @Test
    public void testGetOtherStudentTranscriptAsStudent() throws AccessDeniedException, DatabaseAccessException, FileNotFoundException, InvalidUserException {
        grads.setUser("nguy0621");
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
            StudentRecord actual = grads.getTranscript("nguy0621");
            fail();
        }catch (AccessDeniedException ex){
            //do nothing
        }
    }

    /**
     * Test if getting the correct transcript
     */
    @Test
    public void testGetTranscript() throws Exception {
        grads.setUser("nguy0621");
        StudentRecord Luan= grads.getTranscript("nguy0621");
        Assert.assertEquals(StudentRecordFactory.LuanRecord(), Luan);
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

}
