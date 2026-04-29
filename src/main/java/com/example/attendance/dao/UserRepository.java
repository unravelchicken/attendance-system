package com.example.attendance.dao;

import com.example.attendance.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

// 继承 JpaSpecificationExecutor 做多条件查询
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    // 按用户名查询（JPA自动根据方法名生成SQL）
    User findByUsername(String username);
}