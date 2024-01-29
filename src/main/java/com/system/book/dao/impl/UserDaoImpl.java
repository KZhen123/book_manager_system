package com.system.book.dao.impl;

import com.system.book.dao.BaseDao;
import com.system.book.dao.UserDao;
import com.system.book.pojo.User;

import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public int addUser(User user) {
        String sql = "insert into user values(NULL,?,?,?,?,?)";
        return baseUpdate(sql, user.getName(), user.getPassword(), user.getAdmin(), user.getAvailableNum(), user.getPhone());
    }

    @Override
    public int updateUser(User user) {
        String sql = "update user set phone=?, availableNum=? where id=?";
        int update = baseUpdate(sql, user.getPhone(), user.getAvailableNum(), user.getId());
        return update;
    }

    @Override
    public boolean existUser(String name) {
        return false;
    }

    @Override
    public boolean verifyUser(User user) {
        return false;
    }

    @Override
    public User getUserByName(String username) {
        String sql = "select id,name,password,admin,availableNum,phone from user where name = ?";
        List<User> users = baseQuery(User.class, sql, username);
        return users == null || users.isEmpty() ? null : users.get(0);
    }

    @Override
    public List<User> selectAll() {
        String sql = "select id,name,password,admin,availableNum from user";
        List<User> users = baseQuery(User.class, sql);
        return users;
    }

    /**
     * 用户管理 查询非管理员
     */
    @Override
    public List<User> selectPage(int size, int begin, String name) {
        String sql = "select id, name,phone, admin,availableNum from user where name like ? limit ?,? ";
        List<User> users = baseQuery(User.class, sql, '%' + name + '%', begin, size);
        return users;
    }

    @Override
    public int selectCount(String name) {
        String sql = "select count(1) from user where name like ?";
        int count = Math.toIntExact(baseQueryObject(Long.class, sql, '%' + name + '%'));
        return count;
    }

    @Override
    public User getUserById(int id) {
        String sql = "select id,name,password,admin,availableNum,phone from user where id = ?";
        List<User> users = baseQuery(User.class, sql, id);
        return users == null || users.isEmpty() ? null : users.get(0);
    }

    @Override
    public boolean updateNum(int id, int num) {
        String sql = "update user set availableNum=? where id=?";
        int update = baseUpdate(sql, num, id);
        return update == 1 ? true : false;
    }

    @Override
    public boolean updatePwd(int userId, String pwd) {
        String sql = "update user set password=? where id=?";
        int update = baseUpdate(sql, pwd, userId);
        return update == 1 ? true : false;
    }
}
