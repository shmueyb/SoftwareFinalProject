package edu.umn.csci5801.studentrecord.StudentRecordFactory;

import java.util.LinkedList;
import java.util.List;

import edu.umn.csci5801.session.Professor;
import edu.umn.csci5801.session.Student;
import edu.umn.csci5801.studentrecord.StudentRecord;
import edu.umn.csci5801.studentrecord.program.Degree;
import edu.umn.csci5801.studentrecord.program.Department;
import edu.umn.csci5801.studentrecord.requirements.Milestone;
import edu.umn.csci5801.studentrecord.requirements.MilestoneSet;
import edu.umn.csci5801.studentrecord.transcript.Course;
import edu.umn.csci5801.studentrecord.transcript.CourseArea;
import edu.umn.csci5801.studentrecord.transcript.CourseTaken;
import edu.umn.csci5801.studentrecord.transcript.Grade;
import edu.umn.csci5801.studentrecord.transcript.Semester;
import edu.umn.csci5801.studentrecord.transcript.Term;

/**
 */
public class StudentRecordFactory {
    static private Student Trang = new Student("Trang", "Nguye,", "1111");
    static private Student Ben = new Student("Ben", "Hagaman", "2222");
    static private Student Xum = new Student("Xum", "Giang", "3333");
    static private Student Catherine = new Student("Katherine", "Reed", "4444");
    static private Student Sam     = new Student ("Sam", "Blustin", "5555");
    static private Department math = Department.MATH;
    static private Department CompSci = Department.COMPUTER_SCIENCE;
    static private Term fall = new Term(Semester.FALL, 2014);
    static private Term spring = new Term(Semester.SPRING, 2014);

    /**
     *
     * @return: a list of student records for database
     * @throws Exception
     */
    public static List<StudentRecord> createRecords() throws Exception{
        List<StudentRecord> list = new LinkedList<StudentRecord>();
        list.add(TrangRecord());
        list.add(BenRecord());
        list.add(XumRecord());
        list.add(CatherineRecord());
        list.add(SamRecord());
        return list;
    }

    /**
     *
     * @return: dummy list of notes
     */
    public static List<String> notes() {
        List<String> notes = new LinkedList<String>();
        for (int i = 0; i<10;i++) {
            notes.add("" + i);
        }
        return notes;
    }

    /**
     * generating example milestones
     * @return: list of milestones
     */
    public static List<MilestoneSet> milestones() {
        List<MilestoneSet> milestoneSets = new LinkedList<MilestoneSet>();
        milestoneSets.add(new MilestoneSet(Milestone.DEFENSE_PASSED, fall));
        milestoneSets.add(new MilestoneSet(Milestone.DPF_APPROVED, fall));
        milestoneSets.add(new MilestoneSet(Milestone.DPF_SUBMITTED, fall));
        milestoneSets.add(new MilestoneSet(Milestone.GRADUATION_PACKET_REQUESTED, fall));
        milestoneSets.add(new MilestoneSet(Milestone.ORAL_PE_PASSED, fall));
        milestoneSets.add(new MilestoneSet(Milestone.PRELIM_COMMITTEE_APPOINTED,spring));
        milestoneSets.add(new MilestoneSet(Milestone.PROPOSAL_PASSED,spring));
        milestoneSets.add(new MilestoneSet(Milestone.THESIS_APPROVED,spring));
        milestoneSets.add(new MilestoneSet(Milestone.TRACKING_FORM_APPROVED,spring));
        return milestoneSets;
    }

    /**
     * generating a list of : professors, committee, advisors
     * @return: list of professors
     */
    public static List<Professor> professors() {
        List<Professor> professors = new LinkedList<Professor>();
        Professor professor1 = new Professor("Youself", "Saad", math);
        Professor professor2 = new Professor("Nick","Harper", CompSci);
        Professor professor = new Professor("Anand", "Tripathy", CompSci);
        Professor professor3 = new Professor("Albert", "Eistein", math);
        Professor professor4 = new Professor("Luc", "Nelson", CompSci);
        professors.add(professor);
        professors.add(professor1);
        professors.add(professor2);
        professors.add(professor3);
        professors.add(professor4);
        return professors;
    }

    /**
     * generating a list of courses
     * @return: list of courses taken
     */
    public static List<CourseTaken> courseTakens() {
        CourseTaken courseTaken = new CourseTaken(new Course("Developing the Interactive Web", "csci5117", "3", CourseArea.APPLICATIONS), fall, Grade.A);
        CourseTaken courseTaken1 = new CourseTaken(new Course("Aritificial Intelligence", "csci5511", "3", CourseArea.ARCHITECTURE_SYSTEMS_SOFTWARE), fall, Grade.A);
        CourseTaken courseTaken2 = new CourseTaken(new Course("SoftWare Engineer", "csci5801", "3", CourseArea.ARCHITECTURE_SYSTEMS_SOFTWARE), fall, Grade.A);
        CourseTaken courseTaken3 = new CourseTaken(new Course("Programming Language", "csci5106", "3", CourseArea.THEORY_ALGORITHMS), fall, Grade.A);
        List<CourseTaken> courseTakenList = new LinkedList<CourseTaken>();
        courseTakenList.add(courseTaken);
        courseTakenList.add(courseTaken1);
        courseTakenList.add(courseTaken2);
        courseTakenList.add(courseTaken3);
        return  courseTakenList;
    }

    /**
     * generating Trang's record
     * @return Trang's record
     */
    public static StudentRecord TrangRecord() {
        return  new StudentRecord.StudentRecordBuilder()
                .student(Trang)
                .department(CompSci)
                .degreeSought(Degree.MS_A)
                .termBegan(fall)
                .advisors(professors())
                .committee(professors())
                .coursesTaken(courseTakens())
                .milesstoneSet(milestones())
                .notes(notes())
                .build();
    }

    /**
     * generating Ben's record
     * @return Ben's record
     */
    public static StudentRecord BenRecord() {
        return new StudentRecord.StudentRecordBuilder()
                .student(Ben)
                .department(math)
                .degreeSought(Degree.MS_A)
                .termBegan(spring)
                .advisors(professors())
                .committee(professors())
                .coursesTaken(courseTakens())
                .milesstoneSet(milestones())
                .notes(notes())
                .build();
    }

    /**
     * generating Xum's record
     * @return: Xum's record
     */
    public static StudentRecord XumRecord() {
        return new StudentRecord.StudentRecordBuilder()
                .student(Xum)
                .department(math)
                .degreeSought(Degree.MS_B)
                .termBegan(spring)
                .advisors(professors())
                .committee(professors())
                .coursesTaken(courseTakens())
                .milesstoneSet(milestones())
                .notes(notes())
                .build();
    }

    /**
     * generating Sam's record
     * @return Sam's record
     */
    public static StudentRecord SamRecord() {
        return new StudentRecord.StudentRecordBuilder()
                .student(Sam)
                .department(CompSci)
                .degreeSought(Degree.PHD)
                .termBegan(spring)
                .advisors(professors())
                .committee(professors())
                .coursesTaken(courseTakens())
                .milesstoneSet(milestones())
                .notes(notes())
                .build();
    }

    /**
     * generating Catherine's record
     * @return Catherine's record
     */
    public static StudentRecord CatherineRecord() {
        return new StudentRecord.StudentRecordBuilder()
                .student(Catherine)
                .department(CompSci)
                .degreeSought(Degree.PHD)
                .termBegan(fall)
                .advisors(professors())
                .committee(professors())
                .coursesTaken(courseTakens())
                .milesstoneSet(milestones())
                .notes(notes())
                .build();

    }
}

