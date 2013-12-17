package edu.umn.csci5801.session;

import edu.umn.csci5801.studentrecord.program.Department;

/**
 * Created by UBLUSSA on 12/10/13.
 */
public class User {
    private UserInfo user;
    private UserType role;
    private Department department;

    public User(String id, String firstName, String lastName, UserType role, Department department) {
        this.user = new UserInfo(id, firstName, lastName);
        this.role = role;
        this.department = department;
    }

    public String getID() {
        return user.getID();
    }

    public String getFirstName() {
        return user.getFirstName();
    }

    public String getLastName() {
        return user.getLastName();
    }

    public UserType getRole() {
        return role;
    }

    public Department getDepartment() {
        return department;
    }



    /**
     * This subclass exists to support the structure of the data in the database.
     * It is abstracted away in this class because we must include some data in an object
     * per the JSON layout, but all data really belongs to the User.
     *
     */
    private class UserInfo {
        String id;
        String firstName;
        String lastName;

        UserInfo(String id, String firstName, String lastName) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        String getID() {
            return id;
        }

        String getFirstName() {
            return firstName;
        }

        String getLastName() {
            return lastName;
        }
    }
}
