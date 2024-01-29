package com.system.book.service;

import com.system.book.pojo.Category;
import com.system.book.vo.CategoryVo;

import java.util.List;

public interface CategoryService {
    List<CategoryVo> selectPage(int size, int page);

    int selectCount();

    boolean addCategory(Category category);

    boolean updateCategory(Category category);

    boolean deleteCategory(int id);

    List<CategoryVo> selectAll();

}
