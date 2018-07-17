package com.nefu.shop.web.servlet.admin;

import com.nefu.shop.domain.po.Book;
import com.nefu.shop.service.BookService;
import com.nefu.shop.service.impl.BookServiceImpl;
import com.nefu.shop.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "FindProductByManyConditionServlet")
public class FindProductByManyConditionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        String category = request.getParameter("category");
        String name = request.getParameter("name");
        String minprice = request.getParameter("minprice");
        String maxprice = request.getParameter("maxprice");
        System.out.println(category);
      Double  minprices = StringUtils.isEmpty(minprice)? null :Double.parseDouble(minprice);
      Double maxprices = StringUtils.isEmpty(maxprice)? null : Double.parseDouble(maxprice);
        if(category.equals("选择商品类别"))
            category=null;

        BookService bookService = new BookServiceImpl();
        try {
            List<Book> books = bookService.searchBook(id, name, category, minprices, maxprices);
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
