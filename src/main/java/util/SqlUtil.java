package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqlUtil {
    private static boolean mutex = false;

    @SuppressWarnings("all")
    public static Connection createCon() {
        while (mutex) {
            // wait for mutex
        }

        try {
            //TODO: 这个地方应该写成配置文件，但是我懒了
            mutex = true;
            String url = "jdbc:mysql://localhost:3306/mooc?useUnicode=true&characterEncoding=UTF-8";
            String userName = "root";
            String password = "1234";
            String driver = "com.mysql.jdbc.Driver";

            Class.forName(driver);
            return DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void closeCon(Connection con) {
        try {
            con.close();
            mutex = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}