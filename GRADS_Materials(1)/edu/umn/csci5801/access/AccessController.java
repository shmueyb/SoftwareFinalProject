package edu.umn.csci5801.access;

/**
 * Created with IntelliJ IDEA.
 * User: Ben
 * Date: 12/5/13
 * Time: 12:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccessController {

    private AccessController accessController;

    private AccessController() {

    }

    public AccessController getAccessController() {
        if (accessController == null) {
            accessController = new AccessController();
        }

        return accessController;
    }

}
