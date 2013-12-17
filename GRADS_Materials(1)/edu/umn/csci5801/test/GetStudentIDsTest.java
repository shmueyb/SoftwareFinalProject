package edu.umn.csci5801.test;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.umn.csci5801.GRADS;
import edu.umn.csci5801.access.AccessDeniedException;
import edu.umn.csci5801.studentrecord.StudentRecordFactory.StudentRecordFactory;

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
        grads.setUser("tolas9999");
        List<String> actual  = grads.getStudentIDs();
        Assert.assertTrue(actual.contains("nguy0621"));
        Assert.assertTrue(actual.contains("gayxx067"));
        Assert.assertTrue(actual.contains("desil1337"));
        Assert.assertTrue(actual.contains("1111"));
        Assert.assertSame(actual.size(), 4);
    }

    /**
     * Checks to make sure student cannot at a list of students in a dept.
     * @throws edu.umn.csci5801.db.DatabaseAccessException
     * @throws java.io.FileNotFoundException
     */
    @Test
    public void testGetStudentIDs_AsStudent() throws Exception {

        grads.setUser("gayxx067");
        try {
            grads.getStudentIDs();
            fail();
        } catch (AccessDeniedException ex) {
        }
    }

}
