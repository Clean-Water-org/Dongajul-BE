package com.dongajul.user.adapter.out.persistence;

import com.dongajul.user.adapter.out.persistence.jpa.entity.UserEntity;
import com.dongajul.user.domain.User;
import com.dongajul.user.domain.User.UserId;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    User mapToDomainEntity(UserEntity userEntity) {
        return User.builder()
                .id(new UserId(userEntity.getId()))
                .userName(userEntity.getUserName())
                .isAuthenticatedPhone(userEntity.isAuthenticatedPhone())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .phone(userEntity.getPhone())
                .isSleeper(userEntity.isSleeper())
                .isDeleted(userEntity.isDeleted())
                .build();
    }

    UserEntity mapToJpaEntity(User user) {
        return new UserEntity(
                user.getId() == null ? null : user.getId().getValue(),
                user.getUserName(),
                user.isAuthenticatedPhone(),
                user.getEmail(),
                user.getPassword(),
                user.getPhone(),
                user.isSleeper(),
                user.isDeleted()
        );
    }
}
