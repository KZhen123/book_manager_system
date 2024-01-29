package com.system.book.controller;

import com.system.book.pojo.Result;
import com.system.book.pojo.User;
import com.system.book.service.BorrowService;
import com.system.book.service.impl.BorrowServiceImpl;
import com.system.book.vo.BorrowPieVo;
import com.system.book.vo.BorrowVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

@Controller
public class BorrowController {

    private BorrowService borrowService = new BorrowServiceImpl();


    @GetMapping(value = "/borrow/selectPage", produces = "application/json")
    @ResponseBody
    public Result selectPage(@RequestParam int size, @RequestParam int page) {
        List<BorrowVo> bookVos = borrowService.selectPage(size, page);
        Result<List<BorrowVo>> result = new Result<>("查询成功", borrowService.selectCount(), bookVos);
        return result;
    }

    @RequestMapping(value = "/borrow/pie", produces = "application/json")
    @ResponseBody
    public List<BorrowPieVo> getPieData() {
        List<BorrowPieVo> borrowPieVos = borrowService.getPieData();
        return borrowPieVos;
    }

    @RequestMapping(value = "/borrow/back", produces = "application/json")
    @ResponseBody
    public Result back(@RequestParam int id) {
        boolean res = borrowService.backBook(id);
        Result result;
        if (res) {
            result = new Result("还书成功");
        } else {
            result = new Result(500, "还书失败");
        }
        return result;
    }

    @RequestMapping(value = "/borrow/delay", produces = "application/json")
    @ResponseBody
    public Result delay(@RequestParam int id, @RequestParam String endTime) throws ParseException {
        boolean res = borrowService.updateEndTime(id, endTime);
        Result result;
        if (res) {
            result = new Result("修改成功");
        } else {
            result = new Result(500, "修改失败");
        }
        return result;
    }

    @GetMapping(value = "/borrow/selectUserPage", produces = "application/json")
    @ResponseBody
    public Result selectUserPage(@RequestParam int size, @RequestParam int page, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("user") == null) {
            return new Result(500, "登录过期！请重新登录");
        }
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        List<BorrowVo> bookVos = borrowService.selectUserPage(size, page, userId);
        Result<List<BorrowVo>> result = new Result<>("查询成功", borrowService.selectCount(), bookVos);
        return result;
    }
}
