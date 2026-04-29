package com.example.attendance.pojo;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "[user]")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 100) // 密码长度改100，存加密后的字符串
    private String password;

    @Column(name = "real_name", length = 50)
    private String realName;

    @Column(name = "role", length = 20) // 角色：admin/teacher/student
    private String role;

    @Column(name = "create_time")
    private LocalDateTime createTime;
}