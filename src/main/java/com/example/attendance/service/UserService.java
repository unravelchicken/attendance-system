package com.example.attendance.service;

import com.example.attendance.pojo.User;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {

    // ================= 基础CRUD（第七周） =================
    void addUser(User user);
    void updateUser(User user);
    void deleteUserById(Long id);
    User getUserById(Long id);
    User getUserByUsername(String username);
    List<User> getAllUsers();

    // ================= 第八周：JPA高级查询（必须加！） =================
    /**
     * 分页查询
     * @param pageNum 页码（从1开始）
     * @param pageSize 每页条数
     */
    Page<User> getUserByPage(int pageNum, int pageSize);

    /**
     * 排序查询
     * @param sortField 排序字段（如 id, username, createTime）
     * @param isAsc true=正序 false=倒序
     */
    List<User> getUserBySort(String sortField, boolean isAsc);

    /**
     * 多条件查询
     * @param username 用户名
     * @param role 角色
     * @param startTime 开始时间
     * @param endTime 结束时间
     */
    List<User> getUserByConditions(String username, String role, LocalDateTime startTime, LocalDateTime endTime);
}