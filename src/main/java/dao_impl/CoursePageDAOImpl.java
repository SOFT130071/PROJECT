package dao_impl;

import entity.CoursePage;
import util.SqlUtil;
import util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CoursePageDAOImpl implements CoursePageDAO {

    @Override
    public int append(CoursePage course_pages) {
        Connection conn = SqlUtil.createCon();
        try {
            String sql = "INSERT INTO `mooc`.`course_pages` (`c_id`,`number`,`title`,`content`,`link`) VALUES ('" +
                    course_pages.getC_id() + "','" + course_pages.getNumber() + "','" + course_pages.getTitle() + "','" +
                    course_pages.getContent() + "', ";
            if (StringUtil.isEmpty(course_pages.getLink())) {
                sql += null + ")";
            } else {
                sql += "'" + course_pages.getLink() + "')";
            }
            System.out.println(sql);
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
    public int delete(String id) {
        Connection conn = SqlUtil.createCon();
        try {
            String sql = "DELETE FROM `mooc`.`course_pages` WHERE id='" + id + "'";
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
    public int modify(CoursePage course_pages) {
        Connection conn = SqlUtil.createCon();
        try {
            String sql = "UPDATE `mooc`.`course_pages` SET ";
            String match = "";
            if (StringUtil.isNotEmpty(course_pages.getNumber()))
                match += ", number='" + course_pages.getNumber() + "' ";
            if (StringUtil.isNotEmpty(course_pages.getTitle()))
                match += ", title='" + course_pages.getTitle() + "' ";
            if (StringUtil.isNotEmpty(course_pages.getContent()))
                match += ", content='" + course_pages.getContent() + "' ";
            if (StringUtil.isNotEmpty(course_pages.getLink()))
                match += ", link='" + course_pages.getLink() + "' ";

            if (!match.isEmpty()) {
                sql += match.substring(1);
            }

            sql += "WHERE id='" + course_pages.getId() + "'";

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

    //查找
    @Override
    public List<CoursePage> infoList(CoursePage course_pages) {
        Connection conn = SqlUtil.createCon();
        try {
            String sql = "SELECT * FROM `mooc`.`course_pages` ";
            String match = "";
            if (StringUtil.isNotEmpty(course_pages.getId()))
                match += "AND id='" + course_pages.getId() + "' ";
            if (StringUtil.isNotEmpty(course_pages.getC_id()))
                match += "AND c_id='" + course_pages.getC_id() + "' ";
            if (StringUtil.isNotEmpty(course_pages.getNumber()))
                match += "AND number='" + course_pages.getNumber() + "' ";
            if (StringUtil.isNotEmpty(course_pages.getTitle()))
                match += "AND title='" + course_pages.getTitle() + "' ";
            if (StringUtil.isNotEmpty(course_pages.getContent()))
                match += "AND content='" + course_pages.getContent() + "' ";
            if (StringUtil.isNotEmpty(course_pages.getLink()))
                match += "AND link='" + course_pages.getLink() + "' ";

            if (!match.isEmpty())
                sql += "WHERE " + match.substring(3);

            PreparedStatement ppst = conn.prepareStatement(sql);
            ResultSet re = ppst.executeQuery();
            SqlUtil.closeCon();
            List<CoursePage> result = new ArrayList<>();
            while (re.next()) {
                CoursePage c = new CoursePage();
                c.setId(re.getString("id"));
                c.setC_id(re.getString("c_id"));
                c.setNumber(re.getString("number"));
                c.setTitle(re.getString("title"));
                c.setContent(re.getString("content"));
                c.setLink(re.getString("link"));
                result.add(c);
            }
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            SqlUtil.closeCon();
            return null;
        }

    }
}
