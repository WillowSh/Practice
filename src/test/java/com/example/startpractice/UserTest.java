package com.example.startpractice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {
    @Test
    void contextLoads() {
    }
    @Test
    void TestMain(){
        new StartPracticeApplication().main(new String[]{});
    }
}
