package application.web.controller;

import domain.useCase.RegisterUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
class RegisterController {
    private final RegisterUserUseCase registerUserUseCase;

    @PostMapping("/users")
    void register() {
        registerUserUseCase.register();

    }

}
