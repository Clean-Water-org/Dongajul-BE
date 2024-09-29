package com.dongajul.common.encryption;

import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Argon2Encryptor implements Encryptor{

    private final int ITERATIONS = 2; // 반복 횟수
    private final int MEM_LIMIT = 66536; // 메모리 제한
    private final int HASH_LENGTH = 32; // 해시 길이
    private final int PARALLELISM = 1; // 병렬성 수

    @Override
    public int getSaltLength() { return 16; }

    @Override
    public String encrypt(String plainText, byte[] salt) {

        Argon2Parameters.Builder builder = getArgon2ParametersBuilder(salt);
        Argon2BytesGenerator generate = new Argon2BytesGenerator();

        generate.init(builder.build());
        byte[] result = new byte[HASH_LENGTH];
        generate.generateBytes(plainText.getBytes(StandardCharsets.UTF_8), result, 0, result.length);

        return encodeBase64(result);
    }

    @Override
    public boolean isMatch(String plainText, String encryptedText, byte[] salt) {

        Argon2Parameters.Builder builder = getArgon2ParametersBuilder(salt);
        Argon2BytesGenerator verifier = new Argon2BytesGenerator();

        verifier.init(builder.build());
        byte[] testHash = new byte[HASH_LENGTH];
        verifier.generateBytes(plainText.getBytes(StandardCharsets.UTF_8), testHash, 0, testHash.length);

        return Arrays.equals(decodeBase64(encryptedText), testHash);
    }

    private Argon2Parameters.Builder getArgon2ParametersBuilder(byte[] salt) {
        return new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
                .withVersion(Argon2Parameters.ARGON2_VERSION_13)
                .withIterations(ITERATIONS)
                .withMemoryAsKB(MEM_LIMIT)
                .withParallelism(PARALLELISM)
                .withSalt(salt);
    }

    public static void main(String[] args) {
        Argon2Encryptor argon2Encryptor = new Argon2Encryptor();
        String plainText = "Baeldung";

        String saltStr = argon2Encryptor.encodeBase64(argon2Encryptor.generateSaltByte());
        // save saltStr to database...
        // get saltStr from database...

        byte[] salt = argon2Encryptor.decodeBase64(saltStr);
        String encryptedText = argon2Encryptor.encrypt(plainText, salt);

        System.out.println(encryptedText);
        System.out.println("isMatch " + argon2Encryptor.isMatch(plainText, encryptedText, salt));
    }
}
