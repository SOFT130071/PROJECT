package dao_impl;

public class DAOFactory {
    public static UserDAO getUserDAOInstance() {
        return new UserDAOImpl();
    }

    public static HomeworkSubmitDAO getHomeworkSubmitDAOInstance() {
        return new HomeworkSubmitDAOImpl();
    }

    public static CourseDAO getCourseDAOInstance() {
        return new CourseDAOImpl();
    }

    public static CoursePageDAO getCoursePageDAOInstance() {
        return new CoursePageDAOImpl();
    }

    public static ChooseCourseDAO getChooseCourseDAOInstance() {
        return new ChooseCourseDAOImpl();
    }

    public static TalkDAO getTalkDAOInstance() {
        return new TalkDAOImpl();
    }

    public static HomeworkDAO getHomeworkDAOInstance() {
        return new HomeworkDAOImpl();
    }

    public static StatisticsDAO getStatisticsDAOInstance() {
        return new StatisticsDAOImpl();
    }

    public static ResourceDAO getResourceDAOInstance() {
        return new ResourceDAOImpl();
    }
}