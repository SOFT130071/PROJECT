package dao_impl;

import entity.Talk;

import java.util.List;
import java.util.Map;

public interface TalkDAO {
    int append(Talk Talk);

    int delete(String id);

    int modify(Talk Talk);

    List<Map<String, String>> infoList(Talk Talk);
}
