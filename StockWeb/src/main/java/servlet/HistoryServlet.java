package main.java.servlet;

import main.java.bean.Buy;
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
import java.util.ArrayList;

@WebServlet("/HistoryServlet")
public class HistoryServlet extends HttpServlet {
    private ResultSet rs, psrs;
    private PreparedStatement pstmt, ps;
    private Connection LoginServletConn;
    String name, u_name, s_name, type;
    float price;
    int quantity;
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println(this.getClass() + ".service() called ...");
            HttpSession session = request.getSession(true);

            name = (String) request.getSession().getAttribute("UserNameSession");
            type = request.getParameter("type");
            if(type == null) type = "s_name";
            if(type.equals("price")) type = "s_name";

            LoginServletConn = ConnectMySql.getConn();
            pstmt = LoginServletConn.prepareStatement("SELECT * FROM buy WHERE u_name=? order by ?");
            pstmt.setString(1, name);
            pstmt.setString(2, type);
            rs = pstmt.executeQuery();
            ArrayList<Buy> list = new ArrayList<Buy>();
            while(rs.next()) {
                s_name = rs.getString("s_name");
                quantity = rs.getInt("quantity");
                ps = LoginServletConn.prepareStatement("select s.* " +
                        "from stock s " +
                        "inner join ( " +
                        "    select name, max(time) as MaxDate " +
                        "    from stock " +
                        "    group by name " +
                        ") tm on s.name = tm.name and s.time = tm.MaxDate and s.name=?");
                ps.setString(1, s_name);
                psrs = ps.executeQuery();
                if(psrs.next()) {
                    price = psrs.getFloat("price");
                }
                psrs.close();

                Buy buy = new Buy();
                buy.setU_name(name);
                buy.setS_name(s_name);
                buy.setPrice(price);
                buy.setQuantity(quantity);
                list.add(buy);
            }
            rs.close();

            session.setAttribute("list",list);
            RequestDispatcher rd = request.getRequestDispatcher("history.jsp");
            rd.forward(request, response);
        } catch (Throwable te) {
            te.printStackTrace();
        }
    }
}

