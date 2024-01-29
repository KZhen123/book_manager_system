package com.system.book.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {
    private int id;
    private int user;
    private String title;
    private String page;
    private String content;
    private String phone;
    private int status;
    private Date time;
}
