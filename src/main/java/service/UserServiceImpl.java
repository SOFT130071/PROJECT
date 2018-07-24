package service;

import com.google.gson.JsonObject;
import dao_impl.DAOFactory;
import dao_impl.UserDAO;
import entity.User;

import java.util.Map;

public class UserServiceImpl {
    private JsonObject jsonObject;
    private UserDAO userDAO;

    UserServiceImpl(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
        userDAO = DAOFactory.getUserDAOInstance();
    }

    public synchronized int logged() {
        String username = jsonObject.get("username").getAsString();
        if (hasThisUser(username) == 0x010101) return 0x010104; // not logged in local

        return Integer.parseInt(userDAO.infoList(new User("", username)).get("logged")) == 1 ? 0x010105 : 0x010104;
    }

    public synchronized int log() {
        String username = jsonObject.get("username").getAsString();
        String password = jsonObject.get("password").getAsString();

        int hasThisUser = hasThisUser(username);
        if (hasThisUser != 0) return hasThisUser;

        User user = new User("", username, "", password);
        Map<String, String> map = userDAO.infoList(user);
        if (map.size() == 0) return 0x010102;

        user = new User("", username, "", "", password, "", "1");
        int code = userDAO.modify(user);

        return code == -1 ? 0x010103 : 0x010100;
    }

    public synchronized int reg() {
        String username = jsonObject.get("username").getAsString();
        String password = jsonObject.get("password").getAsString();
        String email = jsonObject.get("email").getAsString();
        int type = jsonObject.get("type").getAsInt();
        String nickname = jsonObject.get("nickname").getAsString();

        int code = hasThisUser(username);

        if (code == 0x010101) return 0x010201;

        User user = new User();
        Map<String, String> map = userDAO.infoList(user);
        int uid = map.size(); // Get the new id
        user = new User(String.valueOf(uid), username, nickname, email, password, String.valueOf(type), "0");
        int ret = userDAO.append(user);

        return ret == -1 ? 0x010202 : 0x010200;
    }

    private synchronized int hasThisUser(String username) {
        User user = new User("", username);
        Map<String, String> map = userDAO.infoList(user);
        return map.size() == 0 ? 0x010101 : 0;
    }

    private synchronized int onlyOne(String string, String type) {
        switch (type) {

        }
        return 0;
    }
}
