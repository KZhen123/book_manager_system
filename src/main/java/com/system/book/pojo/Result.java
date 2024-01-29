package com.system.book.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一返回对象
 */

@Data
public class Result<T> implements Serializable {

    private Integer code = 200;

    private String msg = "操作成功";


    private Integer count;

    private T data;

    /**
     * 通过静态方法获取实例
     */
    public static <T> Result<T> of(T data) {
        return new Result<>(data);
    }

    @Deprecated
    public Result() {

    }

    private Result(T data) {
        this.data = data;
    }

    public Result(int code,String msg) {
        this.msg = msg;
        this.code = code;
    }

    public Result(String msg) {
        this.msg = msg;
    }

    public Result(String msg, Integer count, T data) {
        this.msg = msg;
        this.count = count;
        this.data = data;
    }
}
