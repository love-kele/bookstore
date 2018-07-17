package com.nefu.shop.web.servlet.user;

import com.nefu.shop.domain.po.User;
import com.nefu.shop.service.UserService;
import com.nefu.shop.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        User user = new User();
        UserService userService = new UserServiceImpl();
        request.setCharacterEncoding("utf-8");

        try {
            BeanUtils.populate(user,request.getParameterMap());

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        user.setActiveCode(UUID.randomUUID().toString().toLowerCase());
        user.setState(1);
        user.setRegistTime(new Date());
        user.setRole("普通");

        try {
          int ans= userService.register(user);
          if(ans==1){
              response.setContentType("text/html;charset=UTF-8");

              // 要重定向的新位置
              String site = new String(request.getContextPath()+"/client/registersuccess.jsp");

              response.setStatus(response.SC_MOVED_TEMPORARILY);
              response.setHeader("Location", site);
          }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
