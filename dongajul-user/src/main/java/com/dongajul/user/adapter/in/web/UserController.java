package com.dongajul.user.adapter.in.web;

import com.dongajul.user.adapter.in.web.dto.CreateUserDto;
import com.dongajul.user.application.port.in.CreateUserUseCase;
import com.dongajul.user.application.port.in.model.CreateUserCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final CreateUserUseCase createUserUseCase;

    @PostMapping("/createUser")
    public CreateUserDto.Response createUser(@Valid @RequestBody CreateUserDto.Request request) {
        CreateUserCommand createUserCommand = new CreateUserCommand(request.getName(),
                request.isAuthenticatedPhone(),
                request.getEmail(),
                request.getPassword(),
                request.getPhone(),
                false,
                false);

        boolean result = createUserUseCase.createUser(createUserCommand);

        return new CreateUserDto.Response(result);
    }
}
