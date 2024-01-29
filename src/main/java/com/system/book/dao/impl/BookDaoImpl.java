package com.system.book.dao.impl;

import com.system.book.dao.BaseDao;
import com.system.book.dao.BookDao;
import com.system.book.pojo.Book;

import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {
    /**
     * 模糊查找name
     * */
    public List<Book> selectPage(int size,int begin,String name){
        String sql = "select id, name, author, publish, category, num from book where name like ? limit ?,? ";
        List<Book> books = baseQuery(Book.class,sql, '%' + name + '%',begin,size);
        return books;
    }

    @Override
    public int selectCount(String name) {
        String sql = "select count(1) from book  where name like ?";
        int count = Math.toIntExact(baseQueryObject(Long.class, sql,'%'+name+'%'));
        return count;
    }

    @Override
    public List<Book> selectAll() {
        String sql = "select id, name, author, publish, category, num from book";
        List<Book> books = baseQuery(Book.class,sql);
        return books;
    }

    @Override
    public boolean insert(Book book) {
        String sql = "insert into book values(NULL,?,?,?,?,?)";
        int update = baseUpdate(sql, book.getName(), book.getAuthor(), book.getPublish(), book.getCategory(), book.getNum());
        return update==1?true:false;
    }

    @Override
    public boolean deleteBook(Integer id) {
        String sql = "delete from book where id = ?";
        int update = baseUpdate(sql, id);
        return update==1?true:false;
    }

    @Override
    public boolean updateBook(Book book) {
        String sql = "update book set name=?, author=?, publish=?, category=? ,num=? where id=?";
        int update = baseUpdate(sql, book.getName(), book.getAuthor(), book.getPublish(), book.getCategory(), book.getNum(), book.getId());
        return update==1?true:false;
    }

    @Override
    public Book getBookById(int id) {
        String sql = "select id, name, author, publish, category, num from book where id = ?";
        List<Book> books = baseQuery(Book.class,sql,id);
        return books == null || books.isEmpty() ? null : books.get(0);
    }

    @Override
    public boolean updateNum(int id, int num) {
        String sql = "update book set num=? where id=?";
        int update = baseUpdate(sql, num, id);
        return update==1?true:false;
    }
}
