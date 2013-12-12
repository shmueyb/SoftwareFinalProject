package edu.umn.csci5801.db;

import edu.umn.csci5801.studentrecord.program.Degree;
import edu.umn.csci5801.studentrecord.program.DegreePlan;
import edu.umn.csci5801.studentrecord.program.Department;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ben
 * Date: 12/12/13
 * Time: 2:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class DegreePlanDAO {

    private static List<DegreePlan> getAllDegreePlans() {
        List<DegreePlan> allPlans = new ArrayList<DegreePlan>();

        //TODO

        return allPlans;
    }

    public static DegreePlan getDegreePlanByDepartmentAndDegree(Department dept, Degree degree) {
        List<DegreePlan> allPlans = getAllDegreePlans();
        DegreePlan matchingPlan = new DegreePlan();//TODO: don't actually make a new degree plan, fill in method

        //todo filter out correct degree plan

        return matchingPlan;
    }
}
