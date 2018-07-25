package dao_impl;

import entity.User;

import java.util.List;
import java.util.Map;

public interface UserDAO {
    int append(User user);

    int delete(int id);

    int modify(User user);

    List<Map<String, String>> infoList(User user);
}
