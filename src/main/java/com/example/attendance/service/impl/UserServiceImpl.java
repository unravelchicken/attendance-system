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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    //注入密码加密器
    @Autowired
    private PasswordEncoder passwordEncoder;

    // ====================== 基础CRUD（修改addUser方法，加密码加密） ======================
    @Override
    public void addUser(User user) {
        if (!StringUtils.hasLength(user.getUsername())) throw new RuntimeException("用户名不能为空");
        User exist = userRepository.findByUsername(user.getUsername());
        if (exist != null) throw new RuntimeException("用户名已存在");
        // 新增给密码自动加密（注册时用）
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreateTime(LocalDateTime.now());
        // 默认角色设为student（注册用户默认是学生）
        if (!StringUtils.hasLength(user.getRole())) {
            user.setRole("student");
        }
        userRepository.save(user);
    }

    // 其他方法（updateUser/deleteUser/getUserById等）不用改，保留之前的就行
    @Override
    public void updateUser(User user) {
        if (user.getId() == null) throw new RuntimeException("ID不能为空");
        // 如果更新了密码，也要加密（可选，注册用的多）
        if (StringUtils.hasLength(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }

    // 下面的分页/排序/多条件方法，保留第八周的代码，不用改
    @Override
    public Page<User> getUserByPage(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.by(Sort.Direction.DESC, "createTime"));
        return userRepository.findAll(pageable);
    }

    @Override
    public List<User> getUserBySort(String sortField, boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortField);
        return userRepository.findAll(sort);
    }

    @Override
    public List<User> getUserByConditions(String username, String role, LocalDateTime startTime, LocalDateTime endTime) {
        return userRepository.findAll((Specification<User>) (root, query, cb) -> {
            List<jakarta.persistence.criteria.Predicate> predicates = new java.util.ArrayList<>();
            if (StringUtils.hasLength(username)) {
                predicates.add(cb.like(root.get("username"), "%" + username + "%"));
            }
            if (StringUtils.hasLength(role)) {
                predicates.add(cb.equal(root.get("role"), role));
            }
            if (startTime != null && endTime != null) {
                predicates.add(cb.between(root.get("createTime"), startTime, endTime));
            }
            return query.where(predicates.toArray(new jakarta.persistence.criteria.Predicate[0])).getRestriction();
        });
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

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}