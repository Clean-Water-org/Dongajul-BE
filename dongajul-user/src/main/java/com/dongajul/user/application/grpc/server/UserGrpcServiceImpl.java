package com.dongajul.user.application.grpc.server;

import com.apis.grpc.UserGrpc;
import com.apis.grpc.UserListRequest;
import com.apis.grpc.UserRequest;
import com.apis.grpc.UserResponse;
import com.dongajul.user.adapter.out.persistence.jpa.entity.UserEntity;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserGrpcServiceImpl extends UserGrpc.UserImplBase {
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

    //StreamObserver 인터페이스: 클라이언트로부터 데이터를 수신하고, 응답을 반환
    @Override
    public void getUserList(UserListRequest request, StreamObserver<UserResponse> responseObserver) {
        System.out.println("=== Get UserList Request");
        System.out.println("=== List Request Data" + request);

        List<UserEntity> userEntityList = new ArrayList<>();

        for (int i=0; i<3; i++) {
            userEntityList.add(new UserEntity(UUID.randomUUID(),
                                              "이름" + i,
                                              true,
                                              "grace"+i+"@gmail.com",
                                              "pw1234",
                                              "0101234123"+i,
                                              false,
                                              false));
        }

        //응답 데이터 세팅
        for(UserEntity userEntity : userEntityList) {
            UserResponse userResponse = UserResponse.newBuilder()
                    .setUserName(userEntity.getUserName())
                    .setIsAuthenticatedPhone(userEntity.isAuthenticatedPhone())
                    .setEmail(userEntity.getEmail())
                    .setPassword(userEntity.getPassword())
                    .setPhone(userEntity.getPhone())
                    .setIsSleeper(userEntity.isSleeper())
                    .setIsDeleted(userEntity.isDeleted())
                    .build();

            responseObserver.onNext(userResponse);
        }
        responseObserver.onCompleted();
    }
}
