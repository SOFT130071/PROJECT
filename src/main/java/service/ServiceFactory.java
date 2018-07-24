package service;

import com.google.gson.JsonObject;

public class ServiceFactory {
    public static UserServiceImpl getUserServiceInstance(JsonObject param) {
        return new UserServiceImpl(param);
    }

    public static MailServiceImpl getMailServiceInstance(JsonObject param) {
        return new MailServiceImpl();
    }
}
