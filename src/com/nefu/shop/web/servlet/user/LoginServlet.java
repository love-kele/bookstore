package com.nefu.shop.web.servlet.user;

import com.nefu.shop.domain.po.User;
import com.nefu.shop.service.UserService;
import com.nefu.shop.service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = new UserServiceImpl();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println(username);
        try {
            username=new String(username.getBytes("ISO-8859-1"),"utf-8");
            System.out.println(username);
            User login = userService.login(username, password);
            if (login != null) {
                if(login.getId()!=1) {
                    request.getSession().setAttribute("user", login);
                    response.setContentType("text/html;charset=UTF-8");
                    // 要重定向的新位置
                    String site = new String(request.getContextPath() + "/client/myAccount.jsp");

                    response.setStatus(response.SC_MOVED_TEMPORARILY);
                    response.setHeader("Location", site);
                }else {
                    response.setContentType("text/html;charset=UTF-8");
                    // 要重定向的新位置
                    String site = new String(request.getContextPath() + "/admin/login/home.jsp");

                    response.setStatus(response.SC_MOVED_TEMPORARILY);
                    response.setHeader("Location", site);
                }
            } else {

                request.getSession().setAttribute("error", "用户名或密码错误");
                response.setContentType("text/html;charset=UTF-8");
                // 要重定向的新位置
                String site = new String(request.getContextPath()+"/client/login.jsp");

                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", site);
            }
        } catch (SQLException e) {

            e.printStackTrace();
            request.getSession().setAttribute("error", "网络连接失败请稍后再试");
            response.setContentType("text/html;charset=UTF-8");
            // 要重定向的新位置
            String site = new String(request.getContextPath()+"/client/login.jsp");

            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", site);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }


}
