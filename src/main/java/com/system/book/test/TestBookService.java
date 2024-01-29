package com.system.book.test;

import com.system.book.pojo.Book;
import com.system.book.pojo.Feedback;
import com.system.book.pojo.Notice;
import com.system.book.pojo.User;
import com.system.book.service.*;
import com.system.book.service.impl.*;
import com.system.book.vo.*;
import org.junit.Test;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestBookService {

    private BookService bookService = new BookServiceImpl();

    private BorrowService borrowService = new BorrowServiceImpl();

    private NoticeService noticeService = new NoticeServiceImpl();

    private UserService userService = new UserServiceImpl();

    private FeedbackService feedbackService = new FeedbackServiceImpl();

    @Test
    public void testSelectAll() throws ParseException {
//        Map<String,Object> map = new HashMap<>();
//        map.put("size",10);
//        map.put("page",1);
//        List<BookVo> bookVos = bookService.selectPage(map);
//        bookVos.forEach(System.out::println);

//        List<BorrowVo> borrowVos = borrowService.selectPage(10, 1);
//        borrowVos.forEach(System.out::println);

//        List<NoticeVo> noticeVos = noticeService.selectPage(10, 1);
//        noticeVos.forEach(System.out::println);

//        List<BorrowPieVo> pieData = borrowService.getPieData();
//        pieData.forEach(System.out::println);

//        List<UserVo> userVos = userService.selectPage(10, 1);
//        userVos.forEach(System.out::println);

//        List<FeedbackVo> feedbackVos = feedbackService.selectPage(10, 1);
//        feedbackVos.forEach(System.out::println);

//        boolean b = bookService.deleteBook(76);
//        System.out.println(b);

//        Book book = new Book(77, "背影修改", "朱自清","新华书店",3,2);
//        boolean b = bookService.updateBook(book);
//        System.out.println(b);

//        Notice notice = new Notice(null,"测试内容",null,null,"测试标题");
//        boolean add = noticeService.add(notice, 1);
//        System.out.println(add);

//        User userById = userService.getUserById(3);
//        boolean res = bookService.borrowBook(72, userById);
//        System.out.println(res);

        Feedback feedback = new Feedback();
        feedback.setPage("dddddd");
        feedback.setPhone("23793");
        feedback.setTitle("uewiyuy");
        feedback.setContent("mkdljsfjas");
        boolean add = feedbackService.add(feedback);
        System.out.println(add);
    }
}
