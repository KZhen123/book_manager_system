package com.system.book.controller;

import com.system.book.pojo.Book;
import com.system.book.pojo.Result;
import com.system.book.pojo.User;
import com.system.book.service.BookService;
import com.system.book.service.UserService;
import com.system.book.service.impl.BookServiceImpl;
import com.system.book.service.impl.UserServiceImpl;
import com.system.book.vo.BookVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

@Controller
public class BookController {

    private UserService userService = new UserServiceImpl();

    private BookService bookService = new BookServiceImpl();


    @RequestMapping(value = "/book/selectPage", produces = "application/json")
    @ResponseBody
    public Result selectPage(@RequestParam int size, @RequestParam int page,
                             @RequestParam(value = "name", required = false, defaultValue = "") String name) {
        List<BookVo> bookVos = bookService.selectPage(size, page, name);
        Result<List<BookVo>> result = new Result<>("查询成功", bookService.selectCount(name), bookVos);
        return result;
    }

    @RequestMapping(value = "/book/add", produces = "application/json")
    @ResponseBody
    public Result addBook(Book book) {
        boolean res = bookService.addBook(book);
        Result result;
        if (res) {
            result = new Result("新增成功");
        } else {
            result = new Result(500, "新增失败");
        }
        return result;
    }

    @RequestMapping(value = "/book/update", produces = "application/json")
    @ResponseBody
    public Result updateBook(Book book) {
        boolean res = bookService.updateBook(book);
        Result result;
        if (res) {
            result = new Result("编辑成功");
        } else {
            result = new Result(500, "编辑失败");
        }
        return result;
    }

    @RequestMapping(value = "/book/delete", produces = "application/json")
    @ResponseBody
    public Result deleteBook(@RequestParam int id) {
        boolean res = bookService.deleteBook(id);
        Result result;
        if (res) {
            result = new Result("删除成功");
        } else {
            result = new Result(500, "删除失败");
        }
        return result;
    }

    @RequestMapping(value = "/book/borrow", produces = "application/json")
    @ResponseBody
    public Result borrowBook(@RequestParam int id, HttpServletRequest request) throws ParseException {
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("user") == null) {
            return new Result(500, "登录过期！请重新登录");
        }
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        // 检查当前用户剩余借书数
        User userById = userService.getUserById(userId);
        if (userById.getAvailableNum() == 0) {
            return new Result(500, "当前用户借书数量达到上限！");
        }
        boolean res = bookService.borrowBook(id, userById);
        Result result;
        if (res) {
            result = new Result("借书成功");
        } else {
            result = new Result(500, "借书失败");
        }
        return result;
    }


}
