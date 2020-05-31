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

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private PreparedStatement pstmt;
    private Connection LoginServletConn;
    String username, password, address, email;
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println(this.getClass() + ".service() called ...");
            LoginServletConn = ConnectMySql.getConn();
            pstmt = LoginServletConn.prepareStatement("INSERT INTO user VALUES(?,?,?,?,0)");
            username = request.getParameter("username");
            password = request.getParameter("password");
            address = request.getParameter("address");
            email = request.getParameter("email");
            System.out.println(username);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, address);
            pstmt.setString(4, email);
            pstmt.executeUpdate();
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            request.getSession().setAttribute("error", e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
