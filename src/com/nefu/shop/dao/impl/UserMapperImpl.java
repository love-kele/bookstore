package com.nefu.shop.dao.impl;

import java.sql.SQLException;

import com.nefu.shop.domain.po.User;
import com.nefu.shop.utils.Base;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.util.List;

import com.nefu.shop.dao.UserMapper;
import com.nefu.shop.utils.JDBCUtils;

public class UserMapperImpl implements UserMapper {


    /**
     * 根据用户名和密码获取一个user
     * @param username  用户名
     * @param password  密码
     * @return
     * @throws SQLException
     */
    public User getUser(String username, String password) throws SQLException {


        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        User user = new User();
        String sql = "Select * from user where username=? and password=?";
        List<User> list = qr.query(sql, new BeanListHandler<User>(User.class),username,password);

        if (!list.isEmpty())
            return list.get(0);
        else
            return null;
    }

    /**
     *  添加一个用户
     * @param user
     * @return
     * @throws SQLException 添加失败
     */
    public int insertUser(User user) throws SQLException {

        Connection con = JDBCUtils.getConnection();

        QueryRunner qr = new QueryRunner();
        String sql = "insert into user (username,password,gender,email,telephone,introduce,activeCode,state,role,registTime) values(?,?,?,?,?,?,?,?,?,?)";

        int ans = qr.update(con, sql, Base.setUserParams(user));

        return ans;
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @Override
    public int updateUser(User user) throws SQLException {
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
          String  sql ="update user set password=? , gender=? , telephone=? where id=?";

        int update = qr.update(sql, user.getPassword(), user.getGender(), user.getTelephone(), user.getId());

        return update;
    }

}
