package edu.umn.csci5801.test;

import org.junit.Assert;
import org.junit.Test;

import edu.umn.csci5801.GRADS;
import edu.umn.csci5801.db.DatabaseAccessException;
import edu.umn.csci5801.session.InvalidUserException;
import edu.umn.csci5801.studentrecord.StudentRecordFactory.StudentRecordFactory;

/**
 */
public class SetUserTest {
    @Test
    public void testSetUserValidID() throws InvalidUserException {
        GRADS grads = null;
        try {
            StudentRecordFactory.instantiateTestDb();
            grads = new GRADS("GRADS_Materials/Data/TestStudents.txt", "GRADS_Materials/Data/courses.txt", "GRADS_Materials/Data/TestUsers.txt");
            grads.setUser("nguy0621");
            Assert.assertSame("nguy0621", grads.getUser());
        } catch (DatabaseAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (Exception e) {
        }
    }


}
