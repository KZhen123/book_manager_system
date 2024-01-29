package com.system.book.dao.impl;

import com.system.book.dao.BaseDao;
import com.system.book.dao.FeedbackDao;
import com.system.book.pojo.Feedback;

import java.util.List;

public class FeedbackDaoImpl extends BaseDao implements FeedbackDao {
    @Override
    public List<Feedback> selectPage(int size, int begin) {
        String sql = "select id,user,phone,title,page,content,time,status from feedback limit ?,? ";
        List<Feedback> feedbacks = baseQuery(Feedback.class, sql, begin, size);
        return feedbacks;
    }

    @Override
    public int selectCount() {
        String sql = "select count(1) from feedback";
        int count = Math.toIntExact(baseQueryObject(Long.class, sql));
        return count;
    }

    @Override
    public boolean delete(int id) {
        String sql = "delete from feedback where id = ?";
        int update = baseUpdate(sql, id);
        return update == 1 ? true : false;
    }

    @Override
    public boolean updateStatus(int id, int i) {
        String sql = "update feedback set status = ? where id = ?";
        int update = baseUpdate(sql, i, id);
        return update == 1 ? true : false;
    }

    @Override
    public List<Feedback> selectUserPage(int size, int begin, int userId) {
        String sql = "select id,user,phone,title,page,content,time,status from feedback where user = ? limit ?,? ";
        List<Feedback> feedbacks = baseQuery(Feedback.class, sql, userId, begin, size);
        return feedbacks;
    }

    @Override
    public boolean insert(Feedback feedback) {
        String sql = "insert into feedback values(NULL,?,?,?,?,?,?,?)";
        int update = baseUpdate(sql, feedback.getUser(), feedback.getTitle(), feedback.getPage(), feedback.getContent(), feedback.getPhone(), feedback.getStatus(), feedback.getTime());
        return update == 1 ? true : false;
    }

    @Override
    public int selectUserCount(int userId) {
        String sql = "select count(1) from feedback where user = ?";
        int count = Math.toIntExact(baseQueryObject(Long.class, sql, userId));
        return count;
    }
}
