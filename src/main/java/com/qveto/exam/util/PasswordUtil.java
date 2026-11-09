package com.qveto.exam.util;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public final class PasswordUtil {
    private static final Argon2 argon2 = Argon2Factory.create();

    private PasswordUtil() {}
    
    public static String hashPassword(char[] rawPassword) {
        if(isBlank(rawPassword)) {
            throw new IllegalArgumentException();
        }
        // Synchronous Gatekeeper Pattern combined with a Token Bucket Rate Limiter
        try {
            return argon2.hash(4, 65536, 2, rawPassword);
        } finally {
            argon2.wipeArray(rawPassword);
        }
    }

    public static boolean verifyPassword(char[] rawPassword, String hashedPassword) {
        if(isBlank(rawPassword) || isBlank(hashedPassword)) return false;
        return argon2.verify(hashedPassword, rawPassword);
    }

    private static boolean isBlank(char[] array) {
        if(array == null || array.length == 0) return true;
        for(char c : array) {
            if(!Character.isWhitespace(c)) return false;
        }
        return true;
    }

    private static boolean isBlank(String string) {
        return string == null || string.isBlank();
    }
}
