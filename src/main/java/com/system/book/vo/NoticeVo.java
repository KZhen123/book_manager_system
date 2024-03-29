package com.system.book.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeVo {
    private int id;
    private String content;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private Date time;
    private String username;
    private String title;
}
