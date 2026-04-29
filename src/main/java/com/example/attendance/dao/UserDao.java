package com.example.attendance.dao;

import com.example.attendance.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 根据用户名查询用户
    public User findByUsername(String username) {
        try {
            // 注意：SQL Server 中 user 是关键字，所以必须加 []，写成 [user]
            String sql = "SELECT * FROM [user] WHERE username = ?";
            // 使用 BeanPropertyRowMapper 自动将结果集映射为 User 对象
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
        } catch (Exception e) {
            return null; // 查询不到该用户时，返回 null
        }
    }
}
