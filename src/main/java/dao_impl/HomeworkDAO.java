package dao_impl;

import entity.Homework;

import java.util.List;
import java.util.Map;

public interface HomeworkDAO {
    int append(Homework homework);

    int delete(String id);

    int modify(Homework homework);

    List<Map<String, String>> infoList(Homework homework);
}
