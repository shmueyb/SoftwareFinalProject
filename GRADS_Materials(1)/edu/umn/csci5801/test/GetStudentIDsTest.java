package edu.umn.csci5801.test;

import edu.umn.csci5801.GRADS;
import edu.umn.csci5801.access.AccessDeniedException;
import edu.umn.csci5801.db.DatabaseAccessException;
import edu.umn.csci5801.session.InvalidUserException;
import edu.umn.csci5801.studentrecord.StudentRecord;
import edu.umn.csci5801.studentrecord.StudentRecordFactory.StudentRecordFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.fail;

/**
 */
public class GetStudentIDsTest {

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
     * Check GPC can get list of Students in their Dept.
     * @throws AccessDeniedException
     * @throws edu.umn.csci5801.db.DatabaseAccessException
     * @throws java.io.FileNotFoundException
     */
    @Test
    public void testGetStudentIDs_asGPC() throws Exception {
        grads.setUser("smith0001");  /* Math Dept.*/
        // grads = new GRADS("GRADS_Materials/Data/students.txt", "GRADS_Materials/Data/courses.txt", "GRADS_Materials/Data/users.txt");
        grads.setUser("tolas9999");  /* Math Dept.*/

        List<String> actual  = grads.getStudentIDs();
        assertEquals(actual.get(0),"nguy0621");
        assertEquals(actual.size(),4);
        List<String> expected = null;
        assertEquals(expected, actual);
    }

    /**
     * Checks to make sure student cannot at a list of students in a dept.
     * @throws edu.umn.csci5801.db.DatabaseAccessException
     * @throws java.io.FileNotFoundException
     */
    @Test
    public void testGetStudentIDs_AsStudent() throws DatabaseAccessException, FileNotFoundException, InvalidUserException {

        grads.setUser("gayxx067");
        try {
            grads.getStudentIDs();
            fail();
        } catch (AccessDeniedException ex) {

        } catch (DatabaseAccessException de) {

        } catch (InvalidUserException ie){

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Test if getting the correct transcript
     */
    @Test
    public void testGetTranscript() throws Exception {
        grads.setUser("nguy0621");
        StudentRecord Luan= grads.getTranscript("nguy0621");
        Assert.assertSame(StudentRecordFactory.LuanRecord(), Luan);
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
