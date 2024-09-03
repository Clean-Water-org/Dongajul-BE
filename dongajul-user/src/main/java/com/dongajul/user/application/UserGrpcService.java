package com.dongajul.user.application;

import com.apis.grpc.UserGrpc;
import com.apis.grpc.UserListRequest;
import com.apis.grpc.UserRequest;
import com.apis.grpc.UserResponse;
import com.dongajul.user.adapter.out.persistence.jpa.entity.User;
import com.dongajul.user.adapter.out.persistence.jpa.repository.UserRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserGrpcService extends UserGrpc.UserImplBase {
//    private final UserRepository userRepository;
    
    @Override
    public void getUser(UserRequest request, StreamObserver<UserResponse> responseObserver) {
//        User user = userRepository.findById(UUID.fromString(request.getId()))
//                                  .orElseThrow(NullPointerException::new);
//
//        UserResponse userResponse = UserResponse.newBuilder()
//                                         .setUserName(user.getUserName())
//                                         .setIsAuthenticatedPhone(user.isAuthenticatedPhone())
//                                         .setEmail(user.getEmail())
//                                         .setPassword(user.getPassword())
//                                         .setPhone(user.getPhone())
//                                         .setIsSleeper(user.isSleeper())
//                                         .setIsDeleted(user.isDeleted())
//                                         .build();

        System.out.println("=== Get Request");
        System.out.println("=== Request Data" + request);

        UserResponse userResponse = UserResponse.newBuilder()
                                                .setUserName("윤소영")
                                                .setIsAuthenticatedPhone(true)
                                                .setEmail("grace9028@gmail.com")
                                                .setPassword("ggg1234")
                                                .setPhone("01026619302")
                                                .setIsSleeper(false)
                                                .setIsDeleted(false)
                                                .build();

        responseObserver.onNext(userResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void getUserList(UserListRequest request, StreamObserver<UserResponse> responseObserver) {
        System.out.println("=== Get UserList Request");
        System.out.println("=== List Request Data" + request);

        List<User> userList = new ArrayList<>();

        for (int i=0; i<3; i++) {
            userList.add(new User(UUID.randomUUID(),
                    "이름" + i,
                    true,
                    "grace"+i+"@gmail.com",
                    "pw1234",
                    "0101234123"+i,
                    false,
                    false));
        }

        //응답 데이터 세팅
        for(User user : userList) {
            UserResponse userResponse = UserResponse.newBuilder()
                    .setUserName(user.getUserName())
                    .setIsAuthenticatedPhone(user.isAuthenticatedPhone())
                    .setEmail(user.getEmail())
                    .setPassword(user.getPassword())
                    .setPhone(user.getPhone())
                    .setIsSleeper(user.isSleeper())
                    .setIsDeleted(user.isDeleted())
                    .build();

            responseObserver.onNext(userResponse);
        }
        responseObserver.onCompleted();
    }
}
