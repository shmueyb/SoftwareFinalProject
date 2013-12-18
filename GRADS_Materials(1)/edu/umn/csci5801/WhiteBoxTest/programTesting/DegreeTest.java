package edu.umn.csci5801.WhiteBoxTest.programTesting;

import org.junit.Assert;
import org.junit.Test;

import edu.umn.csci5801.studentrecord.program.Degree;

/**
 */
public class DegreeTest {

    @Test
    public void TestMS_A() {
        Assert.assertSame(Degree.MS_A.ordinal(), 0);
    }

    @Test
    public void TestMS_B() {
        Assert.assertSame(Degree.MS_B.ordinal(), 1);
    }

    @Test
    public void TestMS_C() {
        Assert.assertSame(Degree.MS_C.ordinal(), 2);
    }

    @Test
    public void TestPH_D() {
        Assert.assertSame(Degree.PHD.ordinal(), 3);
    }

}
