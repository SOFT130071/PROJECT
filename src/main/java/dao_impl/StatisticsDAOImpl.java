package dao_impl;

import util.SqlUtil;
import util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsDAOImpl implements StatisticsDAO {
    @Override
    public int append(entity.Statistics statistics) {
        Connection conn = SqlUtil.createCon();
        try {
            String sql = "INSERT INTO `mooc`.`statistics` (s_id,c_id,cp_id) VALUES (?, ?, ?)";
            PreparedStatement ppst = conn.prepareStatement(sql);
            ppst.setString(1, statistics.getS_id());
            ppst.setString(2, statistics.getC_id());
            ppst.setString(3, statistics.getCp_id());

            int re = ppst.executeUpdate();
            SqlUtil.closeCon();
            return re;
        } catch (Exception e) {
            e.printStackTrace();
            SqlUtil.closeCon();
            return -1;
        }
    }

    @Override
    public int delete(entity.Statistics statistics) {
        Connection conn = SqlUtil.createCon();
        try {
            String sql = "DELETE FROM `mooc`.`statistics` WHERE ";
            String match = "";
            if (StringUtil.isNotEmpty(statistics.getS_id()))
                match += "AND s_id='" + statistics.getS_id() + "' ";
            if (StringUtil.isNotEmpty(statistics.getC_id()))
                match += "AND c_id='" + statistics.getC_id() + "' ";
            if (StringUtil.isNotEmpty(statistics.getCp_id()))
                match += "AND cp_id='" + statistics.getCp_id() + "' ";

            if (!match.isEmpty())
                sql += match.substring(3);

            PreparedStatement ps = conn.prepareStatement(sql);
            int re = ps.executeUpdate();
            SqlUtil.closeCon();
            return re;
        } catch (Exception e) {
            e.printStackTrace();
            SqlUtil.closeCon();
            return -1;
        }
    }

    @Override
    public List<Map<String, String>> infoList(entity.Statistics statistics) {
        Connection conn = SqlUtil.createCon();
        try {
            String sql = "select count(cp_id), nickname, statistics.s_id from users, statistics where c_id=" + statistics.getC_id() + " and users.uid=statistics.s_id group by statistics.s_id order by count(cp_id) desc, statistics.s_id;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet re = ps.executeQuery();
            SqlUtil.closeCon();
            List<Map<String, String>> result = new ArrayList<>();
            while (re.next()) {
                Map<String, String> map = new HashMap<>();
                map.put("count(pid)", re.getString("count(cp_id)"));
                map.put("uid", re.getString("s_id"));
                map.put("nickname", re.getString("nickname"));
                result.add(map);
            }
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            SqlUtil.closeCon();
            return null;
        }
    }

    @Override
    public List<Map<String, String>> infoList(String s_id, String c_id, String cp_id) {
        Connection conn = SqlUtil.createCon();
        try {
            String sql = "select * from statistics where c_id=" + c_id + " and s_id=" + s_id + " and cp_id=" + cp_id + ";";

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet re = ps.executeQuery();
            SqlUtil.closeCon();
            List<Map<String, String>> result = new ArrayList<>();
            while (re.next()) {
                Map<String, String> map = new HashMap<>();
                map.put("pid", re.getString("cp_id"));
                map.put("uid", re.getString("s_id"));
                map.put("cid", re.getString("c_id"));
                result.add(map);
            }
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            SqlUtil.closeCon();
            return null;
        }
    }
}
