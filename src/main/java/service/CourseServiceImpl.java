package service;

import com.google.gson.JsonObject;
import dao_impl.*;
import entity.*;
import util.CoursePageComparator;

import java.util.*;

public class CourseServiceImpl implements CourseService {
    private JsonObject jsonObject;
    private CourseDAO courseDAO;
    private HomeworkSubmitDAO homeworkSubmitDAO;
    private CoursePageDAO coursePageDAO;
    private ChooseCourseDAO chooseCourseDAO;
    private HomeworkDAO homeworkDAO;
    private StatisticsDAO statisticsDAO;
    private ResourceDAO resourceDAO;

    CourseServiceImpl(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
        courseDAO = DAOFactory.getCourseDAOInstance();
        homeworkSubmitDAO = DAOFactory.getHomeworkSubmitDAOInstance();
        coursePageDAO = DAOFactory.getCoursePageDAOInstance();
        chooseCourseDAO = DAOFactory.getChooseCourseDAOInstance();
        homeworkDAO = DAOFactory.getHomeworkDAOInstance();
        statisticsDAO = DAOFactory.getStatisticsDAOInstance();
        resourceDAO = DAOFactory.getResourceDAOInstance();
    }

    CourseServiceImpl() {
        courseDAO = DAOFactory.getCourseDAOInstance();
        homeworkSubmitDAO = DAOFactory.getHomeworkSubmitDAOInstance();
        coursePageDAO = DAOFactory.getCoursePageDAOInstance();
        chooseCourseDAO = DAOFactory.getChooseCourseDAOInstance();
        homeworkDAO = DAOFactory.getHomeworkDAOInstance();
        statisticsDAO = DAOFactory.getStatisticsDAOInstance();
        resourceDAO = DAOFactory.getResourceDAOInstance();
    }

    @Override
    public int createCourse() {
        String title = jsonObject.get("title").getAsString();
        String teacher_id = jsonObject.get("teacher_id").getAsString();
        String pic_url = jsonObject.get("pic_url").getAsString();
        String content = jsonObject.get("content").getAsString();

        if (courseDAO.append(new Course(title, teacher_id, pic_url, content)) == -1)
            return 0x020101;

        Course c = new Course();
        c.setT_uid(teacher_id);
        List<Map<String, String>> list = courseDAO.infoList(c);
        if (list == null || list.size() == 0) return 0x020101;
        return Integer.parseInt(list.get(list.size() - 1).get("cid"));
    }

    @Override
    public int deleteCourse() {
        String cid = jsonObject.get("cid").getAsString();
        if (courseDAO.delete(cid) == -1)
            return 0x020207;
        return 0x020200;
    }

    @Override
    public int modifyCourse() {
        String title = "", teacher_id = "", pic_url = "", content = "";
        String cid = jsonObject.get("cid").getAsString();
        if (jsonObject.has("teacher_id"))
            teacher_id = jsonObject.get("teacher_id").getAsString();
        if (jsonObject.has("title"))
            title = jsonObject.get("title").getAsString();
        if (jsonObject.has("pic_url"))
            pic_url = jsonObject.get("pic_url").getAsString();
        if (jsonObject.has("content"))
            content = jsonObject.get("content").getAsString();

        return courseDAO.modify(new Course(cid, title, teacher_id, pic_url, content)) == -1 ? 0x020301 : 0x020300;
    }

    public JsonObject doCourseInfo() {
        if (jsonObject.has("cid"))
            return getCourseInfo();
        if (jsonObject.has("tid"))
            return myCourse();
        if (jsonObject.has("uid"))
            return myCourseTable();
        return getCourseList();
    }

    public JsonObject myCourse() {
        String cid = jsonObject.get("tid").getAsString();
        Course course = new Course();
        course.setT_uid(cid);
        List<Map<String, String>> list = courseDAO.infoList(course);

        JsonObject jsonObject = new JsonObject();

        if (list == null) return jsonObject;

        Map<String, String> map;
        for (int i = 0; i < list.size(); i++) {
            map = list.get(i);
            JsonObject j = new JsonObject();
            j.addProperty("cid", map.get("cid"));
            j.addProperty("title", map.get("title"));
            j.addProperty("teacher_id", map.get("teacher_id"));
            j.addProperty("pic_url", map.get("pic_url"));
            j.addProperty("content", map.get("content"));
            jsonObject.add(i + "", j);
        }

        return jsonObject;
    }

