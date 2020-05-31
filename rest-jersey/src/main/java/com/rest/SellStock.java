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

import javax.ws.rs.*;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/SellStock")
public class SellStock {
    private ResultSet rs;
    private PreparedStatement pstmt;
    private Connection LoginServletConn;
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getClichedMessage() {
        try {
            System.out.println(this.getClass() + ".service() called ...");
            LoginServletConn = ConnectMysql.getConn();

            pstmt = LoginServletConn.prepareStatement("select s.* " +
                    "from stock s " +
                    "inner join ( " +
                    "    select name, max(time) as MaxDate " +
                    "    from stock " +
                    "    group by name " +
                    ") tm on s.name = tm.name and s.time = tm.MaxDate");
            rs = pstmt.executeQuery();
            int quantity = 0;
            while (rs.next()) {
                quantity = rs.getInt("quantity");
            }

            return "Success";
        } catch (Throwable te) {
            ArrayList<Stock> list = new ArrayList<Stock>();
            te.printStackTrace();
            return "Fail";
        }
    }
}
