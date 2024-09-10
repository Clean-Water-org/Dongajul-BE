package com.dongajul.user.application.port.in;

import com.dongajul.user.application.port.in.model.CreateUserCommand;

public interface CreateUserUseCase {
    boolean createUser(CreateUserCommand command);
}
