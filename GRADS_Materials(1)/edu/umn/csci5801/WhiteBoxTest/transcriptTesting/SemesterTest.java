package edu.umn.csci5801.WhiteBoxTest.transcriptTesting;

import edu.umn.csci5801.studentrecord.transcript.Semester;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: lil4000
 * Date: 12/17/13
 * Time: 10:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class SemesterTest {
    @Test
    public void TestSpring() {
        Assert.assertEquals(Semester.SPRING.ordinal(), 0);
    }

    @Test
    public void TestFall() {
        Assert.assertEquals(Semester.SUMMER.ordinal(), 1);
    }

    @Test
    public void TestSummer() {
        Assert.assertEquals(Semester.FALL.ordinal(), 2);
    }

    @Test
    public void TestNext(){
        Assert.assertEquals(Semester.SUMMER.next(), Semester.FALL);
    }

}
