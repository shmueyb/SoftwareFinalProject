package edu.umn.csci5801.db;


import java.util.ArrayList;
import java.util.List;

import edu.umn.csci5801.studentrecord.program.Degree;
import edu.umn.csci5801.studentrecord.program.DegreePlan;
import edu.umn.csci5801.studentrecord.program.DegreeRequirement;
import edu.umn.csci5801.studentrecord.program.DegreeRequirementFactory;
import edu.umn.csci5801.studentrecord.program.Department;
import edu.umn.csci5801.studentrecord.requirements.Milestone;
import edu.umn.csci5801.studentrecord.transcript.Course;

/**
 * Created with IntelliJ IDEA.
 * User: Ben
 * Date: 12/12/13
 * Time: 2:01 PM
 * To change this template use File | Settings | File Templates.
 *
 * We decided to hard code the requirements as there was no direction that a new requirements file can be passed
 * in, and there are no controls from the interface to allow for this updating.  Otherwise, we would have created
 * a JSON formatted file that could be updated or swapped.
 */
public class DegreePlanDAO {

    private static List<DegreePlan> getAllDegreePlans() {
        List<DegreePlan> allPlans = new ArrayList<DegreePlan>();

        return allPlans;
    }

    public static DegreePlan getDegreePlanByDepartmentAndDegree(Department dept, Degree degree) throws DatabaseAccessException{
        return getDegreePhDRequirements();
    }

