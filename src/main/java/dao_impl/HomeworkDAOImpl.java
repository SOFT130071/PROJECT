package dao_impl;

import entity.Homework;
import util.SqlUtil;
import util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeworkDAOImpl implements HomeworkDAO {
    @Override
    public int append(Homework homework) {
        Connection conn = SqlUtil.createCon();
        try {
            String sql = "INSERT INTO `mooc`.`homework` (beg_time,title,content,end_time,c_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ppst = conn.prepareStatement(sql);

            ppst.setString(1, homework.getBeg_time());
            ppst.setString(2, homework.getTitle());
            ppst.setString(3, homework.getContent());
            ppst.setString(4, homework.getEnd_time());
            ppst.setString(5, homework.getC_id());

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
            String sql = "DELETE FROM `mooc`.`homework` WHERE id='" + id + "'";
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
    public int modify(Homework homework) {
        Connection conn = SqlUtil.createCon();
        try {
            String sql = "UPDATE `mooc`.`homework` SET ";
            String match = "";

            if (StringUtil.isNotEmpty(homework.getBeg_time()))
                match += ", beg_time='" + homework.getBeg_time() + "' ";
            if (StringUtil.isNotEmpty(homework.getTitle()))
                match += ", title='" + homework.getTitle() + "' ";
            if (StringUtil.isNotEmpty(homework.getContent()))
                match += ", content='" + homework.getContent() + "' ";
            if (StringUtil.isNotEmpty(homework.getEnd_time()))
                match += ", end_time='" + homework.getEnd_time() + "' ";

            if (!match.isEmpty())
                sql += match.substring(1);

            sql += "WHERE id='" + homework.getId() + "'";

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
    public List<Map<String, String>> infoList(Homework homework) {
        Connection conn = SqlUtil.createCon();
        try {
            String sql = "SELECT * FROM `mooc`.`homework` ";
            String match = "";
            if (StringUtil.isNotEmpty(homework.getId()))
                match += "AND id='" + homework.getId() + "' ";
            if (StringUtil.isNotEmpty(homework.getBeg_time()))
                match += "AND beg_time='" + homework.getBeg_time() + "' ";
            if (StringUtil.isNotEmpty(homework.getTitle()))
                match += "AND title='" + homework.getTitle() + "' ";
            if (StringUtil.isNotEmpty(homework.getContent()))
                match += "AND content='" + homework.getContent() + "' ";
            if (StringUtil.isNotEmpty(homework.getEnd_time()))
                match += "AND end_time='" + homework.getEnd_time() + "' ";
            if (StringUtil.isNotEmpty(homework.getC_id()))
                match += "AND c_id='" + homework.getC_id() + "' ";

            if (!match.isEmpty())
                sql += " WHERE " + match.substring(3);

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet re = ps.executeQuery();
            SqlUtil.closeCon();
            List<Map<String, String>> result = new ArrayList<>();
            while (re.next()) {
                Map<String, String> map = new HashMap<>();
                map.put("hid", re.getString("id"));
                map.put("start_time", re.getString("beg_time"));
                map.put("title", re.getString("title"));
                map.put("content", re.getString("content"));
                map.put("end_time", re.getString("end_time"));
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
