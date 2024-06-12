package storage.persistence.repository;

import domain.domain.User;
import domain.mapper.UserMapper;
import domain.repository.RegisterUserRepository;

public class RegisterUserRepositoryImpl implements RegisterUserRepository {
    public void save(User user) {
        // Save user to database
    }

    @Override
    public void save(UserMapper userMapper) {

    }
}
