package com.nefu.shop.service.impl;

import com.nefu.shop.dao.OrdersMapper;
import com.nefu.shop.dao.impl.OrdersMapperImpl;
import com.nefu.shop.domain.po.Order;
import com.nefu.shop.domain.po.OrderItem;
import com.nefu.shop.domain.vo.OrderInfo;
import com.nefu.shop.service.OrdersService;

import java.sql.SQLException;
import java.util.List;

public class OrdersServiceImpl implements OrdersService {
    @Override
    public List<Order> getOrders(Integer user_id,String id,String rname) throws SQLException {
        OrdersMapper ordersMapper= new OrdersMapperImpl();

        return ordersMapper.getOrders(user_id,id,rname);
    }

    @Override
    public OrderInfo showOrderInfo(String id) throws SQLException {
        OrdersMapper ordersMapper= new OrdersMapperImpl();

        return ordersMapper.getOrderInfo(id);
    }

    @Override
    public int CreateOrders(Order order, List<OrderItem> orderItem) throws SQLException {
        OrdersMapper ordersMapper= new OrdersMapperImpl();


        return ordersMapper.insertOrders(order,orderItem);
    }

    @Override
    public int DelOrders(String order_id) throws SQLException {
        OrdersMapper ordersMapper= new OrdersMapperImpl();

        return ordersMapper.deleteOrders(order_id);
    }

    @Override
    public int PayOrder(String order_id) throws SQLException {
        OrdersMapper ordersMapper= new OrdersMapperImpl();

        return ordersMapper.pay(order_id);
    }
}
