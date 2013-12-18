package edu.umn.csci5801.WhiteBoxTest.transcriptTesting;

import edu.umn.csci5801.studentrecord.transcript.CourseArea;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: lil4000
 * Date: 12/17/13
 * Time: 9:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class CourseAreaTest {

    @Test
    public void TestMS_A() {
        Assert.assertSame(CourseArea.THEORY_ALGORITHMS.ordinal(), 0);
    }

    @Test
    public void TestMS_B() {
        Assert.assertSame(CourseArea.ARCHITECTURE_SYSTEMS_SOFTWARE.ordinal(), 1);
    }

    @Test
    public void TestMS_C() {
        Assert.assertSame(CourseArea.APPLICATIONS.ordinal(), 2);
    }

}
