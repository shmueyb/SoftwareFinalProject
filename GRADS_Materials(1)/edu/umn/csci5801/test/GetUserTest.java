package edu.umn.csci5801.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.umn.csci5801.GRADS;
import edu.umn.csci5801.db.DatabaseAccessException;
import edu.umn.csci5801.session.InvalidUserException;
import edu.umn.csci5801.studentrecord.StudentRecordFactory.StudentRecordFactory;

/**
 */
public class GetUserTest {
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
     * Test getting the current GPC user
     */
    @Test
    public void testGetUserValidGPC() throws InvalidUserException {
        try {
            grads.setUser("smith0001");
        } catch (DatabaseAccessException e) {
            e.printStackTrace();
        }
        assertEquals( grads.getUser(), "smith0001");
    }
    /**
     * Test getting the current Student user
     */
    @Test
    public void testGetUserValidStudentID() throws InvalidUserException{
        try {
            grads.setUser("nguy0621");
        } catch (DatabaseAccessException e) {
            e.printStackTrace();
        }
        assertEquals( grads.getUser(), "nguy0621");
    }

}
