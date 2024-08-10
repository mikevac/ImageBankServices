package com.vac.main.encoders;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class IBPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return hashSha512(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(encode(rawPassword));
    }

    private String hashSha512(String rawPassword) {
        StringBuilder b = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] digest = md.digest(rawPassword.getBytes());
            for (int i = 0; i < digest.length; i++) {
                b.append(Integer.toHexString(0xFF & digest[i]));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error selecting algorithm");
        }
        return b.toString();
    }

}
