package com.demo.grpc.unary.client;

import com.demo.grpc.HelloGrpc;
import com.demo.grpc.HelloRequest;
import com.demo.grpc.HelloResponse;
import io.grpc.ManagedChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;

/**
 * 클라이언트 동작 코드
 */
@Slf4j
public class HelloGrpcClientCaller {

    private ManagedChannel channel;
    private HelloGrpc.HelloBlockingStub blockingStub;

//    private String name;
//    private int age;
//    private String message;

    //생성한 채널로 stub 생성
    public HelloGrpcClientCaller(ManagedChannel chl) {
        channel = chl;
        blockingStub = HelloGrpc.newBlockingStub(channel);
    }

    public void sendUnaryBlocking() {
        log.info(">>> Unary: Send Call");

        HelloResponse response = blockingStub.sayHello(HelloRequest.newBuilder()
                .setName("soyoung") // .proto에 정의한 request value
                .setAge(25) // .proto에 정의한 request value
                .setMessage("Hello, Nice to meet you")  // .proto에 정의한 request value
                .build());

//        HelloResponse response = blockingStub.sayHello(HelloRequest.newBuilder()
//                .setName(name) // .proto에 정의한 request value
//                .setAge(age) // .proto에 정의한 request value
//                .setMessage(message)  // .proto에 정의한 request value
//                .build());

        log.info(">>> Response Data => [%S]".formatted(response));
//        return response;
    }

//    public void setHelloRequest(String name, int age, String message){
//        this.name = name;
//        this.age = age;
//        this.message = message;
//    }

    public void sendServerStreamingBlocking() {
        log.info(">>> Server Streaming: Send Call");

        //요청은 하나만 보내고, 여러 개의 응답을 받는다.
        Iterator<HelloResponse> helloResponseIterator = blockingStub.lotsOfReplies(HelloRequest.newBuilder()
                .setName("soyoung")
                .setAge(25)
                .setMessage("Hello!!")
                .build());

        helloResponseIterator.forEachRemaining(response -> {
            log.info(">>> Response Data => [%S]".formatted(response));
        });
    }

}
