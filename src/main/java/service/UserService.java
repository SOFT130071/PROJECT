package service;

import com.google.gson.JsonObject;

public interface UserService {
    int logout();

    int logged();

    int log();

    int reg();

    JsonObject getUserInfo();
}
