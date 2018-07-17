package com.nefu.shop.web.servlet.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ShowMyCartServlet")
public class ShowMyCartServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



            response.setContentType("text/html;charset=UTF-8");
            // 要重定向的新位置
            String site = new String(request.getContextPath() + "/client/cart.jsp");

            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", site);

    }
}
