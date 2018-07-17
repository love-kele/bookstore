package com.nefu.shop.web.servlet.book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MenuSearchSerlvet")
public class MenuSearchSerlvet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
             request.getRequestDispatcher("/searchByName").forward(request,response);
    }
}
