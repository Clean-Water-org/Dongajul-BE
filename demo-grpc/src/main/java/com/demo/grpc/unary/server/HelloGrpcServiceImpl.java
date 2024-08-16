package com.demo.grpc.unary.server;

import com.demo.grpc.HelloGrpc;
import com.demo.grpc.HelloRequest;
import com.demo.grpc.HelloResponse;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

//HelloGrpc : proto를 build할 때 protobuf에 의해 자동으로 생성된 추상 클래스
//-> gRPC를 이용하여 통신하는데 필요한 모든 절차를 대신 수행
/**
 * 서버 동작 (클라이언트에서 요청을 받음)
 */
@Slf4j
public class HelloGrpcServiceImpl extends HelloGrpc.HelloImplBase {

    //단방향 RPC
    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        log.info("=== Get Request");
        log.info("=== Request Data => [%s]".formatted(request));

        //응답 데이터 셋업
        HelloResponse response = HelloResponse.newBuilder()
                .setGreetingMessage("Hello, %s".formatted(request.getName())) //.proto에 정의한 response value
                .setQuestionMessage("What do you do for fun?")  //.proto에 정의한 response value
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private String[] questionMessages = {
            "What do you do for fun?",
            "What kind of books do you like?",
            "What kind of food do you like?",
            "What is your favorite color?",
            "What is your favorite sports?"
    };

    //Server Streaming RPC
    @Override
    public void lotsOfReplies(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        log.info("=== Get Replies");
        log.info("=== Request Data => [%s]".formatted(request));

        //응답 데이터 설정
        for(String questionMessage : questionMessages) {
            HelloResponse response = HelloResponse.newBuilder()
                    .setGreetingMessage("Hello, %s".formatted(request.getName()))
                    .setQuestionMessage(questionMessage)
                    .build();

            responseObserver.onNext(response);
        }

        responseObserver.onCompleted();
    }

}
