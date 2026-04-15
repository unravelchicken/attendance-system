package com.example.attendance.dao;

import com.example.attendance.pojo.CourseSelection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseSelectionRepository extends JpaRepository<CourseSelection, Long> {
}