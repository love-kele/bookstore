package com.nefu.shop.dao.impl;

import com.nefu.shop.dao.BookMapper;
import com.nefu.shop.domain.po.Book;
import com.nefu.shop.domain.vo.BookList;
import com.nefu.shop.domain.vo.Cars;
import com.nefu.shop.utils.Base;
import com.nefu.shop.utils.JDBCUtils;
import com.nefu.shop.utils.StringUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class BookMapperImpl implements BookMapper {

    /**
     * 分类查询全部图书分页
     * @param category
     * @param page
     * @return
     * @throws SQLException
     */
    @Override
    public BookList getBookList(String category, int page) throws SQLException {

        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

        String sql1 = "select * from products limit ?,?";
        String sql2 = "select * from products where category=? limit ?,?";

        int start = 4 * (page - 1);

        if (category.equals("全部商品")) {

            int num = getCount();
            int ToalPage = 0;
            ToalPage = (num % 4 != 0) ? num / 4 + 1 : num / 4;

            List<Book> books = qr.query(sql1, new BeanListHandler<Book>(Book.class), start, 4);


            BookList bookList = new BookList();
            bookList.setPs(books);
            bookList.setTotalCount(num);
            bookList.setCategory(category);
            bookList.setCurrentPage(page);
            bookList.setTotalPage(ToalPage);

            return bookList;

        } else {
            int num = getCount(category);
            int ToalPage = 0;
            ToalPage = (num % 4 != 0) ? num / 4 + 1 : num / 4;

            List<Book> books = qr.query(sql2, new BeanListHandler<Book>(Book.class), category, start, 4);

            BookList bookList = new BookList();
            bookList.setPs(books);
            bookList.setTotalCount(num);
            bookList.setCategory(category);
            bookList.setCurrentPage(page);
            bookList.setTotalPage(ToalPage);

            return bookList;
        }
    }

    /**
     * 取所有图书数量
     * @return
     * @throws SQLException
     */
    @Override
    public int getCount() throws SQLException {

        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

        String sql = "select count(*) from products";

        Object query = qr.query(sql, new ScalarHandler());

        return Integer.parseInt(query.toString());
    }

    /**
     * 通过id查询图书
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public Book getBookbyId(String id) throws SQLException {

        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

        String sql = "select * from products where id=?";

        Book book = qr.query(sql, new BeanHandler<Book>(Book.class), id);

        if (book != null)
            return book;
        else
            return null;
    }

    /**
     * 通过用户id获取购物车信息
     * @param userid
     * @return
     * @throws SQLException
     */
    @Override
    public List<Cars> getOrder(int userid) throws SQLException {

//        String sql ="select  p.name ,p.price , ot.buynum , p.pnum  from products p ,orderitem ot ,orders o where o.user_id=?  and ot.order_id=o.id and p.id=ot.product_id and o.paystate=0";
//
//        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
//        List<Car> cars = qr.query(sql, new BeanListHandler<Car>(Car.class),userid);
//        List<Cars> list = new ArrayList<>();
//        for (Car o: cars)
//             {
//                 Cars cart = new Cars();
//                 o.setId(userid);
//                 cart.setKey(o);
//                 cart.setValue(o.getBuynum());
//                 list.add(cart);
//
//             }
//
//
        return null;
    }

    /**
     * 通过图书名模糊查询分页
     * @param name
     * @param page
     * @return
     * @throws SQLException
     */
    @Override
    public BookList getBookListByName(String name, int page) throws SQLException {

        String sql = "select * from products where name like ? limit ?,?";

        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        int start = 4 * (page - 1);
        int num = getCountName(name);
        int ToalPage = 0;
        ToalPage = (num % 4 != 0) ? num / 4 + 1 : num / 4;

        List<Book> books = qr.query(sql, new BeanListHandler<Book>(Book.class), "%" + name + "%", start, 4);

        BookList bookList = new BookList();
        bookList.setPs(books);
        bookList.setTotalCount(num);
        bookList.setSearchfield(name);
        bookList.setCurrentPage(page);
        bookList.setTotalPage(ToalPage);

        return bookList;
    }

    /**
     * 查询最新上架图书
     * @return
     * @throws SQLException
     */
    @Override
    public List<Book> getBookByTime() throws SQLException {
        String sql = "select * from products order by saleTime desc limit 0 , 2";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

        List<Book> query = qr.query(sql, new BeanListHandler<Book>(Book.class));

        return query;
    }

    /**
     * 获取所有图书不分页（后台）
     * @return
     * @throws SQLException
     */
    @Override
    public List<Book> getAllBook() throws SQLException {

        String sql = "select * from products ";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        List<Book> books = qr.query(sql, new BeanListHandler<Book>(Book.class));

        return books;
    }

    /**
     * 根据多种条件模糊查询图书 不分页（后台）
     * @param id
     * @param name
     * @param category
     * @param lowprice
     * @param highprice
     * @return
     * @throws SQLException
     */
    @Override
    public List<Book> getBook(String id, String name, String category, Double lowprice, Double highprice) throws SQLException {

        String sql = "select * from products where 1=1";

        if (!StringUtils.isEmpty(id)) {
            sql = sql + " and id like " + "'%" + id + "%'";
        }
        if (!StringUtils.isEmpty(name)) {
            sql = sql + " and name like " + "'%" + name + "%'";
        }
        if (!StringUtils.isEmpty(category)) {
            sql = sql + " and category like " + "'%" + category + "%'";
        }
        if (lowprice != null && highprice != null) {
            sql = sql + " and price > " + lowprice + " and price < " + highprice;
        }
        if (lowprice != null && highprice == null) {
            sql = sql + " and price >" + lowprice;
        }
        if (lowprice == null && highprice != null) {
            sql = sql + " and price <" + highprice;
        }
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

        System.out.println(sql);
        List<Book> books = qr.query(sql, new BeanListHandler<Book>(Book.class));

        return books;
    }

    /**
     * 添加图书（后台）
     * @param book
     * @return
     * @throws SQLException
     */
    @Override
    public int insertBook(Book book) throws SQLException {
        String sql = "insert into products values (?,?,?,?,?,?,?,?)";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

        int update = qr.update(sql, Base.setParams(book));

        return update;
    }

    /**
     * 删除图书（后台）
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public int delBook(String id) throws SQLException {
        String sql = "delete from products where id=?";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

        int update = qr.update(sql, id);
        return update;
    }

    /**
     * 获取某类图书数量
     * @param category
     * @return
     * @throws SQLException
     */
    public int getCount(String category) throws SQLException {

        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

        String sql = "select count(*) from products where category=?";

        Object query = qr.query(sql, new ScalarHandler(), category);

        return Integer.parseInt(query.toString());
    }

    /**
     * 获取某种图书数量
     * @param name
     * @return
     * @throws SQLException
     */
    public int getCountName(String name) throws SQLException {
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

        String sql = "select Count(*) from products where name like ? ";

        Object query = qr.query(sql, new ScalarHandler(), "%" + name + "%");

        return Integer.parseInt(query.toString());
    }

}
