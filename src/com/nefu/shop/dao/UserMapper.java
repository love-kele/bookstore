package com.nefu.shop.dao;

import com.nefu.shop.domain.po.User;

import java.sql.SQLException;

public interface UserMapper {

	User getUser(String username, String password) throws SQLException;
      
	int insertUser(User user) throws SQLException;

   int updateUser(User user) throws SQLException;

}
