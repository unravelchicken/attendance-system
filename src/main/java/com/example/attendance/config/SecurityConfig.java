package com.example.attendance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 1. 密码加密器（和PPT示例一致，用于BCrypt加密验证）
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. 认证管理器（解决AuthController注入问题）
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // 3. 核心安全配置（放行路径+表单登录+成功跳转）
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 关闭CSRF（方便接口测试）
                .csrf(csrf -> csrf.disable())
                // 放行关键路径（注册接口、欢迎页无需登录）
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**", "/welcome.html").permitAll()
                        .anyRequest().authenticated()
                )
                // 表单登录配置（用Spring Security自带登录页）
                .formLogin(form -> form
                        .permitAll()
                        // 登录成功后直接跳转到欢迎页（和你朋友的界面一致）
                        .defaultSuccessUrl("/welcome.html", true)
                );

        return http.build();
    }
}