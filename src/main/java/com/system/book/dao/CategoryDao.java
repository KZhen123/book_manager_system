package com.system.book.dao;

import com.system.book.pojo.Book;
import com.system.book.pojo.Category;
import com.system.book.vo.CategoryVo;

import java.util.List;

public interface CategoryDao {
    List<Category> selectAll();

    List<Category> selectPage(int size, int begin);

    int selectCount();

    boolean insert(Category category);

    boolean update(Category category);

    boolean delete(int id);
}
