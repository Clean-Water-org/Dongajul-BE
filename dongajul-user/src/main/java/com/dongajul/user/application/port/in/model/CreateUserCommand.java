package com.dongajul.user.application.port.in.model;

import jakarta.validation.constraints.NotBlank;

public record CreateUserCommand(
    @NotBlank String email,
    @NotBlank String userName,
    @NotBlank String password,
    @NotBlank String phone
) {
    public CreateUserCommand(
            String email,
            String userName,
            String password,
            String phone
    ) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.phone = phone;
    }
}
