package com.dongajul.user.application;

import com.apis.grpc.UserGrpc;
import com.apis.grpc.UserRequest;
import com.apis.grpc.UserResponse;
import com.dongajul.user.adapter.out.persistence.jpa.entity.User;
import com.dongajul.user.adapter.out.persistence.jpa.repository.UserRepository;
import io.grpc.stub.StreamObserver;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserGrpcService extends UserGrpc.UserImplBase {
    private final UserRepository userRepository;
    
    @Override
    public void getUser(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        User user = userRepository.findById(UUID.fromString(request.getId()))
                                  .orElseThrow(NullPointerException::new);

        UserResponse userResponse = UserResponse.newBuilder()
                                         .setUserName(user.getUserName())
                                         .setIsAuthenticatedPhone(user.isAuthenticatedPhone())
                                         .setEmail(user.getEmail())
                                         .setPassword(user.getPassword())
                                         .setPhone(user.getPhone())
                                         .setIsSleeper(user.isSleeper())
                                         .setIsDeleted(user.isDeleted())
                                         .build();
    }
}
