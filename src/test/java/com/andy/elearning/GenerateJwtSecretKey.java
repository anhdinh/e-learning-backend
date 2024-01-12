package com.andy.elearning;

import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GenerateJwtSecretKey {

    public static String generateSecretKey(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));

            byte[] keyBytes = new byte[16];
            System.arraycopy(hash, 0, keyBytes, 0, 16);

            SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");

            StringBuilder hexString = new StringBuilder();
            for (byte b : secretKey.getEncoded()) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException | java.io.UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Test
    public void generateSecret(){
        String jwtSecret = generateSecretKey("daaaaaaaaaaadatsdfajsdjasjdjasdjajsd");
        System.out.println(jwtSecret);
    }
}
