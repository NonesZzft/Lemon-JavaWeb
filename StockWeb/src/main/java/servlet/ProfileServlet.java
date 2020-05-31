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

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
    private Connection LoginServletConn;
    private boolean save;
    private String name, password, address, email;

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println(this.getClass() + ".service() called ...");
            LoginServletConn = ConnectMySql.getConn();
            String temp_save = request.getParameter("save").toString();
            if(temp_save.equals("true")) save = true;
            else save = false;
            name = (String) request.getSession().getAttribute("UserNameSession");
            if(save) {
                password = request.getParameter("password");
                address = request.getParameter("address");
                email = request.getParameter("email");
                PreparedStatement ps = LoginServletConn.prepareStatement("UPDATE user SET password=?,"
                        +"address=?, email=? WHERE name=?");
                ps.setString(1, password);
                ps.setString(2, address);
                ps.setString(3, email);
                ps.setString(4, name);
                ps.executeUpdate();
            }
            PreparedStatement pstmt = LoginServletConn.prepareStatement("SELECT password, address, email, balance from USER where name=?");
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                request.getSession().setAttribute("profile_password", rs.getString(1));
                request.getSession().setAttribute("profile_address", rs.getString(2));
                request.getSession().setAttribute("profile_email", rs.getString(3));
                request.getSession().setAttribute("profile_balance", rs.getFloat(4));
                RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
                rd.forward(request, response);
                return ;
            }
            rs.close();
            RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            request.getSession().setAttribute("error", e.getMessage());
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }
    }
}
