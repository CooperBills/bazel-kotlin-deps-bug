package com.example

import com.example.Service.HelloRequest
import io.grpc.netty.GrpcSslContexts
import io.grpc.netty.NettyChannelBuilder
import javax.net.ssl.SSLException

object KotlinExample {

  @Throws(SSLException::class)
  @JvmStatic
  fun main(args: Array<String>) {
    println("Hello From Kotlin")
    val channel = NettyChannelBuilder
        .forAddress("", 1234)
        .sslContext(GrpcSslContexts.forClient().build())
        .build()
    val stub = GreeterGrpc.newBlockingStub(channel)
    val request = HelloRequest.newBuilder().setName("Hello").build()
    stub.sayHello(request)
  }
}
