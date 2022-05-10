package com.ljm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName com.ljm.Application
 * @Description 启动类
 * @Author Jim
 * @Date 2022/2/21 16:18
 **/
@SpringBootApplication()
@MapperScan("com.ljm.mapper")
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
