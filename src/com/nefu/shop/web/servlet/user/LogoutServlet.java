package com.nefu.shop.web.servlet.user;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getSession().removeAttribute("user");

        resp.setContentType("text/html;charset=UTF-8");
        // 要重定向的新位置
        String site = new String(req.getContextPath()+"/client/index.jsp");

        resp.setStatus(resp.SC_MOVED_TEMPORARILY);
        resp.setHeader("Location", site);
    }
}
