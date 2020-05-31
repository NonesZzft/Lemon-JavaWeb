package main.java.com.rest;
import com.sun.net.httpserver.HttpServer;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import main.java.bean.Stock;
import main.java.dao.ConnectMysql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/StockDetail")
public class StockDetail {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
//    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public ArrayList<Stock> getClichedMessage(@QueryParam("name") String name, @QueryParam("search") String search) throws Exception {
        Connection LoginServletConn;
        PreparedStatement pstmt;
        ResultSet rs;
        try {
            ArrayList<Stock> list = new ArrayList<Stock>();
            LoginServletConn = ConnectMysql.getConn();

            String sql = "";
            if(search==null || search.equals("5year")) {
                sql = "SELECT * FROM stock WHERE name=? order by time desc";
            }
            else if(search.equals("year")) {
                sql = "select * from stock where name=? and weekday(time) < 5 and weekday(time) = 0 and year(now()) = year(time) and time(time) = '08:00:00'" +
                        "  order by time desc ";
            }
            else if(search.equals("month")) {
                sql = "select * from stock where name=? and month(now()) = month(time) and weekday(time) < 5 and year(now()) = year(time) and time(time) = '08:00:00'" +
                        "  order by time desc";
            }
            else if(search.equals("past")) {
                sql = "select * from stock where name=? and week(now())-1 = week(time) and weekday(time) < 5 and  year(now()) = year(time)" +
                        "  order by time desc";
            }
            else if(search.equals("current")) {
                sql = "select * from stock where name=? and weekday(time) < 5 and week(now()) = week(time) and year(now()) = year(time) and time(time) = '08:00:00'" +
                        "  order by time desc";
            }
            else if(search.equals("last")) {
                sql = "select * from stock where name=? and day(now()) = day(time) and year(now()) = year(time) and month(time) = month(now()) " +
                        "  order by time desc";
            }
            pstmt = LoginServletConn.prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Stock stock = new Stock();
                stock.setName(rs.getString("name"));
                stock.setPrice(rs.getFloat("price"));
                stock.setTime(rs.getTimestamp("time"));
                stock.setQuantity(rs.getInt("quantity"));
                list.add(stock);
            }
            rs.close();

            return list;
        } catch (Throwable te) {
            ArrayList<Stock> list = new ArrayList<Stock>();
            te.printStackTrace();
            return list;
        }
    }
}
