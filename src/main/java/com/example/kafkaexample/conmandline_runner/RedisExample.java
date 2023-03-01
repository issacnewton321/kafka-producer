package com.example.kafkaexample.conmandline_runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisExample implements CommandLineRunner {
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void run(String... args) throws Exception {
        redisTemplate.opsForValue().set("quan", "Hong quan dep trai");
        //print key to screen

        System.out.println(redisTemplate.opsForValue().get("quan"));
    }
}
