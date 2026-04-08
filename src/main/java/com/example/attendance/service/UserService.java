package com.example.attendance.service;

import com.example.attendance.pojo.User;
import java.util.List;

/**
 * 用户业务逻辑接口
 */
public interface UserService {
    /**
     * 新增用户
     */
    void addUser(User user);

    /**
     * 更新用户
     */
    void updateUser(User user);

    /**
     * 根据ID删除用户
     */
    void deleteUserById(Long id);

    /**
     * 根据ID查询用户
     */
    User getUserById(Long id);

    /**
     * 根据用户名查询用户
     */
    User getUserByUsername(String username);

    /**
     * 查询所有用户
     */
    List<User> getAllUsers();

    /**
     * 根据角色查询用户
     */
    List<User> getUsersByRole(String role);

    /**
     * 统计某角色的用户数量
     */
    Integer countUsersByRole(String role);
}