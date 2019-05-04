# bazel-kotlin-deps

Example showing kotlin deps breaking on transitive internal dependencies in 3rd parties.

**Expected behavior:** Identical Java and Kotlin binaries both run.

**Actual behavior:** The Java binary runs as expected, but the Kotlin binary fails to compile.

Java run command: `bazel run //src/main/java/com/example:java_example`

Kotlin run command: `bazel run //src/main/java/com/example:kotlin_example`

Bazel Version: 0.24.1

```
error: supertypes of the following classes cannot be resolved. Please make sure you have the required dependencies in the classpath:
    class io.grpc.netty.NettyChannelBuilder, unresolved supertypes: io.grpc.internal.AbstractManagedChannelImplBuilder

src/main/java/com/example/KotlinExample.kt:16:10: error: cannot access class 'io.netty.handler.ssl.SslContext'. Check your module classpath for missing or conflicting dependencies
        .sslContext(GrpcSslContexts.forClient().build())
         ^
src/main/java/com/example/KotlinExample.kt:16:37: error: cannot access class 'io.netty.handler.ssl.SslContextBuilder'. Check your module classpath for missing or conflicting dependencies
        .sslContext(GrpcSslContexts.forClient().build())
                                    ^
src/main/java/com/example/KotlinExample.kt:16:49: error: unresolved reference: build
        .sslContext(GrpcSslContexts.forClient().build())
                                                ^
src/main/java/com/example/KotlinExample.kt:17:10: error: unresolved reference: build
        .build()
         ^
Target //src/main/java/com/example:kotlin_example failed to build

```

### Further investigation

Digging further into it, adding an additional dependency to the Kotlin binary definition (`"@io_netty_netty_handler//jar`) resolves _most_ of the errors. However, we still get:

```
error: supertypes of the following classes cannot be resolved. Please make sure you have the required dependencies in the classpath:
    class io.grpc.netty.NettyChannelBuilder, unresolved supertypes: io.grpc.internal.AbstractManagedChannelImplBuilder

src/main/java/com/example/KotlinExample.kt:17:10: error: unresolved reference: build
        .build()
         ^
Target //src/main/java/com/example:kotlin_example failed to build
```

The big problem here is that `AbstractManagedChannelImplBuilder` is in `@io_grpc_grpc_java//core:internal`, which is marked private, so we can't depend directly on it.
