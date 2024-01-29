package com.system.book.dao.impl;

import com.system.book.dao.BaseDao;
import com.system.book.dao.BorrowDao;
import com.system.book.pojo.Borrow;
import com.system.book.vo.BorrowPieVo;

import java.util.List;

public class BorrowDaoImpl extends BaseDao implements BorrowDao {
    @Override
    public List<Borrow> selectPage(int size, int begin) {
        String sql = "select id,user_id,book_id,borrowTime,backTime,endTime from borrow limit ?,? ";
        List<Borrow> borrows = baseQuery(Borrow.class, sql, begin, size);
        return borrows;
    }

    @Override
    public int selectCount() {
        String sql = "select count(1) from borrow";
        int count = Math.toIntExact(baseQueryObject(Long.class, sql));
        return count;
    }

    /**
     * 获取类别借阅数，三重联合查询
     */
    @Override
    public List<BorrowPieVo> getPieData() {
        String sql = "select c.name,count(*) as value  from borrow b left join book b2 on b.book_id = b2.id  left join category c on b2.category = c.id group by c.id";
        List<BorrowPieVo> borrowPieVos = baseQuery(BorrowPieVo.class, sql);
        return borrowPieVos;
    }

    @Override
    public boolean updateBackTime(int id, String format) {
        String sql = "update borrow set backTime = ? where id = ?";
        int update = baseUpdate(sql, format, id);
        return update == 1 ? true : false;
    }

    @Override
    public boolean updateEndTime(int id, String endTime) {
        String sql = "update borrow set endTime = ? where id = ?";
        int update = baseUpdate(sql, endTime, id);
        return update == 1 ? true : false;
    }

    @Override
    public boolean insert(Borrow borrow) {
        String sql = "insert into borrow values(NULL,?,?,?,?,?)";
        int update = baseUpdate(sql, borrow.getUser_id(), borrow.getBook_id(), borrow.getBorrowTime(), borrow.getBackTime(), borrow.getEndTime());
        return update == 1 ? true : false;
    }

    @Override
    public List<Borrow> selectUserPage(int size, int begin, int userId) {
        String sql = "select id,user_id,book_id,borrowTime,backTime,endTime from borrow where user_id = ? limit ?,? ";
        List<Borrow> borrows = baseQuery(Borrow.class, sql, userId, begin, size);
        return borrows;
    }

    @Override
    public Borrow getBorrowById(int id) {
        String sql = "select id,user_id,book_id,borrowTime,backTime,endTime from borrow where id = ?";
        List<Borrow> borrows = baseQuery(Borrow.class, sql, id);
        return borrows.size() == 0 ? null : borrows.get(0);
    }
}
