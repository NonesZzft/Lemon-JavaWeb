package main.java.servlet;

import main.java.bean.Bank;
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

@WebServlet("/BankServlet")
public class BankServlet extends HttpServlet {
    private ResultSet rs;
    private PreparedStatement pstmt;
    private Connection LoginServletConn;
    String name;
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println(this.getClass() + ".service() called ...");
            LoginServletConn = ConnectMySql.getConn();
            name = (String) request.getSession().getAttribute("UserNameSession");
            pstmt = LoginServletConn.prepareStatement("SELECT accounting, routing, balance from BANK WHERE u_name=?");
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            ArrayList<Bank> list = new ArrayList<Bank>();
            while(rs.next()) {
                Bank bank = new Bank();
                bank.setAccounting(rs.getString("accounting"));
                bank.setRouting(rs.getString("routing"));
                bank.setBalance(rs.getFloat("balance"));
                list.add(bank);
            }
            rs.close();
            System.out.println(list.size());
            HttpSession session = request.getSession(true);
            session.setAttribute("bank_list", list);
            RequestDispatcher rd = request.getRequestDispatcher("bank.jsp");
            rd.forward(request, response);
        }catch (Exception e) {
            request.getSession().setAttribute("error", e.getMessage());
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }
    }
}
