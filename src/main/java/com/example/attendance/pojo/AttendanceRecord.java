package com.example.attendance.pojo;

import lombok.Data;

// Lombok 注解：自动生成 getter/setter/toString 等方法
@Data
public class AttendanceRecord {
    // 学号
    private String studentId;
    // 打卡时间（格式示例：2026-03-18 08:20）
    private String checkInTime;
    // 打卡状态：正常/迟到/缺勤
    private String status;
}