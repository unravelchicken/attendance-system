package com.example.attendance.controller;

import com.example.attendance.common.Result;
import com.example.attendance.pojo.Student;
import com.example.attendance.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
// Controller层，加@RestController
@RestController
@RequestMapping("/student")
public class StudentController {
    // 注入Service（面向接口编程）
    @Autowired
    private StudentService studentService;
    // 把启动类里的hello接口移到这里
    @GetMapping("/hello")
    public String hello() {
        return "欢迎来到班级考勤管理系统！";
    }
    // 把启动类里的about接口移到这里
    @GetMapping("/about")
    public String about() {
        return "姓名：蒋沛珍，专业：计算机科学与技术";
    }
    // 学生接口
    @PostMapping("/create")
    public Result<String> createStudent(@RequestBody Student student) {
        try {
            String result = studentService.createStudent(student);
            return Result.success(result);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    // 查询学生接口
    @GetMapping("/{id}")
    public Result<Student> getStudentById(@PathVariable String id) {
        Student student = studentService.getStudentById(id);
        return Result.success(student);
    }
}