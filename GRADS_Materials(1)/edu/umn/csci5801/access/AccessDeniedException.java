package edu.umn.csci5801.access;

/**
 * Created with IntelliJ IDEA.
 * User: Ben
 * Date: 12/7/13
 * Time: 9:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccessDeniedException extends Exception {

    public AccessDeniedException(String message) {
        super(message);
    }
}
