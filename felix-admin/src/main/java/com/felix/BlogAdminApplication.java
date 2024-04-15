package com.felix;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ClassName:BlogAdminApplication
 * Package:com.felix
 * Description:
 *
 * @Author FelixT
 * @Create 2024/4/13 10:05
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("com.felix.mapper")
public class BlogAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogAdminApplication.class,args);
    }
}
