package com.dongajul.user.application.grpc.server;

import com.dongajul.user.application.UserGrpcService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

public class UserGrpcServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        //클라이언트 요청을 수신하는데 사용할 포트 지정
        Server grpcServer = ServerBuilder.forPort(8080)
                                         .addService(new UserGrpcService())  //서비스 구현 클래스의 인스턴스를 생성하여 .addServcie() 메서드에 전달
                                         .build();

        grpcServer.start();
        grpcServer.awaitTermination();
    }
}