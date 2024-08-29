package com.apis.proto.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class UserGrpcClient {
    public static void main(String[] args) {
        // 스텁에 대한 gRPC 채널을 생성하고 연결하려는 서버 주소와 포트를 지정
        // 채널을 생성하려면 ManagedChannelBuilder를 사용합니다.
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        UserGrpcClientCaller userGrpcClientCaller = new UserGrpcClientCaller(channel);
        userGrpcClientCaller.sendUserRequest();
    }
}
