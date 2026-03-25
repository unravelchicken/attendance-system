package com.example.attendance.service.impl;

import com.example.attendance.dao.StudentDao;
import com.example.attendance.pojo.Student;
import com.example.attendance.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// @Service：标记这是Service层组件，Spring自动管理对象
@Service
public class StudentServiceImpl implements StudentService {
    //  @Autowired：自动注入Dao层，Service调用Dao完成数据库操作
    @Autowired
    private StudentDao studentDao;
    @Override
    public String createStudent(Student student) {
        // 业务校验（作业要求：Service层做校验，Controller不写业务逻辑）
        if (student.getStudentId() == null || student.getStudentId().isEmpty()) {
            throw new RuntimeException("学号不能为空");
        }
        if (student.getName() == null || student.getName().isEmpty()) {
            throw new RuntimeException("姓名不能为空");
        }
        // 调用Dao层，把学生数据插入数据库
        studentDao.insert(student);
        return "学生创建成功";
    }

    @Override
    public Student getStudentById(String studentId) {
        // 调用Dao层查询学生
        return studentDao.findById(studentId);
    }
}