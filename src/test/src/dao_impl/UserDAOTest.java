package dao_impl;

import entity.User;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class UserDAOTest {

    @Test
    public void testInfo() {
        UserDAO userDAO = DAOFactory.getUserDAOInstance();
        List<Map<String, String>> ret = userDAO.infoList(new User());
        userDAO
    }
}
