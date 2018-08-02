package service;

import com.google.gson.JsonObject;

public class ServiceFactory {
    public static UserServiceImpl getUserServiceInstance(JsonObject param) {
        return new UserServiceImpl(param);
    }

    public static UserServiceImpl getUserServiceInstance() {
        return new UserServiceImpl();
    }

    public static CourseServiceImpl getCourseServiceInstance(JsonObject param) {
        return new CourseServiceImpl(param);
    }

    public static CourseServiceImpl getCourseServiceInstance() {
        return new CourseServiceImpl();
    }

    public static TalkServiceImpl getDiscussionInstance(JsonObject param) {
        return new TalkServiceImpl(param);
    }
}
