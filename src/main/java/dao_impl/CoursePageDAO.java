package dao_impl;

import entity.CoursePage;

import java.util.List;

public interface CoursePageDAO {
    int append(CoursePage CoursePage);

    int delete(String pid);

    int modify(CoursePage CoursePage);

    List<CoursePage> infoList(CoursePage CoursePage);
}
