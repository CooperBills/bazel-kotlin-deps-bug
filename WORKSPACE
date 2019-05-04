load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")
load("@bazel_tools//tools/build_defs/repo:git.bzl", "git_repository")

# Google Protobuf
git_repository(
    name = "com_google_protobuf",
    commit = "v3.6.1.3",
    remote = "https://github.com/google/protobuf",
)

# Java GRPC
git_repository(
    name = "io_grpc_grpc_java",
    commit = "v1.20.0",
    remote = "https://github.com/grpc/grpc-java.git",
)

load("@io_grpc_grpc_java//:repositories.bzl", "grpc_java_repositories")

grpc_java_repositories()

# Kotlin Support
rules_kotlin_version = "59dc7473c777b5054e91c1af6b95ed0ecbdc0ace"

http_archive(
    name = "io_bazel_rules_kotlin",
    strip_prefix = "rules_kotlin-%s" % rules_kotlin_version,
    type = "zip",
    urls = ["https://github.com/bazelbuild/rules_kotlin/archive/%s.zip" % rules_kotlin_version],
)

load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kotlin_repositories", "kt_register_toolchains")

kotlin_repositories()

kt_register_toolchains()
