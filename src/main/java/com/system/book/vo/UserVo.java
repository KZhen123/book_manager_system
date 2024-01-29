package com.system.book.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    private Integer id;

    private String name;


    private String phone;

    private Integer availableNum;

    private Integer admin;
}
