package edu.umn.csci5801.WhiteBoxTest.programTesting;

import org.junit.Assert;
import org.junit.Test;

import edu.umn.csci5801.studentrecord.program.Department;

/**
 */
public class DepartmentTest {

    @Test
    public void testCompScie() {
        Assert.assertSame(Department.COMPUTER_SCIENCE, 0);
    }

    @Test
    public void testMath() {
        Assert.assertSame(Department.MATH, 1);
    }
}
