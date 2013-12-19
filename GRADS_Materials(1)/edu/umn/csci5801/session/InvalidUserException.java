package edu.umn.csci5801.session;

/**
 * @author Ben Hagaman
 * @author Sam Blustin
 * @author Catherine Reeves
 * @author Xum Giang
 * @author Trang Nguyen
 *
 * InvalidUserException.java
 *
 * This exception should be thrown if the user is set to an invalid user.
 */
public class InvalidUserException extends Exception {

    /**
     * InvalidUserException
     *
     * @param message the message to print out.
     */
    public InvalidUserException(String message) {
        super(message);
    }
}
