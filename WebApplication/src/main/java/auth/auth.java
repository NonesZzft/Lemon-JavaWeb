package main.java.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import main.java.dao.ConnectMySql;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class auth {
    private ResultSet rs;
    private PreparedStatement pstmt;
    private Connection LoginServletConn;
    public String parseJWT(String token) {
        try {
            LoginServletConn = ConnectMySql.getConn();
            pstmt = LoginServletConn.prepareStatement("select name, password from user ");
            rs = pstmt.executeQuery();
            String name, password;
            while(rs.next()) {
                name = rs.getString("name");
                password = rs.getString("password");
                if(token.equals(name+password)) {
                    return "True";
                }
            }
            return "False";
        } catch (Exception ex) {
            return "False";
        }
    }

    public String createJWT(String name, String password) {
        return name+password;
    }
}
