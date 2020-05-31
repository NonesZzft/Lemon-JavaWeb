package main.java.rest;

import com.sun.jersey.api.client.AsyncWebResource;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import main.java.bean.Stock;
import main.java.dao.ConnectMySql;
import sun.rmi.runtime.Log;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Future;

@WebServlet("/SellServlet")
public class SellServlet extends HttpServlet {
    private ResultSet rs;
    private PreparedStatement pstmt;
    private Connection LoginServletConn;
    ArrayList<Integer> quantity = new ArrayList<Integer>();
    ArrayList<Integer> bought = new ArrayList<Integer>();
    ArrayList<Integer> time = new ArrayList<Integer>();
    String username;
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println(this.getClass() + ".service() called ...");

            HttpSession session = request.getSession(true);
            List<Stock> list = (List<Stock>) session.getAttribute("list");

            System.out.println(list.size());

            for(int i = 0; i < list.size(); i++) {
                String s = "quantity"+Integer.toString(i+1);
                quantity.add(Integer.parseInt(request.getParameter(s)));
                s = "time"+Integer.toString(i+1);
                String temp = request.getParameter(s);
                if(temp == null) temp = "30";
                time.add(Integer.parseInt(temp));
            }

            Client client = Client.create();
            AsyncWebResource webResource = client.asyncResource("http://localhost:8081/rest_jersey_war_exploded/SellStock");
            Future<String> s = webResource.get(String.class);
            System.out.println(s);

            boolean ans = true;
            LoginServletConn = ConnectMySql.getConn();
            for(int i = 0; i < list.size(); i++) {
                pstmt = LoginServletConn.prepareStatement("SELECT * FROM buy WHERE s_name=?");
                pstmt.setString(1, list.get(i).getName());
                rs = pstmt.executeQuery();
                if(rs.next()) {
                    int q = rs.getInt("quantity");
                    bought.add(q-quantity.get(i));
                    if(q < quantity.get(i)) {
                        ans = false;
                    }
                }
                rs.close();
            }
            if(ans) {
                String schedule = getRandomString(10);
                String sql;
                for(int i = 0; i < list.size(); i++) {
                    schedule = getRandomString(10);
                    sql = "create event "+ schedule +" on schedule at " +
                            "  CURRENT_TIMESTAMP + INTERVAL "+ time.get(i) +" second  do UPDATE buy SET quantity="+ bought.get(i) +" WHERE s_name='"+ list.get(i).getName() +"';";
//                    System.out.println(sql);
                    pstmt = LoginServletConn.prepareStatement(sql);
                    pstmt.executeUpdate();
                }
                for(int i = 0; i < list.size(); i++) {
                    schedule = getRandomString(10);
                    sql = "create event "+ schedule +" on schedule at " +
                            "  CURRENT_TIMESTAMP + INTERVAL "+ time.get(i) +" second do UPDATE user SET balance=balance + " + quantity.get(i) + " " +
                            "  *(select price from stock s where s.name = '"+ list.get(i).getName()+"' and s.time = (SELECT MAX(s2.time) " +
                            "  FROM stock s2 WHERE s2.name = '"+ list.get(i).getName() +"')) WHERE name='"+ username +"';";
                    pstmt = LoginServletConn.prepareStatement(sql);
                    pstmt.executeUpdate();
                }

                RequestDispatcher rd = request.getRequestDispatcher("sell_success.jsp");
                rd.forward(request, response);
            }
            else {
                RequestDispatcher rd = request.getRequestDispatcher("sell_fail.jsp");
                rd.forward(request, response);
            }
        } catch (Throwable te) {
            te.printStackTrace();
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
