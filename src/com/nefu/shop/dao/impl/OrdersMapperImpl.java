package com.nefu.shop.dao.impl;

import com.nefu.shop.dao.OrdersMapper;
import com.nefu.shop.domain.po.Order;
import com.nefu.shop.domain.po.OrderItem;
import com.nefu.shop.domain.vo.Car;
import com.nefu.shop.domain.vo.OrderInfo;
import com.nefu.shop.utils.Base;
import com.nefu.shop.utils.JDBCUtils;
import com.nefu.shop.utils.StringUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersMapperImpl implements OrdersMapper {
    /**
     * 查看个人订单
     *
     * @param user_id
     * @return
     * @throws SQLException
     */
    @Override
    public List<Order> getOrders(Integer user_id ,String id,String rname) throws SQLException {

        String sql="select * from orders ";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        List<Order> orders;
        System.out.println(user_id);

        if(user_id!=null) {
            sql=sql+"where user_id=?";
            orders = qr.query(sql,new BeanListHandler<Order>(Order.class),user_id);
        }else {
            sql=sql+"where 1=1";
            if (!StringUtils.isEmpty(id)) {
                sql = sql + " and id like "+" '%"+id+"%'";

            } else if (!StringUtils.isEmpty(rname) ) {

                sql = sql + " and receiverName like"+" '%"+rname+"%' ";
            }
            System.out.println(sql);

            orders = qr.query(sql, new BeanListHandler<Order>(Order.class));

        }
            return orders;
    }

    /**
     * 查看订单详细
     *
     * @param id
     * @return
     * @throws SQLException
     */

    @Override
    public OrderInfo getOrderInfo(String id) throws SQLException {

        String sql1 = "select * from orders where id=?";
        String sql2 = "select p.name ,p.price ,ot.buynum from products p,orderitem ot where ot.order_id=? and p.id=ot.product_id";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        OrderInfo orderInfo = new OrderInfo();

        Order order = qr.query(sql1, new BeanHandler<Order>(Order.class), id);
        orderInfo.setId(order.getId());
        orderInfo.setReceiverAddress(order.getReceiverAddress());
        orderInfo.setReceiverName(order.getReceiverName());
        orderInfo.setPaystate(order.getPaystate());
        orderInfo.setReceiverPhone(order.getReceiverPhone());
        orderInfo.setMoney(order.getMoney());
        List<OrderItem> list = new ArrayList<>();
        List<Car> cars = qr.query(sql2, new BeanListHandler<Car>(Car.class), id);
        cars.forEach(v -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setBuynum(v.getBuynum());

            orderItem.setP(v.getName(), v.getPrice());
            list.add(orderItem);
        });

        orderInfo.setOrderItems(list);
        return orderInfo;
    }

    /**
     * 创建订单
     *
     * @param order
     * @param orderItemList
     * @return
     * @throws SQLException
     */
    @Override
    public int insertOrders(Order order, List<OrderItem> orderItemList) throws SQLException {

        String sql1 = "insert into orders values(?,?,?,?,?,?,?,?)";


        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

        int update = qr.update(sql1, Base.setParams(order));
        int num = insertOrderItem(orderItemList);

        return update + num;
    }

    /**
     * 删除订单
     *
     * @param order_id
     * @return
     * @throws SQLException
     */
    @Override
    public int deleteOrders(String order_id) throws SQLException {

        String sql = "delete from orders where id=?";

        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

        int update = qr.update(sql, order_id);
        int i = deleteOrderItem(order_id);

        return i + update;
    }

    /**
     * 支付
     * @param order_id
     * @return
     * @throws SQLException
     */
    @Override
    public int pay(String order_id) throws SQLException {

        String sql="UPDATE orders SET paystate=1 WHERE id=?";

        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

        int update = qr.update(sql, order_id);

        return update;
    }

    /**
     * 创建订单和商品的联系
     *
     * @param items
     * @return
     * @throws SQLException
     */
    public int insertOrderItem(List<OrderItem> items) throws SQLException {

        String sql = "insert into orderitem values(?,?,?)";
        int count = 0;
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        for (OrderItem ot : items) {
            int update = qr.update(sql, ot.getOrder_id(), ot.getProduct_id(), ot.getBuynum());
            count += update;
        }

        return count;
    }

    /**
     * 删除订单与商品联系
     * @param order_id
     * @return
     * @throws SQLException
     */
    public int deleteOrderItem(String order_id) throws SQLException {
        String sql = "DELETE FROM orderitem WHERE order_id=?";

        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

        int update = qr.update(sql, order_id);

        return update;
    }

}
