package com.dongajul.user.adapter.out.persistence;

import com.dongajul.user.adapter.out.persistence.jpa.entity.UserEntity;
import com.dongajul.user.adapter.out.persistence.jpa.repository.UserRepository;
import com.dongajul.user.application.port.out.CreateUserPort;
import com.dongajul.user.config.PersistenceAdapter;
import com.dongajul.user.domain.User;
import jakarta.persistence.EntityExistsException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class UserPersistenceAdapter implements CreateUserPort {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void createUser(User user) {

        //TODO 이메일, 닉네임 중복 확인
        Optional<UserEntity> checkUserExist = userRepository.findByEmail(user.getEmail());

        if (checkUserExist.isPresent()) {
            throw new EntityExistsException("User with email (" + user.getEmail() + ") already exists");
        }

        UserEntity userJpaEntity = userMapper.mapToJpaEntity(user);
        userMapper.mapToDomainEntity(userRepository.save(userJpaEntity));
    }
}
