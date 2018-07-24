package dao_impl;

public class DAOFactory {
    public static UserDAO getUserDAOInstance() {
        return new UserDAOImpl();
    }
}
