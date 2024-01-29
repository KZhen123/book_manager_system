package com.system.book.controller;

import com.system.book.pojo.Feedback;
import com.system.book.pojo.Result;
import com.system.book.pojo.User;
import com.system.book.service.FeedbackService;
import com.system.book.service.impl.FeedbackServiceImpl;
import com.system.book.vo.FeedbackVo;
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
public class FeedbackController {

    private FeedbackService feedbackService = new FeedbackServiceImpl();

    @GetMapping(value = "/feedback/selectPage", produces = "application/json")
    @ResponseBody
    public Result selectPage(@RequestParam int size, @RequestParam int page) {
        List<FeedbackVo> feedbackVos = feedbackService.selectPage(size, page);
        Result<List<FeedbackVo>> result = new Result<>("查询成功", feedbackService.selectCount(), feedbackVos);
        return result;
    }

    @RequestMapping(value = "/feedback/delete", produces = "application/json")
    @ResponseBody
    public Result delete(@RequestParam int id) {
        boolean res = feedbackService.delete(id);
        Result result;
        if (res) {
            result = new Result("删除成功");
        } else {
            result = new Result(500, "删除失败");
        }
        return result;
    }

    @RequestMapping(value = "/feedback/changeTrue", produces = "application/json")
    @ResponseBody
    public Result changeTrue(@RequestParam int id) {
        boolean res = feedbackService.update(id, 1);
        Result result;
        if (res) {
            result = new Result("修改成功");
        } else {
            result = new Result(500, "修改失败");
        }
        return result;
    }

    @RequestMapping(value = "/feedback/changeFalse", produces = "application/json")
    @ResponseBody
    public Result changeFalse(@RequestParam int id) {
        boolean res = feedbackService.update(id, 0);
        Result result;
        if (res) {
            result = new Result("修改成功");
        } else {
            result = new Result(500, "修改失败");
        }
        return result;
    }

    @GetMapping(value = "/feedback/selectUserPage", produces = "application/json")
    @ResponseBody
    public Result selectUserPage(@RequestParam int size, @RequestParam int page, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("user") == null) {
            return new Result(500, "登录过期！请重新登录");
        }
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        List<FeedbackVo> feedbackVos = feedbackService.selectUserPage(size, page, userId);
        Result<List<FeedbackVo>> result = new Result<>("查询成功", feedbackService.selectUserCount(userId), feedbackVos);
        return result;
    }

    @RequestMapping(value = "/feedback/add", produces = "application/json")
    @ResponseBody
    public Result add(Feedback feedback, HttpServletRequest request) throws ParseException {
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("user") == null) {
            return new Result(500, "登录过期！请重新登录");
        }
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        feedback.setUser(userId);
        boolean res = feedbackService.add(feedback);
        Result result;
        if (res) {
            result = new Result("反馈成功");
        } else {
            result = new Result(500, "反馈失败");
        }
        return result;
    }
}
