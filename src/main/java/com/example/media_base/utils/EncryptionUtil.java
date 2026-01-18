//package com.example.media_base.utils;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//public class EncryptionUtil {
//
//    @Autowired
//    private static PasswordEncoder passwordEncoder;
//
//    public static String encode(String raw) {
//        return passwordEncoder.encode(raw);
//    }
//
//    public static boolean matches(String raw, String hashed) {
//        System.out.println("encoder: " + passwordEncoder);
//        return passwordEncoder.matches(raw, hashed);
//    }
//}
