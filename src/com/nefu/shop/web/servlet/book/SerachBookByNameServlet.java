package com.nefu.shop.web.servlet.book;

import com.nefu.shop.domain.vo.BookList;
import com.nefu.shop.service.BookService;
import com.nefu.shop.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "SerachBookByNameServlet")
public class SerachBookByNameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String name = req.getParameter("textfield");
        String page = req.getParameter("page");
        name=new String(name.getBytes("ISO-8859-1"),"utf-8");
        System.out.println(name);
        page=page==null||page.isEmpty() ? "1" : page;

        BookService bookService = new BookServiceImpl();

        try {
            BookList bookList = bookService.queryAllByName(name, Integer.parseInt(page));
            req.getSession().setAttribute("bean",bookList);

            resp.setContentType("text/html;charset=UTF-8");

            // 要重定向的新位置
            String site = new String(req.getContextPath()+"/client/product_search_list.jsp");
            resp.setStatus(resp.SC_MOVED_TEMPORARILY);
            resp.setHeader("Location", site);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
