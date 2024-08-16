package com.demo.grpc.unary.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * 클라이언트 애플리케이션 Main
 */
public class HelloGrpcClient {

    public static void main(String[] args) {
        /**
         * 채널 생성
         */
        //스텁에 대한 gPRC 채널을 생성하고 연결하려는 서버 주소와 포트를 지정
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        HelloGrpcClientCaller helloGrpcClientCaller = new HelloGrpcClientCaller(channel);
        helloGrpcClientCaller.sendUnaryBlocking();

    }
}
