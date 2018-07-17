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

@WebServlet(name = "SearchBookServlet")
public class SearchBookServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       BookService bookService = new BookServiceImpl();
        String page = request.getParameter("page");
        String category = request.getParameter("category");
        //category = new String("全部商品".getBytes("ISO-8859-1"),"utf-8");
        category = category==null? new String("全部商品"):new String(category.getBytes("ISO-8859-1"),"utf-8");
        BookList bookList=null;
        if(page==null)
            page="1";

        try {
          bookList=  bookService.queryAll(category,Integer.parseInt(page));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=UTF-8");

        // 要重定向的新位置
        String site = new String(request.getContextPath()+"client/product_list.jsp");
        Object bean = request.getSession().getAttribute("bean");


        if (bean == null) {
            request.getSession().setAttribute("bean", bookList);
        } else {
            request.getSession().removeAttribute("bean");
            request.getSession().setAttribute("bean", bookList);
        }

        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", site);
    }
}
