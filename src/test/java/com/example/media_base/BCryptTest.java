package com.example.media_base;


import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BCryptTest {

    @Test
    public void encoderTest() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "admin123";
        String encoded = encoder.encode(password);
        System.out.println(encoded);
        assertTrue(encoder.matches(password, encoded));
    }
}
