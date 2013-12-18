package edu.umn.csci5801.WhiteBoxTest.transcriptTesting;

import edu.umn.csci5801.studentrecord.transcript.Course;
import edu.umn.csci5801.studentrecord.transcript.CourseArea;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: lil4000
 * Date: 12/17/13
 * Time: 8:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class CourseTest {
    private Course course;

    @Before
    public void init() {
        course = new Course("Software Engineering","csci5801", "3",
                CourseArea.ARCHITECTURE_SYSTEMS_SOFTWARE);
    }

    @Test
    public void getNameTest() {
        Assert.assertEquals(course.getName(), "Software Engineering");
    }

    @Test
    public void getIDTest(){
        Assert.assertEquals(course.getId(), "csci5801");
    }

    @Test
    public void getNumCreditsTest(){
        Assert.assertEquals(course.getNumCredits(), "3");
    }

    @Test
    public void getCourseAreaTest(){
        Assert.assertEquals(course.getCourseArea(), CourseArea.ARCHITECTURE_SYSTEMS_SOFTWARE);
    }

    @Test
    public void setNameTest(){
        course.setName("Networks");
        Assert.assertEquals(course.getName(), "Networks");
    }

    @Test
    public void setIDTest(){
        course.setId("csci4211");
        Assert.assertEquals(course.getId(), "csci4211");
    }

    @Test
    public void setNumCreditsTest(){
        course.setNumCredits("4");
        Assert.assertEquals(course.getNumCredits(), "4");
    }

    @Test
    public void setCourseArea(){
        course.setCourseArea(CourseArea.THEORY_ALGORITHMS);
        Assert.assertEquals(course.getCourseArea(), CourseArea.THEORY_ALGORITHMS);
    }




}
