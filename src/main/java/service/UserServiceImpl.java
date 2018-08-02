package service;

import com.google.gson.JsonObject;
import dao_impl.DAOFactory;
import dao_impl.UserDAO;
import entity.User;
import util.EncryptUtil;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private JsonObject jsonObject;
    private UserDAO userDAO;

    UserServiceImpl(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
        userDAO = DAOFactory.getUserDAOInstance();
    }

    UserServiceImpl() {
        userDAO = DAOFactory.getUserDAOInstance();
    }
    @Override
    public boolean hasUsername(String username) {
        User user = new User();
        user.setUsername(username);
        List<Map<String, String>> list = userDAO.infoList(user);
        return !(list == null || list.size() == 0);
    }

    @Override
    public int sendVerifyCode() {
        MailService ms = new MailService();
        return ms.sendMsg(jsonObject.get("email").getAsString(), "MOOC 激活码", EncryptUtil.md5(jsonObject.get("email").getAsString(), jsonObject.get("username").getAsString()));
    }

    @Override
    public JsonObject userInfo() {
        User user = new User();
        user.setUid(jsonObject.get("uid").getAsString());
        List<Map<String, String>> list = userDAO.infoList(user);

        JsonObject jsonObject = new JsonObject();

        if (list == null) return jsonObject;

        for (Map<String, String> map : list) {
            jsonObject.addProperty("uid", map.get("iud"));
            jsonObject.addProperty("username", map.get("username"));
            jsonObject.addProperty("nickname", map.get("nickname"));
            jsonObject.addProperty("email", map.get("email"));
        }

        return jsonObject;
    }

    @Override
    public int login() {
        String username = jsonObject.get("username").getAsString();
        String password = jsonObject.get("password").getAsString();

        User user = new User();
        user.setUsername(username);

        List<Map<String, String>> list = userDAO.infoList(user);
        if (list == null || list.size() == 0) return 0x010101;

        if (EncryptUtil.verify(password, username, list.get(0).get("password")))
            return Integer.parseInt(list.get(0).get("uid"));
        return 0x010102;
    }

    @Override
    public int register() {
        String username = jsonObject.get("username").getAsString();
        String password = EncryptUtil.md5(jsonObject.get("password").getAsString(), username);
        String email = jsonObject.get("email").getAsString();
        String nickname = jsonObject.get("nickname").getAsString();
        String verify = jsonObject.get("verify").getAsString();

        if (hasUsername(username)) return 0x010201;

        if (!EncryptUtil.verify(email, username, verify))
            return 0x010203;

        if (userDAO.append(new User("", username, nickname, password, email)) == -1)
            return 0x010202;

        User user = new User();
        user.setUsername(username);

        List<Map<String, String>> list = userDAO.infoList(user);
        if (list == null || list.size() == 0) return 0x010204;

        return Integer.parseInt(list.get(0).get("uid"));
    }
}
