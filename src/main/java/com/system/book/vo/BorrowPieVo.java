package com.system.book.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowPieVo {
    private Long value; // 借阅数

    private String name; // 类别
}
