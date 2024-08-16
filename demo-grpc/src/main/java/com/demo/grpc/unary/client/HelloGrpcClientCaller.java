package com.demo.grpc.unary.client;

import com.demo.grpc.HelloGrpc;
import com.demo.grpc.HelloRequest;
import com.demo.grpc.HelloResponse;
import io.grpc.ManagedChannel;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 클라이언트 동작 코드
 */
@Slf4j
public class HelloGrpcClientCaller {

    private ManagedChannel channel;
    private HelloGrpc.HelloBlockingStub blockingStub;
    private HelloGrpc.HelloStub asyncStub;

//    private String name;
//    private int age;
//    private String message;

    //생성한 채널로 stub 생성
    public HelloGrpcClientCaller(ManagedChannel chl) {
        channel = chl;
        blockingStub = HelloGrpc.newBlockingStub(channel);
        asyncStub = HelloGrpc.newStub(channel);
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


    private final String[] names = {
            "herojoon",
            "yejin",
            "jonghoon",
            "sophia",
            "woojin"
    };

    private int minAge = 1;
    private int maxAge = 100;

    // min 나이부터 max 나이까지 랜덤 값 return
    private int getRandomAge() {
        return (int) (Math.random() * (maxAge - minAge + 1)) + minAge;
    }

    public void sendClientStreamingAsync() throws InterruptedException {
        log.info(">>> Client Streaming: Send Call");

        // 요청 데이터 목록 생성
        List<HelloRequest> helloRequestList = new ArrayList<>();
        for(String name : names) {
            helloRequestList.add(
                    HelloRequest.newBuilder()
                            .setName(name)
                            .setAge(getRandomAge())
                            .setMessage("Hello~~~!")
                            .build()
            );
        }

        final CountDownLatch finishLatch = new CountDownLatch(1);
        StreamObserver<HelloResponse> responseObserver = new StreamObserver<>() {
            @Override
            public void onNext(HelloResponse helloResponse) {
                // 서버 응답 출력
                log.info(">>> Response Data => [%S]".formatted(helloResponse));
            }

            @Override
            public void onError(Throwable throwable) { //스트림에서 종료 오류 발생 시 수신
                Status status = Status.fromThrowable(throwable);
                log.warn(">>> Warning => [%s]".formatted(status));
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() { //스트림이 성공적으로 완료되었다고 응답 받음
                log.info(">>> Finished");
                finishLatch.countDown();
            }
        };

        StreamObserver<HelloRequest> requestObserver = asyncStub.lotsOfGreetings(responseObserver);
        try {
            for (HelloRequest helloRequest : helloRequestList) {
                requestObserver.onNext(helloRequest);
                log.info(">>> Req Name: " + helloRequest.getName());

                Thread.sleep(1000);
                if (finishLatch.getCount() == 0) {  // 오류 발생 시 다음 코드를 전송하더라도 처리되지 않기 때문에 전송하지 않도록 처리
                    log.info(">>> Stop the next request");
                    return;
                }
            }
        }catch (RuntimeException e) {
            requestObserver.onError(e);
            throw e;
        }
        //요청 끝
        requestObserver.onCompleted();

        finishLatch.await(1, TimeUnit.MINUTES);

        log.info(">>> End");
    }

    public void sendBidirectionalStreamingAsync() throws InterruptedException {
        log.info(">>> Bidirectional Streaming: Send Call");

        //요청 데이터 목록 생성
        List<HelloRequest> helloRequestList = new ArrayList<>();
        for(String name : names) {
            helloRequestList.add(
                    HelloRequest.newBuilder()
                            .setName(name)
                            .setAge(getRandomAge())
                            .setMessage("Hello~~!")
                            .build()
            );
        }

        final CountDownLatch finishLatch = new CountDownLatch(1);
        StreamObserver<HelloResponse> responseObserver = new StreamObserver<>() {

            @Override
            public void onNext(HelloResponse helloResponse) {
                //서버 응답
                log.info(">>> Response Data => [%S]".formatted(helloResponse));
            }

            @Override
            public void onError(Throwable throwable) {  //스트림에서 종료 오류 발생 시 수신
                Status status = Status.fromThrowable(throwable);
                log.warn(">>> Warning => [%s]".formatted(status));
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() { //스트림이 성공적으로 완료되었을 때 응답
                log.info(">>> Finished");
                finishLatch.countDown();
            }
        };

        StreamObserver<HelloRequest> requestObserver = asyncStub.bidiHello(responseObserver);
        try {
            for (HelloRequest req: helloRequestList) {
                requestObserver.onNext(req);
            }
        } catch (RuntimeException e) {
            //RPC 취소
            requestObserver.onError(e);
            throw e;
        }
        requestObserver.onCompleted();

        finishLatch.await(1, TimeUnit.MINUTES);
    }

}
