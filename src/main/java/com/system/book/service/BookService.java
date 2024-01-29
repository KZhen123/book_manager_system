package com.system.book.service;

import com.system.book.pojo.Book;
import com.system.book.pojo.User;
import com.system.book.vo.BookVo;

import java.text.ParseException;
import java.util.List;

public interface BookService {
    boolean addBook(Book book);

    List<BookVo> selectPage(int size, int page,String name);

    int selectCount(String name);

    boolean deleteBook(int id);

    boolean updateBook(Book book);

    boolean borrowBook(int id, User user) throws ParseException;

    Book getBookById(int id);
}
