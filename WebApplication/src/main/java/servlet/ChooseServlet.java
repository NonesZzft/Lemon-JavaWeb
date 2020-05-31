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

@WebServlet("/ChooseServlet")
public class ChooseServlet extends HttpServlet {
    private ResultSet rs;
    private PreparedStatement pstmt;
    private Connection LoginServletConn;
    String [] stocks;
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println(this.getClass() + ".service() called ...");

            HttpSession session = request.getSession(true);
            stocks = request.getParameterValues("sell");
            ArrayList<Stock> list = new ArrayList<Stock>();
            ArrayList<Buy> buy_list = new ArrayList<Buy>();
            LoginServletConn = ConnectMySql.getConn();
            for(int i = 0; i < stocks.length; i++) {
                pstmt = LoginServletConn.prepareStatement("select s.* " +
                        "from stock s " +
                        "inner join ( " +
                        "    select name, max(time) as MaxDate " +
                        "    from stock " +
                        "    group by name " +
                        ") tm on s.name = tm.name and s.time = tm.MaxDate and s.name=?");
                pstmt.setString(1, stocks[i]);
                rs = pstmt.executeQuery();
                if(rs.next()) {
                    Stock stock = new Stock();
                    stock.setName(rs.getString("name"));
                    stock.setPrice(rs.getFloat("price"));
                    stock.setTime(rs.getTimestamp("time"));
                    stock.setQuantity(rs.getInt("quantity"));
                    list.add(stock);
                }
                pstmt = LoginServletConn.prepareStatement("SELECT * FROM buy WHERE s_name=?");
                pstmt.setString(1, stocks[i]);
                rs = pstmt.executeQuery();
                if(rs.next()) {
                    Buy buy = new Buy();
                    buy.setQuantity(rs.getInt("quantity"));
                    buy_list.add(buy);
                }
            }
            session.setAttribute("list", list);
            session.setAttribute("buy_list", buy_list);
            RequestDispatcher rd = request.getRequestDispatcher("sell.jsp");
            rd.forward(request, response);
        } catch (Throwable te) {
            te.printStackTrace();
        }
    }
}
