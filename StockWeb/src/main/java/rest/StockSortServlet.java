package main.java.rest;

import com.alibaba.fastjson.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import main.java.bean.Stock;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

@WebServlet("/StockSortServlet")
public class StockSortServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int count = 0;
        int currentPage;
        try {
            System.out.println(this.getClass() + ".service() called ...");
            HttpSession session = request.getSession(true);

            Object username = request.getSession().getAttribute("UserNameSession");
            if (username == null) {
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
            currentPage = Integer.parseInt(request.getParameter("currentPage").toString());
            if (currentPage < 1) {
                currentPage = 1;
            }

            String type = request.getParameter("type");
            System.out.println(type);

            Client client = Client.create();
            WebResource webResource = client.resource("http://localhost:8081/rest_jersey_war_exploded/StockSort");
            MultivaluedMap formData = new MultivaluedMapImpl();
            formData.add("type", type);

            String s = webResource.queryParams(formData).get(String.class);
            ArrayList<Stock> list = (ArrayList<Stock>) JSONObject.parseArray(s, Stock.class);

            System.out.println("list size in servlet = " + list.size());

            session.setAttribute("list",list);
            count = list.size();

            request.removeAttribute("currentPage");
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("count", count);

            RequestDispatcher rd = request.getRequestDispatcher("stock.jsp");
            rd.forward(request, response);
        } catch (Throwable te) {
            te.printStackTrace();
        }
    }
}
