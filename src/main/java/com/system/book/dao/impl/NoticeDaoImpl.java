package com.system.book.dao.impl;

import com.system.book.dao.BaseDao;
import com.system.book.dao.NoticeDao;
import com.system.book.pojo.Notice;

import java.util.List;

public class NoticeDaoImpl extends BaseDao implements NoticeDao {
    @Override
    public List<Notice> selectPage(int size, int begin, String content) {
        String sql = "select id,content,time,title,user from notice where content like ? limit ?,? ";
        List<Notice> notices = baseQuery(Notice.class, sql, '%' + content + '%', begin, size);
        return notices;
    }

    @Override
    public int selectCount(String content) {
        String sql = "select count(1) from notice where content like ?";
        int count = Math.toIntExact(baseQueryObject(Long.class, sql, '%' + content + '%'));
        return count;
    }

    @Override
    public boolean add(Notice notice, int userId, String date) {
        String sql = "insert into notice values(NULL,?,?,?,?)";
        int update = baseUpdate(sql, notice.getContent(), date, userId, notice.getTitle());
        return update == 1 ? true : false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "delete from notice where id = ?";
        int update = baseUpdate(sql, id);
        return update == 1 ? true : false;
    }
}
