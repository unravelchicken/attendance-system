package com.example.attendance.dao;

import com.example.attendance.pojo.User;
import java.util.List;

public interface UserDao {
    void insert(User user);
    void update(User user);
    void deleteById(Long id);

    User findById(Long id);
    User findByUsername(String username);

    List<User> findAll();
    List<User> findByRole(String role);

    Integer countByRole(String role);
}