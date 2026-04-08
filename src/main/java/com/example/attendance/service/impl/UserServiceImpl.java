package com.example.attendance.service.impl;

import com.example.attendance.dao.UserDao;
import com.example.attendance.pojo.User;
import com.example.attendance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * UserService实现类：封装Dao，添加业务逻辑
 */
@Service // 标记为业务逻辑层，Spring自动扫描
public class UserServiceImpl implements UserService {

    // 注入UserDao
    @Autowired
    private UserDao userDao;

    /**
     * 1. 新增用户
     */
    @Override
    public void addUser(User user) {
        // 校验：用户名不能为空
        if (!StringUtils.hasLength(user.getUsername())) {
            throw new RuntimeException("用户名不能为空");
        }
        // 校验：用户名不能重复
        User existUser = null;
        try {
            existUser = userDao.findByUsername(user.getUsername());
        } catch (Exception e) {
            // 查不到用户，说明用户名可用，忽略异常
        }
        if (existUser != null) {
            throw new RuntimeException("用户名已存在");
        }
        // 自动设置创建时间
        user.setCreateTime(LocalDateTime.now());
        // 调用Dao新增用户
        userDao.insert(user);
    }

    /**
     * 2. 更新用户
     */
    @Override
    public void updateUser(User user) {
        // 校验：用户ID不能为空
        if (user.getId() == null) {
            throw new RuntimeException("用户ID不能为空");
        }
        userDao.update(user);
    }

    /**
     * 3. 根据ID删除用户
     */
    @Override
    public void deleteUserById(Long id) {
        userDao.deleteById(id);
    }

    /**
     * 4. 根据ID查询用户
     */
    @Override
    public User getUserById(Long id) {
        return userDao.findById(id);
    }

    /**
     * 5. 根据用户名查询用户
     */
    @Override
    public User getUserByUsername(String username) {
        return userDao.findByUsername(username);
    }

    /**
     * 6. 查询所有用户
     */
    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    /**
     * 7. 根据角色查询用户
     */
    @Override
    public List<User> getUsersByRole(String role) {
        return userDao.findByRole(role);
    }

    /**
     * 8. 统计某角色的用户数量
     */
    @Override
    public Integer countUsersByRole(String role) {
        return userDao.countByRole(role);
    }
}