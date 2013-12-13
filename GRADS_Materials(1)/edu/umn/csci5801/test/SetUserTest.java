package edu.umn.csci5801.test;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.umn.csci5801.GRADS;
import edu.umn.csci5801.db.DatabaseAccessException;
import edu.umn.csci5801.session.InvalidUserException;
import edu.umn.csci5801.studentrecord.StudentRecordFactory.StudentRecordFactory;

/**
 */
public class SetUserTest {
    private static GRADS grads;

    @Before
    public void init() {

        if(grads == null) {
            try {
                StudentRecordFactory.instantiateTestDb();
                grads = new GRADS("GRADS_Materials/Data/students.txt", "GRADS_Materials/Data/courses.txt", "GRADS_Materials/Data/users.txt");

            } catch (DatabaseAccessException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void testSetUserValidID() throws InvalidUserException {
        try {
            grads.setUser("nguy0621");
        } catch (DatabaseAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Assert.assertSame("nguy0621", grads.getUser());
    }

    @Test
    public void testSetUserInvalidUser() throws DatabaseAccessException {

        try {
            grads.setUser("invalid");
            fail();
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test with Null setUserID should throw exception
     */
    @Test
    public void testSetUserID_Null() {
        try {
            try {
                grads.setUser(null);
            } catch (DatabaseAccessException e) {
                e.printStackTrace();
            }
            fail();
        } catch (InvalidUserException ex) {
        }
    }
}
