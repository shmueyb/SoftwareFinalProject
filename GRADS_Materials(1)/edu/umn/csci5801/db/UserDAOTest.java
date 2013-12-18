package edu.umn.csci5801.db;

import edu.umn.csci5801.GRADS;
import edu.umn.csci5801.session.User;
import edu.umn.csci5801.studentrecord.StudentRecord;
import edu.umn.csci5801.studentrecord.program.Department;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by UBLUSSA on 12/11/13.
 */
public class UserDAOTest {
    @Test
    public void testGetUserByID() throws Exception {
        GRADS grads = new GRADS("GRADS_Materials/Data/students.txt", "GRADS_Materials/Data/courses.txt", "GRADS_Materials/Data/users.txt");
        User user = UserDAO.getUserByID("tolas9999");
        assertEquals(user.getFirstName(), "Georganne");
        assertEquals(user.getDepartment(), Department.COMPUTER_SCIENCE);
    }

}
