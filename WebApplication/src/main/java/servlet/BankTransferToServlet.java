package main.java.servlet;

import main.java.dao.ConnectMySql;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/BankTransferToServlet")
public class BankTransferToServlet extends HttpServlet {
    private ResultSet rs;
    private PreparedStatement pstmt;
    private Connection LoginServletConn;
    float amount, balance;
    String accounting, name;
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            System.out.println(this.getClass() + ".service() called ...");
            LoginServletConn = ConnectMySql.getConn();
            name = (String) request.getSession().getAttribute("UserNameSession");
            amount = Float.parseFloat(request.getParameter("amount"));
            accounting = request.getParameter("accounting");

            pstmt = LoginServletConn.prepareStatement("SELECT balance FROM user WHERE name=?");
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                balance = rs.getFloat("balance");
            }
            rs.close();
            balance = balance - amount;
            if(balance >= 0) {
                pstmt = LoginServletConn.prepareStatement("UPDATE user SET balance=? WHERE name=?");
                pstmt.setFloat(1, balance);
                pstmt.setString(2, name);
                pstmt.executeUpdate();


                pstmt = LoginServletConn.prepareStatement("SELECT balance FROM  bank WHERE accounting=?");
                pstmt.setString(1, accounting);
                rs = pstmt.executeQuery();
                if(rs.next()) {
                    balance = rs.getFloat("balance");
                }
                rs.close();

                balance = balance + amount;
                pstmt = LoginServletConn.prepareStatement("UPDATE bank SET balance=? WHERE accounting=?");
                pstmt.setFloat(1, balance);
                pstmt.setString(2, accounting);
                pstmt.executeUpdate();

                System.out.println("balance update");
            }
            else {
                System.out.println("balance not update");
            }
            RequestDispatcher rd = request.getRequestDispatcher("BankServlet");
            rd.forward(request, response);
        } catch (Exception e) {
            request.getSession().setAttribute("error", e.getMessage());
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }
    }
}
