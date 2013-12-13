package edu.umn.csci5801.test;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import edu.umn.csci5801.GRADS;
import edu.umn.csci5801.db.DatabaseAccessException;
import edu.umn.csci5801.session.InvalidUserException;
import edu.umn.csci5801.studentrecord.StudentRecordFactory.StudentRecordFactory;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import edu.umn.csci5801.GRADS;
import edu.umn.csci5801.db.DatabaseAccessException;
import edu.umn.csci5801.session.InvalidUserException;
import edu.umn.csci5801.studentrecord.StudentRecord;
import edu.umn.csci5801.studentrecord.StudentRecordFactory.StudentRecordFactory;
import edu.umn.csci5801.studentrecord.transcript.CourseTaken;
import edu.umn.csci5801.studentrecord.transcript.ProgressSummary;

/**
 */
public class SetUserTest {
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
                grads.setUser(null);
            } catch (DatabaseAccessException e) {
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
    }


}
