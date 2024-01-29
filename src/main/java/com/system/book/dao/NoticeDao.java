package com.system.book.dao;

import com.system.book.pojo.Notice;

import java.util.List;

public interface NoticeDao {
    List<Notice> selectPage(int size, int begin,String content);

    int selectCount(String content);

    boolean add(Notice notice, int userId, String date);

    boolean delete(int id);
}
