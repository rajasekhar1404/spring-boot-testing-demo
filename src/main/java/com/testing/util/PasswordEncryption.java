package com.testing.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncryption {

    private static PasswordEncoder encoder;

    public PasswordEncryption(PasswordEncoder encoder) {
         this.encoder = encoder;
    }

    public static String encrypt(String password) {
        return encoder.encode(password);
    }

}
