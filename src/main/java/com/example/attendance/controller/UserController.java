package com.example.attendance.controller;

import com.example.attendance.pojo.User;
import com.example.attendance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // ====================== 基础接口（保留） ======================
    @PostMapping("/add")
    public String addUser(@RequestBody User user) {
        try { userService.addUser(user); return "新增成功"; } catch (Exception e) { return "失败：" + e.getMessage(); }
    }

    @PutMapping("/update")
    public String updateUser(@RequestBody User user) {
        try { userService.updateUser(user); return "更新成功"; } catch (Exception e) { return "失败：" + e.getMessage(); }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        try { userService.deleteUserById(id); return "删除成功"; } catch (Exception e) { return "失败：" + e.getMessage(); }
    }

    @GetMapping("/get/{id}")
    public User getById(@PathVariable Long id) { return userService.getUserById(id); }

    @GetMapping("/list")
    public List<User> getAll() { return userService.getAllUsers(); }

    // ====================== 第八周：高级测试接口（重点！） ======================

    /**
     * 接口1：分页查询
     * 访问：http://localhost:8080/user/page?pageNum=1&pageSize=2
     */
    @GetMapping("/page")
    public Page<User> getUserByPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "2") int pageSize
    ) {
        return userService.getUserByPage(pageNum, pageSize);
    }

    /**
     * 接口2：排序查询
     * 访问：http://localhost:8080/user/sort?sortField=id&isAsc=false
     */
    @GetMapping("/sort")
    public List<User> getUserBySort(
            @RequestParam String sortField,
            @RequestParam(defaultValue = "false") boolean isAsc
    ) {
        return userService.getUserBySort(sortField, isAsc);
    }

    /**
     * 接口3：多条件查询
     * 访问：http://localhost:8080/user/condition?username=admin&role=admin&startTime=2026-01-01T00:00:00&endTime=2026-12-31T23:59:59
     */
    @GetMapping("/condition")
    public List<User> getUserByCondition(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String role,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime startTime,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime endTime
    ) {
        return userService.getUserByConditions(username, role, startTime, endTime);
    }
}