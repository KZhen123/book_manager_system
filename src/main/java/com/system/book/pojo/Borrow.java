package com.system.book.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
public class Borrow {
    private Integer id;
    private int user_id;
    private int book_id;

    private Date borrowTime;

    private Date backTime;
    private Date endTime;

    public Borrow(Integer id, int user_id, int book_id, Date borrowTime, Date endTime) throws ParseException {
        this.id = id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.borrowTime = borrowTime;
        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = fmt.parse("1900-01-01 00:00:00");
        this.backTime = date;
        this.endTime = endTime;
    }
}
