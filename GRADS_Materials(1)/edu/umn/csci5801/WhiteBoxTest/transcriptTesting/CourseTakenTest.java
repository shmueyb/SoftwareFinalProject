package edu.umn.csci5801.WhiteBoxTest.transcriptTesting;

import edu.umn.csci5801.studentrecord.transcript.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: lil4000
 * Date: 12/17/13
 * Time: 9:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class CourseTakenTest {
    private CourseTaken courseTaken;

    @Before
    public void init() {
        Course course = new Course("Software Engineering", "csci5801", "3",
                CourseArea.ARCHITECTURE_SYSTEMS_SOFTWARE);
        Term term = new Term(Semester.FALL, 2012);
        courseTaken = new CourseTaken(course, term, Grade.B);
    }

    @Test
    public void getCourseTest() {
        Assert.assertEquals(courseTaken.getCourse().getName(), "Software Engineering");
        Assert.assertEquals(courseTaken.getCourse().getId(), "csci5801");
        Assert.assertEquals(courseTaken.getCourse().getNumCredits(), "3");
        Assert.assertEquals(courseTaken.getCourse().getCourseArea(), CourseArea.ARCHITECTURE_SYSTEMS_SOFTWARE);
    }

    @Test
    public void getTermTest(){
        Assert.assertEquals(courseTaken.getTerm().getSemester(), Semester.FALL);
        Assert.assertEquals(courseTaken.getTerm().getYear(), (Object) 2012);
    }

    @Test
    public void getGradeTest(){
        Assert.assertEquals(courseTaken.getGrade(), Grade.B);
    }

    @Test
    public void setCourseTest(){
       courseTaken.setCourse(new Course("Networks", "csci4211", "4", CourseArea.APPLICATIONS));
       Assert.assertEquals(courseTaken.getCourse().getName(), "Networks");
       Assert.assertEquals(courseTaken.getCourse().getId(), "csci4211");
       Assert.assertEquals(courseTaken.getCourse().getNumCredits(), "4");
       Assert.assertEquals(courseTaken.getCourse().getCourseArea(), CourseArea.APPLICATIONS);
    }

    @Test
    public void setGradeTest(){
        courseTaken.setGrade(Grade.C);
        Assert.assertEquals(courseTaken.getGrade(), Grade.C);
    }

    @Test
    public void setTermTest(){
        courseTaken.setTerm(new Term(Semester.SPRING, 2008));
        Assert.assertEquals(courseTaken.getTerm().getSemester(), Semester.SPRING);
        Assert.assertEquals(courseTaken.getTerm().getYear(), (Object) 2008);
    }

}