    public JsonObject myCourseTable() {

        List<Map<String, String>> list = courseDAO.infoList(jsonObject.get("uid").getAsString());

        JsonObject jsonObject = new JsonObject();

        if (list == null) return jsonObject;

        Map<String, String> map;
        for (int i = 0; i < list.size(); i++) {
            map = list.get(i);
            JsonObject j = new JsonObject();
            j.addProperty("cid", map.get("cid"));
            j.addProperty("title", map.get("title"));
            jsonObject.add(i + "", j);
        }

        return jsonObject;
    }

    @Override
    public JsonObject getCourseInfo() {
        String cid = jsonObject.get("cid").getAsString();
        Course course = new Course();
        course.setId(cid);
        List<Map<String, String>> list = courseDAO.infoList(course);

        JsonObject jsonObject = new JsonObject();

        if (list == null) return jsonObject;

        for (Map<String, String> map : list) {
            jsonObject.addProperty("cid", map.get("cid"));
            jsonObject.addProperty("title", map.get("title"));
            jsonObject.addProperty("teacher_id", map.get("teacher_id"));
            jsonObject.addProperty("pic_url", map.get("pic_url"));
            jsonObject.addProperty("content", map.get("content"));
        }

        return jsonObject;
    }

    //匹配查找
    public JsonObject getCourseList() {
        String content = "", name = "", title = "";
        if (jsonObject.has("content"))
            content = jsonObject.get("content").getAsString();
        if (jsonObject.has("name"))
            name = jsonObject.get("name").getAsString();
        if (jsonObject.has("title"))
            title = jsonObject.get("title").getAsString();
        List<Map<String, String>> list;
        if (jsonObject.get("order").getAsString().equals("hot"))
            list = courseDAO.infoListHot(title, content, name, jsonObject.get("choose").getAsString());
        else
            list = courseDAO.infoList(title, content, name, jsonObject.get("choose").getAsString());
        JsonObject jsonObject = new JsonObject();

        if (list == null) return jsonObject;
        Map<String, String> map;
        for (int i = 0; i < list.size(); i++) {
            map = list.get(i);
            JsonObject j = new JsonObject();
            j.addProperty("cid", map.get("id"));
            j.addProperty("title", map.get("title"));
            j.addProperty("nickname", map.get("nickname"));
            j.addProperty("pic_url", map.get("img"));
            j.addProperty("content", map.get("content"));
            jsonObject.add(i + "", j);
        }

        return jsonObject;
    }

    @Override
    public int createCoursePage() {
        String cid = jsonObject.get("cid").getAsString();
        String number = jsonObject.get("number").getAsString();
        String title = jsonObject.get("title").getAsString();
        String content = jsonObject.get("content").getAsString();
        String url = "";
        if (jsonObject.has("url"))
            url = jsonObject.get("url").getAsString();

        if (coursePageDAO.append(new CoursePage(cid, number, title, content, url)) == -1)
            return 0x020101;

        CoursePage page = new CoursePage();
        page.setC_id(cid);
        List<CoursePage> list = coursePageDAO.infoList(page);
        if (list == null || list.size() == 0) return 0x020102;
        return Integer.parseInt(list.get(list.size() - 1).getId());
    }

    @Override
    public int deleteCoursePage() {
        String pid = jsonObject.get("pid").getAsString();
        if (coursePageDAO.delete(pid) == -1)
            return 0x020207;
        return 0x020200;
    }

    @Override
    public int modifyCoursePage() {
        String number = "", title = "", content = "", url = "";
        String pid = jsonObject.get("pid").getAsString();
        if (jsonObject.has("number"))
            number = jsonObject.get("number").getAsString();
        if (jsonObject.has("title"))
            title = jsonObject.get("title").getAsString();
        if (jsonObject.has("content"))
            content = jsonObject.get("content").getAsString();
        if (jsonObject.has("url"))
            url = jsonObject.get("url").getAsString();
        return coursePageDAO.modify(new CoursePage(pid, "", number, title, content, url)) == -1 ? 0x020301 : 0x020300;
    }

    @Override
    @SuppressWarnings("unchecked")
    public JsonObject getCoursePageInfo() {
        CoursePage coursePage = new CoursePage();
        coursePage.setC_id(jsonObject.get("cid").getAsString());
        List<CoursePage> list = coursePageDAO.infoList(coursePage);

        JsonObject jsonObject = new JsonObject();
        if (list == null) return jsonObject;

        list.sort(new CoursePageComparator());

        CoursePage page;
        for (int i = 0; i < list.size(); i++) {
            page = list.get(i);
            JsonObject j = new JsonObject();
            j.addProperty("pid", page.getId());
            j.addProperty("title", page.getTitle());
            j.addProperty("content", page.getContent());
            j.addProperty("number", page.getNumber());
            j.addProperty("url", page.getLink());
            jsonObject.add(i + "", j);
        }

        return jsonObject;
    }

