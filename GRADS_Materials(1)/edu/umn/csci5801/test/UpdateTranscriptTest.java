package edu.umn.csci5801.test;

import edu.umn.csci5801.GRADS;
import edu.umn.csci5801.access.AccessDeniedException;
import edu.umn.csci5801.db.DatabaseAccessException;
import edu.umn.csci5801.session.InvalidUserException;
import edu.umn.csci5801.session.Student;
import edu.umn.csci5801.studentrecord.StudentRecord;
import edu.umn.csci5801.studentrecord.StudentRecordFactory.StudentRecordFactory;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;

/**
 */
public class UpdateTranscriptTest {

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
     * Checks that a GPC can update a student record for a student in their  dept., changed term began
     * @throws AccessDeniedException
     * @throws FileNotFoundException
     * @throws DatabaseAccessException
     */
    @Test
    public void testUpdateTranscriptStudent_TermBegan_InDept_asGPC() throws  FileNotFoundException, DatabaseAccessException, InvalidUserException {
        grads.setUser("tolas9999");
        try {
            grads.updateTranscript("gayxx067",StudentRecordFactory.GregRecordUpdated());
            assertEquals(grads.getTranscript("gayxx067"),StudentRecordFactory.GregRecordUpdated());
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
            grads.updateTranscript("gayxx067",StudentRecordFactory.GregRecordUpdated());
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
        try {
            grads.updateTranscript("gayxx067",StudentRecordFactory.GregRecordUpdated());
            assertEquals(grads.getTranscript("gayxx067"),StudentRecordFactory.GregRecordUpdated());
        } catch (AccessDeniedException e) {
            fail();
        }
    }


    /**
     * Checks that a GPC can update a student's Grade
     * @throws AccessDeniedException
     * @throws FileNotFoundException
     * @throws DatabaseAccessException
     */
    @Test
    public void testUpdateTranscript_CourseDelete() throws Exception{
        grads.setUser("tolas9999");
        try {
            grads.updateTranscript("gayxx067",StudentRecordFactory.GregRecordUpdatedCourse());
            assertEquals(grads.getTranscript("gayxx067"),StudentRecordFactory.GregRecordUpdatedCourse());
        } catch (AccessDeniedException e) {
            fail();
        }
    }
}
