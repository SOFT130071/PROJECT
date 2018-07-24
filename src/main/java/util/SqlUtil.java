package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqlUtil {
    public static Connection createCon() {
        try {
            //TODO: 这个地方应该写成配置文件，但是我懒了
            String url = "jdbc:mysql://localhost:3306/mooc";
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
}