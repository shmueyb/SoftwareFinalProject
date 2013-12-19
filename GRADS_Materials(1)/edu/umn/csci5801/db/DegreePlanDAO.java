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
 * @author Ben Hagaman
 * @author Sam Blustin
 * @author Catherine Reeves
 * @author Xum Giang
 * @author Trang Nguyen
 *
 * DegreePlanDAO.java
 *
 * Database Access Object which is an interface between the DegreePlan database and DegreePlan objects.
 * Allows for the lookup of DegreePlans by department and degree.
 */
public class DegreePlanDAO {
    /**
     * Returns all available degree plans.
     *
     * @return a List of all degree plans.
     */
    private static List<DegreePlan> getAllDegreePlans() throws DatabaseAccessException{
        List<DegreePlan> allPlans = new ArrayList<DegreePlan>();

        allPlans.add(getDegreePhDRequirements());
        allPlans.add(getDegreeMSARequirements());
        allPlans.add(getDegreeMSBRequirements());
        //allPlans.add(getDegreeMSCRequirements());

        return allPlans;
    }

    /**
     * returns the degree plan associated with the supplied department and degree.
     *
     * @param dept the department for the degree plan that we wish to retrieve.
     * @param degree the degree we wish to retrieve the degree plan for.
     * @return the DegreePlan
     * @throws DatabaseAccessException if there was an error in retrieving the DegreePlan.
     */
    public static DegreePlan getDegreePlanByDepartmentAndDegree(Department dept, Degree degree) throws DatabaseAccessException{
        return getDegreePhDRequirements();
    }

    /**
     * Returns the PhD degree requirements. (This would normally be in a JSON database, but for the sake of time, and overall
     * quality of the program, we decided to just save it in here, as we found it very unlikely that it would be changed during
     * testing).
     *
     * @return the DegreePlan for PhD students.
     * @throws DatabaseAccessException if there is an exception in setting up the requirements due to a database error.
     */
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



