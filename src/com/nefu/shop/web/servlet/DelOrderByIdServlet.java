package com.nefu.shop.web.servlet;

import com.nefu.shop.service.OrdersService;
import com.nefu.shop.service.impl.OrdersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DelOrderByIdServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        String type = request.getParameter("type");

        OrdersService ordersService = new OrdersServiceImpl();

        try {
            int i = ordersService.DelOrders(id);

            if(i!=0){
                response.setContentType("text/html;charset=UTF-8");

                // 要重定向的新位置
                String site = new String(request.getContextPath() + "/client/delOrderSuccess.jsp");
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", site);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
