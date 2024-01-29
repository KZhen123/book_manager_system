package com.system.book.service.impl;

import com.system.book.dao.NoticeDao;
import com.system.book.dao.UserDao;
import com.system.book.dao.impl.NoticeDaoImpl;
import com.system.book.dao.impl.UserDaoImpl;
import com.system.book.pojo.*;
import com.system.book.service.NoticeService;
import com.system.book.vo.NoticeVo;
import org.springframework.beans.BeanUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NoticeServiceImpl implements NoticeService {

    private NoticeDao noticeDao = new NoticeDaoImpl();

    private UserDao userDao = new UserDaoImpl();

    @Override
    public List<NoticeVo> selectPage(int size, int page, String content) {
        int begin = (page - 1) * size;
        List<Notice> notices = noticeDao.selectPage(size, begin, content);
        List<User> users = userDao.selectAll();
        // list转map
        Map<Integer, String> userMap = users.stream()
                .collect(Collectors.toMap(User::getId, user -> user.getName()));
        List<NoticeVo> noticeVos = new ArrayList<>();
        for (Notice notice : notices) {
            NoticeVo noticeVo = new NoticeVo();
            BeanUtils.copyProperties(notice, noticeVo);
            // 通过id获取name
            noticeVo.setUsername(userMap.get(notice.getUser()));
            noticeVos.add(noticeVo);
        }
        return noticeVos;
    }

    @Override
    public int selectCount(String content) {
        return noticeDao.selectCount(content);
    }

    @Override
    public boolean add(Notice notice, int userId) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(date);
        return noticeDao.add(notice, userId, format);
    }

    @Override
    public boolean delete(int id) {
        return noticeDao.delete(id);
    }

}
