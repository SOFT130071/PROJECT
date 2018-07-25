package service;

import com.google.gson.JsonObject;
import dao_impl.DAOFactory;
import dao_impl.UserDAO;
import entity.User;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private JsonObject jsonObject;
    private UserDAO userDAO;

    UserServiceImpl(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
        userDAO = DAOFactory.getUserDAOInstance();
    }

    @Override
    public synchronized int logged() {
        String username = jsonObject.get("username").getAsString();
        if (!hasThisUser(username)) return 0x010104; // not logged in local

        return Integer.parseInt(userDAO.infoList(new User("", username)).get(0).get("logged")) == 1 ? 0x010105 : 0x010104;
    }

    @Override
    public synchronized int log() {
        String username = jsonObject.get("username").getAsString();
        String password = jsonObject.get("password").getAsString();

        boolean hasThisUser = hasThisUser(username);
        if (!hasThisUser) return 0x010101;

        User user = new User("", username, "", password);
        List<Map<String, String>> list = userDAO.infoList(user);
        if (list.size() == 0) return 0x010102;

        user = new User(list.get(0).get("uid"), username, "", "", password, "", "1");
        int code = userDAO.modify(user);

        return code == -1 ? 0x010103 : 0x010100;
    }

    @Override
    public synchronized int reg() {
        String username = jsonObject.get("username").getAsString();
        String password = jsonObject.get("password").getAsString();
        String email = jsonObject.get("email").getAsString();
        int type = jsonObject.get("type").getAsInt();
        String nickname = jsonObject.get("nickname").getAsString();

        if (hasThisUser(username)) return 0x010201;

        return userDAO.append(new User("", username, nickname, email, password, String.valueOf(type), "0")) == -1 ? 0x010202 : 0x010200;
    }

    private synchronized boolean hasThisUser(String username) {
        User user = new User("", username);
        return userDAO.infoList(user).size() != 0;
    }
}
