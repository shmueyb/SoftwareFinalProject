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
    static private Student Luan = new Student("Luan", "Nguyen", "nguy0621");
    static private Student Greg = new Student("Gregory", "Gay", "gayxx067");
    static private Student Ian = new Student("Ian", "De Silva", "desil1337");
    //TODO: add these below to users.txt
    static private Student Catherine = new Student("Catherine", "Reed", "1111");

    static private Department CompSci = Department.COMPUTER_SCIENCE;

    /**
     *
     * @return: a list of student records for database
     * @throws Exception
     */
    public static List<StudentRecord> createRecords() throws Exception{
        List<StudentRecord> list = new LinkedList<StudentRecord>();
        list.add(LuanRecord());
        list.add(GregRecord());
        list.add(IanRecord());
        list.add(CatherineRecord());
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
     * generating Luan's record
     * @return Luan's record
     */
    public static StudentRecord LuanRecord() {
        return  new StudentRecord.StudentRecordBuilder()
                .student(Luan)
                .department(CompSci)
                .degreeSought(Degree.PHD)
                .termBegan(new Term(Semester.SPRING, 2008))
                .advisors(LuanAdvisors())
                .committee(LuanCommittee())
                .coursesTaken(LuanCoursesTaken())
                .milesstoneSet(LuanMilestones())
                .notes(notes())
                .build();
    }

    public static List<Professor> LuanAdvisors() {
        List<Professor> advisors = new LinkedList<Professor>();
        advisors.add(new Professor("william", "Schuler", Department.COMPUTER_SCIENCE));
        return advisors;
    }

    public static List<Professor> LuanCommittee() {
        List<Professor> committee = new LinkedList<Professor>();
        committee.add(new Professor("Maria", "Gini", Department.COMPUTER_SCIENCE));
        return committee;
    }

    public static List<CourseTaken> LuanCoursesTaken() {
        List<CourseTaken> courseTakens = new LinkedList<CourseTaken>();
        courseTakens.add(new CourseTaken
                (new Course("Advanced Algorithms and Data Structures", "csci5421", "3", CourseArea.THEORY_ALGORITHMS),
                        new Term(Semester.SPRING, 2008), Grade.A));
        courseTakens.add(new CourseTaken
                (new Course("Machine Learning", "csci5525", "3", CourseArea.APPLICATIONS),
                        new Term(Semester.SPRING, 2008), Grade.A));
        courseTakens.add(new CourseTaken
                (new Course("Operating System", "csci5103", "3", CourseArea.ARCHITECTURE_SYSTEMS_SOFTWARE),
                        new Term(Semester.SUMMER, 2008), Grade.B));
        return courseTakens;
    }

    public static List<MilestoneSet> LuanMilestones() {
        List<MilestoneSet> milestoneSets = new LinkedList<MilestoneSet>();
        milestoneSets.add(new MilestoneSet(Milestone.DEFENSE_PASSED, new Term(Semester.FALL, 2014)));

        return  milestoneSets;
    }
    /**
     * generating Greg's record
     * @return Greg's record
     */
    public static StudentRecord GregRecord() {
        return  new StudentRecord.StudentRecordBuilder()
                .student(Greg)
                .department(CompSci)
                .degreeSought(Degree.MS_A)
                .termBegan(new Term(Semester.SPRING, 2009))
                .advisors(GregAdvisors())
                .committee(GregCommittee())
                .coursesTaken(GregCoursesTaken())
                .milesstoneSet(GregMilestones())
                .notes(notes())
                .build();
    }

    public static List<Professor> GregAdvisors() {
        List<Professor> advisors = new LinkedList<Professor>();
        advisors.add(new Professor("Mats", "Heidahl", Department.COMPUTER_SCIENCE));
        return advisors;
    }

    public static List<Professor> GregCommittee() {
        List<Professor> committee = new LinkedList<Professor>();
        committee.add(new Professor("John", "Doe", Department.COMPUTER_SCIENCE));
        return committee;
    }

    public static List<CourseTaken> GregCoursesTaken() {
        List<CourseTaken> courseTakens = new LinkedList<CourseTaken>();
        courseTakens.add(new CourseTaken
                (new Course("Introduction to Distributed Systems", "csci5105", "3", CourseArea.ARCHITECTURE_SYSTEMS_SOFTWARE),
                        new Term(Semester.SPRING, 2009), Grade.A));
        courseTakens.add(new CourseTaken
                (new Course("Programming Languages", "csci5106", "3", CourseArea.ARCHITECTURE_SYSTEMS_SOFTWARE),
                        new Term(Semester.FALL, 2009), Grade.B));
        courseTakens.add(new CourseTaken
                (new Course("Introduction to Computer Security", "csci5271", "3", CourseArea.APPLICATIONS),
                        new Term(Semester.SUMMER, 2010), Grade.C));
        return courseTakens;
    }

    public static List<MilestoneSet> GregMilestones() {
        List<MilestoneSet> milestoneSets = new LinkedList<MilestoneSet>();
        milestoneSets.add(new MilestoneSet(Milestone.THESIS_APPROVED, new Term(Semester.FALL, 2014)));
        return  milestoneSets;
    }
    /**
     * generating Ian's record
     * @return: Ian's record
     */
    public static StudentRecord IanRecord() {
        return  new StudentRecord.StudentRecordBuilder()
                .student(Ian)
                .department(CompSci)
                .degreeSought(Degree.MS_B)
                .termBegan(new Term(Semester.SPRING, 2010))
                .advisors(IanAdvisors())
                .committee(IanCommittee())
                .coursesTaken(IanCoursesTaken())
                .milesstoneSet(IanMilestones())
                .notes(notes())
                .build();
    }

    public static List<Professor> IanAdvisors() {
        List<Professor> advisors = new LinkedList<Professor>();
        advisors.add(new Professor("Luc", "Nelson", Department.COMPUTER_SCIENCE));
        return advisors;
    }

    public static List<Professor> IanCommittee() {
        List<Professor> committee = new LinkedList<Professor>();
        committee.add(new Professor("Steve", "Jensen", Department.COMPUTER_SCIENCE));
        return committee;
    }

    public static List<CourseTaken> IanCoursesTaken() {
        List<CourseTaken> courseTakens = new LinkedList<CourseTaken>();
        courseTakens.add(new CourseTaken
                (new Course("Data Communications and Computer Networks", "csci5211", "3", CourseArea.ARCHITECTURE_SYSTEMS_SOFTWARE),
                        new Term(Semester.SPRING, 2010), Grade.C));
        courseTakens.add(new CourseTaken
                (new Course("Analysis of Numberial Algorithms", "csci5301", "3", CourseArea.THEORY_ALGORITHMS),
                        new Term(Semester.FALL, 2010), Grade.B));
        courseTakens.add(new CourseTaken
                (new Course("Introduction to Computer Security", "scsi5302", "3", CourseArea.APPLICATIONS),
                        new Term(Semester.SUMMER, 2010), Grade.A));
        return courseTakens;
    }

    public static List<MilestoneSet> IanMilestones() {
        List<MilestoneSet> milestoneSets = new LinkedList<MilestoneSet>();
        milestoneSets.add(new MilestoneSet(Milestone.DPF_APPROVED, new Term(Semester.FALL, 2014)));
        return  milestoneSets;
    }

    /**
     * Catherine's record
     * @return
     */
    public static StudentRecord CatherineRecord() {
        return  new StudentRecord.StudentRecordBuilder()
                .student(Catherine)
                .department(CompSci)
                .degreeSought(Degree.MS_C)
                .termBegan(new Term(Semester.SPRING, 2011))
                .advisors(CatherineAdvisors())
                .committee(CatherineCommittee())
                .coursesTaken(CatherineCoursesTaken())
                .milesstoneSet(CatherineMilestones())
                .notes(notes())
                .build();
    }

    public static List<Professor> CatherineAdvisors() {
        List<Professor> advisors = new LinkedList<Professor>();
        advisors.add(new Professor("John", "Harper", Department.COMPUTER_SCIENCE));
        return advisors;
    }

    public static List<Professor> CatherineCommittee() {
        List<Professor> committee = new LinkedList<Professor>();
        committee.add(new Professor("Matt", "Hein", Department.COMPUTER_SCIENCE));
        return committee;
    }

    public static List<CourseTaken> CatherineCoursesTaken() {
        List<CourseTaken> courseTakens = new LinkedList<CourseTaken>();
        courseTakens.add(new CourseTaken
                (new Course("computational Complexity", "csci5403", "3", CourseArea.THEORY_ALGORITHMS),
                        new Term(Semester.SPRING, 2011), Grade.A));
        courseTakens.add(new CourseTaken
                (new Course("Advanced Algorithms and Data Structures", "csci5421", "3", CourseArea.THEORY_ALGORITHMS),
                        new Term(Semester.FALL, 2011), Grade.B));
        courseTakens.add(new CourseTaken
                (new Course("Modern Cryptography", "csci5471", "3", CourseArea.APPLICATIONS),
                        new Term(Semester.SUMMER, 2011), Grade.A));
        return courseTakens;
    }

    public static List<MilestoneSet> CatherineMilestones() {
        List<MilestoneSet> milestoneSets = new LinkedList<MilestoneSet>();
        milestoneSets.add(new MilestoneSet(Milestone.PRELIM_COMMITTEE_APPOINTED, new Term(Semester.FALL, 2014)));
        return  milestoneSets;
    }

}

