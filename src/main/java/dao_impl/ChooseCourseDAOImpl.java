package dao_impl;

import entity.ChooseCourse;
import util.SqlUtil;
import util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChooseCourseDAOImpl implements ChooseCourseDAO {
    @Override
    public int append(ChooseCourse choose_course_) {
        Connection conn = SqlUtil.createCon();
        try {
            String sql = "INSERT INTO `mooc`.`choose_course` (c_id,s_id) VALUES ('" + choose_course_.getC_id() + "','" + choose_course_.getS_id() + "')";
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
    public int delete(ChooseCourse choose_course_) {
        Connection conn = SqlUtil.createCon();
        try {
            String sql = "DELETE FROM `mooc`.`choose_course` WHERE c_id='" + choose_course_.getC_id() + "'";
            if (StringUtil.isNotEmpty(choose_course_.getS_id())) {
                sql += " AND s_id='" + choose_course_.getS_id() + "'";
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
    public List<Map<String, String>> infoList(ChooseCourse choose_course_) {
        Connection conn = SqlUtil.createCon();
        try {
            String sql = "SELECT * FROM `mooc`.`choose_course` ";
            String match = "";
            if (StringUtil.isNotEmpty(choose_course_.getC_id()))
                match += "AND c_id='" + choose_course_.getC_id() + "' ";
            if (StringUtil.isNotEmpty(choose_course_.getS_id()))
                match += "AND s_id='" + choose_course_.getS_id() + "' ";

            if (!match.isEmpty())
                sql += "WHERE " + match.substring(3);

            PreparedStatement ppst = conn.prepareStatement(sql);
            ResultSet re = ppst.executeQuery();
            SqlUtil.closeCon();
            List<Map<String, String>> result = new ArrayList<>();
            while (re.next()) {
                Map<String, String> map = new HashMap<>();
                map.put("cid", re.getString("c_id"));
                map.put("uid", re.getString("s_id"));
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
