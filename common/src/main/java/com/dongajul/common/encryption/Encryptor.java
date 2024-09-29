package com.dongajul.common.encryption;

import org.springframework.stereotype.Component;

@Component
public interface Encryptor {

    int getSaltLength();

    String encrypt(String plainText, byte[] salt);

    boolean isMatch(String plainText, String encryptedText, byte[] salt);

    default byte[] generateSaltByte() {
        byte[] salt = new byte[getSaltLength()];
        for (int i = 0; i < 16; i++) {
            salt[i] = (byte) (Math.random() * 256);
        }
        return salt;
    }

    default String encodeBase64(byte[] bytes) {
        return java.util.Base64.getEncoder().encodeToString(bytes);
    }

    default byte[] decodeBase64(String base64) {
        return java.util.Base64.getDecoder().decode(base64);
    }

}
