package main.java.com.rest;
import main.java.bean.Stock;
import main.java.dao.ConnectMysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;


import javax.ws.rs.*;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/StockList")
public class StockList {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
//    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public ArrayList<Stock> getClichedMessage(@QueryParam("type") String type, @QueryParam("info") String info) throws Exception {
        Connection LoginServletConn;
        PreparedStatement pstmt;
        ResultSet rs;
        try {
            System.out.println(this.getClass() + ".service() called ...");
            ArrayList<Stock> list = new ArrayList<Stock>();
            LoginServletConn = ConnectMysql.getConn();
            pstmt = LoginServletConn.prepareStatement("select s.* " +
                    "from stock s " +
                    "inner join ( " +
                    "    select name, max(time) as MaxDate " +
                    "    from stock " +
                    "    group by name " +
                    ") tm on s.name = tm.name and s.time = tm.MaxDate");
            rs = pstmt.executeQuery();
            System.out.println(rs);
            while (rs.next()) {
                Stock stock = new Stock();
                stock.setName(rs.getString("name"));
                stock.setPrice(rs.getFloat("price"));
                stock.setTime(rs.getTimestamp("time"));
                stock.setQuantity(rs.getInt("quantity"));

                String name = rs.getString("name");
                float price = rs.getFloat("price");
                Date time = rs.getDate("time");
                int quantity = rs.getInt("quantity");
                int priority = 0;
//                String type = formParam.getFirst("type");
//                String info = formParam.getFirst("info");


                if (type == null || type.equals("")) {
                    priority = 2;
                } else if (type.equals("name")) {
                    if (name.equals(info)) {
                        priority = 2;
                    } else if (name.contains(info)) {
                        priority = 1;
                    } else priority = 0;
                } else if (type.equals("price")) {
                    if (Integer.parseInt(info) == price) {
                        priority = 2;
                    } else if (Integer.parseInt(info) < price) {
                        priority = 1;
                    } else priority = 0;
                } else if (type.equals("quantity")) {
                    if (Integer.parseInt(info) == quantity) {
                        priority = 2;
                    } else if (Integer.parseInt(info) < quantity) {
                        priority = 1;
                    } else priority = 0;
                }

                stock.setPriority(priority);

                if (priority > 0) {
                    list.add(stock);

                }
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
