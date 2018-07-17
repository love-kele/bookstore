package com.nefu.shop.service.impl;

import com.nefu.shop.dao.UserMapper;
import com.nefu.shop.dao.impl.UserMapperImpl;
import com.nefu.shop.domain.po.User;
import com.nefu.shop.service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    @Override
    public int register(User user) throws SQLException {
        UserMapper userMapper = new UserMapperImpl();

        return userMapper.insertUser(user);
    }

    @Override
    public User login(String username, String password) throws SQLException {
        UserMapper userMapper = new UserMapperImpl();

        return userMapper.getUser(username,password);
    }

    @Override
    public int changeUser(User user) throws SQLException {
        UserMapper userMapper = new UserMapperImpl();

        return userMapper.updateUser(user);
    }
}
