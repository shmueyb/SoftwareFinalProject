package edu.umn.csci5801.WhiteBoxTest.requirementTesting;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.umn.csci5801.studentrecord.requirements.Milestone;
import edu.umn.csci5801.studentrecord.requirements.MilestoneSet;
import edu.umn.csci5801.studentrecord.transcript.Semester;
import edu.umn.csci5801.studentrecord.transcript.Term;

/**
 */
public class MilestoneSetTest {
    private MilestoneSet milestoneSet;

    @Before
    public void init() {
        milestoneSet = new MilestoneSet(Milestone.DEFENSE_PASSED, new Term(Semester.FALL, 2015));
    }

    @Test
    public void getMilestoneTest() {
        Assert.assertEquals(milestoneSet.getMilestone(), Milestone.DEFENSE_PASSED);
    }
    @Test
    public void setMilestoneTest() {

        milestoneSet.setMilestone(Milestone.PRELIM_COMMITTEE_APPOINTED);
        Assert.assertEquals(milestoneSet.getMilestone(), Milestone.PRELIM_COMMITTEE_APPOINTED);
        milestoneSet.setMilestone(Milestone.DPF_APPROVED);
        Assert.assertEquals(milestoneSet.getMilestone(), Milestone.DPF_APPROVED);
        milestoneSet.setMilestone(Milestone.THESIS_APPROVED);
        Assert.assertEquals(milestoneSet.getMilestone(), Milestone.THESIS_APPROVED);
        milestoneSet.setMilestone(Milestone.TRACKING_FORM_APPROVED);
        Assert.assertEquals(milestoneSet.getMilestone(), Milestone.TRACKING_FORM_APPROVED);
        milestoneSet.setMilestone(Milestone.DPF_SUBMITTED);
        Assert.assertEquals(milestoneSet.getMilestone(), Milestone.DPF_SUBMITTED);
        milestoneSet.setMilestone(Milestone.GRADUATION_PACKET_REQUESTED);
        Assert.assertEquals(milestoneSet.getMilestone(), Milestone.GRADUATION_PACKET_REQUESTED);
        milestoneSet.setMilestone(Milestone.ORAL_PE_PASSED);
        Assert.assertEquals(milestoneSet.getMilestone(), Milestone.ORAL_PE_PASSED);
        milestoneSet.setMilestone(Milestone.WRITTEN_PE_APPROVED);
        Assert.assertEquals(milestoneSet.getMilestone(), Milestone.WRITTEN_PE_APPROVED);

    }

    @Test
    public void getTerm() {
        Assert.assertEquals(milestoneSet.getTerm(), new Term(Semester.FALL, 2015));
    }

    @Test
    public void setTerm() {
        milestoneSet.setTerm(new Term (Semester.SPRING, 5000));
        Assert.assertEquals(milestoneSet.getTerm(), new Term(Semester.SPRING, 5000));
    }
}
