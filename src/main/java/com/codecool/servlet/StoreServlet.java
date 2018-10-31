package com.codecool.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.codecool.servlet.store.Item;
import com.codecool.servlet.store.ItemStore;

@WebServlet(name="store", urlPatterns = {"/store"}, loadOnStartup = 1)
public class StoreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        PrintWriter out = response.getWriter();
        StringBuffer buffer = new StringBuffer();

        ItemStore.store.clear();
        Item cola = new Item("Cola",2.3);
        Item fanta = new Item("Fanta",2.3);
        Item sprite = new Item("Sprite",2.3);
        Item Milk = new Item("Milk",3);
        Item MacBook = new Item("MacBook",800);

        String item = request.getParameter("item");
        boolean remove = Boolean.getBoolean(request.getParameter("remove"));

        if(item!=null){
            if(remove){
                if(ItemStore.store.size()>0){
                    ItemStore.basket.remove(ItemStore.store.get(Integer.parseInt(item)));
                } else{
                    System.out.println("Error index");
                }
            } else{
                    ItemStore.basket.add(ItemStore.store.get(Integer.parseInt(item)));
                }
        }

        for(int i=0; i<ItemStore.store.size(); i++){
            buffer.append("<tr><td>"+ItemStore.store.get(i).getId()+"</td><td>"+ItemStore.store.get(i).getName()
                    +"</td><td>"+ItemStore.store.get(i).getPrice()
                    +"</td><td><a href=\"/store?item="+ItemStore.store.get(i).getId()+"&remove="+false+"\">" +
                    "Buy</a></td>" +
                    "<td><a href=\"/store?item="+ItemStore.store.get(i).getId()+"&remove="+true+"\">Remove</a></td>");
        }

        String title = "Servlet Store";

        try {
            out.println("<!DOCTYPE html>");
            out.println("<html><head>");
            out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
            out.println("<title>"+title+"</title></head>");
            out.println("<body>");
            out.println("<h1>Store</h1>");
            out.print("Basket Item" + ItemStore.basket.size());// says Hello
            out.println("<form method=\"get\" action=\"/store\">");
            out.println("<table><th><td>ID</td><td>NAME</td><td>PRIZE</td><td>Buy</td><td>Remove</td></th>");
            out.println(buffer);
            out.println("</table></form>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();  // Always close the output writer
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id =Integer.parseInt(request.getParameter("value"));
        ItemStore.basket.add(ItemStore.store.get(id));
        doGet(request,response);
    }
}
