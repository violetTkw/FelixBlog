package com.felix;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * ClassName:FelixBlogApplication
 * Package:com.felix
 * Description:
 *
 * @Author FelixT
 * @Create 2024/4/4 20:32
 * @Version 1.0
 */
@SpringBootApplication
@EnableScheduling
public class FelixBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(FelixBlogApplication.class,args);
    }
}
