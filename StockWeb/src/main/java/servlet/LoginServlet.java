package main.java.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import main.java.dao.ConnectMySql;

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

            Client client = Client.create();
            WebResource webResource = client.resource("http://localhost:8082/WebApplication_war_exploded/Login");
            MultivaluedMap formData = new MultivaluedMapImpl();
            formData.add("userName", userName);
            formData.add("userPassword", userPassword);
            String s = webResource.queryParams(formData).get(String.class);
            System.out.println(s);

//            LoginServletConn = ConnectMySql.getConn();
//            pstmt = LoginServletConn.prepareStatement("select name, password from user where name = ? and password = ?");
//            pstmt.setString(1, userName);
//            pstmt.setString(2, userPassword);
//            rs = pstmt.executeQuery();

            if(s.equals("False")) {
                this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }
            else {
                request.getSession().setAttribute("UserNameSession", userName);
                request.getSession().setAttribute("token", s);
                this.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
                return;
            }

        } catch (Exception e) {
            System.out.println("exception" + e.getLocalizedMessage());
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
