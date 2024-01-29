package com.system.book.test;

import com.system.book.dao.BaseDao;
import com.system.book.pojo.User;
import org.junit.Test;

import java.util.List;

public class TestBaseDao {

    @Test
    public void testBaseQueryObject(){
        BaseDao baseDao = new BaseDao();
        User user = baseDao.baseQueryObject(User.class, "select * from user where name = ?","user1");
        System.out.println(user.toString());
    }

    @Test
    public void testBaseQuery(){
        BaseDao baseDao = new BaseDao();
        List<Object> users = baseDao.baseQuery(User.class, "select * from user");
        users.forEach(System.out::println);
    }

}
