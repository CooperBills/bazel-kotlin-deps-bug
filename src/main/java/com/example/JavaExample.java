package com.example;

import com.example.GreeterGrpc.GreeterBlockingStub;
import com.example.Service.HelloRequest;
import io.grpc.ManagedChannel;
import io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.NettyChannelBuilder;
import javax.net.ssl.SSLException;

public class JavaExample {

  public static void main(String[] args) throws SSLException {
    System.out.println("Hello From Java");
    ManagedChannel channel = NettyChannelBuilder
        .forAddress("", 1234)
        .sslContext(GrpcSslContexts.forClient().build())
        .build();
    GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel);
    HelloRequest request = HelloRequest.newBuilder().setName("Hello").build();
    stub.sayHello(request);
  }
}
