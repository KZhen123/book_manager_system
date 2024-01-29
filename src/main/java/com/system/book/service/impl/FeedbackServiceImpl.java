package com.system.book.service.impl;

import com.system.book.dao.FeedbackDao;
import com.system.book.dao.UserDao;
import com.system.book.dao.impl.FeedbackDaoImpl;
import com.system.book.dao.impl.UserDaoImpl;
import com.system.book.pojo.*;
import com.system.book.service.FeedbackService;
import com.system.book.vo.BookVo;
import com.system.book.vo.BorrowVo;
import com.system.book.vo.FeedbackVo;
import org.springframework.beans.BeanUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FeedbackServiceImpl implements FeedbackService {

    private FeedbackDao feedbackDao = new FeedbackDaoImpl();

    private UserDao userDao = new UserDaoImpl();

    @Override
    public List<FeedbackVo> selectPage(int size, int page) {
        int begin = (page - 1) * size;
        List<Feedback> feedbacks = feedbackDao.selectPage(size, begin);
        List<User> users = userDao.selectAll();
        // list转map
        Map<Integer, String> userMap = users.stream()
                .collect(Collectors.toMap(User::getId, user -> user.getName()));
        List<FeedbackVo> feedbackVos = new ArrayList<>();
        for (Feedback feedback : feedbacks) {
            FeedbackVo feedbackVo = new FeedbackVo();
            BeanUtils.copyProperties(feedback, feedbackVo);
            // 通过id获取name
            feedbackVo.setUsername(userMap.get(feedback.getUser()));
            feedbackVos.add(feedbackVo);
        }
        return feedbackVos;
    }

    @Override
    public int selectCount() {
        return feedbackDao.selectCount();
    }

    @Override
    public boolean delete(int id) {
        return feedbackDao.delete(id);
    }

    @Override
    public boolean update(int id, int i) {
        return feedbackDao.updateStatus(id,i);
    }

    @Override
    public List<FeedbackVo> selectUserPage(int size, int page, int userId) {
        int begin = (page - 1) * size;
        List<Feedback> feedbacks = feedbackDao.selectUserPage(size, begin, userId);
        List<User> users = userDao.selectAll();
        // list转map
        Map<Integer, String> userMap = users.stream()
                .collect(Collectors.toMap(User::getId, user -> user.getName()));
        List<FeedbackVo> feedbackVos = new ArrayList<>();
        for (Feedback feedback : feedbacks) {
            FeedbackVo feedbackVo = new FeedbackVo();
            BeanUtils.copyProperties(feedback, feedbackVo);
            // 通过id获取name
            feedbackVo.setUsername(userMap.get(feedback.getUser()));
            feedbackVos.add(feedbackVo);
        }
        return feedbackVos;
    }

    @Override
    public boolean add(Feedback feedback) throws ParseException {
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(new Date());
        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = fmt.parse(time);
        feedback.setTime(date);
        feedback.setStatus(0);
        return feedbackDao.insert(feedback);
    }

    @Override
    public int selectUserCount(int userId) {
        return feedbackDao.selectUserCount(userId);
    }
}
