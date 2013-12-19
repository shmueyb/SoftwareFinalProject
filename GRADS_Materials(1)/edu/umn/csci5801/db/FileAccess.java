package edu.umn.csci5801.db;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import edu.umn.csci5801.session.User;
import edu.umn.csci5801.studentrecord.StudentRecord;
import edu.umn.csci5801.studentrecord.transcript.Course;


/**
 * Created by us.
 * Class for specifying and accessing the JSON "database"
 * Used to read and write the database files.
 */
public class FileAccess {

    private String studentsFilePath;
    private String coursesFilePath;
    private String usersFilePath;
    private static FileAccess instance;

    /**
     * Private constructor for the singleton FileAccess object
     *
     * @param studentsFilePath the path to the Students JSON database
     * @param coursesFilePath the path to the Courses JSON database
     * @param usersFilePath the path to the Users JSON database
     */
    private FileAccess(String studentsFilePath, String coursesFilePath, String usersFilePath) {
        this.studentsFilePath = studentsFilePath;
        this.coursesFilePath = coursesFilePath;
        this.usersFilePath = usersFilePath;
    }

    /**
     * Returns the full list of students in the students database.
     *
     * @return List of students in the students database
     * @throws DatabaseAccessException if there was an error when attempting to read from the database
     */
    public List<StudentRecord> getStudentsJSON() throws DatabaseAccessException {
        List<StudentRecord> studentRecords;
        try {
            studentRecords = new Gson().fromJson(
                                        new FileReader( new File(studentsFilePath)),
                                        new TypeToken<List<StudentRecord>>(){}.getType());
        } catch (FileNotFoundException e) {
            throw new DatabaseAccessException("An error occured while writing to the students database:\n"
                    + e.getMessage());
        }
        return studentRecords;
    }

    /**
     * Re-writes the students database to represent the list of students passed in
     *
     * @param newStudentRecords the records we wish to write to a JSON file, and replace the students database file with.
     */
    public void writeStudentsJSON(List<StudentRecord> newStudentRecords) throws DatabaseAccessException{
        BufferedWriter out;
        try {
            FileWriter fstream = new FileWriter(studentsFilePath, false);
            String newRecords = new GsonBuilder().setPrettyPrinting().create().toJson(newStudentRecords);
            out = new BufferedWriter(fstream);
            out.write(newRecords);
            out.close();
        } catch (IOException e)
        {
            throw new DatabaseAccessException("An error occured while writing to the students database:\n"
                    + e.getMessage());
        }

    }

    /**
     * Returns the full list of courses in the courses database.
     *
     * @return List of courses in the courses database
     * @throws DatabaseAccessException if there was an error when attempting to read from the database
     */
    public List<Course> getCourseJSON() throws DatabaseAccessException {
        List<Course> courses;
        try {
            courses = new Gson().fromJson(
                                        new FileReader( new File(coursesFilePath)),
                                        new TypeToken<List<Course>>(){}.getType());
        } catch (Exception e) {
            throw new DatabaseAccessException("An error occured while trying to read from the courses database.\n"
                    + e.getMessage());
        }
        return courses;
    }

    /**
     * Returns the full list of users in the user database.
     *
     * @return List of users in the user database
     * @throws DatabaseAccessException if there was an error when attempting to read from the database
     */
    public List<User> getUserJSON() throws DatabaseAccessException {
        List<User> users;
        try {
            users = new Gson().fromJson(
                                        new FileReader(new File(usersFilePath)),
                                        new TypeToken<List<User>>(){}.getType());
        } catch (Exception e) {
            throw new DatabaseAccessException("An error occured while trying to read from the user database.\n"
                    + e.getMessage());
        }
        return users;
    }

    /**
     * Initializes the singleton FileAccess object. Sets the database paths to use during the execution of this instance
     * of GRADS. (Note: not actually a singleton currently to prevent errors while testing GRADS)
     *
     * @param studentsFilePath the path to the Students JSON database
     * @param coursesFilePath the path to the Courses JSON database
     * @param usersFilePath the path to the Users JSON database
     * @throws DatabaseAccessException if there is an attempt to initialize FileAccess twice. This is to prevent
     *         someone from changing the databases being used during execution, as this could be a dangerous operation.
     */
    public static void initialize(String studentsFilePath, String coursesFilePath, String usersFilePath) throws DatabaseAccessException{
        //if (instance != null) {

            // - typically we would keep this here for a production system, but since it could throw off
            //   testing a lot if you initialize GRADS twice on the same JVM, we decided to comment it out
            //   to save you the pain if it broke your tests.

            //throw new DatabaseAccessException("The database path may not be changed without restarting GRADS");
        //}
        instance = new FileAccess(studentsFilePath, coursesFilePath, usersFilePath);
    }

    /**
     * returns the singleton instance of FileAccess. Must be initialized before getting the instance
     *
     * Note: this is not actually a singleton here because of the reason mentioned in initialize(). In
     *       case GRADS is tested by creating several instances of GRADS on the same jvm, we wanted to
     *       ensure there aren't problems with testing it.
     *
     * @return the initialized instance of FileAccess
     * @throws DatabaseAccessException if FileAccess has not been initialized.
     */
    public static FileAccess getInstance() throws DatabaseAccessException{
        if (instance == null) {
            throw new DatabaseAccessException("The database has not been initialized");
        }
        return instance;
    }

}
