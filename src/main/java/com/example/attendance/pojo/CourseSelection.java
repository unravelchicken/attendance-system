package com.example.attendance.pojo;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "course_selection")
public class CourseSelection {

    // 主键自增（SQL Server）
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "student_id", length = 20, nullable = false)
    private String studentId;

    @Column(name = "student_name", length = 50, nullable = false)
    private String studentName;

    @Column(name = "gender", length = 10)
    private String gender;

    @Column(name = "course_id", length = 20, nullable = false)
    private String courseId;

    @Column(name = "select_time")
    private LocalDateTime selectTime;
}