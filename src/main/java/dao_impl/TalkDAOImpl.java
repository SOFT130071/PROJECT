package dao_impl;

import entity.Talk;
import util.SqlUtil;
import util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TalkDAOImpl implements TalkDAO {
    @Override
    public int append(Talk talk) {
        Connection conn = SqlUtil.createCon();

        try {
            String sql = "INSERT INTO `mooc`.`talk` (content,`time`,username) VALUES (?, ?, ?)";
            PreparedStatement ppst = conn.prepareStatement(sql);
            ppst.setString(1, talk.getContent());
            ppst.setString(2, talk.getTime());
            ppst.setString(3, talk.getUsername());

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
    public int delete(String id) {
        Connection conn = SqlUtil.createCon();
        try {
            String sql = "DELETE FROM `mooc`.`talk` WHERE id='" + id + "'";
            PreparedStatement ppst = conn.prepareStatement(sql);
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
    public int modify(Talk talk) {
        Connection conn = SqlUtil.createCon();
        try {
            String sql = "UPDATE `mooc`.`talk` SET ";
            String match = "";
            if (StringUtil.isNotEmpty(talk.getContent()))
                match += ", content='" + talk.getContent() + "' ";
            if (StringUtil.isNotEmpty(talk.getTime()))
                match += ", time='" + talk.getTime() + "' ";
            if (StringUtil.isNotEmpty(talk.getUsername()))
                match += ", username='" + talk.getUsername() + "' ";

            if (!match.isEmpty())
                sql += match.substring(1);

            sql += "WHERE id='" + talk.getId() + "'";

            PreparedStatement ppst = conn.prepareStatement(sql);
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
    public List<Map<String, String>> infoList(Talk talk) {
        Connection conn = SqlUtil.createCon();
        try {
            String sql = "SELECT * FROM `mooc`.`talk` ";
            String match = "";
            if (StringUtil.isNotEmpty(talk.getId()))
                match += "AND id='" + talk.getId() + "' ";
            if (StringUtil.isNotEmpty(talk.getContent()))
                match += "AND content='" + talk.getContent() + "' ";
            if (StringUtil.isNotEmpty(talk.getTime()))
                match += "AND time='" + talk.getTime() + "' ";
            if (StringUtil.isNotEmpty(talk.getUsername()))
                match += "AND username='" + talk.getUsername() + "' ";

            if (!match.isEmpty())
                sql += "WHERE " + match.substring(3);

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet re = ps.executeQuery();
            SqlUtil.closeCon();
            List<Map<String, String>> result = new ArrayList<>();
            while (re.next()) {
                Map<String, String> map = new HashMap<>();
                map.put("id", re.getString("id"));
                map.put("content", re.getString("content"));
                map.put("time", re.getString("time"));
                map.put("username", re.getString("username"));
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
