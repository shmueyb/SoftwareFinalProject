package edu.umn.csci5801.test;

import edu.umn.csci5801.access.AccessController;
import edu.umn.csci5801.session.Session;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Ben
 * Date: 12/8/13
 * Time: 4:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class AccessControllerTest {

    @Test
    public void testCheckUserCanGetListOfStudentIDs_asGPC() {  /*
        Session session = EasyMock.createMock(Session.class);
        session.expect(getCurrentUserType()).andReturn(UserType.GPC).anyTimes();

        AccessController controller = new AccessController(session);

        try {
            controller.checkUserCanGetListOfStudentIDs();
        } catch (AccessDeniedException ex) {
            fail();
        }

        */
    }



    @Test
    public void testCheckUserCanGetListOfStudentIDs_asStudent() {  /*
        Session session = EasyMock.createMock(Session.class);
        session.expect(getCurrentUserType()).andReturn(UserType.STUDENT).anyTimes();

        AccessController controller = new AccessController(session);

        try {
            controller.checkUserCanGetListOfStudentIDs();
            fail()
        } catch (AccessDeniedException ex) {
            //do nothing
        }

        */
    }
}
