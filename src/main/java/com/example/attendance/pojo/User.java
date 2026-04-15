package com.example.attendance.pojo;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 用户实体类：JPA注解适配SQL Server [user]表
 * user是SQL Server关键字，必须加[]避免语法错误
 */
@Data // Lombok：自动生成getter/setter/toString
@Entity // 标记为JPA实体类，JPA自动扫描映射
@Table(name = "[user]") // 指定对应数据库表名
public class User {

    // 主键ID
    @Id // 标记为主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // SQL Server专用：自增策略
    @Column(name = "id") // 对应数据库列名
    private Long id;

    // 用户名
    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    // 密码
    @Column(name = "password", nullable = false, length = 100)
    private String password;

    // 真实姓名
    @Column(name = "real_name", length = 50)
    private String realName;

    // 角色（admin/teacher/student）
    @Column(name = "role", length = 20)
    private String role;

    // 创建时间
    @Column(name = "create_time")
    private LocalDateTime createTime;
}