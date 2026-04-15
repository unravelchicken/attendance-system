package com.example.attendance.dao;

import com.example.attendance.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

/**
 * 用户Repository：继承JpaRepository，自动获得所有CRUD方法
 * 泛型：<实体类类型, 主键类型> → <User, Long>
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 自定义查询1：根据用户名查用户（登录校验用）
     * 方法名规则：findBy + 属性名 → JPA自动生成SQL
     */
    Optional<User> findByUsername(String username);

    /**
     * 自定义查询2：根据角色查用户列表
     */
    List<User> findByRole(String role);

    /**
     * 自定义查询3：根据角色统计用户数（@Query手写JPQL，复杂查询用）
     */
    @Query("SELECT COUNT(u) FROM User u WHERE u.role = :role")
    Integer countByRole(@Param("role") String role);
}