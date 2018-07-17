package com.nefu.shop.web.servlet;

import com.nefu.shop.domain.vo.Cars;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "ChangeCartServlet")
public class ChangeCartServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String count = request.getParameter("count");

        List<Cars> carsList = (List<Cars>) request.getSession().getAttribute("cart");

        Iterator<Cars> iterator = carsList.iterator();

        while (iterator.hasNext()){
            Cars cars = iterator.next();
            if(cars.getKey().getId().equals(id)){
                if(count.equals("0"))
                    iterator.remove();
                else
                    cars.setValue(Integer.parseInt(count));
            }

        }

//        carsnewlist.forEach(
//                v->{
//                    if(v.getKey().getId().equals(id)){
//                if(count.equals("0"))
//                   carsnewlist.remove(v);
//                else
//                v.setValue(Integer.parseInt(count));
//            }
//
//        });

        response.setContentType("text/html;charset=UTF-8");

        request.getSession().setAttribute("cart", carsList);
        // 要重定向的新位置
        String site = new String(request.getContextPath() + "/client/cart.jsp");
        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", site);

    }
}
