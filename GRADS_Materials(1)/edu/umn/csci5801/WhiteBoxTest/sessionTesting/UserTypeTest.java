package edu.umn.csci5801.WhiteBoxTest.sessionTesting;

import edu.umn.csci5801.session.UserType;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: lil4000
 * Date: 12/17/13
 * Time: 10:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserTypeTest {
    @Test
    public void TestGPC() {
        Assert.assertEquals(UserType.GRADUATE_PROGRAM_COORDINATOR.ordinal(), 1);
    }

    @Test
    public void TestSt() {
        Assert.assertEquals(UserType.STUDENT.ordinal(), 0);
    }
}
