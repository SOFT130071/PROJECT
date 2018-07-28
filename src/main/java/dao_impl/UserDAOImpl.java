package dao_impl;

import entity.User;
import util.SqlUtil;
import util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAOImpl implements UserDAO {

    UserDAOImpl() {
    }

    @Override
    public int append(User user) {
        Connection con = SqlUtil.createCon();
        if ("undefined".equals(user.getUsername())) return -1;
        if (con != null) try {
            String sql = "INSERT INTO `mooc`.`users` (`username`, `nickname`, `email`, `password`, `logged`) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ppst = con.prepareStatement(sql);
            ppst.setString(1, user.getUsername());
            ppst.setString(2, user.getNickname());
            ppst.setString(3, user.getEmail());
            ppst.setString(4, user.getPassword());
            ppst.setString(5, user.getLogged());

            int ret = ppst.executeUpdate();
            SqlUtil.closeCon();

            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int delete(int id) {
        Connection con = SqlUtil.createCon();
        if (con != null) try {
            String sql = "DELETE FROM users WHERE uid=?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, String.valueOf(id));

            int ret = pstm.executeUpdate();
            SqlUtil.closeCon();

            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int modify(User user) {
        Connection con = SqlUtil.createCon();
        if (con != null) try {
            String sql = "UPDATE users SET username=?, nickname=?, email=?, password=?, logged=? WHERE uid=?";
            PreparedStatement ppst = con.prepareStatement(sql);
            ppst.setString(6, user.getUid());
            ppst.setString(1, user.getUsername());
            ppst.setString(2, user.getNickname());
            ppst.setString(3, user.getEmail());
            ppst.setString(4, user.getPassword());
            ppst.setString(5, user.getLogged());

            int ret = ppst.executeUpdate();
            SqlUtil.closeCon();

            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Map<String, String>> infoList(User user) {
        Connection con = SqlUtil.createCon();
        if (con != null) try {
            StringBuilder stringBuilder = new StringBuilder("SELECT * FROM users");
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
            if (StringUtil.isNotEmpty(user.getLogged())) {
                stringBuilder.append(" AND logged=\"");
                stringBuilder.append(user.getLogged());
                stringBuilder.append("\"");
            }

            PreparedStatement pstm = con.prepareStatement(stringBuilder.toString().replaceFirst("AND", "WHERE"));

            ResultSet rs = pstm.executeQuery();

            List<Map<String, String>> ret = new ArrayList<>();
            while (rs.next()) {
                Map<String, String> map = new HashMap<>();
                map.put("uid", rs.getString("uid"));
                map.put("username", rs.getString("username"));
                map.put("nickname", rs.getString("nickname"));
                map.put("password", rs.getString("password"));
                map.put("email", rs.getString("email"));
                map.put("logged", rs.getString("logged"));
                ret.add(map);
            }
            SqlUtil.closeCon();
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

