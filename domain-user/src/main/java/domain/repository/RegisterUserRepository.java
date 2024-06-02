package domain.repository;

import domain.mapper.UserMapper;

public interface RegisterUserRepository {
    void save(UserMapper userMapper);
}
