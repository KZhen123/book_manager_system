package com.system.book.controller;

import com.system.book.pojo.Category;
import com.system.book.pojo.Result;
import com.system.book.service.CategoryService;
import com.system.book.service.impl.CategoryServiceImpl;
import com.system.book.vo.CategoryVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CategoryController {

    private CategoryService categoryService = new CategoryServiceImpl();


    @GetMapping(value = "/category/selectPage", produces = "application/json")
    @ResponseBody
    public Result selectPage(@RequestParam int size, @RequestParam int page) {
        List<CategoryVo> categoryVos = categoryService.selectPage(size, page);
        Result<List<CategoryVo>> result = new Result<>("查询成功", categoryService.selectCount(), categoryVos);
        return result;
    }

    @RequestMapping(value = "/category/all", produces = "application/json")
    @ResponseBody
    public List<CategoryVo> getAll() {
        List<CategoryVo> categoryVos = categoryService.selectAll();
        return categoryVos;
    }

    @RequestMapping(value = "/category/add", produces = "application/json")
    @ResponseBody
    public Result addCategory(Category category) {
        boolean res = categoryService.addCategory(category);
        Result result;
        if (res) {
            result = new Result("新增成功");
        } else {
            result = new Result(500, "新增失败");
        }
        return result;
    }

    @RequestMapping(value = "/category/update", produces = "application/json")
    @ResponseBody
    public Result updateCategory(Category category) {
        boolean res = categoryService.updateCategory(category);
        Result result;
        if (res) {
            result = new Result("编辑成功");
        } else {
            result = new Result(500, "编辑失败");
        }
        return result;
    }

    @RequestMapping(value = "/category/delete", produces = "application/json")
    @ResponseBody
    public Result deleteCategory(@RequestParam int id) {
        boolean res = categoryService.deleteCategory(id);
        Result result;
        if (res) {
            result = new Result("删除成功");
        } else {
            result = new Result(500, "删除失败");
        }
        return result;
    }
}
