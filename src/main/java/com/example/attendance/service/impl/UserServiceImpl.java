package com.example.attendance.service.impl;

import com.example.attendance.dao.UserRepository;
import com.example.attendance.pojo.User;
import com.example.attendance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    // ====================== 基础CRUD（保留第七周的） ======================
    @Override
    public void addUser(User user) {
        if (!StringUtils.hasLength(user.getUsername())) throw new RuntimeException("用户名不能为空");
        User exist = userRepository.findByUsername(user.getUsername());
        if (exist != null) throw new RuntimeException("用户名已存在");
        user.setCreateTime(LocalDateTime.now());
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        if (user.getId() == null) throw new RuntimeException("ID不能为空");
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ====================== 第八周：JPA高级查询（重点！） ======================

    /**
     * 1. 分页查询（默认按创建时间倒序）
     * @param pageNum 页码（从1开始）
     * @param pageSize 每页条数
     */
    @Override
    public Page<User> getUserByPage(int pageNum, int pageSize) {
        // 构造分页对象：PageRequest.of(页码, 每页条数, 排序)
        // 注意：JPA页码从0开始，所以 pageNum - 1
        Pageable pageable = PageRequest.of(
                pageNum - 1,
                pageSize,
                Sort.by(Sort.Direction.DESC, "createTime") // 按创建时间倒序
        );
        return userRepository.findAll(pageable);
    }

    /**
     * 2. 排序查询
     * @param sortField 排序字段（如 id, username, create_time）
     * @param isAsc true=正序 false=倒序
     */
    @Override
    public List<User> getUserBySort(String sortField, boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortField);
        return userRepository.findAll(sort);
    }

    /**
     * 3. 多条件查询（动态拼接条件）
     * @param username 用户名（模糊查询）
     * @param role 角色（精确查询）
     * @param startTime 开始时间
     * @param endTime 结束时间
     */
    @Override
    public List<User> getUserByConditions(String username, String role, LocalDateTime startTime, LocalDateTime endTime) {
        // Specification 是一个查询条件构造器
        return userRepository.findAll((Specification<User>) (root, query, criteriaBuilder) -> {
            // 1. 拼接用户名模糊查询：like %username%
            List<jakarta.persistence.criteria.Predicate> predicates = new java.util.ArrayList<>();

            if (StringUtils.hasLength(username)) {
                predicates.add(criteriaBuilder.like(root.get("username"), "%" + username + "%"));
            }

            // 2. 拼接角色精确查询：role = ?
            if (StringUtils.hasLength(role)) {
                predicates.add(criteriaBuilder.equal(root.get("role"), role));
            }

            // 3. 拼接时间范围查询：create_time between ? and ?
            if (startTime != null && endTime != null) {
                predicates.add(criteriaBuilder.between(root.get("createTime"), startTime, endTime));
            }

            // 把所有条件组合起来
            return query.where(predicates.toArray(new jakarta.persistence.criteria.Predicate[0])).getRestriction();
        });
    }
}