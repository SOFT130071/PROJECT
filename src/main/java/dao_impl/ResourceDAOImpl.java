package dao_impl;

import entity.Resource;
import util.SqlUtil;
import util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceDAOImpl implements ResourceDAO {

    @Override
    public int append(Resource resources) {
        Connection conn = SqlUtil.createCon();
        try {
            String sql = "INSERT INTO `mooc`.`resources` (c_id,content,`number`) VALUES (?, ?, ?)";
            PreparedStatement ppst = conn.prepareStatement(sql);
            ppst.setString(1, resources.getC_id());
            ppst.setString(2, resources.getContent());
            ppst.setString(3, resources.getNumber());

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
    public int delete(Resource resources) {
        Connection conn = SqlUtil.createCon();
        try {
            String sql = "DELETE FROM `mooc`.`resources` WHERE c_id='" + resources.getC_id() + "'";
            if (StringUtil.isNotEmpty(resources.getNumber())) {
                sql += " AND `number`='" + resources.getNumber() + "'";
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
    public int modify(Resource resources) {
        Connection conn = SqlUtil.createCon();
        try {
            String sql = "UPDATE `mooc`.`resources` SET content=? WHERE c_id=? AND number=?";
            PreparedStatement ppst = conn.prepareStatement(sql);
            ppst.setString(1, resources.getContent());
            ppst.setString(2, resources.getC_id());
            ppst.setString(3, resources.getNumber());

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
    public List<Map<String, String>> infoList(Resource resources) {
        Connection conn = SqlUtil.createCon();
        try {
            String sql = "SELECT * FROM `mooc`.`resources` ";
            String match = "";
            if (!StringUtil.isEmpty(resources.getC_id()))
                match += "AND c_id='" + resources.getC_id() + "' ";
            if (!StringUtil.isEmpty(resources.getNumber()))
                match += "AND `number`='" + resources.getNumber() + "' ";

            if (!match.isEmpty())
                sql += "WHERE " + match.substring(3);

            PreparedStatement ppst = conn.prepareStatement(sql);
            ResultSet re = ppst.executeQuery();
            SqlUtil.closeCon();
            List<Map<String, String>> result = new ArrayList<>();
            while (re.next()) {
                Map<String, String> map = new HashMap<>();
                map.put("c_id", re.getString("c_id"));
                map.put("content", re.getString("content"));
                map.put("number", re.getString("number"));
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
