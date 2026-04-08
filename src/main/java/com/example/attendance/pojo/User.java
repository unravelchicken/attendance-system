package com.example.attendance.pojo;

import lombok.Data;
import java.time.LocalDateTime;

@Data // 自动生成getter/setter/toString，项目已集成Lombok
public class User {
    // 用户ID
    private Long id;
    // 用户名
    private String username;
    // 密码
    private String password;
    // 真实姓名
    private String realName;
    // 角色（admin/teacher/student）
    private String role;
    // 创建时间
    private LocalDateTime createTime;
}