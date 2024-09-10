package com.dongajul.user.domain;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class User {

    private final UserId id;
    private final String userName;
    private final boolean isAuthenticatedPhone;
    private final String email;
    private final String password;
    private final String phone;
    private final boolean isSleeper;
    private final boolean isDeleted;

    @Value
    public static class UserId {
        UUID value;
    }
}
