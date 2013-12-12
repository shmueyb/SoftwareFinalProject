package edu.umn.csci5801.studentrecord.program;

import edu.umn.csci5801.studentrecord.requirements.MilestoneSet;
import edu.umn.csci5801.studentrecord.requirements.Milestone;
import edu.umn.csci5801.studentrecord.transcript.CourseTaken;
import edu.umn.csci5801.studentrecord.transcript.Course;
import edu.umn.csci5801.studentrecord.transcript.CourseArea;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lil4000
 * Date: 12/11/13
 * Time: 6:01 PM
 * To change this template use File | Settings | File Templates.
 */


public class GradRequirements {
    List<String> theory_algorithms_course_list;
    List<String> architecture_systems_software_course_list;
    List<String> applications_course_list;

    double min_breadth_gpa_PHD = 3.45;
    int total_breadth_courses_PHD = 5;

    boolean passed = false;
    public boolean check_phd_requirements_passed(List<CourseTaken> coursesTaken, List<MilestoneSet> milestoneSetList ){
        boolean breadth_req_passed = false;
        boolean thesis_phd_req_passed = false;
        boolean colloquium_req_passed = false;
        boolean milestone_req_passed = false;
        boolean research_class_req_passed = false;
        List<CourseTaken> breadth_courses_list = null;
        int numThesisCredits = 0;

        List<Milestone> milestoneListPHD = Arrays.asList(Milestone.PRELIM_COMMITTEE_APPOINTED,
                Milestone.WRITTEN_PE_SUBMITTED, Milestone.WRITTEN_PE_APPROVED, Milestone.ORAL_PE_PASSED,
                Milestone.DPF_SUBMITTED, Milestone.DPF_APPROVED, Milestone.THESIS_COMMITTEE_APPOINTED,
                Milestone.PROPOSAL_PASSED, Milestone.GRADUATION_PACKET_REQUESTED, Milestone.THESIS_SUBMITTED,
                Milestone.THESIS_APPROVED, Milestone.DEFENSE_PASSED) ;

        for (CourseTaken courseTaken : coursesTaken){
            Course studentCourse = courseTaken.getCourse();
            double grade = courseTaken.getGrade().numericValue();
            if(studentCourse.getCourseArea().equals(null)){
                CourseArea courseArea = findCourseArea_PHD(studentCourse);
                studentCourse.setCourseArea(courseArea);
            }


            if(studentCourse.getId().equals("csci8888") & (grade>1.667)) {
                numThesisCredits++;
            }

            if(!(studentCourse.getCourseArea().equals(null))){
                int num_breadth_courses_taken = 0;
                int num_theory = 0;
                int num_arch_sys_soft = 0;
                int num_app = 0;
                double total_grade_points = 0;
                double total_credits = 0;


                if(studentCourse.getCourseArea().equals(CourseArea.APPLICATIONS) & grade>1.667){
                    num_breadth_courses_taken++;
                    num_app++;
                    total_grade_points++;
                    total_credits++;
                }
                else if(studentCourse.getCourseArea().equals(CourseArea.THEORY_ALGORITHMS) & grade>1.667){
                    num_breadth_courses_taken++;
                    num_theory++;
                    total_grade_points++;
                    total_credits++;
                }
                else if(studentCourse.getCourseArea().equals(CourseArea.ARCHITECTURE_SYSTEMS_SOFTWARE) & grade>1.667){
                    num_breadth_courses_taken++;
                    num_arch_sys_soft++;
                    total_grade_points++;
                    total_credits++;
                }
                double breadth_req_gpa = total_grade_points/total_credits;


                if((num_breadth_courses_taken >= 5) & (num_app >= 1) & (num_theory>= 1) & (num_arch_sys_soft>=1) &(breadth_req_gpa>=min_breadth_gpa_PHD) ){
                    breadth_req_passed = true;
                }
                if(numThesisCredits >= 24){
                    thesis_phd_req_passed = true;
                }
                if(studentCourse.getId().equals("csci8970") & (courseTaken.getGrade().equals("S"))){
                    colloquium_req_passed = true;
                }
                if(milestoneSetList.containsAll(milestoneListPHD)){
                    milestone_req_passed = true;
                }

            }

        }

        return passed;

    }


    private CourseArea findCourseArea_PHD(Course takenCourse){
        theory_algorithms_course_list = Arrays.asList("csci5302", "csci5304", "csci5403", "csci5421", "csci5525");
        architecture_systems_software_course_list = Arrays.asList("csci5103", "csci5104", "csci5105", "csci5106", "csci5161", "csci5204", "csci5211", "csci5221", "csci5231", "csci5451", "csci5461", "csci5708", "csci5801", "csci5802");
        applications_course_list = Arrays.asList("csci5115", "csci5125", "csci5271", "csci5471", "csci5481", "csci5511", "csci5512", "csci5521", "csci5523", "csci5551", "csci5561", "csci5607", "csci5608", "csci5609", "csci5611", "csci5619", "csci5707");

        String courseID = takenCourse.getId();
        if(theory_algorithms_course_list.contains(courseID)){
            return CourseArea.THEORY_ALGORITHMS;
        }
        else if(architecture_systems_software_course_list.contains(courseID)){
            return CourseArea.ARCHITECTURE_SYSTEMS_SOFTWARE;
        }
        else if(applications_course_list.contains(courseID)){
            return CourseArea.APPLICATIONS;
        }
        return null;

    }

}
