package co.ex.productbackend.controllers;

import co.ex.productbackend.DTOS.WareHouseDTO;
import co.ex.productbackend.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WareHouseController {

    @Autowired
    private WarehouseService warehouseService;

    @PostMapping
    public ResponseEntity<WareHouseDTO> createWarehouse(@RequestBody WareHouseDTO warehouseDTO) {
        return ResponseEntity.ok(warehouseService.saveWarehouse(warehouseDTO));
    }

    @GetMapping
    public ResponseEntity<List<WareHouseDTO>> getAllWarehouses() {
        return ResponseEntity.ok(warehouseService.getAllWarehouses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WareHouseDTO> getWarehouseById(@PathVariable Long id) {
        return ResponseEntity.ok(warehouseService.getWarehouseById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WareHouseDTO> updateWarehouse(@PathVariable Long id, @RequestBody WareHouseDTO warehouseDTO) {
        return ResponseEntity.ok(warehouseService.updateWarehouse(id, warehouseDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable Long id) {
        warehouseService.deleteWarehouse(id);
        return ResponseEntity.noContent().build();
    }
}
