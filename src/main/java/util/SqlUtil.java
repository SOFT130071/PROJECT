package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqlUtil {
    private static Connection connection = null;
    private static boolean mutex = false;

    public SqlUtil() {
        try {
            //TODO: 这个地方应该写成配置文件，但是我懒了
            String url = "jdbc:mysql://localhost:3306/mooc?useUnicode=true&characterEncoding=UTF-8";
            String userName = "root";
            String password = "1234";
            String driver = "com.mysql.jdbc.Driver";

            Class.forName(driver);
            connection = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("all")
    public synchronized static Connection createCon() {
        while (mutex) {
            // wait for mutex
        }
        mutex = true;
        return connection;
    }

    public static void closeCon() {
        mutex = false;
    }
}