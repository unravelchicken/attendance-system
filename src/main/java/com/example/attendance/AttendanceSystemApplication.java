package com.example.attendance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 只保留启动注解，去掉@RestController和所有接口方法
@SpringBootApplication
public class AttendanceSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttendanceSystemApplication.class, args);
	}

}