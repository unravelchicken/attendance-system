package com.example.attendance.dao;

import com.example.attendance.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 新增学生到 SQL Server
    public void insert(Student student) {
        String sql = "INSERT INTO student (student_id, name, class_name) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, student.getStudentId(), student.getName(), student.getClassName());
    }

    // 根据学号查询学生
    public Student findById(String studentId) {
        try {
            String sql = "SELECT * FROM student WHERE student_id = ?";
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Student.class), studentId);
        } catch (Exception e) {
            return null; // 查不到返回 null
        }
    }
}