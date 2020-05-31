package main.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectMysql {
    public static Connection getConn() throws Exception {
        Connection conn;

        String url = "jdbc:mysql://localhost:3306/stock";
        String user = "stock";
        String password = "stock";

        try {
            // password is set to login password
            System.out.println(url + " " + user + " " + password);
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("success");
            return conn;
        } catch (Exception e) {
            System.out.println("exception: " + e.getLocalizedMessage());
            if (e.getLocalizedMessage().contains("Communications link failure")) {
                e = new Exception("dao fail");
                throw e;
            } else {
                throw e;
            }
        }
    }
}
