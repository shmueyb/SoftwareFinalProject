package edu.umn.csci5801.WhiteBoxTest.transcriptTesting;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.umn.csci5801.studentrecord.transcript.CheckResultDetails;
import edu.umn.csci5801.test.StudentRecordFactory;

/**
 */
public class CheckResultDetailsTest {
    private CheckResultDetails checkResultDetails;

    @Before
    public void init() {
        checkResultDetails =  new CheckResultDetails();
    }

    @Test
    public void setAndGetGPA() {
        checkResultDetails.setGPA(2.0F);
        Assert.assertTrue(checkResultDetails.getGPA() == 2.0);
    }

    @Test
    public void setAndGetCourses() {
        checkResultDetails.setCourses(StudentRecordFactory.CatherineCoursesTaken());
        Assert.assertEquals(checkResultDetails.getCourses(), StudentRecordFactory.CatherineCoursesTaken());
    }

    @Test
    public void setAndGetOthers() {
        List<String> others = new LinkedList<String>();
        others.add("yay");
        others.add("hoorah!");
        checkResultDetails.setOther(others);
        Assert.assertSame(checkResultDetails.getOther().size(), 2);
        Assert.assertTrue(checkResultDetails.getOther().contains("yay"));
        Assert.assertTrue(checkResultDetails.getOther().contains("hoorah!"));
    }
}
