package com.nefu.shop.web.servlet.admin;

import com.nefu.shop.domain.po.Book;
import com.nefu.shop.service.BookService;
import com.nefu.shop.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ListProductServlet")
public class ListProductServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BookService bookService = new BookServiceImpl();

        try {
            List<Book> books = bookService.showAllBook();
            request.getSession().setAttribute("ps",books);

            response.setContentType("text/html;charset=UTF-8");

            // 要重定向的新位置
            String site = new String(request.getContextPath()+"/admin/products/list.jsp");
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", site);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
