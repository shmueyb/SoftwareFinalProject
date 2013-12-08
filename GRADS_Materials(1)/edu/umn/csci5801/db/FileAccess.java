package edu.umn.csci5801.db;

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

    public String getStudentsJSON() {
        //TODO: fill in method: read file into a single JSON string
        return "";
    }

    public void writeStudentsJSON(String json) {
        //TODO: fill in method: overwrite path/students.txt with the supplied string
    }

    public String getCourseJSON() {
        //TODO: fill in method
        return "";
    }

    public String getUserJSON() {
        //TODO: fill in method
        return "";
    }

    public String getProgressJSON() {
        //TODO: fill in method
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
