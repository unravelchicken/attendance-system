package com.example.attendance.service.impl;

import com.example.attendance.dao.UserRepository;
import com.example.attendance.pojo.User;
import com.example.attendance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * UserService实现类：JPA版，用UserRepository替代JdbcTemplate
 * 无需写SQL，代码大幅简化
 */
@Service
public class UserServiceImpl implements UserService {

    // 注入UserRepository（JPA自动实现，直接用）
    @Autowired
    private UserRepository userRepository;

    /**
     * 1. 新增用户（用户名校验+自动设置创建时间）
     */
    @Override
    public void addUser(User user) {
        // 校验用户名非空
        if (!StringUtils.hasLength(user.getUsername())) {
            throw new RuntimeException("用户名不能为空");
        }
        // 校验用户名不重复
        Optional<User> existUser = userRepository.findByUsername(user.getUsername());
        if (existUser.isPresent()) {
            throw new RuntimeException("用户名已存在");
        }
        // 自动设置创建时间
        user.setCreateTime(LocalDateTime.now());
        // JPA一行代码完成新增，不用写SQL！
        userRepository.save(user);
    }

    /**
     * 2. 更新用户
     */
    @Override
    public void updateUser(User user) {
        if (user.getId() == null) throw new RuntimeException("用户ID不能为空");
        // 校验用户存在
        if (!userRepository.existsById(user.getId())) {
            throw new RuntimeException("用户不存在，无法更新");
        }
        // JPA一行代码完成更新
        userRepository.save(user);
    }

    /**
     * 3. 按ID删除用户
     */
    @Override
    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("用户不存在，无法删除");
        }
        // JPA一行代码完成删除
        userRepository.deleteById(id);
    }

    /**
     * 4. 按ID查询用户
     */
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("用户不存在"));
    }

    /**
     * 5. 按用户名查询用户（登录用）
     */
    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    /**
     * 6. 查询所有用户
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * 7. 按角色查询用户
     */
    @Override
    public List<User> getUsersByRole(String role) {
        return userRepository.findByRole(role);
    }

    /**
     * 8. 按角色统计用户数
     */
    @Override
    public Integer countUsersByRole(String role) {
        return userRepository.countByRole(role);
    }
}