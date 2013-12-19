package edu.umn.csci5801.access;

/**
 * @author Ben Hagaman
 * @author Sam Blustin
 * @author Catherine Reeves
 * @author Xum Giang
 * @author Trang Nguyen
 *
 * AccessDeniedException.java
 *
 * This Exception should be thrown when the user tries to do something, for which they don't have the proper privileges.
 */
public class AccessDeniedException extends Exception {

    /**
     * Constructor with message.
     *
     * @param message the message to display with the exception.
     */
    public AccessDeniedException(String message) {
        super(message);
    }
}
