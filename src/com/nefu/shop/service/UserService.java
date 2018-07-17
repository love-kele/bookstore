package com.nefu.shop.service;

import com.nefu.shop.domain.po.User;

import java.sql.SQLException;

public interface UserService {

   int register(User user) throws SQLException;

   User login(String username,String password) throws SQLException;

   int changeUser(User user) throws SQLException;
}
