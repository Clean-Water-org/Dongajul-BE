package com.dongajul.user.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public interface CreateUserDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    class Request {

        @NotNull
        private boolean isAuthenticatedPhone;

        @NotBlank
        private String email;

        @NotBlank
        private String nickName;

        @NotBlank
        private String password;

        @NotBlank
        private String name;

        @NotBlank
        private String phone;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    class Response {
        private Boolean isSucceed;
    }
}
