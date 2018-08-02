package service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Test;

public class ServiceTest {
    @Test
    public void test() {
        //System.out.println(ServiceFactory.getCourseServiceInstance().getTopHotCourse());
        //System.out.println(ServiceFactory.getCourseServiceInstance().getLatestCourse());
        //DAOFactory.getCourseDAOInstance().delete("1");
//        JsonObject j = new JsonObject();
//        j.addProperty("cid", "2");
//        //ServiceFactory.getDiscussionInstance(j).append();
//        CourseService cs = ServiceFactory.getCourseServiceInstance(j);
////        System.out.println(cs.getCourseInfo());
////        System.out.println(cs.getCoursePageInfo());
////        System.out.println(cs.getResourceInfo());
//        //System.out.println(cs.getHomeworkInfo());
//        System.out.println(cs.getRecordInfo());
//        JsonObject j1 = new JsonObject();
//        j1.addProperty("uid", "1");
//        System.out.println(ServiceFactory.getDiscussionInstance(new JsonObject()).boardInfo());
        JsonObject param = new JsonParser().parse("{\"type\":\"courseinfo\",\"title\":\"P\",\"content\":\"\",\"name\":\"\",\"order\":\"hot\",\"choose\":\"desc\"}").getAsJsonObject();
        System.out.println(ServiceFactory.getCourseServiceInstance(param).doCourseInfo());
    }
}
