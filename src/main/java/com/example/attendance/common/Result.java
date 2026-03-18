package com.example.attendance.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    // 状态码：200成功，400客户端错误，500服务器错误
    private Integer code;
    // 响应消息
    private String msg;
    // 响应数据（泛型：可以是对象、列表、null）
    private T data;

    // 成功响应（带数据）
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "操作成功", data);
    }

    // 成功响应（不带数据，只返回消息）
    public static <T> Result<T> success(String msg) {
        return new Result<>(200, msg, null);
    }

    // 失败响应
    public static <T> Result<T> error(String msg) {
        return new Result<>(500, msg, null);
    }
}