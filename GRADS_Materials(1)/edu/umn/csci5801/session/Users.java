package edu.umn.csci5801.session;

import edu.umn.csci5801.studentrecord.program.Department;

/**
 * Created with IntelliJ IDEA.
 * User: Ben
 * Date: 12/5/13
 * Time: 1:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Users {

    private User user;
    private UserType role;
    private Department department;

    public Users(User user, UserType role, Department department) {
        //TODO find user type and set it. Also set professor/student user based on type
        this.user = user;
        this.role = role;
        this.department = department;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserType getRole() {
        return role;
    }

    public void setRole(UserType role) {
        this.role = role;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
