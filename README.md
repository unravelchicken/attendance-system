# 考勤系统（SpringBoot 版本）
## 项目介绍
这是基于 SpringBoot 开发的考勤系统接口模块，实现了考勤打卡、考勤记录查询/更新等核心接口功能，用于完成编程作业的接口开发需求。

## 技术栈
- 核心框架：SpringBoot 2.x
- 构建工具：Maven 3.6+
- 开发语言：Java 8 及以上
- 接口测试：HTTP 文件（test.http）

## 快速开始
### 环境要求
- JDK 8 及以上版本
- Maven 3.6+（或 IDE 自带 Maven 环境）
- 开发工具：IntelliJ IDEA / Eclipse

### 运行步骤
1. 克隆仓库到本地：
   ```bash
   # GitHub 仓库
   git clone https://github.com/unravelchicken/attendance-system.git
   # Gitee 仓库（备用）
   git clone https://gitee.com/jiang-peizhen/attendance-system.git
2.用 IDE 打开项目，等待 Maven 自动下载依赖（首次打开可能需要几分钟）
3.运行项目启动类 AttendanceSystemApplication.java
4.接口测试：通过 IDEA 自带的 HTTP 客户端运行 test.http 文件，或用 Postman 调用接口

项目目录结构
 attendance-system/
├── src/main/java/                # 核心代码目录
│   └── com/xxx/attendance/       # 业务包路径（根据实际包名调整）
│       ├── controller/          # 接口控制器层
│       ├── entity/              # 实体类（如AttendanceRecord、Student）
│       ├── util/                # 工具类（如Result返回结果封装）
│       └── AttendanceSystemApplication.java  # 项目启动类
├── src/main/resources/           # 配置文件目录
│   └── application.properties    # 项目配置（端口、数据源等）
├── test.http                     # 接口测试文件
├── pom.xml                       # Maven 依赖配置
└── README.md                     # 项目说明文档  

作者信息
作者：unravelchicken
代码仓库：
GitHub：https://github.com/unravelchicken/attendance-system
Gitee：https://gitee.com/jiang-peizhen/attendance-system
