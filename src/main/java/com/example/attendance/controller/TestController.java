package com.example.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // user 是关键字，必须加 []
    @GetMapping("/test/user")
    public List<Map<String, Object>> testUser() {
        System.out.println("===== 查询用户表 =====");
        return jdbcTemplate.queryForList("select * from [user]");
    }

    @GetMapping("/test/course")
    public List<Map<String, Object>> testCourse() {
        System.out.println("===== 查询课程表 =====");
        return jdbcTemplate.queryForList("select * from course");
    }

    @GetMapping("/test/selection")
    public List<Map<String, Object>> testSelection() {
        System.out.println("===== 查询选课表 =====");
        return jdbcTemplate.queryForList("select * from course_selection");
    }
}