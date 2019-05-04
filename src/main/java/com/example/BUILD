load("@io_grpc_grpc_java//:java_grpc_library.bzl", "java_grpc_library")
load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_binary")

java_binary(
    name = "java_example",
    srcs = [
        "JavaExample.java",
    ],
    main_class = "com.example.JavaExample",
    deps = [
        "protos_java",
        "protos_java_grpc",
        "@com_google_protobuf//:protobuf_java",
        "@com_google_protobuf//:protobuf_java_util",
        "@io_grpc_grpc_java//core",
        "@io_grpc_grpc_java//netty",
        "@io_grpc_grpc_java//protobuf",
        "@io_grpc_grpc_java//stub",
    ],
)

kt_jvm_binary(
    name = "kotlin_example",
    srcs = [
        "KotlinExample.kt",
    ],
    main_class = "com.example.KotlinExample",
    deps = [
        "protos_java",
        "protos_java_grpc",
        "@com_google_protobuf//:protobuf_java",
        "@com_google_protobuf//:protobuf_java_util",
        "@io_grpc_grpc_java//core",
        #        "@io_grpc_grpc_java//core:internal",
        "@io_grpc_grpc_java//netty",
        "@io_grpc_grpc_java//protobuf",
        "@io_grpc_grpc_java//stub",
        #        "@io_netty_netty_handler//jar",
    ],
)

proto_library(
    name = "protos",
    srcs = ["service.proto"],
)

java_proto_library(
    name = "protos_java",
    deps = ["protos"],
)

java_grpc_library(
    name = "protos_java_grpc",
    srcs = ["protos"],
    deps = ["protos_java"],
)
