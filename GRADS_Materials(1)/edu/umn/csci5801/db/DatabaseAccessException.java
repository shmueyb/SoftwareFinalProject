package edu.umn.csci5801.db;

/**
 * @author Ben Hagaman
 * @author Sam Blustin
 * @author Catherine Reeves
 * @author Xum Giang
 * @author Trang Nguyen
 *
 * DatabaseAccessException.java
 *
 * This exception is to be thrown when there is a general problem with accessing any database.
 */
public class DatabaseAccessException extends Exception{

    /**
     * Creates an exception with the supplied message.
     *
     * @param message the error message to print out.
     */
    public DatabaseAccessException(String message) {
        super(message);
    }
}
