package com.demo.grpc.unary.server;

import com.demo.grpc.HelloGrpc;
import com.demo.grpc.HelloRequest;
import com.demo.grpc.HelloResponse;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

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

    // Client Streaming RPC (클라이언트 스트리밍 RPC)
    @Override
    public StreamObserver<HelloRequest> lotsOfGreetings(StreamObserver<HelloResponse> responseObserver) {
        return new StreamObserver<HelloRequest>() {
            List<String> nameList = new ArrayList<>();

            int i = 0; //강제 오류 발생 테스트를 위한 변수(바로 오류를 발생시키지 않고 중간에 오류 발생시키기 위해 사용)

            //요청 데이터 처리
            @Override
            public void onNext(HelloRequest helloRequest) {
                nameList.add(helloRequest.getName());   //응답 시 전달을 위해 요청 데이터의 name 값을 nameList에 담는다
                log.info("=== name is %s [age: %d]".formatted(helloRequest.getName(), helloRequest.getAge()));

                //강제 오류 발생 테스트를 위한 코드
//                i++;
//                if(i == 2) {
//                    try {
//                        throw new Exception("Error Error Error!!");
//                    }catch (Exception e) {
//                        throw new RuntimeException(e);
//                    }
//                }
            }

            //오류 처리
            @Override
            public void onError(Throwable throwable) {
                log.warn("=== Warning => [%s]".formatted(throwable.getMessage()));
            }

            //응답 전달
            @Override
            public void onCompleted() {
                responseObserver.onNext(HelloResponse.newBuilder()
                        .setGreetingMessage("Hello, %s".formatted(String.join(",", nameList)))
                        .setQuestionMessage("What do you do for fun?")
                        .build());

                responseObserver.onCompleted();
            }
        };
    }

    //Bidirectional Streaming RPC (양방향 스트리밍 RPC)
    @Override
    public StreamObserver<HelloRequest> bidiHello(StreamObserver<HelloResponse> responseObserver) {
        return new StreamObserver<HelloRequest>() {
            @Override
            public void onNext(HelloRequest helloRequest) {
                log.info("=== name is %s".formatted(helloRequest.getName()));

                responseObserver.onNext(HelloResponse.newBuilder()
                        .setGreetingMessage("Hello, %s".formatted(helloRequest.getName()))
                        .setQuestionMessage("What do you do for fun?")
                        .build());
            }

            @Override
            public void onError(Throwable throwable) {
                log.warn("=== Warning => [%s]".formatted(throwable.getMessage()));
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }

}
