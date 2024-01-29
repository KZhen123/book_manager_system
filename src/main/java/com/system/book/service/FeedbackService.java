package com.system.book.service;

import com.system.book.pojo.Feedback;
import com.system.book.vo.FeedbackVo;

import java.text.ParseException;
import java.util.List;

public interface FeedbackService {
    List<FeedbackVo> selectPage(int size, int page);

    int selectCount();

    boolean delete(int id);

    boolean update(int id, int i);

    List<FeedbackVo> selectUserPage(int size, int page, int userId);

    boolean add(Feedback feedback) throws ParseException;

    int selectUserCount(int userId);
}
