package service;

import com.google.gson.JsonObject;

public class ServiceFactory {

    /**
     * Please put "undefined" in username if no username in local
     *
     * @param param JsonObject of the functions
     */
    public static UserServiceImpl getUserServiceInstance(JsonObject param) {
        return new UserServiceImpl(param);
    }

    public static MailServiceImpl getMailServiceInstance(JsonObject param) {
        return new MailServiceImpl();
    }
}
