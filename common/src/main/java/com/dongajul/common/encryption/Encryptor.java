package com.dongajul.common.encryption;

import org.springframework.stereotype.Component;

@Component
public interface Encryptor {

    String encrypt(String plainText, byte[] salt);

    boolean isMatch(String encryptedText, byte[] salt);

    default byte[] generateSalt16Byte() {
        byte[] salt = new byte[16];
        for (int i = 0; i < 16; i++) {
            salt[i] = (byte) (Math.random() * 256);
        }
        return salt;
    }

}
