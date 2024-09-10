package com.dongajul.user.application.port.out;

import com.dongajul.user.domain.User;

public interface CreateUserPort {
    User createUser(User user);
}
