package com.system.book.dao.impl;

import com.system.book.dao.BaseDao;
import com.system.book.dao.CategoryDao;
import com.system.book.pojo.Category;

import java.util.List;

public class CategoryDaoImpl extends BaseDao implements CategoryDao {
    @Override
    public List<Category> selectAll() {
        String sql = "select id,name from category";
        List<Category> categories = baseQuery(Category.class, sql);
        return categories;
    }

    @Override
    public List<Category> selectPage(int size, int begin) {
        String sql = "select id, name from category limit ?,? ";
        List<Category> categories = baseQuery(Category.class, sql, begin, size);
        return categories;
    }

    @Override
    public int selectCount() {
        String sql = "select count(1) from category";
        int count = Math.toIntExact(baseQueryObject(Long.class, sql));
        return count;
    }

    @Override
    public boolean insert(Category category) {
        String sql = "insert into category values(NULL,?)";
        int update = baseUpdate(sql, category.getName());
        return update == 1 ? true : false;
    }

    @Override
    public boolean update(Category category) {
        String sql = "update category set name=? where id = ?";
        int update = baseUpdate(sql, category.getName(), category.getId());
        return update == 1 ? true : false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "delete from category where id = ?";
        int update = baseUpdate(sql, id);
        return update == 1 ? true : false;
    }

}
