package com.app.ktf.blog.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptUtil {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // Hash a raw password
    public static String hash(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    // Verify if raw password matches the encoded one
    public static boolean match(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}
