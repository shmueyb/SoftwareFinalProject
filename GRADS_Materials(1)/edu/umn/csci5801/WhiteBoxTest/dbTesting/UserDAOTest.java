package edu.umn.csci5801.WhiteBoxTest.dbTesting;

import edu.umn.csci5801.GRADS;
import edu.umn.csci5801.db.UserDAO;
import edu.umn.csci5801.session.Users;
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
        Users user = UserDAO.getUserByID("tolas9999");
        assertEquals(user.getUser().getFirstName(), "Georganne");
        assertEquals(user.getDepartment(), Department.COMPUTER_SCIENCE);
    }

}
