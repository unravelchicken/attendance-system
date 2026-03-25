package com.example.attendance.service;

import com.example.attendance.pojo.Student;

// 面向接口编程：只定义方法，不写实现
public interface StudentService {
    /**
     * 新增学生（带业务校验）
     * @param student 学生对象
     * @return 成功信息
     */
    String createStudent(Student student);

    /**
     * 根据学号查询学生
     * @param studentId 学号
     * @return 学生对象
     */
    Student getStudentById(String studentId);
}