    @Override
    public int createHomework() {
        String cid = jsonObject.get("cid").getAsString();
        String start_time = jsonObject.get("start_time").getAsString();
        String end_time = jsonObject.get("end_time").getAsString();
        String title = jsonObject.get("title").getAsString();
        String content = jsonObject.get("content").getAsString();

        if (homeworkDAO.append(new Homework(start_time, title, content, end_time, cid)) == -1)
            return 0x020101;

        Homework homeworkBean = new Homework();
        homeworkBean.setC_id(cid);
        List<Map<String, String>> list = homeworkDAO.infoList(homeworkBean);
        if (list == null || list.size() == 0) return 0x020101;
        return Integer.parseInt(list.get(list.size() - 1).get("hid"));
    }

    @Override
    public int deleteHomework() {
        String hid = jsonObject.get("hid").getAsString();

        if (homeworkDAO.delete(hid) == -1)
            return 0x111111;
        return 0x000000;
    }

    @Override
    public int modifyHomework() {
        String start_time = "", title = "", content = "", end_time = "";
        String hid = jsonObject.get("hid").getAsString();
        if (jsonObject.has("start_time"))
            start_time = jsonObject.get("start_time").getAsString();
        if (jsonObject.has("end_time"))
            end_time = jsonObject.get("end_time").getAsString();
        if (jsonObject.has("title"))
            title = jsonObject.get("title").getAsString();
        if (jsonObject.has("content"))
            content = jsonObject.get("content").getAsString();
        return homeworkDAO.modify(new Homework(hid, start_time, title, content, end_time, "")) == -1 ? 0x020301 : 0x020300;
    }

    @Override
    public JsonObject getHomeworkInfo() {
        Homework homework = new Homework();
        if (jsonObject.has("cid"))
            homework.setC_id(jsonObject.get("cid").getAsString());
        if (jsonObject.has("hid"))
            homework.setId(jsonObject.get("hid").getAsString());
        List<Map<String, String>> list = homeworkDAO.infoList(homework);

        JsonObject jsonObject = new JsonObject();

        if (list == null) return jsonObject;

        Map<String, String> map;
        for (int i = 0; i < list.size(); i++) {
            map = list.get(i);
            JsonObject j = new JsonObject();
            j.addProperty("hid", map.get("hid"));
            j.addProperty("cid", map.get("cid"));
            j.addProperty("start_time", map.get("start_time"));
            j.addProperty("title", map.get("title"));
            j.addProperty("content", map.get("content"));
            j.addProperty("end_time", map.get("end_time"));
            jsonObject.add(i + "", j);
        }

        return jsonObject;
    }

    @Override
    public int createCourseTable() {
        String cid = jsonObject.get("cid").getAsString();
        String uid = jsonObject.get("uid").getAsString();

        return chooseCourseDAO.append(new ChooseCourse(cid, uid)) == -1 ? 0x020101 : 0x020100;
    }

    @Override
    public int deleteCourseTable() {
        ChooseCourse c = new ChooseCourse();
        if (jsonObject.has("cid"))
            c.setC_id(jsonObject.get("cid").getAsString());
        if (jsonObject.has("uid"))
            c.setS_id(jsonObject.get("uid").getAsString());

        return chooseCourseDAO.delete(c) == -1 ? 0x020101 : 0x020101;
    }

    @Override
    public JsonObject courseTableInfo() {
        ChooseCourse courseTableBean = new ChooseCourse();
        if (jsonObject.has("cid"))
            courseTableBean.setC_id(jsonObject.get("cid").getAsString());
        if (jsonObject.has("uid"))
            courseTableBean.setS_id(jsonObject.get("uid").getAsString());
        List<Map<String, String>> list = chooseCourseDAO.infoList(courseTableBean);

        JsonObject jsonObject = new JsonObject();

        if (list == null) return jsonObject;

        Map<String, String> map;
        for (int i = 0; i < list.size(); i++) {
            map = list.get(i);
            JsonObject j = new JsonObject();
            j.addProperty("uid", map.get("uid"));
            j.addProperty("cid", map.get("cid"));
            jsonObject.add(i + "", j);
        }

        return jsonObject;
    }