    public static DegreePlan getDegreePhDRequirements() throws DatabaseAccessException{

        DegreeRequirement breadth;
        DegreeRequirement theoryAlgs;
        DegreeRequirement archSS;
        DegreeRequirement apps;

        List<Course> theoryAlgsCourses = new ArrayList<Course>();
        List<Course> aSSCourses = new ArrayList<Course>();
        List<Course> appCourses = new ArrayList<Course>();

        List<String> onlyCSCI = new ArrayList<String>();
        onlyCSCI.add("csci");

        theoryAlgsCourses.add(CourseDAO.getCourseByID("csci5302"));
        theoryAlgsCourses.add(CourseDAO.getCourseByID("csci5304"));
        theoryAlgsCourses.add(CourseDAO.getCourseByID("csci5403"));
        theoryAlgsCourses.add(CourseDAO.getCourseByID("csci5421"));
        theoryAlgsCourses.add(CourseDAO.getCourseByID("csci5525"));

        aSSCourses.add(CourseDAO.getCourseByID("csci5103"));
        aSSCourses.add(CourseDAO.getCourseByID("csci5104"));
        aSSCourses.add(CourseDAO.getCourseByID("csci5105"));
        aSSCourses.add(CourseDAO.getCourseByID("csci5106"));
        aSSCourses.add(CourseDAO.getCourseByID("csci5161"));
        aSSCourses.add(CourseDAO.getCourseByID("csci5204"));
        aSSCourses.add(CourseDAO.getCourseByID("csci5211"));
        aSSCourses.add(CourseDAO.getCourseByID("csci5221"));
        aSSCourses.add(CourseDAO.getCourseByID("csci5231"));
        aSSCourses.add(CourseDAO.getCourseByID("csci5451"));
        aSSCourses.add(CourseDAO.getCourseByID("csci5461"));
        aSSCourses.add(CourseDAO.getCourseByID("csci5708"));
        aSSCourses.add(CourseDAO.getCourseByID("csci5801"));
        aSSCourses.add(CourseDAO.getCourseByID("csci5802"));


        appCourses.add(CourseDAO.getCourseByID("csci5115"));
        appCourses.add(CourseDAO.getCourseByID("csci5125"));
        appCourses.add(CourseDAO.getCourseByID("csci5271"));
        appCourses.add(CourseDAO.getCourseByID("csci5471"));
        appCourses.add(CourseDAO.getCourseByID("csci5481"));
        appCourses.add(CourseDAO.getCourseByID("csci5511"));
        appCourses.add(CourseDAO.getCourseByID("csci5512"));
        appCourses.add(CourseDAO.getCourseByID("csci5521"));
        appCourses.add(CourseDAO.getCourseByID("csci5523"));
        appCourses.add(CourseDAO.getCourseByID("csci5551"));
        appCourses.add(CourseDAO.getCourseByID("csci5561"));
        appCourses.add(CourseDAO.getCourseByID("csci5607"));
        appCourses.add(CourseDAO.getCourseByID("csci5608"));
        appCourses.add(CourseDAO.getCourseByID("csci5609"));
        appCourses.add(CourseDAO.getCourseByID("csci5611"));
        appCourses.add(CourseDAO.getCourseByID("csci5619"));
        appCourses.add(CourseDAO.getCourseByID("csci5707"));


        DegreeRequirementFactory factory = new DegreeRequirementFactory();

        factory.setRequirementName("Theory and Algorithms");
        factory.setApplicableCourses(theoryAlgsCourses);
        factory.setMinCourseCount(1);
        factory.setSNAllowed(false);

        theoryAlgs = factory.make();



        factory = new DegreeRequirementFactory();

        factory.setRequirementName("Architecture, Systems, and Software");
        factory.setApplicableCourses(aSSCourses);
        factory.setSNAllowed(false);
        factory.setMinCourseCount(1);

        archSS = factory.make();



        factory = new DegreeRequirementFactory();

        factory.setRequirementName("Applications");
        factory.setApplicableCourses(appCourses);
        factory.setSNAllowed(false);
        factory.setMinCourseCount(1);

        apps = factory.make();


        factory = new DegreeRequirementFactory();

        List<DegreeRequirement> breadthSubReqs = new ArrayList<DegreeRequirement>();
        breadthSubReqs.add(theoryAlgs);
        breadthSubReqs.add(archSS);
        breadthSubReqs.add(apps);

        factory.setRequirementName("Breadth Requirement");
        factory.setSubRequirements(breadthSubReqs);
        factory.setSNAllowed(false);
        factory.setMinCourseCount(5);
        factory.setMinGPA(3.45);
        factory.setOnlyAllowCoursesThatPassSubReqs(true);

        breadth = factory.make();



        factory = new DegreeRequirementFactory();

        List<Course> phdThesis = new ArrayList<Course>();
        phdThesis.add(CourseDAO.getCourseByID("csci8888"));

        factory.setRequirementName("Thesis PHD");
        factory.setMinCredits(24);
        factory.setApplicableCourses(phdThesis);
        factory.setMayRepeatCoursesForCredit(true);

        DegreeRequirement thesisReq = factory.make();



        factory = new DegreeRequirementFactory();

        List<Course> colloqList = new ArrayList<Course>();
        colloqList.add(CourseDAO.getCourseByID("csci8970"));

        factory.setRequirementName("Colloquium");
        factory.setApplicableCourses(colloqList);
        factory.setMustTakeAllCourses(true);

        DegreeRequirement colloq = factory.make();



        factory = new DegreeRequirementFactory();

        factory.setMinCredits(6);
        factory.setCourseDeptsToExclude(onlyCSCI);
        factory.setMinCourseLevel(5000);
        factory.setRequirementName("Out of Department");

        DegreeRequirement outOfDept = factory.make();



        factory = new DegreeRequirementFactory();

        ArrayList<Course> introResearchCourses = new ArrayList<Course>();
        introResearchCourses.add(CourseDAO.getCourseByID("csci8001"));
        introResearchCourses.add(CourseDAO.getCourseByID("csci8002"));

        factory.setApplicableCourses(introResearchCourses);
        factory.setMustTakeAllCourses(true);
        factory.setSNAllowed(true);
        factory.setMinCourseLevel(5000);
        factory.setRequirementName("Introduction to Research");

        DegreeRequirement introToResearch = factory.make();



        factory = new DegreeRequirementFactory();

        List<Course> thesisCredits = new ArrayList<Course>();
        thesisCredits.add(CourseDAO.getCourseByID("csci8888"));
        thesisCredits.add(CourseDAO.getCourseByID("csci8777"));

        factory.setCoursesToExclude(thesisCredits);
        factory.setMinCredits(16);
        factory.setCourseDeptsToInclude(onlyCSCI);
        factory.setMinCourseLevel(5000);
        factory.setRequirementName("16 csci Credits");

        DegreeRequirement csci16Credits = factory.make();



        factory = new DegreeRequirementFactory();
        List<DegreeRequirement> totalCreditsSubReqs = new ArrayList<DegreeRequirement>();
        totalCreditsSubReqs.add(csci16Credits);

        factory.setMinCredits(31);
        factory.setSubRequirements(totalCreditsSubReqs);
        factory.setCoursesToExclude(phdThesis);
        factory.setRequirementName("Total Credits");

        DegreeRequirement totalCredits = factory.make();



        factory = new DegreeRequirementFactory();

        List<DegreeRequirement> otherReqList = new ArrayList<DegreeRequirement>();
        otherReqList.add(thesisReq);
        otherReqList.add(colloq);
        otherReqList.add(outOfDept);
        otherReqList.add(introToResearch);
        otherReqList.add(totalCredits);

        factory.setSubRequirements(otherReqList);
        factory.setRequirementName("Other Course Requirements");

        DegreeRequirement otherReqs = factory.make();



        factory = new DegreeRequirementFactory();

        factory.setRequirementName("Overall GPA");
        factory.setMinGPA(3.45);

        DegreeRequirement overallGPA = factory.make();



        factory = new DegreeRequirementFactory();

        factory.setRequirementName("In Program GPA");
        factory.setMinGPA(3.45);

        DegreeRequirement inProgramGPA = factory.make();



        factory = new DegreeRequirementFactory();

        List<DegreeRequirement> otherGPAReqs = new ArrayList<DegreeRequirement>();
        otherGPAReqs.add(overallGPA);
        otherGPAReqs.add(inProgramGPA);

        factory.setRequirementName("Other GPA Requirements");
        factory.setSubRequirements(otherGPAReqs);

        DegreeRequirement otherGPARequirements = factory.make();


        factory = new DegreeRequirementFactory();

        List<Milestone> milestoneList = new ArrayList<Milestone>();
        milestoneList.add(Milestone.PRELIM_COMMITTEE_APPOINTED);
        milestoneList.add(Milestone.WRITTEN_PE_SUBMITTED);
        milestoneList.add(Milestone.WRITTEN_PE_APPROVED);
        milestoneList.add(Milestone.ORAL_PE_PASSED);
        milestoneList.add(Milestone.DPF_SUBMITTED);
        milestoneList.add(Milestone.DPF_APPROVED);
        milestoneList.add(Milestone.THESIS_COMMITTEE_APPOINTED);
        milestoneList.add(Milestone.PROPOSAL_PASSED);
        milestoneList.add(Milestone.GRADUATION_PACKET_REQUESTED);
        milestoneList.add(Milestone.THESIS_SUBMITTED);
        milestoneList.add(Milestone.THESIS_APPROVED);
        milestoneList.add(Milestone.DEFENSE_PASSED);

        factory.setRequirementName("Milestones");
        factory.setRequiredMilestones(milestoneList);

        DegreeRequirement milestoneReqs = factory.make();



        DegreePlan phDDegreePlan = new DegreePlan(
                breadth,
                otherReqs,
                otherGPARequirements,
                milestoneReqs,
                Department.COMPUTER_SCIENCE,
                Degree.PHD);

        return phDDegreePlan;
    }
}
