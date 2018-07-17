package com.nefu.shop.web.servlet;

import com.nefu.shop.domain.po.Order;
import com.nefu.shop.domain.po.OrderItem;
import com.nefu.shop.domain.po.User;
import com.nefu.shop.domain.vo.Cars;
import com.nefu.shop.service.OrdersService;
import com.nefu.shop.service.impl.OrdersServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "CreateOrderServlet")
public class CreateOrderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        OrdersService ordersService = new OrdersServiceImpl();
           //获取购物车中的商品
        List<Cars> carsList = (List<Cars>) request.getSession().getAttribute("cart");

          //获取用户信息
        User user= (User) request.getSession().getAttribute("user");


        //获取表单信息创建订单
        Order order = new Order();
        try {
            BeanUtils.populate(order,request.getParameterMap());
           order.setId(UUID.randomUUID().toString().toLowerCase());
           order.setOrdertime(new Date());
           order.setPaystate(0);
           order.setUser_id(user.getId());

            List<OrderItem> orderItems = new ArrayList<>();
            carsList.forEach(v->{
                OrderItem orderItem = new OrderItem();
                orderItem.setBuynum(v.getValue());
                orderItem.setOrder_id(order.getId());
                orderItem.setProduct_id(v.getKey().getId());
                orderItems.add(orderItem);
            });

            int ans = ordersService.CreateOrders(order, orderItems);
            if(ans==carsList.size()+1){
                 //创建订单成功后清空购物车

                request.getSession().removeAttribute("cart");
                response.setContentType("text/html;charset=UTF-8");
                // 要重定向的新位置
                String site = new String(request.getContextPath() + "/client/createOrderSuccess.jsp");
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", site);
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {

            e.printStackTrace();
        }


    }


}
