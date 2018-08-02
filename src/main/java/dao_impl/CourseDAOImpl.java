package dao_impl;

import entity.Course;
import util.SqlUtil;
import util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseDAOImpl implements CourseDAO {

    @Override
    public int append(Course course) {
        Connection conn = SqlUtil.createCon();
        try {
            String sql = "INSERT  INTO `mooc`.`courses` (title, t_uid, img, content) VALUES (?, ?, ?, ?)";
            PreparedStatement ppst = conn.prepareStatement(sql);
            ppst.setString(1, course.getTitle());
            ppst.setString(2, course.getT_uid());
            ppst.setString(3, course.getImg());
            ppst.setString(4, course.getContent());

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
            String sql = "DELETE FROM `mooc`.`courses` WHERE id='" + id + "'";
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
    public int modify(Course course) {
        Connection conn = SqlUtil.createCon();
        try {
            String sql = "UPDATE `mooc`.`courses` SET ";
            String match = "";
            if (StringUtil.isNotEmpty(course.getTitle()))
                match += ", title='" + course.getTitle() + "' ";
            if (StringUtil.isNotEmpty(course.getT_uid()))
                match += ", t_uid='" + course.getT_uid() + "' ";
            if (StringUtil.isNotEmpty(course.getImg()))
                match += ", img='" + course.getImg() + "' ";
            if (StringUtil.isNotEmpty(course.getContent()))
                match += ", content='" + course.getContent() + "' ";

            if (!match.isEmpty())
                sql += match.substring(1);
            sql += "WHERE id='" + course.getId() + "'";

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
    public List<Map<String, String>> infoList(Course course) {
        Connection conn = SqlUtil.createCon();
        try {
            String sql = "SELECT * FROM `mooc`.`courses` ";
            String match = "";
            if (StringUtil.isNotEmpty(course.getId()))
                match += "AND id='" + course.getId() + "' ";
            if (StringUtil.isNotEmpty(course.getTitle()))
                match += "AND title='" + course.getTitle() + "' ";
            if (StringUtil.isNotEmpty(course.getT_uid()))
                match += "AND t_uid='" + course.getT_uid() + "' ";
            if (StringUtil.isNotEmpty(course.getImg()))
                match += "AND img='" + course.getImg() + "' ";
            if (StringUtil.isNotEmpty(course.getContent()))
                match += "AND content='" + course.getContent() + "' ";

            if (!match.isEmpty())
                sql += "WHERE " + match.substring(3);

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet re = ps.executeQuery();
            SqlUtil.closeCon();

            return setReturn(re);

        } catch (Exception e) {
            e.printStackTrace();
            SqlUtil.closeCon();
            return null;
        }
    }

    public List<Map<String, String>> infoList(String title, String content, String name, String c) {
        Connection conn = SqlUtil.createCon();
        try {
            ResultSet re;
            if (StringUtil.isNotEmpty(name)) {
                String sql = "select id, title, img, content, users.nickname from users,courses where (users.uid = courses.t_uid) and (users.nickname like \"%" + name + "%\" or users.username like \"%" + name + "%\"";

                if (StringUtil.isNotEmpty(title))
                    sql += "or courses.title like \"%" + title + "%\" ";
                if (StringUtil.isNotEmpty(content))
                    sql += "or content like \"%" + content + "%\" ";

                sql += ") group by id order by id " + c + ";";
                System.out.println(sql);
                re = conn.prepareStatement(sql).executeQuery();
            } else {
                String sql = "select id, title, img, content, users.nickname from users,courses ";
                String match = "";
                if (StringUtil.isNotEmpty(title))
                    match += "or courses.title like \"%" + title + "%\" ";
                if (StringUtil.isNotEmpty(content))
                    match += "or content like \"%" + content + "%\" ";
                if (match.equals(""))
                    sql += " group by id " + c + ";";
                else
                    sql += " where " + match.substring(2) + " group by id order by id " + c + ";";
                System.out.println(sql);
                re = conn.prepareStatement(sql).executeQuery();
            }
            SqlUtil.closeCon();

            return setReturn(re);
        } catch (Exception e) {
            e.printStackTrace();
            SqlUtil.closeCon();
            return null;
        }
    }

    public List<Map<String, String>> infoList(String uid) {
        Connection conn = SqlUtil.createCon();
        try {
            String sql = "select courses.title, courses.id from courses, choose_course where courses.id=choose_course.c_id and choose_course.s_id=" + uid + " group by courses.id;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet re = ps.executeQuery();
            SqlUtil.closeCon();
            List<Map<String, String>> result = new ArrayList<>();
            while (re.next()) {
                Map<String, String> map = new HashMap<>();
                map.put("cid", re.getString("cid"));
                map.put("title", re.getString("title"));
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
    public List<Map<String, String>> infoListHot(String title, String content, String name, String c) {
        Connection conn = SqlUtil.createCon();
        try {
            ResultSet re;
            if (StringUtil.isNotEmpty(name)) {
                String sql = "select courses.id, title, img, content, users.nickname from users, (courses left join choose_course on courses.id=choose_course.c_id)" +
                        " where (users.uid = courses.t_uid) and (users.nickname like \"%%\" or users.username like \"%%\"";

                if (StringUtil.isNotEmpty(title))
                    sql += "or courses.title like \"%" + title + "%\" ";
                if (StringUtil.isNotEmpty(content))
                    sql += "or content like \"%" + content + "%\" ";
                sql += ") group by courses.id order by count(choose_courses.s_id) " + c + ";";
                System.out.println(sql);
                re = conn.prepareStatement(sql).executeQuery();
            } else {
                String sql = "select courses.id, title, img, content, users.nickname from users, (courses left join choose_course on courses.id=choose_course.c_id) ";
                String match = "";
                if (StringUtil.isNotEmpty(title))
                    match += "or courses.title like \"%" + title + "%\" ";
                if (StringUtil.isNotEmpty(content))
                    match += "or content like \"%" + content + "%\" ";
                if (match.equals(""))
                    sql += " group by courses.id order by count(choose_course.s_id) " + c + ";";
                else
                    sql += " where " + match.substring(2) + " group by courses.id order by count(choose_course.s_id) " + c + ";";
                re = conn.prepareStatement(sql).executeQuery();
            }
            SqlUtil.closeCon();


            return setReturn(re);
        } catch (Exception e) {
            e.printStackTrace();
            SqlUtil.closeCon();
            return null;
        }

    }

    private List<Map<String, String>> setReturn(ResultSet res) {
        try {
            List<Map<String, String>> result = new ArrayList<>();
            while (res.next()) {
                Map<String, String> map = new HashMap<>();
                map.put("id", res.getString("id"));
                map.put("title", res.getString("title"));
                map.put("nickname", res.getString("nickname"));
                map.put("img", res.getString("img"));
                map.put("content", res.getString("content"));
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
