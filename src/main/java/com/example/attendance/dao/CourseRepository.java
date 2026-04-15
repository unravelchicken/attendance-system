package com.example.attendance.dao;

import com.example.attendance.pojo.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, String> {
    // 无需写代码，自动拥有查询/新增/删除/修改方法
}