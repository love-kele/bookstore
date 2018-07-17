package com.nefu.shop.web.servlet.book;

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

@WebServlet(name = "ShowNewBookServlet")
public class ShowNewBookServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService bookService = new BookServiceImpl();

        try {
            Book[] books = bookService.getBookByTime();
            System.out.println(books[0].getName());
            request.getSession().setAttribute("books",books);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
