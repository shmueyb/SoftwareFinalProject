package edu.umn.csci5801.WhiteBoxTest.sessionTesting;

import edu.umn.csci5801.session.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: lil4000
 * Date: 12/17/13
 * Time: 7:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class StudentTest {
    private Student student;

    @Before
    public void init() {
        student = new Student("Xum", "Giang", "gian0036");
    }

    @Test
    public void getFirstNameTest() {
        Assert.assertEquals(student.getFirstName(), "Xum");
    }

    @Test
    public void getLastNameTest() {
        Assert.assertEquals(student.getLastName(), "Giang");
    }

    @Test
    public void getIDTest(){
        Assert.assertEquals(student.getId(), "gian0036");
    }

    @Test
    public void setFirstNameTest() {
        student.setFirstName("John");
        Assert.assertEquals(student.getFirstName(), "John");
    }

    @Test
    public void setLastNameTest() {
        student.setLastName("Nguyen");
        Assert.assertEquals(student.getLastName(), "Nguyen");
    }

    @Test
    public void setIdTest(){
        student.setId("reeve098");
        Assert.assertEquals(student.getId(), "reeve098");
    }

}
