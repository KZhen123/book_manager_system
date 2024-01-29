package com.system.book.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookVo {
    private Integer id;
    private String name;
    private String author;
    private String publish;
    private int category;
    private String categoryName;
    private int num;
}
