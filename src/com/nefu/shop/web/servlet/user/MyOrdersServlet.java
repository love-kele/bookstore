package com.nefu.shop.web.servlet.user;

import com.nefu.shop.domain.po.Order;
import com.nefu.shop.service.OrdersService;
import com.nefu.shop.service.impl.OrdersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "MyOrdersServlet")
public class MyOrdersServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_id = request.getParameter("user_id");
        OrdersService ordersService = new OrdersServiceImpl();

        try {
            List<Order> orders = ordersService.getOrders(Integer.parseInt(user_id),null,null);
            request.getSession().setAttribute("cars",orders);

            response.setContentType("text/html;charset=UTF-8");
            // 要重定向的新位置
            String site = new String(request.getContextPath()+"/client/orderlist.jsp");

            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", site);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
