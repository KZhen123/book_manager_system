package com.system.book.controller;

import com.system.book.pojo.Notice;
import com.system.book.pojo.Result;
import com.system.book.pojo.User;
import com.system.book.service.NoticeService;
import com.system.book.service.impl.NoticeServiceImpl;
import com.system.book.vo.NoticeVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class NoticeController {

    private NoticeService noticeService = new NoticeServiceImpl();

    @GetMapping(value = "/notice/selectPage", produces = "application/json")
    @ResponseBody
    public Result selectPage(@RequestParam int size, @RequestParam int page,
                             @RequestParam(value = "content", required = false, defaultValue = "") String content) {
        List<NoticeVo> noticeVos = noticeService.selectPage(size, page, content);
        Result<List<NoticeVo>> result = new Result<>("查询成功", noticeService.selectCount(content), noticeVos);
        return result;
    }

    @RequestMapping(value = "/notice/add", produces = "application/json")
    @ResponseBody
    public Result add(Notice notice, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("user") == null) {
            return new Result(500, "登录过期！请重新登录");
        }
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        boolean res = noticeService.add(notice, userId);
        Result result;
        if (res) {
            result = new Result("新增成功");
        } else {
            result = new Result(500, "新增失败");
        }
        return result;
    }

    @RequestMapping(value = "/notice/delete", produces = "application/json")
    @ResponseBody
    public Result delete(@RequestParam int id) {
        boolean res = noticeService.delete(id);
        Result result;
        if (res) {
            result = new Result("删除成功");
        } else {
            result = new Result(500, "删除失败");
        }
        return result;
    }
}
