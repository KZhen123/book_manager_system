package com.system.book.service;

import com.system.book.pojo.Borrow;
import com.system.book.vo.BorrowPieVo;
import com.system.book.vo.BorrowVo;

import java.text.ParseException;
import java.util.List;

public interface BorrowService {
    List<BorrowVo> selectPage(int size, int page);

    int selectCount();

    List<BorrowPieVo> getPieData();

    boolean backBook(int id);

    boolean updateEndTime(int id, String endTime) throws ParseException;

    boolean insert(Borrow borrow);

    List<BorrowVo> selectUserPage(int size, int page, int userId);
}
