package edu.umn.csci5801.test;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.umn.csci5801.GRADS;
import edu.umn.csci5801.db.DatabaseAccessException;
import edu.umn.csci5801.session.InvalidUserException;

/**
 */
public class SetUserTest {
    private static GRADS grads;


    @Before
    public void initGrad() throws Exception {
        // creating test files
        StudentRecordFactory.instantiateTestDb();
        // init Grads
        if(grads == null){
            grads = new GRADS("GRADS_Materials/Data/TestStudents.txt", "GRADS_Materials/Data/courses.txt", "GRADS_Materials/Data/TestUsers.txt");

        }
    }
    @Test
    public void testSetUserValidID() throws InvalidUserException {

        try {
            grads.setUser("nguy0621");
        } catch (DatabaseAccessException e) {

        }
        Assert.assertSame("nguy0621", grads.getUser());
    }

    /**
     * Test with Null setUserID should throw exception
     */
    @Test
    public void testSetUserID_Null() {
        try {
            //method supposed to check for null userID
            try {
                try {
                    grads.setUser(null);
                } catch (DatabaseAccessException e) {
                    e.printStackTrace();
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
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
            try {
                grads.setUser("");

            } catch (DatabaseAccessException e) {
                e.printStackTrace();

            }
            fail();
        } catch (InvalidUserException i) {
        }
        Assert.assertSame("nguy0621", grads.getUser());
    }
    /**
     * Test getting the invalid current user
     */
    @Test
    public void testSetUserInvalidUser() {
        try {
            try {
                grads.setUser("invalid");
            } catch (DatabaseAccessException e) {
                e.printStackTrace();
            }
            fail();
        } catch (InvalidUserException i) {
        }
        Assert.assertSame("nguy0621", grads.getUser());
    }

}
