package com.nefu.shop.web.servlet.admin;

import sun.misc.BASE64Decoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.UUID;

@WebServlet(name = "UpdateImageServlet")
public class UpdateImageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String image = request.getParameter("image");
// 只允许jpg
        String header ="data:image/jpeg;base64,";
        if(image.indexOf(header) != 0){
            return;
        }
// 去掉头部
        image = image.substring(header.length());

// 写入磁盘
        boolean success = false;
        String path="";
        String imagename="";
        BASE64Decoder decoder = new BASE64Decoder();
        try{
            byte[] decodedBytes = decoder.decodeBuffer(image);
            imagename = UUID.randomUUID().toString().toLowerCase()+".jpg";
            String imgFileBasePath ="D://IDEA-wt/bookstore/web";
            SimpleDateFormat time=new SimpleDateFormat("MM/dd");
             path ="/productImg/"+ time.format(System.currentTimeMillis());
            System.out.println(path);

           String imgpath = imgFileBasePath+path+"/"+imagename;
            File file = new File(imgFileBasePath+path);
            file.mkdirs();
            FileOutputStream out = new FileOutputStream(imgpath);

            out.write(decodedBytes);
            out.close();

            String data = "{ data :"+request.getContextPath()+path+"/"+imagename+"}";
            response.getWriter().print(data);
            request.getSession().setAttribute("imgurl",request.getContextPath()+path+"/"+imagename);
        }catch(Exception e){
            e.printStackTrace();
        }
//        String data = "{ data :"+request.getContextPath()+path+"/"+imagename+"}";
//        response.getWriter().print(data);

    }

}

