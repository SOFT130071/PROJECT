package dao_impl;

import entity.User;

import java.util.Map;

public interface UserDAO {
    int append(User user);

    int delete(int id);

    int modify(User user);

    Map<String, String> infoList(User user);
}
