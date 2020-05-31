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

@WebServlet("/BankAddServlet")
public class BankAddServlet extends HttpServlet {
    private PreparedStatement pstmt;
    private Connection LoginServletConn;
    String name, accounting, routing;
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println(this.getClass() + ".service() called ...");
            LoginServletConn = ConnectMySql.getConn();
            name = (String) request.getSession().getAttribute("UserNameSession");
            accounting = request.getParameter("accounting");
            routing = request.getParameter("routing");
            pstmt = LoginServletConn.prepareStatement("INSERT INTO bank VALUES(?,?,10000,?)");
            pstmt.setString(1, accounting);
            pstmt.setString(2, routing);
            pstmt.setString(3, name);
            pstmt.executeUpdate();
            RequestDispatcher rd = request.getRequestDispatcher("BankServlet");
            rd.forward(request, response);
        } catch (Exception e) {
            request.getSession().setAttribute("error", e.getMessage());
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }
    }
}
