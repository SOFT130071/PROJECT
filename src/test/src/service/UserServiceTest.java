package service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import util.SqlUtil;

import static org.junit.Assert.assertEquals;

public class UserServiceTest {

    @Test
    public void testNoUsernameLogged() {
        String str = "" +
                "{" +
                "   username: undefined" +
                "}";
        JsonObject jsonObject = new JsonParser().parse(str).getAsJsonObject();
        UserService userService = ServiceFactory.getUserServiceInstance(jsonObject);

        assertEquals(0x010104, userService.logged());
    }

    @Test
    public void testUsernameNotLogged() {
        String str = "" +
                "{" +
                "   username: hypnus" +
                "}";
        JsonObject jsonObject = new JsonParser().parse(str).getAsJsonObject();
        UserService userService = ServiceFactory.getUserServiceInstance(jsonObject);

        assertEquals(0x010104, userService.logged());
    }

    @Test
    public void testUsernameLogged() {
        String str = "" +
                "{" +
                "   username: veronica" +
                "}";
        JsonObject jsonObject = new JsonParser().parse(str).getAsJsonObject();
        UserService userService = ServiceFactory.getUserServiceInstance(jsonObject);

        assertEquals(0x010105, userService.logged());
    }

    @Test
    public void testExistUsernameReg() {
        String str = "" +
                "{" +
                "   username: veronica," +
                "   password: 123," +
                "   nickname: veronica," +
                "   email:asdf@asdf.cn" +
                "}";
        JsonObject jsonObject = new JsonParser().parse(str).getAsJsonObject();
        UserService userService = ServiceFactory.getUserServiceInstance(jsonObject);

        assertEquals(0x010201, userService.reg());
    }

    @Test
    public void testUsernameReg() {
        String str = "" +
                "{" +
                "   username: ronny," +
                "   password: 123," +
                "   nickname: veronica," +
                "   email:asdf@asdf.cn" +
                "}";
        JsonObject jsonObject = new JsonParser().parse(str).getAsJsonObject();
        UserService userService = ServiceFactory.getUserServiceInstance(jsonObject);

        assertEquals(0x010200, userService.reg());
    }

    @Test
    public void testUsernameWrongPasswordLog() {
        String str = "" +
                "{" +
                "   username: veronica," +
                "   password: 1234" +
                "}";
        JsonObject jsonObject = new JsonParser().parse(str).getAsJsonObject();
        UserService userService = ServiceFactory.getUserServiceInstance(jsonObject);

        assertEquals(0x010102, userService.log());
    }

    @Test
    public void testUsernameLog() {
        String str = "" +
                "{" +
                "   username: ronny," +
                "   password: 123" +
                "}";
        JsonObject jsonObject = new JsonParser().parse(str).getAsJsonObject();
        UserService userService = ServiceFactory.getUserServiceInstance(jsonObject);

        assertEquals(0x010100, userService.log());
    }

    @Test
    public void testUsernameLogout() {
        String str = "" +
                "{" +
                "   username: ronny" +
                "}";
        JsonObject jsonObject = new JsonParser().parse(str).getAsJsonObject();
        UserService userService = ServiceFactory.getUserServiceInstance(jsonObject);

        assertEquals(0x010106, userService.logout());
    }

    @Test
    public void testGetInfo() throws Exception {
        new SqlUtil();
        String str = "" +
                "{" +
                "   username: ronny" +
                "}";
        JsonObject jsonObject = new JsonParser().parse(str).getAsJsonObject();
        UserService userService = ServiceFactory.getUserServiceInstance(jsonObject);
        String ret = userService.getUserInfo().toString();

        String expect = "" +
                "{" +
                "   uid: '7'," +
                "   username: ronny," +
                "   nickname: veronica," +
                "   email: asdfasdfasd@asdds.cn," +
                "   logged: '0'" +
                "}";

        JSONAssert.assertEquals(expect, ret, false);
    }
}
