package com.andy.elearning;

import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class GenerateJwtSecretKey {

    private static byte[] generateSecretToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] secretTokenBytes = new byte[64];
        secureRandom.nextBytes(secretTokenBytes);
        return secretTokenBytes;
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexStringBuilder = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            hexStringBuilder.append(String.format("%02x", b & 0xff));
        }
        return hexStringBuilder.toString();
    }

    @Test
    public void generateSecret(){
        byte[] secretTokenBytes = generateSecretToken();
        String hexToken = bytesToHex(secretTokenBytes);
        System.out.println("Generated Secret Token: " + hexToken);
    }
}