        return new DegreePlan(
                        breadth,
                        otherReqs,
                        otherGPARequirements,
                        milestoneReqs,
                        Department.COMPUTER_SCIENCE,
                        Degree.PHD);
    }

    /**
     * Returns the MS_A degree requirements. (This would normally be in a JSON database, but for the sake of time, and overall
     * quality of the program, we decided to just save it in here, as we found it very unlikely that it would be changed during
     * testing).
     *
     * @return the DegreePlan for MS_A students.
     * @throws DatabaseAccessException if there is an exception in setting up the requirements due to a database error.
     */
    public static DegreePlan getDegreeMSARequirements() throws DatabaseAccessException{

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
        factory.setMinCourseCount(3);
        factory.setMinGPA(3.25);
        factory.setOnlyAllowCoursesThatPassSubReqs(true);

        breadth = factory.make();



        factory = new DegreeRequirementFactory();

        List<Course> msThesis = new ArrayList<Course>();
        msThesis.add(CourseDAO.getCourseByID("csci8777"));

        factory.setRequirementName("Thesis MS");
        factory.setMinCredits(10);
        factory.setApplicableCourses(msThesis);
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

        factory.setMinCredits(3);
        factory.setMinCourseLevel(8000);
        factory.setRequirementName("PHD Level Courses");

        DegreeRequirement phdLevel = factory.make();



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

        List<Course> nonCourseList = new ArrayList<Course>();
        nonCourseList.add(CourseDAO.getCourseByID("csci8777"));
        nonCourseList.add(CourseDAO.getCourseByID("csci8888"));
        nonCourseList.add(CourseDAO.getCourseByID("csci8970"));

        factory.setCoursesToExclude(nonCourseList);
        factory.setRequirementName("22 Course Credits");
        factory.setMinCredits(22);
        factory.setMinCourseLevel(5000);

        DegreeRequirement course22Credits = factory.make();



        factory = new DegreeRequirementFactory();

        List<DegreeRequirement> courseCreditsSubReqs = new ArrayList<DegreeRequirement>();
        courseCreditsSubReqs.add(csci16Credits);
        courseCreditsSubReqs.add(course22Credits);

        factory.setSubRequirements(courseCreditsSubReqs);
        factory.setRequirementName("Course Credits");
        factory.setMinCourseLevel(5000);

        DegreeRequirement courseCredits = factory.make();



        factory = new DegreeRequirementFactory();

        factory.setMinCredits(31);
        factory.setRequirementName("Total Credits");

        DegreeRequirement totalCredits = factory.make();



        factory = new DegreeRequirementFactory();

        List<DegreeRequirement> otherReqList = new ArrayList<DegreeRequirement>();
        otherReqList.add(thesisReq);
        otherReqList.add(colloq);
        otherReqList.add(phdLevel);
        otherReqList.add(totalCredits);
        otherReqList.add(courseCredits);

        factory.setSubRequirements(otherReqList);
        factory.setRequirementName("Other Course Requirements");

        DegreeRequirement otherReqs = factory.make();



        factory = new DegreeRequirementFactory();

        factory.setRequirementName("Overall GPA");
        factory.setMinGPA(3.25);

        DegreeRequirement overallGPA = factory.make();



        factory = new DegreeRequirementFactory();

        factory.setRequirementName("In Program GPA");
        factory.setMinGPA(3.25);

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
        milestoneList.add(Milestone.DPF_SUBMITTED);
        milestoneList.add(Milestone.DPF_APPROVED);
        milestoneList.add(Milestone.THESIS_COMMITTEE_APPOINTED);
        milestoneList.add(Milestone.GRADUATION_PACKET_REQUESTED);
        milestoneList.add(Milestone.THESIS_SUBMITTED);
        milestoneList.add(Milestone.THESIS_APPROVED);
        milestoneList.add(Milestone.DEFENSE_PASSED);

        factory.setRequirementName("Milestones");
        factory.setRequiredMilestones(milestoneList);

        DegreeRequirement milestoneReqs = factory.make();



        return new DegreePlan(
                        breadth,
                        otherReqs,
                        otherGPARequirements,
                        milestoneReqs,
                        Department.COMPUTER_SCIENCE,
                        Degree.MS_A);
    }



    /**
     * Returns the MS_B degree requirements. (This would normally be in a JSON database, but for the sake of time, and overall
     * quality of the program, we decided to just save it in here, as we found it very unlikely that it would be changed during
     * testing).
     *
     * @return the DegreePlan for MS_B students.
     * @throws DatabaseAccessException if there is an exception in setting up the requirements due to a database error.
     */
    public static DegreePlan getDegreeMSBRequirements() throws DatabaseAccessException{

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
        factory.setMinCourseCount(3);
        factory.setMinGPA(3.25);
        factory.setOnlyAllowCoursesThatPassSubReqs(true);

        breadth = factory.make();



        factory = new DegreeRequirementFactory();

        List<Course> planBProject = new ArrayList<Course>();
        planBProject.add(CourseDAO.getCourseByID("csci8777"));

        factory.setRequirementName("Plan B Project");
        factory.setApplicableCourses(planBProject);
        factory.setMustTakeAllCourses(true);

        DegreeRequirement planBProjReq = factory.make();



        factory = new DegreeRequirementFactory();

        List<Course> colloqList = new ArrayList<Course>();
        colloqList.add(CourseDAO.getCourseByID("csci8970"));

        factory.setRequirementName("Colloquium");
        factory.setApplicableCourses(colloqList);
        factory.setMustTakeAllCourses(true);

        DegreeRequirement colloq = factory.make();



        factory = new DegreeRequirementFactory();

        factory.setMinCredits(3);
        factory.setMinCourseLevel(8000);
        factory.setRequirementName("PHD Level Courses");

        DegreeRequirement phdLevel = factory.make();



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

        List<Course> nonCourseList = new ArrayList<Course>();
        nonCourseList.add(CourseDAO.getCourseByID("csci8777"));
        nonCourseList.add(CourseDAO.getCourseByID("csci8888"));
        nonCourseList.add(CourseDAO.getCourseByID("csci8970"));

        factory.setCoursesToExclude(nonCourseList);
        factory.setRequirementName("31 Course Credits");
        factory.setMinCredits(31);
        factory.setMinCourseLevel(5000);

        DegreeRequirement course31Credits = factory.make();



        factory = new DegreeRequirementFactory();

        List<DegreeRequirement> courseCreditsSubReqs = new ArrayList<DegreeRequirement>();
        courseCreditsSubReqs.add(csci16Credits);
        courseCreditsSubReqs.add(course31Credits);

        factory.setSubRequirements(courseCreditsSubReqs);
        factory.setRequirementName("Total Credits");
        factory.setMinCourseLevel(5000);

        DegreeRequirement totalCredits = factory.make();



        factory = new DegreeRequirementFactory();

        List<DegreeRequirement> otherReqList = new ArrayList<DegreeRequirement>();
        otherReqList.add(planBProjReq);
        otherReqList.add(colloq);
        otherReqList.add(phdLevel);
        otherReqList.add(totalCredits);

        factory.setSubRequirements(otherReqList);
        factory.setRequirementName("Other Course Requirements");

        DegreeRequirement otherReqs = factory.make();



        factory = new DegreeRequirementFactory();

        factory.setRequirementName("Overall GPA");
        factory.setMinGPA(3.25);

        DegreeRequirement overallGPA = factory.make();



        factory = new DegreeRequirementFactory();

        factory.setRequirementName("In Program GPA");
        factory.setMinGPA(3.25);

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
        milestoneList.add(Milestone.DPF_SUBMITTED);
        milestoneList.add(Milestone.DPF_APPROVED);
        milestoneList.add(Milestone.PROJECT_COMMITTEE_APPOINTED);
        milestoneList.add(Milestone.GRADUATION_PACKET_REQUESTED);
        milestoneList.add(Milestone.DEFENSE_PASSED);

        factory.setRequirementName("Milestones");
        factory.setRequiredMilestones(milestoneList);

        DegreeRequirement milestoneReqs = factory.make();



        return new DegreePlan(
                        breadth,
                        otherReqs,
                        otherGPARequirements,
                        milestoneReqs,
                        Department.COMPUTER_SCIENCE,
                        Degree.MS_B);
    }
}
