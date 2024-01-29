package com.system.book.service;

import com.system.book.pojo.Notice;
import com.system.book.vo.NoticeVo;

import java.util.List;

public interface NoticeService {
    List<NoticeVo> selectPage(int size, int page,String content);

    int selectCount(String content);

    boolean add(Notice notice, int userId);

    boolean delete(int id);
}
