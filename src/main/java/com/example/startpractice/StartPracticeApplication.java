package com.example.startpractice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.startpractice.dao")
//@ComponentScan("com.example.startpractice.dao")
public class StartPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartPracticeApplication.class, args);
    }

}
