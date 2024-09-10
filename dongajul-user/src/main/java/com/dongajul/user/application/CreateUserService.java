package com.dongajul.user.application;

import com.dongajul.common.UseCase;
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
        User user = User.builder()
                         .email(command.email())
                         .userName(command.userName())
                         .password(command.password())  //TODO 비밀번호 암호화 필요
                         .phone(command.phone())
                         .build();

        User createdUser = createUserPort.createUser(user);

        return true;
    }
}
