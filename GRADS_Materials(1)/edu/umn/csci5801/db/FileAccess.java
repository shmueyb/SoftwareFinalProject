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
     *
     * @param studentsFilePath
     * @param coursesFilePath
     * @param usersFilePath
     */
    private FileAccess(String studentsFilePath, String coursesFilePath, String usersFilePath) {
        this.studentsFilePath = studentsFilePath;
        this.coursesFilePath = coursesFilePath;
        this.usersFilePath = usersFilePath;
    }

    /**
     *
     * @return
     * @throws FileNotFoundException
     */
    public List<StudentRecord> getStudentsJSON() throws DatabaseAccessException {
        //TODO: fill in method: read file into a single JSON string
        //GRADS_Materials/Data/students.txt
        List<StudentRecord> studentRecords = null;
        try {
            studentRecords = new Gson().fromJson(
                                        new FileReader( new File(studentsFilePath)),
                                        new TypeToken<List<StudentRecord>>(){}.getType());
        } catch (FileNotFoundException e) {
            throw new DatabaseAccessException(e.getMessage());
        }
        return studentRecords;
    }

    /**
     *
     * @param newStudentRecords
     */
    public void writeStudentsJSON(List<StudentRecord> newStudentRecords) {
        //TODO: fill in method: overwrite path/students.txt with the supplied string


        BufferedWriter out = null;
        try
        {
            //Writer writer = new FileWriter(jsonFileName);
            FileWriter fstream = new FileWriter(studentsFilePath, false); //true tells to append data.
            //fstream.write(newStudentFileContents);
            String newRecords = new GsonBuilder().setPrettyPrinting().create().toJson(newStudentRecords);
            //fstream.write(newRecords);
            //fstream.write("Does this even work?");
            out = new BufferedWriter(fstream);
            out.write(newRecords);
            out.close();
        }
        catch (IOException e)
        {
            System.err.println("Error: " + e.getMessage());
        }

    }

    public static void writeUsersJSON(String jsonFileName, List<User> newStudentRecords) {
        BufferedWriter out;
        try
        {
            FileWriter fstream = new FileWriter(jsonFileName, false); //true tells to append data.
            String newRecords = new GsonBuilder().setPrettyPrinting().create().toJson(newStudentRecords);
            out = new BufferedWriter(fstream);
            out.write(newRecords);
            out.close();
        }
        catch (IOException e)
        {
            System.err.println("Error: " + e.getMessage());
        }

    }

    /**
     *
     * @return
     * @throws DatabaseAccessException
     */
    public List<Course> getCourseJSON() throws DatabaseAccessException {
        //TODO: fill in method: read file into a single JSON string
        //"GRADS_Materials/Data/courses.txt
        List<Course> courses = null;
        try {
            courses = new Gson().fromJson(
                                        new FileReader( new File(coursesFilePath)),
                                        new TypeToken<List<Course>>(){}.getType());
        } catch (FileNotFoundException e) {
            throw new DatabaseAccessException(e.getMessage());
        }
        return courses;
    }

    /**
     *
     * @return
     * @throws FileNotFoundException
     */
    public List<User> getUserJSON() throws DatabaseAccessException {
        //TODO: fill in method: read file into a single JSON string
        //"GRADS_Materials/Data/users.txt"
        List<User> sessions = null;
        try {
            sessions = new Gson().fromJson(
                                        new FileReader(new File(usersFilePath)),
                                        new TypeToken<List<User>>(){}.getType());
        } catch (FileNotFoundException e) {
            throw new DatabaseAccessException("Invalid Path for User Database.\n" + e.getMessage());
        }
        return sessions;
    }

    /**
     *
     * @param studentsFilePath
     * @param coursesFilePath
     * @param usersFilePath
     * @throws DatabaseAccessException
     */
    public static void initialize(String studentsFilePath, String coursesFilePath, String usersFilePath) throws DatabaseAccessException{
        if (instance != null) {
            throw new DatabaseAccessException("The database path may not be changed without restarting GRADS");
        }
        instance = new FileAccess(studentsFilePath, coursesFilePath, usersFilePath);
    }

    /**
     *
     * @return
     * @throws DatabaseAccessException
     */
    public static FileAccess getInstance() throws DatabaseAccessException{
        if (instance == null) {
            throw new DatabaseAccessException("The database has not been initialized");
        }
        return instance;
    }

}
