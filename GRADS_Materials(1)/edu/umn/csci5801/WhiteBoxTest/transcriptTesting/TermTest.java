package edu.umn.csci5801.WhiteBoxTest.transcriptTesting;

import edu.umn.csci5801.studentrecord.transcript.Semester;
import edu.umn.csci5801.studentrecord.transcript.Term;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: lil4000
 * Date: 12/17/13
 * Time: 10:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class TermTest {
    private Term term;

    @Before
    public void init() {
        term = new Term(Semester.SPRING, 2003);
    }

    @Test
    public void getSemesterTest() {
        Assert.assertEquals(term.getSemester(), Semester.SPRING);
    }

    @Test
    public void getYearTest(){
        Assert.assertEquals(term.getYear(), (Object) 2003);
    }

    @Test
    public void getNext1Test(){
        Assert.assertEquals(term.getNext(), new Term(Semester.SUMMER, 2003));
    }

    @Test
    public void getNext2Test(){
        Assert.assertEquals(term.getNext(1), new Term(Semester.SUMMER, 2003));
    }

    @Test
    public void getNext3Test(){
        Assert.assertEquals(term.getNext(3), new Term(Semester.SPRING, 2004));
    }

    @Test
    public void getNextNull(){
        try{
            term.getNext(0);
        }catch (IllegalArgumentException ex){
            //do nothing
        }
    }

}
