package com.yanka.goodcauses.rest;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class TokenUtils {

    public static final String MAGIC_KEY = "obfuscate";
    public static final String TOKEN_DIVIDER = ":";
    private static final int USERNAME_POSITION = 0;
    private static final int EXPIRES_POSITION = 1;
    private static final int SIGNATURE_POSITION = 2;

    public static String createToken(UserDetails userDetails) {
        long expires = System.currentTimeMillis() + 1000L * 60 * 60; // One hour
        StringBuilder tokenBuilder = new StringBuilder(userDetails.getUsername())
            .append(TOKEN_DIVIDER).append(expires)
            .append(TOKEN_DIVIDER).append(TokenUtils.computeSignature(userDetails, expires));
        return tokenBuilder.toString();
    }

    public static String computeSignature(UserDetails userDetails, long expires) {
        StringBuilder signatureBuilder = new StringBuilder(userDetails.getUsername())
            .append(TOKEN_DIVIDER).append(expires)
            .append(TOKEN_DIVIDER).append(userDetails.getPassword())
            .append(TOKEN_DIVIDER).append(TokenUtils.MAGIC_KEY);

        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No MD5 algorithm available!");
        }

        return new String(Hex.encode(digest.digest(signatureBuilder.toString().getBytes())));
    }

    public static String getUserNameFromToken(String authToken) {
        if (null == authToken) {
            return null;
        }
        return authToken.split(TOKEN_DIVIDER)[USERNAME_POSITION];
    }

    public static boolean validateToken(String authToken, UserDetails userDetails) {
        String[] parts = authToken.split(TOKEN_DIVIDER);
        long expires = Long.parseLong(parts[EXPIRES_POSITION]);
        String signature = parts[SIGNATURE_POSITION];
        return expires >= System.currentTimeMillis() && signature.equals(TokenUtils.computeSignature(userDetails, expires));

    }
}
