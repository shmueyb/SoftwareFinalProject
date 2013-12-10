package edu.umn.csci5801.test;

import edu.umn.csci5801.access.AccessController;
import edu.umn.csci5801.access.AccessDeniedException;
import edu.umn.csci5801.session.Session;
import edu.umn.csci5801.session.UserType;
import org.easymock.EasyMock;
import org.junit.Test;

import static junit.framework.Assert.fail;
import static org.easymock.EasyMock.expect;

/**
 * Created with IntelliJ IDEA.
 * User: Ben
 * Date: 12/8/13
 * Time: 4:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class AccessControllerTest {

    @Test
    public void testCheckUserCanGetListOfStudentIDs_asGPC() {
        Session session = EasyMock.createMock(Session.class);
        expect(session.getCurrentUserType()).andReturn(UserType.GPC).anyTimes();

        EasyMock.replay(session);

        AccessController controller = new AccessController(session);

        try {
            controller.checkUserCanGetListOfStudentIDs();
        } catch (AccessDeniedException ex) {
            fail();
        }


    }



    @Test
    public void testCheckUserCanGetListOfStudentIDs_asStudent() {
        Session session = EasyMock.createMock(Session.class);
        expect(session.getCurrentUserType()).andReturn(UserType.STUDENT).anyTimes();
        expect(session.getUserID()).andReturn("Mocked User").anyTimes();

        EasyMock.replay(session);

        AccessController controller = new AccessController(session);

        try {
            controller.checkUserCanGetListOfStudentIDs();
            fail();
        } catch (AccessDeniedException ex) {
            //do nothing
        }
    }
}
