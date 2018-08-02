package dao_impl;

import entity.HomeworkSubmit;
import util.SqlUtil;
import util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeworkSubmitDAOImpl implements HomeworkSubmitDAO {

    @Override
    public int append(HomeworkSubmit homeworkSubmit) {
        Connection conn = SqlUtil.createCon();
        try {
            String sql = "INSERT INTO `mooc`.`homework_submit` (s_id,hw_id,content,score) VALUES (" +
                    homeworkSubmit.getS_id() + "," + homeworkSubmit.getHw_id() + ",'" + homeworkSubmit.getContent() + "',";
            if (StringUtil.isNotEmpty(homeworkSubmit.getScore())) {
                sql += "" + homeworkSubmit.getScore() + ")";
            } else {
                sql += null + ")";
            }
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
    public int delete(String s_id, String hw_id) {
        Connection conn = SqlUtil.createCon();
        try {
            String sql = "DELETE FROM `mooc`.`homework_submit` WHERE hw_id='" + hw_id + "'";
            if (StringUtil.isNotEmpty(s_id)) {
                sql += " AND s_id='" + s_id + "'";
            }
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
    public int modify(HomeworkSubmit homeworkSubmit) {
        Connection conn = SqlUtil.createCon();
        try {
            String sql = "UPDATE `mooc`.`homework_submit` ";
            String match = "";
            if (StringUtil.isNotEmpty(homeworkSubmit.getContent()))
                match += ", content='" + homeworkSubmit.getContent() + "' ";
            if (StringUtil.isNotEmpty(homeworkSubmit.getScore()))
                match += ", score='" + homeworkSubmit.getScore() + "' ";

            if (!match.isEmpty())
                sql += "SET " + match.substring(1);

            match = "";
            if (StringUtil.isNotEmpty(homeworkSubmit.getHw_id()))
                match += "AND hw_id='" + homeworkSubmit.getHw_id() + "' ";
            if (StringUtil.isNotEmpty(homeworkSubmit.getS_id()))
                match += "AND s_id='" + homeworkSubmit.getS_id() + "' ";

            if (!match.isEmpty())
                sql += "WHERE " + match.substring(3);

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
    public List<Map<String, String>> infoList(HomeworkSubmit homeworkSubmit) {
        Connection conn = SqlUtil.createCon();
        try {
            String sql = "SELECT * FROM `mooc`.`homework_submit` ";
            String match = "";
            if (StringUtil.isNotEmpty(homeworkSubmit.getS_id()))
                match += "AND s_id='" + homeworkSubmit.getS_id() + "' ";
            if (StringUtil.isNotEmpty(homeworkSubmit.getHw_id()))
                match += "AND hw_id='" + homeworkSubmit.getHw_id() + "' ";
            if (StringUtil.isNotEmpty(homeworkSubmit.getContent()))
                match += "AND content='" + homeworkSubmit.getContent() + "' ";
            if (StringUtil.isNotEmpty(homeworkSubmit.getScore()))
                match += "AND score='" + homeworkSubmit.getScore() + "' ";

            if (!match.isEmpty())
                sql += "WHERE " + match.substring(3);

            PreparedStatement ppst = conn.prepareStatement(sql);
            ResultSet re = ppst.executeQuery();
            SqlUtil.closeCon();
            List<Map<String, String>> result = new ArrayList<>();
            while (re.next()) {
                Map<String, String> map = new HashMap<>();
                map.put("s_id", re.getString("s_id"));
                map.put("hw_id", re.getString("hw_id"));
                map.put("content", re.getString("content"));
                map.put("score", re.getString("score"));
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
