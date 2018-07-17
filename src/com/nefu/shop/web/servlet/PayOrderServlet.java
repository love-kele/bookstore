package com.nefu.shop.web.servlet;

import com.nefu.shop.service.OrdersService;
import com.nefu.shop.service.impl.OrdersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "PayOrderServlet")
public class PayOrderServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        OrdersService ordersService = new OrdersServiceImpl();

        try {
            int i = ordersService.PayOrder(id);
            if(i==1){
                response.setContentType("text/html;charset=UTF-8");
                // 要重定向的新位置
                String site = new String(request.getContextPath()+"/client/paysuccess.jsp");

                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", site);


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
