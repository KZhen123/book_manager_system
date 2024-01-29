package com.system.book.service.impl;

import com.system.book.dao.BookDao;
import com.system.book.dao.CategoryDao;
import com.system.book.dao.UserDao;
import com.system.book.dao.impl.BookDaoImpl;
import com.system.book.dao.impl.CategoryDaoImpl;
import com.system.book.dao.impl.UserDaoImpl;
import com.system.book.pojo.Book;
import com.system.book.pojo.Borrow;
import com.system.book.pojo.Category;
import com.system.book.pojo.User;
import com.system.book.service.BookService;
import com.system.book.service.BorrowService;
import com.system.book.vo.BookVo;
import org.springframework.beans.BeanUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

    private CategoryDao categoryDao = new CategoryDaoImpl();

    private UserDao userDao = new UserDaoImpl();

    private BorrowService borrowService = new BorrowServiceImpl();

    @Override
    public boolean addBook(Book book) {
        return bookDao.insert(book);
    }

    @Override
    public List<BookVo> selectPage(int size, int page, String name) {
        int begin = (page - 1) * size;
        List<Book> books = bookDao.selectPage(size, begin, name);
        List<Category> categories = categoryDao.selectAll();
        // list转map
        Map<Integer, String> categoryMap = categories.stream()
                .collect(Collectors.toMap(Category::getId, category -> category.getName()));
        List<BookVo> bookVos = new ArrayList<>();
        for (Book book : books) {
            BookVo bookVo = new BookVo();
            BeanUtils.copyProperties(book, bookVo);
            // 通过目录的id获取name
            bookVo.setCategoryName(categoryMap.get(book.getCategory()));
            bookVos.add(bookVo);
        }
        return bookVos;
    }

    @Override
    public int selectCount(String name) {
        return bookDao.selectCount(name);
    }

    @Override
    public boolean deleteBook(int id) {
        return bookDao.deleteBook(id);
    }

    @Override
    public boolean updateBook(Book book) {
        return bookDao.updateBook(book);
    }

    @Override
    public boolean borrowBook(int id, User user) throws ParseException {
        /*
         * 1.  book的num扣减
         * 2.  user的aviilableNum扣减
         * 3.  新增一条borrow
         * */
        Book bookById = getBookById(id);
        boolean update1 = bookDao.updateNum(id, bookById.getNum() - 1);
        boolean update2 = userDao.updateNum(user.getId(), user.getAvailableNum() - 1);
        // 当前Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String borrowTime = dateFormat.format(new Date());
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date borrowDate = fmt.parse(borrowTime);
        // 截止时间为一个月后
        // 获取当前截止时间Calendar
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(borrowDate);
        // 加一个月
        calendar.add(Calendar.MONTH, 1);
        // 转Date
        Date endDate = calendar.getTime();
        Borrow borrow = new Borrow(null, user.getId(), id, borrowDate, endDate);
        boolean update3 = borrowService.insert(borrow);

        return update1 && update2 && update3;
    }

    @Override
    public Book getBookById(int id) {
        return bookDao.getBookById(id);
    }


}
