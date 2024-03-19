import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class WarehouseServer {
    private static final int PORT = 50022;
    private Server server;
    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1); // Executor initialized here

    public void start() throws IOException {
        server = ServerBuilder.forPort(PORT)
                .addService(new WarehouseServiceImpl())
                .build()
                .start();

        System.out.println("Server started, listening on " + PORT);

    }


    public void stop() {
        if (server != null) {
            server.shutdown();
        }
        executor.shutdownNow(); // Ensure the executor is also shut down
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final WarehouseServer server = new WarehouseServer();
        System.out.println("Starting Warehouse Server...");
        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down gRPC server since JVM is shutting down.");
            server.stop();
            System.out.println("Server shut down.");
        }));

        server.blockUntilShutdown();
    }
}
