package application.web.controller;

import domain.model.RegisterUserModel;
import domain.useCase.RegisterUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
class RegisterUserController {
    private final RegisterUserUseCase registerUserUseCase;

    @PostMapping("/users")
    void register(@RequestBody RegisterUserModel registerUserModel) {
        registerUserUseCase.register(new RegisterUserModel());
    }

}
