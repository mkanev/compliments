package com.yanka.goodcauses.security;

import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


/**
 * Simple password encoder that uses a salt value and encodes the password with SHA-256.
 *
 * @author Philip W. Sorst <philip@sorst.net>
 */
public class SaltedSHA512PasswordEncoder implements PasswordEncoder {

    private final String salt;

    public SaltedSHA512PasswordEncoder(String salt) throws NoSuchAlgorithmException {
        this.salt = salt;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        String saltedPassword = rawPassword + this.salt;
        try {
            return Sha512DigestUtils.shaHex(saltedPassword.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported");
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return this.encode(rawPassword).equals(encodedPassword);
    }

}
