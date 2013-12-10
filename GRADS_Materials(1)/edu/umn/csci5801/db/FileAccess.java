package edu.umn.csci5801.db;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.umn.csci5801.session.Users;
import edu.umn.csci5801.studentrecord.StudentRecord;
import edu.umn.csci5801.studentrecord.transcript.Course;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

/**
 * Class for specifying and accessing the JSON "database"
 * Used to read and write the database files.
 */
public class FileAccess {

    private String path;
    private static FileAccess instance;

    private FileAccess(String path) {
        this.path = path;
    }

    public static List<StudentRecord> getStudentsJSON() throws FileNotFoundException {
        //TODO: fill in method: read file into a single JSON string
        List<StudentRecord> studentRecords = new Gson().fromJson( new FileReader( new File("GRADS_Materials/Data/students.txt")), new TypeToken<List<StudentRecord>>(){}.getType());
        return studentRecords;
    }

    public static void writeStudentsJSON(String json) {
        //TODO: fill in method: overwrite path/students.txt with the supplied string
    }

    public static List<Course> getCourseJSON() throws FileNotFoundException {
        //TODO: fill in method: read file into a single JSON string
        List<Course> courses = new Gson().fromJson( new FileReader( new File("GRADS_Materials/Data/courses.txt")), new TypeToken<List<Course>>(){}.getType());
        return courses;
    }

    public static List<Users> getUserJSON() throws FileNotFoundException {
        //TODO: fill in method: read file into a single JSON string
        List<Users> sessions = new Gson().fromJson( new FileReader( new File("GRADS_Materials/Data/users.txt")), new TypeToken<List<Users>>(){}.getType());
        return sessions;
    }

    public static String getProgressJSON() {
        //TODO: fill in method: read file into a single JSON string
        return "";
    }

    public static void initialize(String rootPath) throws DatabaseAccessException{
        if (instance != null) {
            throw new DatabaseAccessException("The database path may not be changed without restarting GRADS");
        }
        instance = new FileAccess(rootPath);
    }

    public static FileAccess getInstance() throws DatabaseAccessException{
        if (instance == null) {
            throw new DatabaseAccessException("The database has not been initialized");
        }
        return instance;
    }

}
