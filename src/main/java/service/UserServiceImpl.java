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
    public synchronized int log() {
        String username = jsonObject.get("username").getAsString();
        String password = jsonObject.get("password").getAsString();

        boolean hasThisUser = hasThisUser(username);
        if (!hasThisUser) return 0x010101;

        User user = new User("", username, "", "", password);
        List<Map<String, String>> list = userDAO.infoList(user);
        if (list.size() == 0) return 0x010102;

        user = new User(list.get(0).get("uid"), username, list.get(0).get("nickname"), list.get(0).get("email"), password, "1");
        int code = userDAO.modify(user);

        if (code == -1) return 0x010103;

        return Integer.parseInt(list.get(0).get("uid"));
    }

    @Override
    public synchronized int reg() {
        String username = jsonObject.get("username").getAsString();
        String password = jsonObject.get("password").getAsString();
        String email = jsonObject.get("email").getAsString();
        String nickname = jsonObject.get("nickname").getAsString();

        if (hasThisUser(username)) return 0x010201;

        return userDAO.append(new User("", username, nickname, email, password, "0")) == -1 ? 0x010202 : 0x010200;
    }

    @Override
    public synchronized int logout() {
        String username = jsonObject.get("username").getAsString();
        if (!hasThisUser(username)) return 0x010104;

        User user = new User("", username);
        List<Map<String, String>> list = userDAO.infoList(user);
        if (list.size() == 0) return 0x010102;

        user = new User(list.get(0).get("uid"), username, list.get(0).get("nickname"), list.get(0).get("email"), list.get(0).get("password"), "0");
        int code = userDAO.modify(user);

        return code == -1 ? 0x010107 : 0x010106;
    }

    @Override
    public synchronized JsonObject getUserInfo() {
        User user = new User("", jsonObject.get("username").getAsString());
        List<Map<String, String>> list = userDAO.infoList(user);

        JsonObject jsonObject = new JsonObject();

        if (list == null) return jsonObject;

        for (Map<String, String> map : list) {
            jsonObject.addProperty("uid", map.get("uid"));
            jsonObject.addProperty("username", map.get("username"));
            jsonObject.addProperty("nickname", map.get("nickname"));
            jsonObject.addProperty("email", map.get("email"));
            jsonObject.addProperty("logged", map.get("logged"));
        }

        return jsonObject;
    }

    private synchronized boolean hasThisUser(String username) {
        User user = new User("", username);
        return userDAO.infoList(user).size() != 0;
    }


}
