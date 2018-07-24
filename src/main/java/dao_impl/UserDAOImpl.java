package dao_impl;

import entity.User;
import util.SqlUtil;
import util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class UserDAOImpl implements UserDAO {
    private Connection con = SqlUtil.createCon();

    UserDAOImpl() {
    }

    @Override
    public int append(User user) {
        if (con != null) try {
            String sql = "INSERT INTO user VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ppst = con.prepareStatement(sql);
            ppst.setInt(1, user.getUid());
            ppst.setString(2, user.getUsername());
            ppst.setString(3, user.getNickname());
            ppst.setString(4, user.getEmail());
            ppst.setString(5, user.getPassword());
            ppst.setInt(6, user.getType());
            ppst.setInt(7, user.getLogged());
            return ppst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int delete(int id) {
        if (con != null) try {
            String sql = "DELETE FROM user WHERE uid=?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, String.valueOf(id));
            return pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int modify(User user) {
        if (con != null) try {
            String sql = "UPDATE user SET uid=?, username=?, nickname=?, email=?, password=?, type=?, logged=?";
            PreparedStatement ppst = con.prepareStatement(sql);
            ppst.setInt(1, user.getUid());
            ppst.setString(2, user.getUsername());
            ppst.setString(3, user.getNickname());
            ppst.setString(4, user.getEmail());
            ppst.setString(5, user.getPassword());
            ppst.setInt(6, user.getType());
            ppst.setInt(7, user.getLogged());
            return ppst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Map<String, String> infoList(User user) {
        if (con != null) try {
            StringBuilder stringBuilder = new StringBuilder("SELECT * FROM user");
            if (StringUtil.isNotEmpty(String.valueOf(user.getUid()))) {
                stringBuilder.append(" AND uid=\"");
                stringBuilder.append(user.getUid());
                stringBuilder.append("\"");
            }
            if (StringUtil.isNotEmpty(user.getUsername())) {
                stringBuilder.append(" AND username=\"");
                stringBuilder.append(user.getUsername());
                stringBuilder.append("\"");
            }
            if (StringUtil.isNotEmpty(user.getNickname())) {
                stringBuilder.append(" AND nickname=\"");
                stringBuilder.append(user.getNickname());
                stringBuilder.append("\"");
            }
            if (StringUtil.isNotEmpty(user.getEmail())) {
                stringBuilder.append(" AND email=\"");
                stringBuilder.append(user.getEmail());
                stringBuilder.append("\"");
            }
            if (StringUtil.isNotEmpty(user.getPassword())) {
                stringBuilder.append(" AND password=\"");
                stringBuilder.append(user.getPassword());
                stringBuilder.append("\"");
            }
            if (StringUtil.isNotEmpty(String.valueOf(user.getType()))) {
                stringBuilder.append(" AND type=\"");
                stringBuilder.append(user.getType());
                stringBuilder.append("\"");
            }
            if (StringUtil.isNotEmpty(String.valueOf(user.getLogged()))) {
                stringBuilder.append(" AND logged=\"");
                stringBuilder.append(user.getLogged());
                stringBuilder.append("\"");
            }

            PreparedStatement pstm = con.prepareStatement(stringBuilder.toString().replaceFirst("AND", "WHERE"));

            ResultSet rs = pstm.executeQuery();

            Map<String, String> map = new HashMap<>();
            while (rs.next()) {
                map.put("uid", rs.getString("uid"));
                map.put("username", rs.getString("username"));
                map.put("nickname", rs.getString("nickname"));
                map.put("password", rs.getString("password"));
                map.put("type", rs.getString("type"));
                map.put("logfed", rs.getString("logfed"));
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

