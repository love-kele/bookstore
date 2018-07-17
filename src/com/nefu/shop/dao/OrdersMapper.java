package com.nefu.shop.dao;

import com.nefu.shop.domain.po.Order;
import com.nefu.shop.domain.po.OrderItem;
import com.nefu.shop.domain.vo.OrderInfo;

import java.sql.SQLException;
import java.util.List;

public interface OrdersMapper {

    List<Order> getOrders(Integer user_id,String id ,String rname) throws SQLException;

    OrderInfo getOrderInfo(String id) throws SQLException;

    int insertOrders(Order order , List<OrderItem> orderItemlist) throws SQLException;

    int deleteOrders(String order_id) throws SQLException;

    int pay(String order_id) throws SQLException;


}
