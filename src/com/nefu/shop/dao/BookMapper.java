package com.nefu.shop.dao;


import com.nefu.shop.domain.po.Book;
import com.nefu.shop.domain.vo.BookList;
import com.nefu.shop.domain.vo.Cars;

import java.sql.SQLException;
import java.util.List;

public interface BookMapper {

    BookList getBookList(String category, int page) throws SQLException;

    int getCount() throws SQLException;

    Book getBookbyId(String id) throws SQLException;

    List<Cars> getOrder(int userid) throws SQLException;

    BookList getBookListByName(String name,int page) throws SQLException;

    List<Book> getBookByTime() throws SQLException;

    List<Book> getAllBook() throws SQLException;

    List<Book> getBook(String id,String name ,String category ,Double lowprice,Double highprice ) throws SQLException;

    int insertBook(Book book) throws SQLException;

    int delBook(String id) throws SQLException;
}
