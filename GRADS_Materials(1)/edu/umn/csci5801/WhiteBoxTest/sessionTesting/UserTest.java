package edu.umn.csci5801.WhiteBoxTest.sessionTesting;

import edu.umn.csci5801.session.User;
import edu.umn.csci5801.session.UserType;
import edu.umn.csci5801.studentrecord.program.Department;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: lil4000
 * Date: 12/17/13
 * Time: 7:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserTest {
    private User user;

    @Before
    public void init() {
        user = new User("gian0036", "Xum", "Giang", UserType.STUDENT, Department.COMPUTER_SCIENCE);
    }

    @Test
    public void getFirstNameTest() {
        Assert.assertEquals(user.getFirstName(), "Xum");
    }

    @Test
    public void getLastNameTest() {
        Assert.assertEquals(user.getLastName(), "Giang");
    }

    @Test
    public void getIDTest(){
        Assert.assertEquals(user.getID(), "gian0036");
    }

    @Test
    public void getRoleTest(){
        Assert.assertEquals(user.getRole(), UserType.STUDENT);
    }

    @Test
    public void getDepartmentTest(){
        Assert.assertEquals(user.getDepartment(), Department.COMPUTER_SCIENCE);
    }



}
