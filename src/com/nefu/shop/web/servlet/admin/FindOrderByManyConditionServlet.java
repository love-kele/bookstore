package com.nefu.shop.web.servlet.admin;

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

@WebServlet(name = "FindOrderByManyConditionServlet")
public class FindOrderByManyConditionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        String receiverName = request.getParameter("receiverName");

        OrdersService ordersService = new OrdersServiceImpl();

        try {
            List<Order> orders = ordersService.getOrders(null, id, receiverName);
            request.getSession().setAttribute("cars",orders);
            response.setContentType("text/html;charset=UTF-8");
            // 要重定向的新位置
            String site = new String(request.getContextPath()+"/admin/orders/list.jsp");

            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", site);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
