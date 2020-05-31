package main.java.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/BankTransferAssistServlet")
public class BankTransferAssistServlet extends HttpServlet {
    String accounting;
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println(this.getClass() + ".service() called ...");
            accounting = request.getParameter("accounting");
            System.out.println("accounting="+accounting);
            request.removeAttribute("accounting");
            request.setAttribute("accounting", accounting);
            RequestDispatcher rd = request.getRequestDispatcher("bank_transfer.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            request.getSession().setAttribute("error", e.getMessage());
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }
    }
}