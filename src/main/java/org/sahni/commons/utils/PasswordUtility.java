package org.sahni.commons.utils;

import io.quarkus.elytron.security.common.BcryptUtil;

public class PasswordUtility {

    // Hash the password using BCrypt
    public static String hashPassword(String password) {
        return BcryptUtil.bcryptHash(password);
    }

    // Check if the entered password matches the stored hashed password
    public static boolean checkPassword(String password, String hashed) {
        return BcryptUtil.matches(password, hashed);
    }
}
