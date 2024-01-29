package com.system.book.dao;

import com.system.book.pojo.Book;

import java.util.List;

public interface BookDao {
    List<Book> selectPage(int size, int begin, String name);


    int selectCount(String name);

    List<Book> selectAll();

    boolean insert(Book book);

    boolean deleteBook(Integer id);

    boolean updateBook(Book book);

    Book getBookById(int id);

    boolean updateNum(int id, int i);
}
