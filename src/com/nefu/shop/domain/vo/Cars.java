package com.nefu.shop.domain.vo;

import com.nefu.shop.domain.vo.Car;

/**
 * 购物车的数据结构
 */
public class Cars {

    private Car key=new Car();

    private int value;

    public Car getKey() {
        return key;
    }

    public void setKey(Car car) {
        this.key.setName(car.getName());
        this.key.setBuynum(car.getBuynum());
        this.key.setPrice(car.getPrice());
        this.key.setPnum(car.getPnum());
        this.key.setId(car.getId());
        this.key.setCategory(car.getCategory());
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
