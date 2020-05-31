package main.java.com.rest;
import com.sun.net.httpserver.HttpServer;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import main.java.bean.Stock;
import main.java.dao.ConnectMysql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/StockSort")
public class StockSort {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
//    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public ArrayList<Stock> getClichedMessage(@QueryParam("type") String type) throws Exception {
        Connection LoginServletConn;
        PreparedStatement pstmt;
        ResultSet rs;
        try {
            System.out.println(this.getClass() + ".service() called ...");
            System.out.println(type);
            ArrayList<Stock> list = new ArrayList<Stock>();
            LoginServletConn = ConnectMysql.getConn();
            String sql = "select s.* from stock s inner join ( select name, max(time) as MaxDate from stock group by name) tm on s.name=tm.name and s.time=tm.MaxDate order by " + type;
            pstmt = LoginServletConn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Stock stock = new Stock();
                stock.setName(rs.getString("name"));
                stock.setPrice(rs.getFloat("price"));
                stock.setTime(rs.getTimestamp("time"));
                stock.setQuantity(rs.getInt("quantity"));
                System.out.println(rs.getString("name"));
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
