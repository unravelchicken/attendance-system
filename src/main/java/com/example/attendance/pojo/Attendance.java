package com.example.attendance.pojo;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "student_id", length = 20, nullable = false)
    private String studentId;

    @Column(name = "course_id", length = 20, nullable = false)
    private String courseId;

    @Column(name = "check_in_time", nullable = false)
    private LocalDateTime checkInTime;

    @Column(name = "seat_row")
    private Byte seatRow;

    @Column(name = "seat_col")
    private Byte seatCol;

    @Column(name = "status", length = 20, nullable = false)
    private String status;

    @Column(name = "ip", length = 15)
    private String ip;

    @Column(name = "create_time")
    private LocalDateTime createTime;
}