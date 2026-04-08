package com.example.attendance.dao;

import com.example.attendance.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // 标记为数据访问组件
public class UserDaoImpl implements UserDao {

    // 注入 JdbcTemplate
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 1. 新增用户
     */
    @Override
    public void insert(User user) {
        String sql = "INSERT INTO [user] (username, password, real_name, role, create_time) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                user.getUsername(),
                user.getPassword(),
                user.getRealName(),
                user.getRole(),
                user.getCreateTime()
        );
    }

    /**
     * 2. 更新用户
     */
    @Override
    public void update(User user) {
        String sql = "UPDATE [user] SET password = ?, real_name = ?, role = ? WHERE id = ?";
        jdbcTemplate.update(
                sql,
                user.getPassword(),
                user.getRealName(),
                user.getRole(),
                user.getId()
        );
    }

    /**
     * 3. 根据ID删除用户
     */
    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM [user] WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    /**
     * 4. 根据ID查询用户
     */
    @Override
    public User findById(Long id) {
        String sql = "SELECT * FROM [user] WHERE id = ?";
        return jdbcTemplate.queryForObject(
                sql,
                new BeanPropertyRowMapper<>(User.class),
                id
        );
    }

    /**
     * 5. 根据用户名查询用户
     */
    @Override
    public User findByUsername(String username) {
        String sql = "SELECT * FROM [user] WHERE username = ?";
        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(User.class),
                    username
            );
        } catch (Exception e) {
            return null; // 查不到返回null，不报错
        }
    }

    /**
     * 6. 查询所有用户
     */
    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM [user]";
        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(User.class)
        );
    }

    /**
     * 7. 根据角色查询用户
     */
    @Override
    public List<User> findByRole(String role) {
        String sql = "SELECT * FROM [user] WHERE role = ?";
        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(User.class),
                role
        );
    }

    /**
     * 8. 统计某角色的用户数量
     */
    @Override
    public Integer countByRole(String role) {
        String sql = "SELECT COUNT(*) FROM [user] WHERE role = ?";
        return jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                role
        );
    }
}