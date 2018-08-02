package dao_impl;

import java.util.List;
import java.util.Map;

public interface StatisticsDAO {
    int append(entity.Statistics statistics);

    int delete(entity.Statistics statistics);

    List<Map<String, String>> infoList(entity.Statistics statistics);

    List<Map<String, String>> infoList(String s_id, String c_id, String cp_id);
}
