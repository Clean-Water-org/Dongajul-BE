package com.apis.proto.client;

import com.apis.grpc.UserGrpc;
import com.apis.grpc.UserListRequest;
import com.apis.grpc.UserRequest;
import com.apis.grpc.UserResponse;
import io.grpc.ManagedChannel;
import java.util.Iterator;

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

    public void sendUserListRequest() {
        System.out.println(">>> Send User List Request");

        Iterator<UserResponse> userList = blockingStub.getUserList(UserListRequest.newBuilder()
                                                                                  .setPage(1)
                                                                                  .setSize(25)
                                                                                  .setSortStandard(
                                                                                          "name")
                                                                                  .setSort("desc")
                                                                                  .build());

        //응답 출력
        userList.forEachRemaining(userResponse -> {
            System.out.println(">>> User List Response: " + userResponse);
        });
    }
}
