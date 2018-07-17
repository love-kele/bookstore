package com.nefu.shop.domain.po;

import com.nefu.shop.domain.po.Book;

/**
 * 修改过的OrderItem表实体
 */
public class OrderItem {

    private int buynum;

    private Book p = new Book();

    private String order_id;

    private String product_id;

    public int getBuynum() {
        return buynum;
    }

    public void setBuynum(int buynum) {
        this.buynum = buynum;
    }

    public Book getP() {
        return p;
    }

    public void setP(String name, double price) {
        this.p.setName(name);
        this.p.setPrice(price);
    }


    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
}
