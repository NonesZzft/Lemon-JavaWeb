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

@WebServlet("/BankDeleteServlet")
public class BankDeleteServlet extends HttpServlet {
    private PreparedStatement pstmt;
    private Connection LoginServletConn;
    String accounting;
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            System.out.println(this.getClass() + ".service() called ...");
            LoginServletConn = ConnectMySql.getConn();
            accounting = request.getParameter("accounting");
            System.out.println("accounting="+accounting);
            pstmt = LoginServletConn.prepareStatement("DELETE FROM bank WHERE accounting=?");
            pstmt.setString(1, accounting);
            pstmt.executeUpdate();
            RequestDispatcher rd = request.getRequestDispatcher("BankServlet");
            rd.forward(request, response);
        } catch (Exception e) {
            request.getSession().setAttribute("error", e.getMessage());
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }
    }
}
