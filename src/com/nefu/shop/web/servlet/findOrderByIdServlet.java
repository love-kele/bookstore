package com.nefu.shop.web.servlet;

import com.nefu.shop.domain.vo.OrderInfo;
import com.nefu.shop.service.OrdersService;
import com.nefu.shop.service.impl.OrdersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "findOrderByIdServlet")
public class findOrderByIdServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        OrdersService ordersService = new OrdersServiceImpl();

        try {
            OrderInfo orderInfo = ordersService.showOrderInfo(id);

            request.getSession().setAttribute("car",orderInfo);

            response.setContentType("text/html;charset=UTF-8");

            // 要重定向的新位置
            String site = new String(request.getContextPath()+"/client/orderInfo.jsp");
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", site);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
