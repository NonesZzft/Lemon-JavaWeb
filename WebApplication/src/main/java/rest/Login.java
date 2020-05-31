package main.java.rest;
import com.sun.net.httpserver.HttpServer;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import main.java.auth.auth;
import main.java.dao.ConnectMySql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/Login")
public class Login {
    private ResultSet rs;
    private PreparedStatement pstmt;
    private Connection LoginServletConn;
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getClichedMessage(@QueryParam("userName") String userName, @QueryParam("userPassword") String userPassword) {
        // Return some cliched textual content
        try {
            System.out.println(this.getClass() + ".service() called ...");
            System.out.println(userName);
            System.out.println(userPassword);
            LoginServletConn = ConnectMySql.getConn();
            pstmt = LoginServletConn.prepareStatement("select name, password from user where name = ? and password = ?");
            pstmt.setString(1, userName);
            pstmt.setString(2, userPassword);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                auth a = new auth();
                return a.createJWT(userName, userPassword);
            }
            else {
                return "False";
            }

        } catch (Exception e) {
            System.out.println("exception" + e.getLocalizedMessage());
            return "False";
        }
    }
}
