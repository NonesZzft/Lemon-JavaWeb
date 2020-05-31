package main.java.servlet;

import main.java.bean.Stock;
import main.java.dao.ConnectMySql;

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
import java.util.*;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    private ResultSet rs;
    private PreparedStatement pstmt;
    private Connection LoginServletConn;

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int count = 0;
        int currentPage;
        try {
            System.out.println(this.getClass() + ".service() called ...");
            HttpSession session = request.getSession(true);

            Object username = request.getSession().getAttribute("UserNameSession");
            if (username == null) {
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
            currentPage = Integer.parseInt(request.getParameter("currentPage").toString());
            if (currentPage < 1) {
                currentPage = 1;
            }


            String type = request.getParameter("type");
            String info = request.getParameter("info");

            System.out.println("!!!"+currentPage);
            System.out.println("!!!"+info);
            System.out.println("!!!"+type);



            ArrayList<Stock> list = new ArrayList<Stock>();
            LoginServletConn = ConnectMySql.getConn();
            pstmt = LoginServletConn.prepareStatement("select s.* " +
                    "from stock s " +
                    "inner join ( " +
                    "    select name, max(time) as MaxDate " +
                    "    from stock " +
                    "    group by name " +
                    ") tm on s.name = tm.name and s.time = tm.MaxDate");
            rs = pstmt.executeQuery();
            System.out.println(rs);
            while(rs.next()) {
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
                if(type == null) {
                    priority = 2;
                }
                else if(type.equals("name")) {
                    if(name.equals(info)) {
                        priority = 2;
                    }
                    else if(name.contains(info)) {
                        priority = 1;
                    }
                    else priority = 0;
                }
                else if(type.equals("price")) {
                    if(Integer.parseInt(info) == price) {
                        priority = 2;
                    }
                    else if(Integer.parseInt(info) < price) {
                        priority = 1;
                    }
                    else priority = 0;
                }

                stock.setPriority(priority);

                if (priority > 0) {
                    list.add(stock);
                    count++;
                }
            }
            rs.close();

            Collections.sort(list, new SortByPriority());

            System.out.println("list size in servlet = " + list.size());

            session.setAttribute("list",list);
            count = list.size();

            request.removeAttribute("currentPage");
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("count", count);

            RequestDispatcher rd = request.getRequestDispatcher("stock.jsp");
            rd.forward(request, response);

        } catch (Throwable te) {
            te.printStackTrace();
        }
    }
}

class SortByPriority implements Comparator {
    public int compare(Object o1, Object o2) {
        Stock s1 = (Stock) o1;
        Stock s2 = (Stock) o2;
        if (s1.getPriority() < s2.getPriority())
            return 1;
        return -1;
    }
}
