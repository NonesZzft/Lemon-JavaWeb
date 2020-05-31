package main.java.com.rest;
import com.sun.net.httpserver.HttpServer;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import main.java.bean.Stock;
import main.java.dao.ConnectMysql;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/BuyStock")
public class BuyStock {
    private ResultSet rs;
    private PreparedStatement pstmt, ps;
    private Connection LoginServletConn;
    float price, balance;
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getClichedMessage(@QueryParam("StockName") String StockName, @QueryParam("UserName") String UserName,
                                    @QueryParam("QUANTITY") String QUANTITY, @QueryParam("schedule") String schedule, @QueryParam("TIME") String TIME) {
        try {
            System.out.println(this.getClass() + ".service() called ...");
            int quantity = Integer.parseInt(QUANTITY);
            int time = Integer.parseInt(TIME);
            LoginServletConn = ConnectMysql.getConn();
            pstmt = LoginServletConn.prepareStatement("select s.* " +
                    "from stock s " +
                    "inner join ( " +
                    "    select name, max(time) as MaxDate " +
                    "    from stock " +
                    "    group by name " +
                    ") tm on s.name = tm.name and s.time = tm.MaxDate and s.name=?");
            pstmt.setString(1, StockName);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                price = rs.getFloat("price");
            }
            rs.close();
            pstmt = LoginServletConn.prepareStatement("SELECT * FROM user WHERE name=?");
            pstmt.setString(1, UserName);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                balance = rs.getFloat("balance");
            }
            rs.close();
            System.out.println(StockName);
            System.out.println(UserName);
            System.out.println(quantity);
            balance = balance - price * quantity;
            System.out.println("balance="+balance);
            String ScheduleName = getRandomString(10);
            System.out.println(ScheduleName);
            if(balance >= 0) {
                String sql;
                sql = "create event " + ScheduleName + " " +
                        "on SCHEDULE at CURRENT_TIMESTAMP + INTERVAL "+ time +" second  " +
                        "do  update user  " +
                        "set balance = balance - " + quantity + "  *(select price from stock s where s.name = '" + StockName + "' and s.time = (SELECT MAX(s2.time) " +
                        "   FROM stock s2 WHERE s2.name = '" + StockName + "'))" +
                        "where name = '" + UserName + "' ;";
                System.out.println(sql);
                pstmt = LoginServletConn.prepareStatement(sql);
                pstmt.executeUpdate();

//                pstmt = LoginServletConn.prepareStatement("UPDATE user SET balance=? WHERE name=?");
//                pstmt.setFloat(1, balance);
//                pstmt.setString(2, UserName);
//                pstmt.executeUpdate();

                pstmt = LoginServletConn.prepareStatement("SELECT * FROM buy WHERE u_name=? AND s_name=?");
                pstmt.setString(1, UserName);
                pstmt.setString(2, StockName);
                rs = pstmt.executeQuery();
                if(rs.next()) {
                    ScheduleName = getRandomString(10);
                    System.out.println(ScheduleName);
                    sql = "create event "+ ScheduleName +" " +
                            "on SCHEDULE at CURRENT_TIMESTAMP + INTERVAL "+ time +" second  " +
                            "do update buy " +
                            "set quantity = quantity + "+ quantity +" where u_name='"+ UserName +"' and s_name = '"+ StockName +"' ; ";
                    System.out.println(sql);
                    pstmt = LoginServletConn.prepareStatement(sql);
                    pstmt.executeUpdate();
//                    int q = rs.getInt("quantity");
//                    q = q + quantity;
//                    ps = LoginServletConn.prepareStatement("UPDATE buy SET quantity=? WHERE u_name=? AND s_name=?");
//                    ps.setInt(1, q);
//                    ps.setString(2, UserName);
//                    ps.setString(3, StockName);
//                    ps.executeUpdate();
                }
                else {
                    ScheduleName = getRandomString(10);
                    System.out.println(ScheduleName);
                    sql = "create event "+ ScheduleName +" " +
                            "on SCHEDULE at CURRENT_TIMESTAMP + INTERVAL "+ time +" second  " +
                            "do insert into buy " +
                            "values(?, ?, ?) ;";
                    System.out.println(sql);
                    pstmt = LoginServletConn.prepareStatement(sql);
                    pstmt.setString(1, UserName);
                    pstmt.setString(2, StockName);
                    pstmt.setInt(3, quantity);
                    pstmt.executeUpdate();
//                    ps = LoginServletConn.prepareStatement("INSERT INTO buy VALUES(?, ?, ?)");
//                    ps.setString(1, UserName);
//                    ps.setString(2, StockName);
//                    ps.setInt(3, quantity);
//                    ps.executeUpdate();
                }

                return "Success";
            }
            else {
                return "Fail";
            }
        } catch (Throwable te) {
            ArrayList<Stock> list = new ArrayList<Stock>();
            te.printStackTrace();
            return "Fail";
        }
    }

    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(52);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

}