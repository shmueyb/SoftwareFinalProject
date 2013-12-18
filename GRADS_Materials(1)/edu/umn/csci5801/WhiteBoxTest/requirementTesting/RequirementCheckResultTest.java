package edu.umn.csci5801.WhiteBoxTest.requirementTesting;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.umn.csci5801.studentrecord.requirements.RequirementCheckResult;
import edu.umn.csci5801.studentrecord.transcript.CheckResultDetails;
import edu.umn.csci5801.test.StudentRecordFactory;

/**
 */
public class RequirementCheckResultTest {
    private RequirementCheckResult requirementCheckResult;
    private CheckResultDetails checkResultDetails = new CheckResultDetails();
    private List<String> others = new LinkedList<String>();
    private List<String> errorMsgs = new LinkedList<String>();


    @Before
    public void init() {
        others.add("a");
        others.add("b");

        CheckResultDetails checkResultDetails = new CheckResultDetails();
        checkResultDetails.setCourses(StudentRecordFactory.CatherineCoursesTaken());
        checkResultDetails.setGPA(3.4F);
        checkResultDetails.setOther(others);
        requirementCheckResult = new RequirementCheckResult("MyRequirement", true, checkResultDetails);
    }

    @Test
    public void getNameTest() {
        Assert.assertEquals(requirementCheckResult.getName(), "MyRequirement");
    }

    @Test
    public void setNameTest() {
        requirementCheckResult.setName("Xum");
        Assert.assertEquals(requirementCheckResult.getName(), "Xum");
    }

    @Test
    public void isPassedTest() {
        Assert.assertTrue(requirementCheckResult.isPassed());
    }

    @Test
    public void setPassedTest() {
        requirementCheckResult.setPassed(false);
        Assert.assertFalse(requirementCheckResult.isPassed());
    }

    @Test
    public void get_setErrorMsgs() {
        errorMsgs.add("1");
        errorMsgs.add("2");
        requirementCheckResult.setErrorMsgs(errorMsgs);
        Assert.assertTrue(requirementCheckResult.getErrorMsgs().contains("1"));
        Assert.assertTrue(requirementCheckResult.getErrorMsgs().contains("2"));
        Assert.assertSame(requirementCheckResult.getErrorMsgs().size(), 2);
    }

    @Test
    public void getDetails() {
        Assert.assertTrue(requirementCheckResult.getDetails().getGPA() == 3.4F);
        Assert.assertEquals(requirementCheckResult.getDetails().getCourses(), StudentRecordFactory.CatherineCoursesTaken());
        Assert.assertEquals(requirementCheckResult.getDetails().getOther(), others);

    }
    @Test
    public void setDetails() {
        CheckResultDetails details = new CheckResultDetails();
        details.setGPA(4.0F);
        details.setOther(errorMsgs);
        details.setCourses(StudentRecordFactory.GregCoursesTaken());
        requirementCheckResult.setDetails(details);
        Assert.assertTrue(requirementCheckResult.getDetails().getGPA() == 4.0F);
        Assert.assertEquals(requirementCheckResult.getDetails().getCourses(), StudentRecordFactory.GregCoursesTaken());
        Assert.assertEquals(requirementCheckResult.getDetails().getOther(), errorMsgs);

    }
}
