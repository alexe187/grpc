# DEZSYS_GK81_WAREHOUSE_GRPC

This lesson introduces the Remote Procedure Technology (RPC).

## Questions

What is gRPC and why does it work accross languages and platforms?

**gRPC** is a modern, open-source, high-performance Remote Procedure Call (RPC) framework that can run in any environment.

it uses **Protocol Buffers** (protobuf) as its interface definition language. Protobuf is language agnostic, which means you can define your service once and then generate client and server stubs in multiple languages.

Describe the RPC life cycle starting with the RPC client?

1. **Stub Creation**: The client initializes a client stub that provides the same methods as the server.

2. **Marshalling**: The client calls a procedure in the stub. The stub marshals (packs) the procedure parameters into a suitable format for transmission over the network.

3. **Communication**: The client communicates with the server by sending the marshalled parameters over the network.

4. **Server Stub Handling**: The server has a stub that receives the marshalled parameters. The server stub unmarshals the parameters and calls the server procedure with the received parameters.

5. **Processing**: The server process runs the procedure with the unmarshalled parameters and produces a result.

6. **Response Marshalling**: The server stub marshals the result of the procedure and sends it back over the network to the client.

7. **Client Handling**: The client stub receives the marshalled result, unmarshals it, and returns it to the client process.

8. **Result**: The client process continues its computation with the result of the procedure.

Describe the workflow of Protocol Buffers?

1. **Define the Schema**: You start by defining the structure of your data in a `.proto` file. This is where you specify the message types and fields. For example:
   
   ```protobuf
   message Person {
      optional string name = 1;
      optional int32 id = 2;
      optional string email = 3;
   }
   ```

2. **Generate Code**: The Protocol Buffers compiler (`protoc`) is then used to generate code in your chosen language (like Java, C++, Python, etc.). [This generated code includes classes for each message type in your `.proto` file, along with accessor methods for each field](https://protobuf.dev/overview/)[1](https://protobuf.dev/overview/).

3. **Use the Generated Code**: You can then use the generated code in your application to create, manipulate, serialize, and deserialize instances of your message types. For example, in Java:
   
   Java
   
   ```java
   Person john = Person.newBuilder()
      .setId(1234)
      .setName("John Doe")
      .setEmail("jdoe@example.com")
      .build();
   output = new FileOutputStream(args[0]);
   john.writeTo(output);
   ```

4. **Backwards Compatibility**: Protocol Buffers are designed to be backwards compatible. [This means you can add new fields to your message types without breaking old programs that are compiled against the “old” version of your `.proto` file](https://protobuf.dev/overview/)[1](https://protobuf.dev/overview/).

What are the benefits of using protocol buffers?

- Compact Data Storage: Protobuf provides a more compact format than JSON or XML, which can result in significant storage savings.

- Fast Parsing: Protobuf messages are faster to encode and decode, improving performance2.

- Cross-Language Compatibility: Protobuf is language-neutral and platform-neutral, making it easy to share data across different programming languages.

- Automatically-Generated Classes: Protobuf generates source code in your chosen language, making it easier to write and read structured data.

- Backward Compatibility: Protobuf allows for the addition of new fields without breaking existing services.

- Efficiency: Protobuf is more efficient in terms of space and time, reducing the bandwidth needed to send messages.

When is the use of protocol not recommended?

- Direct Consumption by Web Browsers: If data from the service is directly consumed by a web browser, JSON might be a better choice due to its native support in JavaScript.

- Server-Side Application in JavaScript: If your server-side application is written in JavaScript, JSON might be more convenient.

- No Schema Enforcement: If you aren’t prepared to tie the data model to a schema, JSON or XML might be more suitable.

- Limited Bandwidth: If you don’t have the bandwidth to add another tool to your arsenal, sticking with more familiar data formats might be better.

- Complex Scenarios: Protobuf might not be well suited for more complex scenarios where data sharing with other languages or changing schemas is involved.

- Human Readability: If you need or want data to be human-readable, JSON or XML might be a better choice.

List 3 different data types that can be used with protocol buffers?

String
Int32
Bool

#### Set up Hello World

https://intuting.medium.com/implement-grpc-service-using-java-gradle-7a54258b60b8


