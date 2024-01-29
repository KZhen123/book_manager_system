package com.system.book.service.impl;

import com.system.book.dao.BookDao;
import com.system.book.dao.BorrowDao;
import com.system.book.dao.UserDao;
import com.system.book.dao.impl.BookDaoImpl;
import com.system.book.dao.impl.BorrowDaoImpl;
import com.system.book.dao.impl.UserDaoImpl;
import com.system.book.pojo.Book;
import com.system.book.pojo.Borrow;
import com.system.book.pojo.User;
import com.system.book.service.BorrowService;
import com.system.book.vo.BookVo;
import com.system.book.vo.BorrowPieVo;
import com.system.book.vo.BorrowVo;
import org.springframework.beans.BeanUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class BorrowServiceImpl implements BorrowService {

    private BorrowDao borrowDao = new BorrowDaoImpl();

    private BookDao bookDao = new BookDaoImpl();

    private UserDao userDao = new UserDaoImpl();

    @Override
    public List<BorrowVo> selectPage(int size, int page) {
        int begin = (page - 1) * size;
        List<Borrow> borrows = borrowDao.selectPage(size, begin);
        List<Book> books = bookDao.selectAll();
        List<User> users = userDao.selectAll();
        // list转map
        Map<Integer, String> bookMap = books.stream()
                .collect(Collectors.toMap(Book::getId, book -> book.getName()));
        Map<Integer, String> userMap = users.stream()
                .collect(Collectors.toMap(User::getId, user -> user.getName()));
        List<BorrowVo> borrowVos = new ArrayList<>();
        for (Borrow borrow : borrows) {
            BorrowVo borrowVo = new BorrowVo();
            BeanUtils.copyProperties(borrow, borrowVo);
            // 通过id获取name
            borrowVo.setUserName(userMap.get(borrow.getUser_id()));
            borrowVo.setBookName(bookMap.get(borrow.getBook_id()));
            borrowVos.add(borrowVo);
        }
        return borrowVos;
    }

    @Override
    public int selectCount() {
        return borrowDao.selectCount();
    }

    @Override
    public List<BorrowPieVo> getPieData() {
        return borrowDao.getPieData();
    }

    @Override
    public boolean backBook(int id) {
        /*
         * 1.  book的num增加
         * 2.  user的aviilableNum增加
         * 3.  backTime更新
         * */
        Borrow borrow = borrowDao.getBorrowById(id);
        Book bookById = bookDao.getBookById(borrow.getBook_id());
        boolean updateNum = bookDao.updateNum(bookById.getId(), bookById.getNum() + 1);
        User userById = userDao.getUserById(borrow.getUser_id());
        boolean updateNum1 = userDao.updateNum(userById.getId(), userById.getAvailableNum() + 1);
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(date);
        boolean updateBackTime = borrowDao.updateBackTime(id, format);
        return updateNum && updateNum1 && updateBackTime;
    }

    @Override
    public boolean updateEndTime(int id, String endTime) throws ParseException {
        // 获取当前截止时间Calendar
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date endDate = fmt.parse(endTime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        // 加一个月
        calendar.add(Calendar.MONTH, 1);
        // 转String
        Date time = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(time);

        return borrowDao.updateEndTime(id, format);
    }

    @Override
    public boolean insert(Borrow borrow) {
        return borrowDao.insert(borrow);
    }

    @Override
    public List<BorrowVo> selectUserPage(int size, int page, int userId) {
        int begin = (page - 1) * size;
        List<Borrow> borrows = borrowDao.selectUserPage(size, begin, userId);
        List<Book> books = bookDao.selectAll();
        List<User> users = userDao.selectAll();
        // list转map
        Map<Integer, String> bookMap = books.stream()
                .collect(Collectors.toMap(Book::getId, book -> book.getName()));
        Map<Integer, String> userMap = users.stream()
                .collect(Collectors.toMap(User::getId, user -> user.getName()));
        List<BorrowVo> borrowVos = new ArrayList<>();
        for (Borrow borrow : borrows) {
            BorrowVo borrowVo = new BorrowVo();
            BeanUtils.copyProperties(borrow, borrowVo);
            // 通过id获取name
            borrowVo.setUserName(userMap.get(borrow.getUser_id()));
            borrowVo.setBookName(bookMap.get(borrow.getBook_id()));
            borrowVos.add(borrowVo);
        }
        return borrowVos;
    }
}
