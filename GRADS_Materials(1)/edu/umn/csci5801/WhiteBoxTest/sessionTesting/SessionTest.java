package edu.umn.csci5801.WhiteBoxTest.sessionTesting;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.umn.csci5801.GRADS;
import edu.umn.csci5801.session.Professor;
import edu.umn.csci5801.session.Session;
import edu.umn.csci5801.session.Student;
import edu.umn.csci5801.session.User;
import edu.umn.csci5801.session.UserType;
import edu.umn.csci5801.studentrecord.program.Department;

/**
 */
public class SessionTest {
    private static Session session;
    private static GRADS grads;

    @BeforeClass
    public static void init() throws Exception {
        grads = new GRADS("GRADS_Materials/Data/TestStudents.txt", "GRADS_Materials/Data/courses.txt", "GRADS_Materials/Data/TestUsers.txt");
        session = new Session("tolas9999");
    }

    @Test
    public void getUserTypeTest() {
        Assert.assertEquals(session.getCurrentUserType(), UserType.GRADUATE_PROGRAM_COORDINATOR);
    }

    @Test
    public void getProfessorUserTest() {
        Assert.assertEquals(session.getProfessorUser(), new Professor("Georgane", "Tolaas", Department.COMPUTER_SCIENCE));
    }

    @Test
    public void getUserTest_student() throws Exception{
        Session session2 = new Session("nguy0621");
        Assert.assertEquals(session2.getStudentUser(), new Student("Luan", "Nguyen", "nguy0621"));
    }
    @Test
    public void getUser() throws Exception{
        User user = new User("tolas9999", "Georganne", "Tolaas", UserType.GRADUATE_PROGRAM_COORDINATOR, Department.COMPUTER_SCIENCE);
        Assert.assertEquals(session.getUser().getDepartment(), user.getDepartment());
        Assert.assertEquals(session.getUser().getLastName(), user.getLastName());
        Assert.assertEquals(session.getUser().getFirstName(), user.getFirstName());
        Assert.assertEquals(session.getUser().getRole(), user.getRole());
        Assert.assertEquals(session.getUser().getID(), user.getID());
    }


}
