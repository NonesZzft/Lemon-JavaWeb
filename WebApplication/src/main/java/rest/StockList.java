package main.java.rest;

import com.alibaba.fastjson.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import main.java.auth.auth;
import main.java.bean.Stock;

import javax.ws.rs.*;
import javax.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;

@Path("/StockList")
public class StockList {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
//    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public ArrayList<Stock> getClichedMessage(@QueryParam("type") String type, @QueryParam("info") String info, @QueryParam("token") String token) throws Exception {

        try {
            System.out.println(this.getClass() + ".service() called ...");


            Client client = Client.create();
            WebResource webResource = client.resource("http://localhost:8081/rest_jersey_war_exploded/StockList");
            MultivaluedMap formData = new MultivaluedMapImpl();
            formData.add("type", type);
            formData.add("info", info);

            String s = webResource.queryParams(formData).get(String.class);
            ArrayList<Stock> list = (ArrayList<Stock>) JSONObject.parseArray(s, Stock.class);


            System.out.println("list size in servlet = " + list.size());

            auth a = new auth();
            String ans = a.parseJWT(token);
            ArrayList<Stock> empty = new ArrayList<Stock>();
            if(ans.equals("True")) {
                return list;
            }
            return empty;
        } catch (Throwable te) {
            ArrayList<Stock> list = new ArrayList<Stock>();
            te.printStackTrace();
            return list;
        }
    }
}
