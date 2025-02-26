package com.myproject.journalApp;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
public class redisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Disabled
    @Test
    public void redistest() {
//        redisTemplate.opsForValue().set("email", "abc@gmail.com");
        Object email = redisTemplate.opsForValue().get("email");
        int a =2;
    }
}
