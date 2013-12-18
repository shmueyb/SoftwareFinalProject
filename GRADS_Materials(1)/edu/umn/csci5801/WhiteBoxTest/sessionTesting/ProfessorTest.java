package edu.umn.csci5801.WhiteBoxTest.sessionTesting;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.umn.csci5801.session.Professor;
import edu.umn.csci5801.studentrecord.program.Department;


/**
 */
public class ProfessorTest {
    private Professor professor;

    @Before
    public  void init() {
        professor = new Professor("Xum", "Giang", Department.MATH);
    }

    @Test
    public void getDepartmentTest() {
        Assert.assertEquals(professor.getDepartment(), Department.MATH);
    }

    @Test
    public void getFirstNameTest() {
        Assert.assertEquals(professor.getFirstName(), "Xum");
    }

    @Test
    public void getLastNameTest() {
        Assert.assertEquals(professor.getLastName(), "Giang");
    }

    @Test
    public void equalTest() {
        Assert.assertTrue(professor.equals(professor));
        Assert.assertFalse(professor.equals(new Professor("John", "Doe", Department.COMPUTER_SCIENCE)));
    }

    @Test
    public void HashCodeTest() {
        Assert.assertEquals(professor.hashCode(), (31 + Department.MATH.hashCode()));
        Professor newProf = new Professor("Jon", "Doe", null);
        Assert.assertEquals(newProf.hashCode(), 31 );
    }
}
