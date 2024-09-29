package com.dongajul.common.encryption;

import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;

import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Argon2Encryptor implements Encryptor{

    private final int ITERATIONS = 2;
    private final int MEM_LIMIT = 66536;
    private final int HASH_LENGTH = 32;
    private final int PARALLELISM = 1;


    @Override
    public String encrypt(String plainText, byte[] salt) {
        // String password = "Baeldung"; // plainText

        Argon2Parameters.Builder builder = getArgon2ParametersBuilder(salt);

        Argon2BytesGenerator generate = new Argon2BytesGenerator();
        generate.init(builder.build());
        byte[] result = new byte[HASH_LENGTH];
        generate.generateBytes(plainText.getBytes(StandardCharsets.UTF_8), result, 0, result.length);

        return Arrays.toString(result);
    }

    @Override
    public boolean isMatch(String encryptedText, byte[] salt) {

        Argon2Parameters.Builder builder = getArgon2ParametersBuilder(salt);

        Argon2BytesGenerator verifier = new Argon2BytesGenerator();
        verifier.init(builder.build());
        byte[] testHash = new byte[HASH_LENGTH];
        verifier.generateBytes(encryptedText.getBytes(StandardCharsets.UTF_8), testHash, 0, testHash.length);
        return encryptedText.equals(Arrays.toString(testHash));
    }

    private Argon2Parameters.Builder getArgon2ParametersBuilder(byte[] salt) {
        return new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
                .withVersion(Argon2Parameters.ARGON2_VERSION_13)
                .withIterations(ITERATIONS)
                .withMemoryAsKB(MEM_LIMIT)
                .withParallelism(PARALLELISM)
                .withSalt(salt);
    }

}
