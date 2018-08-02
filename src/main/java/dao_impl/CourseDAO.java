package dao_impl;

import entity.Course;

import java.util.List;
import java.util.Map;

public interface CourseDAO {
    int append(Course course);

    int delete(String cid);

    int modify(Course course);

    List<Map<String, String>> infoList(Course course);

    List<Map<String, String>> infoList(String id);

    List<Map<String, String>> infoList(String title, String content, String name, String c);

    List<Map<String, String>> infoListHot(String title, String content, String name, String c);
}
