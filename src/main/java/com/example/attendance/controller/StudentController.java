package com.example.attendance.controller;

import com.example.attendance.common.Result;
import com.example.attendance.pojo.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.attendance.pojo.AttendanceRecord;
import java.util.Arrays;
import java.util.List;

@RestController
public class StudentController {

    // 接口1：获取学生信息（GET请求）
    @GetMapping("/student/info")
    public String getStudentInfo() {
        return "姓名：蒋沛珍，学号：42411169，班级：计科2班";
    }

    // 接口2：考勤打卡（POST请求）
    @PostMapping("/student/attendance")
    public String checkAttendance(@RequestBody String studentId) {
        return "学号为 " + studentId + " 的学生打卡成功！";
    }

    // 接口3：获取课程列表（GET请求）
    @GetMapping("/student/courses")
    public List<String> getCourses() {
        return Arrays.asList("SpringBoot开发实践", "计算机网络", "数据结构", "数据库原理");
    }

    // 1. 路径参数：根据学号查询学生
    @GetMapping("/student/{id}")
    public Result<Student> getStudentById(@PathVariable String id) {
        Student student = new Student();
        student.setStudentId(id);
        student.setName("蒋沛珍");
        student.setClassName("计科2班");
        student.setAge(20);
        return Result.success(student);
    }
    // 2. 查询参数：根据班级和页码查询学生列表
    @GetMapping("/student/search")
    public Result<List<Student>> searchStudent(
            @RequestParam String className,
            @RequestParam(defaultValue = "1") int page
    ) {
        // 模拟班级学生列表
        List<Student> students = Arrays.asList(
                new Student("42411169", "蒋沛珍", className, 20),
                new Student("42411170", "张三", className, 21),
                new Student("42411171", "李四", className, 20)
        );
        return Result.success(students);
    }
    // 3. JSON体参数：更新考勤记录
    @PostMapping("/attendance/update")
    public Result<String> updateAttendance(@RequestBody AttendanceRecord record) {
        // 模拟更新逻辑
        System.out.println("收到打卡数据：" + record);
        return Result.success("学号 " + record.getStudentId() + " 打卡成功！状态：" + record.getStatus());
    }
}