package com.system.book.controller;

import com.system.book.pojo.Book;
import com.system.book.pojo.Result;
import com.system.book.pojo.User;
import com.system.book.service.UserService;
import com.system.book.service.impl.UserServiceImpl;
import com.system.book.util.MD5Util;
import com.system.book.vo.BookVo;
import com.system.book.vo.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;
import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

@Controller
public class UserController{

    private UserService userService = new UserServiceImpl();

    @RequestMapping(value = "/user/register")
    protected String register(HttpServletRequest req){
        String username = req.getParameter("username");
        String pwd = req.getParameter("password");
        String phone = req.getParameter("phone");
        if(!verifyCode(req)){
            req.setAttribute("msg","验证码错误！");
            req.setAttribute("username",username);
            req.setAttribute("pwd",pwd);
            // 转发注册页面
            return "user/register";
        }
        User userByName = userService.getUserByName(username);
        if(userByName != null){
            req.setAttribute("msg","用户已存在！");
            req.setAttribute("username",username);
            // 跳回注册页面
            return "user/register";
        }
        User user = new User(null, username, pwd, 0, 10,phone);
        int add = userService.add(user);
        if (add == 1) {
            req.setAttribute("msg","");
            return "user/login";
        } else {
            req.setAttribute("msg","注册失败！");
            req.setAttribute("username",username);
            // 跳回注册页面
            return "user/register";
        }
    }

    @RequestMapping(value = "/user/login")
    protected String login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String pwd = req.getParameter("password");
        if(!verifyCode(req)){
            req.setAttribute("msg","验证码错误！");
            req.setAttribute("username",username);
            req.setAttribute("pwd",pwd);

            req.setAttribute("msg","验证码错误！");
            return "user/login";
        }

        User user = userService.getUserByName(username);
        // 把错误信息和回显的表单信息，保存到request域中
        if (user == null) {
            req.setAttribute("msg","用户不存在！");
            req.setAttribute("username",username);
            return "user/login";
        } else if (!MD5Util.encrypt(pwd).equals(user.getPassword())) {
            req.setAttribute("msg","密码错误！");
            req.setAttribute("username",username);
            return "user/login";
        } else {
            // 把登录信息放入session
            HttpSession session = req.getSession();
            session.setAttribute("user",user);
            if(1==user.getAdmin()){
                return "admin/index";
            }
            return "reader/index";
        }
    }

    @GetMapping(value = "/user/selectPage", produces = "application/json")
    @ResponseBody
    public Result selectPage(@RequestParam int size, @RequestParam int page,
                             @RequestParam(value="name",required = false,defaultValue = "") String name) {
        List<UserVo> userVos = userService.selectPage(size,page,name);
        Result<List<UserVo>> result = new Result<>("查询成功",userService.selectCount(name),userVos);
        return result;
    }

    @RequestMapping(value = "/user/logout", produces = "application/json")
    @ResponseBody
    public void logout(HttpServletRequest request) {
        // 清除session
        request.getSession().removeAttribute("user");
    }

    @RequestMapping(value = "/user/updatePwd", produces = "application/json")
    @ResponseBody
    public Result updatePwd(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session==null||session.getAttribute("user")==null){
            return new Result(500,"登录过期！请重新登录");
        }
        if(!verifyCode(request)){
            return new Result(500,"验证码错误！");
        }
        User sessionUser = (User) session.getAttribute("user");
        int userId = sessionUser.getId();
        User userById = userService.getUserById(userId);
        // 传入参数
        String oldPwd = request.getParameter("oldPwd");
        String pwd = request.getParameter("pwd");
        String phone = request.getParameter("phone");

        // 合法性验证
        if(!(userById.getPhone()).equals(phone)){
            return new Result(500,"手机号错误！");
        }
        if(!MD5Util.encrypt(oldPwd).equals(userById.getPassword())){
            return new Result<>(500,"旧密码错误！");
        }
        boolean updatePwd = userService.updatePwd(userId, MD5Util.encrypt(pwd));
        if (updatePwd){
            // 清除session
            request.getSession().removeAttribute("user");
            return new Result(200,"更新成功,请重新登录");
        }
        return new Result(500, "更新失败");
    }

    @RequestMapping(value = "/user/add", produces = "application/json")
    @ResponseBody
    public Result addUser(User user) {
        String name = user.getName();
        User userByName = userService.getUserByName(name);
        if(userByName != null){
            return new Result(500,"用户已存在！");
        }
        user.setAdmin(0);
        int add = userService.add(user);
        if (add == 1) {
            return new Result(200,"添加成功");
        } else {
            return new Result(500,"添加失败！");
        }
    }


    @RequestMapping(value = "/user/update", produces = "application/json")
    @ResponseBody
    public Result update(User user) {
        int add = userService.update(user);
        if (add == 1) {
            return new Result(200,"编辑成功");
        } else {
            return new Result(500,"编辑失败！");
        }
    }


    private boolean verifyCode(HttpServletRequest req){
        // 验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        String code = req.getParameter("code");
        if(token==null || !token.equalsIgnoreCase(code)){
            return false;
        }
        return true;
    }

}
