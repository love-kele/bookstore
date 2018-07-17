package com.nefu.shop.web.servlet;

import com.nefu.shop.domain.po.Book;
import com.nefu.shop.domain.vo.Car;
import com.nefu.shop.domain.vo.Cars;
import com.nefu.shop.service.BookService;
import com.nefu.shop.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddToCarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<Integer,Integer> map = new HashMap<>();
        map.put(1,0);
        String id = request.getParameter("id");
        BookService bookService = new BookServiceImpl();

        List<Cars> carsList= (List<Cars>) request.getSession().getAttribute("cart");

        List<Cars> carsnewlist = carsList==null||carsList.isEmpty() ? new ArrayList<>() :carsList;

        carsnewlist.forEach(v->{
            if(v.getKey().getId().equals(id)){
                v.setValue(v.getValue()+1);
                   map.put(1,1);

            }

        });
         if(map.get(1)!=1) {
             Car car = new Car();
             try {
                 Book book = bookService.queryById(id);

                 Cars cars = new Cars();
                 car.setBuynum(1);

                 car.setName(book.getName());
                 car.setPnum(book.getPnum());
                 car.setPrice(book.getPrice());
                 car.setCategory(book.getCategory());
                 car.setId(book.getId());

                 cars.setKey(car);
                 cars.setValue(1);

                 carsnewlist.add(cars);

                 response.setContentType("text/html;charset=UTF-8");

                 request.getSession().setAttribute("cart", carsnewlist);
                 // 要重定向的新位置
                 String site = new String(request.getContextPath() + "/client/cart.jsp");
                 response.setStatus(response.SC_MOVED_TEMPORARILY);
                 response.setHeader("Location", site);
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         }else {
             response.setContentType("text/html;charset=UTF-8");

             request.getSession().setAttribute("cart", carsnewlist);
             // 要重定向的新位置
             String site = new String(request.getContextPath() + "/client/cart.jsp");
             response.setStatus(response.SC_MOVED_TEMPORARILY);
             response.setHeader("Location", site);
         }
    }
}
