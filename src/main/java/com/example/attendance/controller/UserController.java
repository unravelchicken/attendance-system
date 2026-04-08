package com.example.attendance.controller;

import com.example.attendance.pojo.User;
import com.example.attendance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user") // 接口统一前缀：/user
public class UserController {

    // 注入UserService
    @Autowired
    private UserService userService;

    /**
     * 1. 新增用户（POST请求）
     * 接口地址：POST /user/add
     * 请求体：JSON格式的User对象
     */
    @PostMapping("/add")
    public String addUser(@RequestBody User user) {
        try {
            userService.addUser(user);
            return "新增用户成功！用户名：" + user.getUsername();
        } catch (Exception e) {
            return "新增用户失败：" + e.getMessage();
        }
    }

    /**
     * 2. 更新用户（PUT请求）
     * 接口地址：PUT /user/update
     * 请求体：JSON格式的User对象（必须带id）
     */
    @PutMapping("/update")
    public String updateUser(@RequestBody User user) {
        try {
            userService.updateUser(user);
            return "更新用户成功！ID：" + user.getId();
        } catch (Exception e) {
            return "更新用户失败：" + e.getMessage();
        }
    }

    /**
     * 3. 根据ID删除用户（DELETE请求）
     * 接口地址：DELETE /user/delete/{id}
     */
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUserById(id);
            return "删除用户成功！ID：" + id;
        } catch (Exception e) {
            return "删除用户失败：" + e.getMessage();
        }
    }

    /**
     * 4. 根据ID查询用户（GET请求）
     * 接口地址：GET /user/get/{id}
     */
    @GetMapping("/get/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    /**
     * 5. 根据用户名查询用户（GET请求，登录校验用）
     * 接口地址：GET /user/getByUsername/{username}
     */
    @GetMapping("/getByUsername/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    /**
     * 6. 查询所有用户（GET请求）
     * 接口地址：GET /user/list
     */
    @GetMapping("/list")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * 7. 根据角色查询用户（GET请求）
     * 接口地址：GET /user/listByRole/{role}
     */
    @GetMapping("/listByRole/{role}")
    public List<User> getUsersByRole(@PathVariable String role) {
        return userService.getUsersByRole(role);
    }

    /**
     * 8. 统计某角色的用户数量（GET请求）
     * 接口地址：GET /user/count/{role}
     */
    @GetMapping("/count/{role}")
    public Integer countUsersByRole(@PathVariable String role) {
        return userService.countUsersByRole(role);
    }
}