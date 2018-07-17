package com.nefu.shop.web.servlet.user;

import com.nefu.shop.domain.po.User;
import com.nefu.shop.service.UserService;
import com.nefu.shop.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ChangeUserServlet")
public class ChangeUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");//一定要放在可能出现乱码的前面

        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String text2 = request.getParameter("text2");
        String gender = request.getParameter("gender");
        //   gender=new String(gender.getBytes("ISO-8859-1"),"utf-8");
        UserService userService = new UserServiceImpl();
        User user = new User();
        user.setId(Integer.parseInt(id));
        user.setPassword(password);
        user.setTelephone(text2);
        user.setGender(gender);
        try {
            int i = userService.changeUser(user);
            if(i!=0){
                response.setContentType("text/html;charset=UTF-8");

                // 要重定向的新位置
                String site = new String(request.getContextPath()+"/client/modifysuccess.jsp");
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", site);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
