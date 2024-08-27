package com.apis.proto.server;

import com.apis.grpc.UserGrpc;
import com.apis.grpc.UserRequest;
import com.apis.grpc.UserResponse;
import io.grpc.stub.StreamObserver;

public class UserGrpcServiceImpl extends UserGrpc.UserImplBase {
    @Override
    public void getUser(UserRequest request, StreamObserver<UserResponse> responseObserver) {
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
}
