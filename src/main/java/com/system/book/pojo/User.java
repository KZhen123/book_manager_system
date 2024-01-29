package com.system.book.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// getter setter equals hashcode
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private Integer id;

    private String name;

    private String password;

    private Integer admin;

    private Integer availableNum;


    private String phone;
}
