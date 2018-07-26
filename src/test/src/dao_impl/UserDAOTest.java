package dao_impl;

import entity.User;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class UserDAOTest {

    @Test
    public void testInfoQueryZeroSet() {
        UserDAO userDAO = DAOFactory.getUserDAOInstance();
        List<Map<String, String>> ret = userDAO.infoList(new User("3"));
        assertEquals(0, ret.size());
    }

    @Test
    public void testInfoQuerySomethingSet() {
        UserDAO userDAO = DAOFactory.getUserDAOInstance();
        List<Map<String, String>> ret = userDAO.infoList(new User());
        assertEquals(2, ret.size());
    }

    @Test
    public void testAppend() {
        UserDAO userDAO = DAOFactory.getUserDAOInstance();
        int ret = userDAO.append(new User("", "hypnus", "池妄", "matsuokahypnus@gmail.com", "123456", "0"));
        assertEquals(1, ret);
    }

    @Test
    public void testDelete() {
        UserDAO userDAO = DAOFactory.getUserDAOInstance();
        int ret = userDAO.delete(6);
        assertEquals(1, ret);
    }

    @Test
    public void testModify() {
        UserDAO userDAO = DAOFactory.getUserDAOInstance();
        int ret = userDAO.modify(new User("5", "hypnus", "池妄", "matsuokahypnus@gmail.com", "123456789", "0"));
        assertEquals(1, ret);
    }
}
