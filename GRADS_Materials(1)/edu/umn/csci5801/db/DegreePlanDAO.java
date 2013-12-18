package edu.umn.csci5801.db;

import java.util.ArrayList;
import java.util.List;

import edu.umn.csci5801.studentrecord.program.Degree;
import edu.umn.csci5801.studentrecord.program.DegreePlan;
import edu.umn.csci5801.studentrecord.program.DegreeRequirement;
import edu.umn.csci5801.studentrecord.program.Department;
import edu.umn.csci5801.studentrecord.transcript.Course;
import edu.umn.csci5801.studentrecord.transcript.Grade;

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

        theoryAlgs = new DegreeRequirement(
                "Theory and Algorithms",
                null,
                theoryAlgsCourses,
                null,
                null,
                false,
                false,
                false,
                0,
                1,
                5000,
                Grade.C
        );

        archSS = new DegreeRequirement(
                "Architecture, Systems, and Software",
                null,
                aSSCourses,
                null,
                null,
                false,
                false,
                false,
                0,
                1,
                5000,
                Grade.C
        );

        apps = new DegreeRequirement(
                "Applications",
                null,
                appCourses,
                null,
                null,
                false,
                false,
                false,
                0,
                1,
                5000,
                Grade.C
        );

        List<DegreeRequirement> breadthSubReqs = new ArrayList<DegreeRequirement>();
        breadthSubReqs.add(theoryAlgs);
        breadthSubReqs.add(archSS);
        breadthSubReqs.add(apps);

        breadth = new DegreeRequirement(
                "Breadth Requirement",
                breadthSubReqs,
                null,
                null,
                3.45,
                false,
                false,
                false,
                0,
                5,
                5000,
                Grade.C
        );


        List<Course> phdThesis = new ArrayList<Course>();
        phdThesis.add(CourseDAO.getCourseByID("csci8888"));

        DegreeRequirement thesisReq = new DegreeRequirement(
                "Thesis PHD",
                null,
                phdThesis,
                null,
                null,
                true,
                false,
                false,
                24,
                0,
                5000,
                Grade.C);


        List<Course> colloqList = new ArrayList<Course>();
        colloqList.add(CourseDAO.getCourseByID("csci"));

        DegreeRequirement colloq = new DegreeRequirement(
                "Colloquium",
                null,
                colloqList,
                null,
                null,
                true,
                false,
                false,
                0,
                1,
                5000,
                Grade.F);


        List<DegreeRequirement> otherReqList = new ArrayList<DegreeRequirement>();
        otherReqList.add(thesisReq);
        otherReqList.add(colloq);

        DegreeRequirement otherReqs = new DegreeRequirement(
                "Other Requirements",
                otherReqList,
                null,
                null,
                3.45,
                false,
                false,
                false,
                31,
                0,
                5000,
                Grade.C);

        DegreeRequirement otherGPARequirements = new DegreeRequirement(
                "OtherGPARequirements",
                null,
                null,
                null,
                3.45,
                false,
                false,
                false,
                0,
                0,
                5000,
                Grade.C);

        DegreeRequirement milestoneReqs = new DegreeRequirement(
                "milestoneReqs",
                null,
                null,
                null,
                null,
                false,
                false,
                false,
                0,
                0,
                5000,
                Grade.C);

        DegreePlan phDDegreePlan = new DegreePlan(
                breadth,
                otherReqs,
                otherGPARequirements,
                milestoneReqs);

        return phDDegreePlan;
    }
}
