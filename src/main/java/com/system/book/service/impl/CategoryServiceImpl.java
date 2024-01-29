package com.system.book.service.impl;

import com.system.book.dao.CategoryDao;
import com.system.book.dao.impl.CategoryDaoImpl;
import com.system.book.pojo.Category;
import com.system.book.service.CategoryService;
import com.system.book.vo.CategoryVo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {


    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<CategoryVo> selectPage(int size, int page) {
        int begin = (page - 1) * size;
        List<Category> categories = categoryDao.selectPage(size, begin);

        List<CategoryVo> categoryVos = new ArrayList<>();
        for (Category category : categories) {
            CategoryVo categoryVo = new CategoryVo();
            BeanUtils.copyProperties(category, categoryVo);
            categoryVos.add(categoryVo);
        }
        return categoryVos;
    }

    @Override
    public int selectCount() {
        return categoryDao.selectCount();
    }

    @Override
    public boolean addCategory(Category category) {
        return categoryDao.insert(category);
    }

    @Override
    public boolean updateCategory(Category category) {
        return categoryDao.update(category);
    }

    @Override
    public boolean deleteCategory(int id) {
        return categoryDao.delete(id);
    }

    @Override
    public List<CategoryVo> selectAll() {
        List<Category> categories = categoryDao.selectAll();
        List<CategoryVo> categoryVos = new ArrayList<>();
        for (Category category : categories) {
            CategoryVo categoryVo = new CategoryVo();
            BeanUtils.copyProperties(category, categoryVo);
            categoryVos.add(categoryVo);
        }
        return categoryVos;
    }
}
