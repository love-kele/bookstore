package com.nefu.shop.domain.vo;

import com.nefu.shop.domain.po.Book;

import java.util.List;

/**
 * 实现分页的数据结构
 */
public class BookList {

   private List<Book> ps;   //book列表
   private  int totalCount; //总的数量
   private  String category; //分类
   private int currentPage;//当前页
   private int totalPage; //总的页数
    private String searchfield;//搜索条件

    public String getSearchfield() {
        return searchfield;
    }

    public void setSearchfield(String searchfield) {
        this.searchfield = searchfield;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<Book> getPs() {
        return ps;
    }

    public void setPs(List<Book> ps) {
        this.ps = ps;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
