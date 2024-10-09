package com.dongajul.user.application;

import com.dongajul.common.UseCase;
import com.dongajul.common.encryption.Argon2Encryptor;
import com.dongajul.user.application.port.in.CreateUserUseCase;
import com.dongajul.user.application.port.in.model.CreateUserCommand;
import com.dongajul.user.application.port.out.CreateUserPort;
import com.dongajul.user.domain.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class CreateUserService implements CreateUserUseCase {

    private final CreateUserPort createUserPort;

    @Override
    public boolean createUser(CreateUserCommand command) {
        // 1. 비즈니스 규칙 검증
        // 2. 모델 상태 조작

        if(!command.isAuthenticatedPhone()){    //휴대전화 인증 필수
            return false;
        }

        //비밀번호 암호화
        Argon2Encryptor argon2Encryptor = new Argon2Encryptor();

        //TODO 회원 테이블에 salt 컬럼 추가 필요?
        String saltStr = argon2Encryptor.encodeBase64(argon2Encryptor.generateSaltByte());
        byte[] salt = argon2Encryptor.decodeBase64(saltStr);
        String encryptedPassword = argon2Encryptor.encrypt(command.password(), salt);

        User user = User.builder()
                         .email(command.email())
                         .userName(command.userName())
                         .password(encryptedPassword)
                         .phone(command.phone())
                         .isAuthenticatedPhone(true)
                         .build();

        createUserPort.createUser(user);

        return true;
    }
}
