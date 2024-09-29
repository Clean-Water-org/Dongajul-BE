package com.dongajul.common.encryption;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootApplication
class Argon2EncryptorTest {

    private final Argon2Encryptor argon2Encryptor = new Argon2Encryptor();

    @Test
    void encrypt() {
        String plainText = "Baeldung";
        byte[] salt = argon2Encryptor.generateSalt16Byte();
        String encryptedText = argon2Encryptor.encrypt(plainText, salt);
        System.out.println(encryptedText);
    }

    @Test
    void isMatch() {
    }
}