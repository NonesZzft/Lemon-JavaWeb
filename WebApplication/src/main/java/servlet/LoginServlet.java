package main.java.servlet;

import main.java.dao.ConnectMySql;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private String userName, userPassword;
    private ResultSet rs;
    private PreparedStatement pstmt;
    private Connection LoginServletConn;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println(this.getClass() + ".service() called ...");
            // test
            userName = request.getParameter("name");
            userPassword = request.getParameter("password");
            LoginServletConn = ConnectMySql.getConn();
            pstmt = LoginServletConn.prepareStatement("select name, password from user where name = ? and password = ?");
            pstmt.setString(1, userName);
            pstmt.setString(2, userPassword);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                request.getSession().setAttribute("UserNameSession", rs.getString(1));
                this.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
                return;
            }
            else {
                System.out.println("rs.next() = 0, no this user");
            }
            System.err.println("name or password is wrong");
            this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            return ;

        } catch (Exception e) {
            System.out.println("exception" + e.getLocalizedMessage());
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
