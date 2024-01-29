package com.system.book.service;

import com.system.book.pojo.User;
import com.system.book.vo.UserVo;

import java.util.List;

public interface UserService {
    /**
     * 新增用户
     * @param user
     * @return
     */
    int add(User user);

    /**
     * 更新用户
     * @param
     * @return
     */
    boolean updatePwd(int userId,String pwd);

    /**
     * 查找用户是否存在
     * @param name
     * @return
     */
    int exist(String name);

    /**
     * 验证用户密码是否正确
     * @param user
     * @return
     */
    boolean verify(User user);

    User getUserByName(String username);

    User getUserById(int id);

    List<UserVo> selectPage(int size, int page,String name);

    int selectCount(String name);

    int update(User user);
}
