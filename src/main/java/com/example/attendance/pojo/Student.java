package com.example.attendance.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 自动生成 getter/setter、toString、equals、hashCode
@Data
// 自动生成全参构造器
@AllArgsConstructor
// 自动生成无参构造器
@NoArgsConstructor
public class Student {
    private String studentId;
    private String name;
    private String className;
    private Integer age;
}