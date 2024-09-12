package com.dongajul.user.application.port.in.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CreateUserCommand(    //입력 유효성 검증
                                    @NotBlank String userName,
                                    @NotNull boolean isAuthenticatedPhone,
                                    @NotBlank String email,
                                    @NotBlank @Pattern(regexp = "(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{10,16}") String password,
                                    @NotBlank String phone,
                                    @NotNull boolean isSleeper,
                                    @NotNull boolean isDeleted
) {
    public CreateUserCommand(
            String userName,
            boolean isAuthenticatedPhone,
            String email,
            String password,
            String phone,
            boolean isSleeper,
            boolean isDeleted
    ) {
        this.userName = userName;
        this.isAuthenticatedPhone = isAuthenticatedPhone;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.isSleeper = isSleeper;
        this.isDeleted = isDeleted;
    }
}


