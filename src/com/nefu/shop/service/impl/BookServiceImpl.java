package com.nefu.shop.service.impl;

import com.nefu.shop.dao.BookMapper;
import com.nefu.shop.dao.impl.BookMapperImpl;
import com.nefu.shop.domain.po.Book;
import com.nefu.shop.domain.vo.BookList;
import com.nefu.shop.domain.vo.Cars;
import com.nefu.shop.service.BookService;

import java.sql.SQLException;
import java.util.List;

public class BookServiceImpl implements BookService {

    @Override
    public BookList queryAll(String category, int page) throws SQLException {

        BookMapper bookMapper = new BookMapperImpl();


        return bookMapper.getBookList(category, page);
    }

    @Override
    public Book queryById(String id) throws SQLException {

        BookMapper bookMapper = new BookMapperImpl();

        return bookMapper.getBookbyId(id);
    }

    @Override
    public Book addToCar(String id) throws SQLException {
        BookMapper bookMapper = new BookMapperImpl();

        return bookMapper.getBookbyId(id);
    }

    @Override
    public List<Cars> showMycart(int  id) throws SQLException {
        BookMapper bookMapper = new BookMapperImpl();

        return bookMapper.getOrder(id);
    }

    @Override
    public BookList queryAllByName(String name,int page) throws SQLException {
        BookMapper bookMapper = new BookMapperImpl();

        return bookMapper.getBookListByName(name,page);
    }

    @Override
    public Book[] getBookByTime() throws SQLException {

        BookMapper bookMapper = new BookMapperImpl();
        List<Book> bookByTime = bookMapper.getBookByTime();
        int size = bookByTime.size();
        Book[] books = new Book[size];
        for (int i = 0; i <size ; i++) {
            books[i]=bookByTime.get(i);
        }

        return books;
    }

    @Override
    public List<Book> showAllBook() throws SQLException {
        BookMapper bookMapper = new BookMapperImpl();
        List<Book> allBook = bookMapper.getAllBook();

        return allBook;
    }

    @Override
    public List<Book> searchBook(String id, String name, String category, Double lowprice, Double highprice) throws SQLException {

        BookMapper bookMapper = new BookMapperImpl();

        return bookMapper.getBook(id,name,category,lowprice,highprice);
    }

    @Override
    public int insertBook(Book book) throws SQLException {

        BookMapper bookMapper = new BookMapperImpl();


        return bookMapper.insertBook(book);
    }

    @Override
    public int delBook(String id) throws SQLException {
        BookMapper bookMapper = new BookMapperImpl();
        return bookMapper.delBook(id);
    }
}
