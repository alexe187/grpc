import com.google.common.collect.ImmutableList;
import io.grpc.stub.StreamObserver;
import warehouse.*;

import java.time.LocalDateTime;


public class WarehouseServiceImpl extends WarehouseServiceGrpc.WarehouseServiceImplBase {
    @Override
    public void getWarehouseData(Warehouse.WarehouseRequest request, StreamObserver<Warehouse.WarehouseResponse> responseObserver) {
        System.out.println("Handling warehouse endpoint" + request.toString());

        String warehouseUUID = request.getUuid();

        System.out.println("Getting data of warehouse with uuid=" + warehouseUUID + "...");

        // create a few dummy product objects

        Warehouse.Product product1 = Warehouse.Product.newBuilder()
                .setProductId("adbe69b5-932b-45d2-9c13-fa408e100cda")
                .setProductName("AK-47")
                .setProductCategory("Waffen")
                .setProductQuantity(103)
                .setProductUnit("1 Waffe/Stück")
                .build();
        Warehouse.Product product2 = Warehouse.Product.newBuilder()
                .setProductId("f3e3e3e3-3e3e-3e3e-3e3e-3e3e3e3e3e3e3")
                .setProductName("C-4")
                .setProductCategory("Sprengstoffe")
                .setProductQuantity(147)
                .setProductUnit("1KG/Packung")
                .build();
        Warehouse.Product product3 = Warehouse.Product.newBuilder()
                .setProductId("da3e3e3e-3e3e-3e3e-3e3e-3e3e3e3e3e3e3")
                .setProductName("Granate")
                .setProductCategory("Sprengstoffe")
                .setProductQuantity(113)
                .setProductUnit("3 Granate/Packung")
                .build();


        // now create the warehouse response object
        Warehouse.WarehouseResponse response = Warehouse.WarehouseResponse.newBuilder()
                .setWarehouseId(warehouseUUID)
                .setWarehouseName("Wien Innenstadt Lager")
                .setWarehouseAddress("Hauptstraße 1")
                .setWarehousePostalCode(1010)
                .setWarehouseCity("Wien")
                .setWarehouseCountry("AUSTRIA")
                .setTimestamp(LocalDateTime.now().toString())
                .addAllProductData(ImmutableList.of(product1, product2, product3))
                .build();

        // send the response to the client
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}