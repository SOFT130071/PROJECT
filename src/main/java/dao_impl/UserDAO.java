package dao_impl;

import entity.User;

import java.util.List;
import java.util.Map;

public interface UserDAO {
    int append(User userBean);

    int delete(String id);

    int modify(User userBean);

    List<Map<String, String>> infoList(User userBean);
}