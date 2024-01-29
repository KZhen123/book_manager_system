package com.system.book.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice {
    private Integer id;
    private String content;
    private Date time;
    private Integer user;
    private String title;
}
