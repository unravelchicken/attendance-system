package com.example.attendance.dao;

import com.example.attendance.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

// 第八周必须继承 JpaSpecificationExecutor 才能做多条件查询
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    // 🔴 必须加：按用户名查询（JPA自动根据方法名生成SQL）
    User findByUsername(String username);
}