package dao_impl;

import entity.ChooseCourse;

import java.util.List;
import java.util.Map;

public interface ChooseCourseDAO {
    int append(ChooseCourse ChooseCourse);

    int delete(ChooseCourse ChooseCourse);

    List<Map<String, String>> infoList(ChooseCourse ChooseCourse);
}
