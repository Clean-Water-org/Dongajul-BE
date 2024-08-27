package com.apis.proto.client;

import com.apis.grpc.UserGrpc;
import com.apis.grpc.UserRequest;
import com.apis.grpc.UserResponse;
import io.grpc.ManagedChannel;

public class UserGrpcClientCaller {
    private final UserGrpc.UserBlockingStub blockingStub;

    public UserGrpcClientCaller(ManagedChannel chl) {
        blockingStub = UserGrpc.newBlockingStub(chl);
    }

    public void sendUserRequest() {
        System.out.println(">>> Send User Request");

        UserResponse response = blockingStub.getUser(UserRequest.newBuilder()
                .setId("user-1234")
                .build());

        System.out.println(">>> User Response: " + response);
    }
}
