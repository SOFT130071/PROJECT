package service;

import com.google.gson.JsonObject;

public interface CourseService {

    int createCourse();

    int deleteCourse();

    int modifyCourse();

    JsonObject getCourseInfo();

    int createCoursePage();

    int deleteCoursePage();

    int modifyCoursePage();

    JsonObject getCoursePageInfo();

    int createHomework();

    int deleteHomework();

    int modifyHomework();

    JsonObject getHomeworkInfo();

    int createCourseTable();

    int deleteCourseTable();

    JsonObject courseTableInfo();

    int createAnswer();

    int deleteAnswer();

    int modifyAnswer();

    JsonObject getAnswerInfo();

    int createStatistics();

    int deleteStatistics();

    JsonObject getStatisticsInfo();

    int createResource();

    int deleteResource();

    int modifyResource();

    JsonObject getResourceInfo();

    JsonObject getTopHotCourse();

    JsonObject getLatestCourse();

    int doHomework();

    int doPage();

    int doCourse();

    JsonObject getCourseList();

    JsonObject doAnswer();
}
