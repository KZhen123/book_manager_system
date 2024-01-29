package com.system.book.dao;

import com.system.book.pojo.Feedback;

import java.util.List;

public interface FeedbackDao {
    List<Feedback> selectPage(int size, int begin);

    int selectCount();

    boolean delete(int id);

    boolean updateStatus(int id, int i);

    List<Feedback> selectUserPage(int size, int begin, int userId);

    boolean insert(Feedback feedback);

    int selectUserCount(int userId);
}