    @Override
    public int createAnswer() {
        String hid = jsonObject.get("hid").getAsString();
        String uid = jsonObject.get("uid").getAsString();
        String content = jsonObject.get("content").getAsString();

        return homeworkSubmitDAO.append(new HomeworkSubmit(uid, hid, content, "")) == -1 ? 0x020101 : 0x020100;
    }

    @Override
    public int deleteAnswer() {
        String hid = "", uid = "";
        if (jsonObject.has("hid"))
            hid = jsonObject.get("hid").getAsString();
        if (jsonObject.has("uid"))
            uid = jsonObject.get("uid").getAsString();

        return homeworkSubmitDAO.delete(uid, hid) == -1 ? 0x020101 : 0x020100;
    }

    @Override
    public int modifyAnswer() {
        String grade = "", content = "";
        String hid = jsonObject.get("hid").getAsString();
        String uid = jsonObject.get("uid").getAsString();
        if (jsonObject.has("grade"))
            grade = jsonObject.get("grade").getAsString();
        if (jsonObject.has("content"))
            content = jsonObject.get("content").getAsString();
        return homeworkSubmitDAO.modify(new HomeworkSubmit(uid, hid, content, grade)) == -1 ? 0x020301 : 0x020300;
    }

    @Override
    public JsonObject getAnswerInfo() {
        HomeworkSubmit answer = new HomeworkSubmit();
        if (jsonObject.has("hid"))
            answer.setHw_id(jsonObject.get("hid").getAsString());
        if (jsonObject.has("uid"))
            answer.setS_id(jsonObject.get("uid").getAsString());
        List<Map<String, String>> list = homeworkSubmitDAO.infoList(answer);

        JsonObject jsonObject = new JsonObject();

        if (list == null) return jsonObject;

        Map<String, String> map;
        for (int i = 0; i < list.size(); i++) {
            map = list.get(i);
            JsonObject j = new JsonObject();
            j.addProperty("uid", map.get("uid"));
            j.addProperty("hid", map.get("hid"));
            j.addProperty("content", map.get("content"));
            j.addProperty("grade", map.get("grade"));
            jsonObject.add(i + "", j);
        }

        return jsonObject;
    }

    @Override
    public int createStatistics() {
        String cid = jsonObject.get("cid").getAsString();
        String uid = jsonObject.get("uid").getAsString();
        String pid = jsonObject.get("pid").getAsString();
        List<Map<String, String>> list = statisticsDAO.infoList(uid, cid, pid);
        if (list == null || list.size() == 0)
            return statisticsDAO.append(new Statistics(uid, cid, pid)) == -1 ? 0x020101 : 0x020100;
        return 0x020101;
    }

    @Override
    public int deleteStatistics() {
        Statistics statistics = new Statistics();
        if (jsonObject.has("cid"))
            statistics.setC_id(jsonObject.get("cid").getAsString());
        if (jsonObject.has("uid"))
            statistics.setS_id(jsonObject.get("uid").getAsString());
        if (jsonObject.has("pid"))
            statistics.setCp_id(jsonObject.get("pid").getAsString());

        return statisticsDAO.delete(statistics) == -1 ? 0x020101 : 0x020100;
    }

    @Override
    public JsonObject getStatisticsInfo() {
        Statistics statistics = new Statistics();
        if (jsonObject.has("cid"))
            statistics.setC_id(jsonObject.get("cid").getAsString());
        List<Map<String, String>> list = statisticsDAO.infoList(statistics);

        JsonObject jsonObject = new JsonObject();

        if (list == null) return jsonObject;

        Map<String, String> map;
        for (int i = 0; i < list.size(); i++) {
            map = list.get(i);
            JsonObject j = new JsonObject();
            j.addProperty("uid", map.get("uid"));
            j.addProperty("nickname", map.get("nickname"));
            j.addProperty("count", map.get("count(pid)"));
            jsonObject.add((i + 1) + "", j);
        }

        return jsonObject;
    }

    @Override
    public int createResource() {
        String cid = jsonObject.get("cid").getAsString();
        String url = jsonObject.get("url").getAsString();
        String number = jsonObject.get("number").getAsString();

        return resourceDAO.append(new Resource(cid, url, number)) == -1 ? 0x020101 : 0x020100;
    }

    @Override
    public int deleteResource() {
        Resource resourceBean = new Resource();
        if (jsonObject.has("cid"))
            resourceBean.setC_id(jsonObject.get("cid").getAsString());
        if (jsonObject.has("url"))
            resourceBean.setContent(jsonObject.get("url").getAsString());
        if (jsonObject.has("number"))
            resourceBean.setNumber(jsonObject.get("number").getAsString());

        return resourceDAO.delete(resourceBean) == -1 ? 0x020101 : 0x020100;
    }

