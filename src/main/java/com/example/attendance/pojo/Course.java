package com.example.attendance.pojo;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "course") // 表名
public class Course {

    // 主键：course_id 手动输入，不是自增
    @Id
    @Column(name = "course_id", length = 20)
    private String courseId;

    @Column(name = "course_name", nullable = false, length = 100)
    private String courseName;

    @Column(name = "teacher_id", nullable = false)
    private Long teacherId;

    @Column(name = "classroom_name", length = 50)
    private String classroomName;

    @Column(name = "rows")
    private Byte rows;

    @Column(name = "cols")
    private Byte cols;

    @Column(name = "exclude_seats", length = 200)
    private String excludeSeats;

    @Column(name = "weekday")
    private Byte weekday;

    @Column(name = "start_week")
    private Byte startWeek;

    @Column(name = "end_week")
    private Byte endWeek;

    @Column(name = "create_time")
    private LocalDateTime createTime;
}