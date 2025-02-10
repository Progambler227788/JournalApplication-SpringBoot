package com.talha.journal.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTests {
    @Autowired
    private RedisTemplate redisTemplate;

    // redis serializer and spring boot serializer are not same, they are different
    // so we can't get same key value both will be behaving differently
    // default serializer and deserializer are used


    @Test
    void testSend(){
        redisTemplate.opsForValue().set("email","data1@gmail.com");
        Object obj = redisTemplate.opsForValue().get("pop");
    }

}
