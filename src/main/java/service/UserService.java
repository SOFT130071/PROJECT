package service;

import com.google.gson.JsonObject;

public interface UserService {
    JsonObject userInfo();

    int login();

    int register();

    boolean hasUsername(String username);

    int sendVerifyCode();
}
