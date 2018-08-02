package dao_impl;

import entity.Resource;

import java.util.List;
import java.util.Map;

public interface ResourceDAO {
    int append(Resource resource);

    int delete(Resource resource);

    int modify(Resource resource);

    List<Map<String, String>> infoList(Resource resource);
}
