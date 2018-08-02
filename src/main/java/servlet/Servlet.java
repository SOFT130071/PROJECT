package servlet;

import com.google.gson.JsonObject;
import service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "Servlet")
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        JsonObject param = new JsonObject();
        Map<String, String[]> h = request.getParameterMap();
        for (Map.Entry<String, String[]> e : h.entrySet())
            param.addProperty(e.getKey(), e.getValue()[0]);
        System.out.println(param.toString());
        switch (request.getParameter("type")) {
            case "pagelist":
                pw.print(ServiceFactory.getCourseServiceInstance(param).getCoursePageInfo());
                break;
            case "add_record":
                pw.print(ServiceFactory.getCourseServiceInstance(param).createStatistics());
                break;
            case "tophot":
                pw.print(ServiceFactory.getCourseServiceInstance().getTopHotCourse());
                break;
            case "latestcourse":
                pw.print(ServiceFactory.getCourseServiceInstance().getLatestCourse());
                break;
            case "login":
                pw.print(ServiceFactory.getUserServiceInstance(param).login());
                break;
            case "mail":
                pw.print(ServiceFactory.getUserServiceInstance(param).sendVerifyCode());
                break;
            case "hasUsername":
                pw.print(ServiceFactory.getUserServiceInstance().hasUsername(request.getParameter("username")));
                break;
            case "register":
                pw.print(ServiceFactory.getUserServiceInstance(param).register());
                break;
            case "board":
                pw.print(ServiceFactory.getDiscussionInstance(param).boardInfo());
                break;
            case "add_board":
                pw.print(ServiceFactory.getDiscussionInstance(param).append());
                break;
            case "courseinfo":
                System.out.println(ServiceFactory.getCourseServiceInstance(param).doCourseInfo());
                pw.print(ServiceFactory.getCourseServiceInstance(param).doCourseInfo());
                break;
            case "resourcelist":
                pw.print(ServiceFactory.getCourseServiceInstance(param).getResourceInfo());
                break;
            case "homeworklist":
                pw.print(ServiceFactory.getCourseServiceInstance(param).getHomeworkInfo());
                break;
            case "userinfo":
                pw.print(ServiceFactory.getUserServiceInstance(param).userInfo());
                break;
            case "homework":
                pw.print(ServiceFactory.getCourseServiceInstance(param).doHomework());
                break;
            case "aresource":
                pw.print(ServiceFactory.getCourseServiceInstance(param).createResource());
                break;
            case "dresource":
                pw.print(ServiceFactory.getCourseServiceInstance(param).deleteResource());
                break;
            case "mresource":
                pw.print(ServiceFactory.getCourseServiceInstance(param).modifyResource());
                break;
            case "page":
                pw.print(ServiceFactory.getCourseServiceInstance(param).doPage());
                break;
            case "course":
                pw.print(ServiceFactory.getCourseServiceInstance(param).doCourse());
                break;
            case "hasJoin":
                pw.print(ServiceFactory.getCourseServiceInstance(param).courseTableInfo().has("0"));
                break;
            case "joincourse":
                pw.print(ServiceFactory.getCourseServiceInstance(param).createCourseTable());
                break;
            case "dropoutcourse":
                pw.print(ServiceFactory.getCourseServiceInstance(param).deleteCourseTable());
                break;
            case "countrecord":
                pw.print(ServiceFactory.getCourseServiceInstance(param).getStatisticsInfo());
                break;
            case "search":
                pw.print(ServiceFactory.getCourseServiceInstance(param).getCourseList());
                break;
            case "answer":
                pw.print(ServiceFactory.getCourseServiceInstance(param).doAnswer());
                break;
            case "add_course":
                pw.print(ServiceFactory.getCourseServiceInstance(param).createCourse());
                break;
        }
        pw.flush();
        pw.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
