package com.nefu.shop.service;

import com.nefu.shop.domain.po.Book;
import com.nefu.shop.domain.vo.BookList;
import com.nefu.shop.domain.vo.Cars;

import java.sql.SQLException;
import java.util.List;

public interface BookService {

    BookList queryAll(String category, int page) throws SQLException;

    Book queryById(String id) throws SQLException;

    Book addToCar(String id) throws SQLException;

    List<Cars> showMycart(int id) throws SQLException;

    BookList queryAllByName(String name,int page) throws SQLException;

    Book[] getBookByTime() throws SQLException;

    List<Book> showAllBook() throws SQLException;

    List<Book> searchBook(String id,String name ,String category ,Double lowprice,Double highprice) throws SQLException;

    int insertBook(Book book) throws SQLException;

    int delBook(String id) throws SQLException;
}
