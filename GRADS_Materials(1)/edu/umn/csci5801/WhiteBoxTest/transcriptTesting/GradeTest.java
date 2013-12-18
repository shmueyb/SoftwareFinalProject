package edu.umn.csci5801.WhiteBoxTest.transcriptTesting;

import edu.umn.csci5801.studentrecord.transcript.Grade;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: lil4000
 * Date: 12/17/13
 * Time: 9:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class GradeTest {
    @Test
    public void TestA() {
        Assert.assertEquals(Grade.A.ordinal(), 0);
    }

    @Test
    public void TestB() {
        Assert.assertEquals(Grade.B.ordinal(), 1);
    }

    @Test
    public void TestC() {
        Assert.assertEquals(Grade.C.ordinal(), 2);
    }

    @Test
    public void TestD() {
    Assert.assertEquals(Grade.D.ordinal(), 3);
    }

    @Test
    public void TestF() {
        Assert.assertEquals(Grade.F.ordinal(), 4);
    }

    @Test
    public void TestS() {
        Assert.assertEquals(Grade.S.ordinal(), 5);
    }

    @Test
    public void TestN() {
        Assert.assertEquals(Grade.N.ordinal(), 6);
    }

    @Test
    public void Test_() {
        Assert.assertEquals(Grade._.ordinal(), 7);
    }

    @Test
    public void TestNumericValue(){
        Assert.assertTrue(Grade.A.numericValue() == 4.0);
    }
}
