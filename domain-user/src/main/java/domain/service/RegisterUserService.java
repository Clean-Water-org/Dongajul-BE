package domain.service;

import domain.domain.User;
import domain.mapper.UserMapper;
import domain.model.RegisterUserModel;
import domain.repository.RegisterUserRepository;
import domain.useCase.RegisterUserUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterUserService implements RegisterUserUseCase {

        private final RegisterUserRepository userRepository;

        public void register(RegisterUserModel registerUserModel) {

                // 도메인 객체를 통해서 검증
                User user = new User();


                // Register user
                userRepository.save(new UserMapper());
        }
}
