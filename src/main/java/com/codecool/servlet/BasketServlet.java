package com.codecool.servlet;

import com.codecool.servlet.store.ItemStore;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="basket", urlPatterns = {"basket"}, loadOnStartup = 1)
public class BasketServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException {

        PrintWriter out = response.getWriter();
        StringBuffer buffer = new StringBuffer();
        String title = "GET method with parameters to display";

        for(int i = 0; i< ItemStore.basket.size(); i++){
            buffer.append("<tr><td>"+ItemStore.basket.get(i).getId()+"</td><td>"+ItemStore.store.get(i).getName()
                    +"</td><td>"+ItemStore.basket.get(i).getPrice()
                    +"</td><td><a href=\"/basket?item="+ItemStore.basket.get(i).getId()+"&remove="+false+"\">" +
                    "Buy</a></td>" +
                    "<td><a href=\"/basket?item="+ItemStore.basket.get(i).getId()+"&remove="+true+"\">Remove</a></td>");
        }

        try {
            out.println("<!DOCTYPE html>");
            out.println("<html><head>");
            out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
            out.println("<title>"+title+"</title></head>");
            out.println("<body>");
            out.println("<h1>Basket</h1>");
            out.print("Basket Item" + ItemStore.basket.size());
            out.println("<table><th><td>ID</td><td>NAME</td><td>PRIZE</td><td>Buy</td><td>Remove</td></th>");
            out.println(buffer);
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();  // Always close the output writer
        }
    }
}