    @Override
    public int modifyResource() {
        Resource resourceBean = new Resource();
        if (jsonObject.has("cid"))
            resourceBean.setC_id(jsonObject.get("cid").getAsString());
        if (jsonObject.has("url"))
            resourceBean.setContent(jsonObject.get("url").getAsString());
        if (jsonObject.has("number"))
            resourceBean.setNumber(jsonObject.get("number").getAsString());

        return resourceDAO.modify(resourceBean) == -1 ? 0x020101 : 0x020100;
    }

    @Override
    public JsonObject getResourceInfo() {
        Resource resourceBean = new Resource();
        if (jsonObject.has("cid"))
            resourceBean.setC_id(jsonObject.get("cid").getAsString());
        if (jsonObject.has("number"))
            resourceBean.setContent(jsonObject.get("number").getAsString());
        List<Map<String, String>> list = resourceDAO.infoList(resourceBean);

        JsonObject jsonObject = new JsonObject();

        if (list == null) return jsonObject;

        Map<String, String> map;
        for (int i = 0; i < list.size(); i++) {
            map = list.get(i);
            JsonObject j = new JsonObject();
            j.addProperty("cid", map.get("c_id"));
            j.addProperty("number", map.get("number"));
            j.addProperty("url", map.get("content"));
            jsonObject.add(i + "", j);
        }

        return jsonObject;
    }

    @Override
    public JsonObject getTopHotCourse() {
        List<Map<String, String>> list = chooseCourseDAO.infoList(new ChooseCourse());
        JsonObject jsonObject = new JsonObject();
        if (list == null) return jsonObject;
        Map<String, Integer> counter = new HashMap<>();
        for (Map<String, String> map : list) {
            if (counter.containsKey(map.get("cid")))
                counter.put(map.get("cid"), counter.get(map.get("cid")) + 1);
            else
                counter.put(map.get("cid"), 1);
        }

        List<Map.Entry<String, Integer>> result = new ArrayList<>(counter.entrySet());
        result.sort(new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        int size = result.size() > 3 ? 3 : result.size();
        int i = result.size() - 1;
        for (int k = 0; k < size; i--, k++) {
            Map.Entry<String, Integer> e = result.get(i);
            Course c = new Course();
            c.setId(e.getKey());
            List<Map<String, String>> course = courseDAO.infoList(c);
            if (course == null)
                continue;
            Map<String, String> map = course.get(0);
            JsonObject j = new JsonObject();
            j.addProperty("cid", map.get("cid"));
            j.addProperty("title", map.get("title"));
            j.addProperty("teacher_id", map.get("teacher_id"));
            j.addProperty("pic_url", map.get("pic_url"));
            j.addProperty("content", map.get("content"));
            jsonObject.add(k + "", j);
        }

        return jsonObject;
    }

    @Override
    public JsonObject getLatestCourse() {
        List<Map<String, String>> list = courseDAO.infoList(new Course());

        JsonObject jsonObject = new JsonObject();

        if (list == null) return jsonObject;

        int size = list.size() > 3 ? 3 : list.size();
        int i = list.size() - 1;
        for (int k = 0; k < size; i--, k++) {
            Map<String, String> map = list.get(i);
            JsonObject j = new JsonObject();
            j.addProperty("cid", map.get("cid"));
            j.addProperty("title", map.get("title"));
            j.addProperty("teacher_id", map.get("teacher_id"));
            j.addProperty("pic_url", map.get("pic_url"));
            j.addProperty("content", map.get("content"));
            jsonObject.add(k + "", j);
        }

        return jsonObject;
    }

    @Override
    public int doHomework() {
        if (jsonObject.has("hid")) {
            if (jsonObject.has("title"))
                return modifyHomework();
            else
                return deleteHomework();
        } else
            return createHomework();
    }

    @Override
    public int doPage() {
        if (jsonObject.has("pid")) {
            if (jsonObject.has("title"))
                return modifyCoursePage();
            else
                return deleteCoursePage();
        } else
            return createCoursePage();
    }

    @Override
    public int doCourse() {
        if (jsonObject.has("cid")) {
            if (jsonObject.has("title"))
                return modifyCourse();
            else
                return deleteCourse();
        } else
            return createCourse();
    }

    public JsonObject doAnswer() {
        if (jsonObject.has("content") || jsonObject.has("grade")) {
            if (getAnswerInfo().has("0"))
                modifyAnswer();
            else
                createAnswer();
        } else
            return getAnswerInfo();
        return null;
    }
}