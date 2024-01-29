package com.system.book.service.impl;

import com.system.book.dao.UserDao;
import com.system.book.dao.impl.UserDaoImpl;
import com.system.book.pojo.User;
import com.system.book.service.UserService;
import com.system.book.util.MD5Util;
import com.system.book.vo.UserVo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {


    private UserDao userDao = new UserDaoImpl();

    @Override
    public int add(User user) {
        user.setPassword(MD5Util.encrypt(user.getPassword()));
        return userDao.addUser(user);
    }

    @Override
    public boolean updatePwd(int userId, String pwd) {
        return userDao.updatePwd(userId, pwd);
    }

    @Override
    public int exist(String name) {
        return 0;
    }

    @Override
    public boolean verify(User user) {
        return false;
    }

    @Override
    public User getUserByName(String username) {
        return userDao.getUserByName(username);
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<UserVo> selectPage(int size, int page, String name) {
        int begin = (page - 1) * size;
        List<User> users = userDao.selectPage(size, begin, name);
        List<UserVo> userVos = new ArrayList<>();
        for (User user : users) {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(user, userVo);
            userVos.add(userVo);
        }
        return userVos;
    }

    @Override
    public int selectCount(String name) {
        return userDao.selectCount(name);
    }

    @Override
    public int update(User user) {
        return userDao.updateUser(user);
    }

}
