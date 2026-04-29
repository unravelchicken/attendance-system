package com.example.attendance.controller;

import com.example.attendance.dao.AttendanceRepository;
import com.example.attendance.dao.CourseRepository;
import com.example.attendance.dao.CourseSelectionRepository;
import com.example.attendance.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 测试接口：查询所有表数据，验证数据库连接
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseSelectionRepository courseSelectionRepository;
    @Autowired
    private AttendanceRepository attendanceRepository;

    // 1. 查询所有用户
    @GetMapping("/user")
    public Object findAllUser(){
        return userRepository.findAll();
    }

    // 2. 查询所有课程
    @GetMapping("/course")
    public Object findAllCourse(){
        return courseRepository.findAll();
    }

    // 3. 查询所有选课记录
    @GetMapping("/selection")
    public Object findAllSelection(){
        return courseSelectionRepository.findAll();
    }

    // 4. 查询所有考勤记录
    @GetMapping("/attendance")
    public Object findAllAttendance(){
        return attendanceRepository.findAll();
    }
}