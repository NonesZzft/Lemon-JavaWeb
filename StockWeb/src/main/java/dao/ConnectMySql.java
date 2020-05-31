package main.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectMySql {
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

    // test
    public static void main(String[] args) {
        Connection loginConn;
        PreparedStatement preparedStatement;
        ResultSet rs;

        try {
            loginConn = ConnectMySql.getConn();
            if (loginConn != null) {
                System.out.println("dao success");
            } else {
                return;
            }

            // find
            preparedStatement = loginConn.prepareStatement("select name,password from user where name = ? and password = ?");
            preparedStatement.setString(1, "user");
            preparedStatement.setString(2, "user");
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                System.out.println("find record:" + rs.getString(1) + ","
                        + rs.getString(2));
                return;
            }
            System.err.println("name or password is wrong");

        } catch (Exception e) {
            System.err.println("ERROR!");
        }
    }
}
