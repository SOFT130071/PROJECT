package service;

import com.google.gson.JsonObject;
import dao_impl.DAOFactory;
import dao_impl.TalkDAO;
import entity.Talk;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TalkServiceImpl implements TalkService {
    private JsonObject jsonObject;
    private TalkDAO discussion_boardDAO;

    TalkServiceImpl(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
        discussion_boardDAO = DAOFactory.getTalkDAOInstance();
    }

    @Override
    public int append() {
        String content = jsonObject.get("content").getAsString();
        String uid = jsonObject.get("username").getAsString();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        if (discussion_boardDAO.append(new Talk(content, time, uid)) == -1)
            return 0x020101;

        return 0x020101;
    }

    @Override
    public JsonObject boardInfo() {
        List<Map<String, String>> list = discussion_boardDAO.infoList(new Talk());

        JsonObject jsonObject = new JsonObject();

        if (list == null) return jsonObject;
        Map<String, String> map;
        for (int i = 0; i < list.size(); i++) {
            map = list.get(i);
            JsonObject j = new JsonObject();
            j.addProperty("did", map.get("id"));
            j.addProperty("content", map.get("content"));
            j.addProperty("time", map.get("time"));
            j.addProperty("username", map.get("username"));
            jsonObject.add(i + "", j);
        }

        return jsonObject;
    }
}
