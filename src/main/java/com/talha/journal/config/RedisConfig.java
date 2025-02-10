package com.talha.journal.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

// when we use bean functions, we need to use configuration annotation
@Configuration
public class RedisConfig {

    // spring boot will automatically invoke it
    // if we provide argument, it will be injected

    // if i type different name other than redisTemplate in function then it does not sync with redis cli
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory factory){
        RedisTemplate redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        // key, value both used as a string
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        return  redisTemplate;

    }
}
