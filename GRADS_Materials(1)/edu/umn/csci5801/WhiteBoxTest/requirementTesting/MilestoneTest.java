package edu.umn.csci5801.WhiteBoxTest.requirementTesting;

import edu.umn.csci5801.studentrecord.requirements.Milestone;
import org.junit.Assert;
import org.junit.Test;

/**
 */

public class MilestoneTest {
    @Test
    public void testPrelimCommittee() {
        Assert.assertSame(Milestone.PRELIM_COMMITTEE_APPOINTED.ordinal(), 0);
    }

    @Test
    public void testWrittenPESubmitted() {
        Assert.assertSame(Milestone.WRITTEN_PE_SUBMITTED.ordinal(), 1);
    }

    @Test
    public void testWrittenPEApproved() {
        Assert.assertSame(Milestone.WRITTEN_PE_APPROVED.ordinal(), 2);
    }

    @Test
    public void testOralPESubmitted() {
        Assert.assertSame(Milestone.ORAL_PE_PASSED.ordinal(), 3);
    }
    @Test
    public void testDPFSubmitted() {
        Assert.assertSame(Milestone.DPF_SUBMITTED.ordinal(), 4);
    }

    @Test
    public void testDPFApproved() {
        Assert.assertSame(Milestone.DPF_APPROVED.ordinal(), 5);
    }

    @Test
    public void testThesisCommittee() {
        Assert.assertSame(Milestone.THESIS_COMMITTEE_APPOINTED.ordinal(), 6);
    }

    @Test
    public void testProposalPass() {
        Assert.assertSame(Milestone.PROPOSAL_PASSED.ordinal(), 7);
    }

    @Test
    public void testGradPacketRequested() {
        Assert.assertSame(Milestone.GRADUATION_PACKET_REQUESTED.ordinal(), 8);
    }

    @Test
    public void testThesisSubmitted() {
        Assert.assertSame(Milestone.THESIS_SUBMITTED.ordinal(), 9);
    }

    @Test
    public void testThesisApproved() {
        Assert.assertSame(Milestone.THESIS_APPROVED.ordinal(), 10);
    }

    @Test
    public void testDefensePassed() {
        Assert.assertSame(Milestone.DEFENSE_PASSED.ordinal(), 11);
    }

    @Test
    public void testProjCommittee() {
        Assert.assertSame(Milestone.PROJECT_COMMITTEE_APPOINTED.ordinal(), 12);
    }

    @Test
    public void testTrackingFormSubmit() {
        Assert.assertSame(Milestone.TRACKING_FORM_SUBMITTED.ordinal(), 13);
    }

    @Test
    public void testTrackingFormApprove() {
        Assert.assertSame(Milestone.TRACKING_FORM_APPROVED.ordinal(), 14);
    }

}
