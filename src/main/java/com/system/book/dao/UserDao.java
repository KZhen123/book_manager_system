package com.system.book.dao;

import com.system.book.pojo.Book;
import com.system.book.pojo.User;

import java.util.List;

public interface UserDao {
    /**
     * 新增用户
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 更新用户
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 查找用户是否存在
     * @param name
     * @return
     */
    boolean existUser(String name);

    /**
     * 验证用户密码是否正确
     * @param user
     * @return
     */
    boolean verifyUser(User user);

    User getUserByName(String username);

    List<User> selectAll();

    List<User> selectPage(int size, int begin,String name);

    int selectCount(String name);

    User getUserById(int id);

    boolean updateNum(int id, int i);

    boolean updatePwd(int userId, String pwd);
}
