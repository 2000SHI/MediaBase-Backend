package com.example.media_base;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest  // init beans before tests
public class RedisTest {

    @Autowired
    private StringRedisTemplate template;

    @Test
    public void test() {
        ValueOperations<String, String> operation = template.opsForValue();
        operation.set("user", "Yuki");
        assertEquals("Yuki", operation.get("user"));
        operation.getOperations().delete("user");
        assertNull(operation.get("user"));
    }
}
