package com.system.book.dao;

import com.system.book.pojo.Borrow;
import com.system.book.vo.BorrowPieVo;
import com.system.book.vo.BorrowVo;

import java.util.Date;
import java.util.List;

public interface BorrowDao {
    List<Borrow> selectPage(int size, int begin);

    int selectCount();

    List<BorrowPieVo> getPieData();

    boolean updateBackTime(int id, String format);

    boolean updateEndTime(int id, String endTime);

    boolean insert(Borrow borrow);

    List<Borrow> selectUserPage(int size, int begin, int userId);

    Borrow getBorrowById(int id);
}
