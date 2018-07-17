package com.nefu.shop.web.servlet.admin;

import com.nefu.shop.domain.po.Book;
import com.nefu.shop.service.BookService;
import com.nefu.shop.service.impl.BookServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@WebServlet(name = "AddProductServlet")
public class AddProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        BookService bookService = new BookServiceImpl();
        Book book = new Book();
        try {


            BeanUtils.populate(book, request.getParameterMap());


            book.setId(UUID.randomUUID().toString().toLowerCase());
            book.setSaleTime(new Date());
            int i = bookService.insertBook(book);
            if(i==1){
                response.setContentType("text/html;charset=UTF-8");

                // 要重定向的新位置
                String site = new String(request.getContextPath()+"/listProduct");
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", site);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
