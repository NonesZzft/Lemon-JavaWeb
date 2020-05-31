package main.java.rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

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

@WebServlet("/BuyServlet")
public class BuyServlet extends HttpServlet {
    String StockName, UserName, QUANTITY, schedule, TIME;
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println(this.getClass() + ".service() called ...");

            StockName = request.getParameter("name");
            QUANTITY = request.getParameter("quantity");
            UserName = (String) request.getSession().getAttribute("UserNameSession");
            schedule = request.getParameter("schedule");
            TIME = request.getParameter("time");

            Client client = Client.create();
            WebResource webResource = client.resource("http://localhost:8081/rest_jersey_war_exploded/BuyStock");
            MultivaluedMap formData = new MultivaluedMapImpl();
            formData.add("StockName", StockName);
            formData.add("UserName", UserName);
            formData.add("QUANTITY", QUANTITY);
            formData.add("schedule", schedule);
            formData.add("TIME", TIME);

            String s = webResource.queryParams(formData).get(String.class);

            if(s.equals("Success")) {
                RequestDispatcher rd = request.getRequestDispatcher("success.jsp");
                rd.forward(request, response);
            }
            else {
                RequestDispatcher rd = request.getRequestDispatcher("fail.jsp");
                rd.forward(request, response);
            }

        } catch (Throwable te) {
            te.printStackTrace();
        }
    }
}
