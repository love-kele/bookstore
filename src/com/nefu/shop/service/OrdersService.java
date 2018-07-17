package com.nefu.shop.service;

import com.nefu.shop.domain.po.Order;
import com.nefu.shop.domain.po.OrderItem;
import com.nefu.shop.domain.vo.OrderInfo;

import java.sql.SQLException;
import java.util.List;

public interface OrdersService {

    List<Order> getOrders(Integer user_id,String id ,String rname) throws SQLException;

    OrderInfo showOrderInfo(String id) throws SQLException;

    int CreateOrders(Order order ,List<OrderItem> orderItem) throws SQLException;

    int DelOrders(String order_id) throws SQLException;

    int PayOrder(String order_id) throws SQLException;
}
