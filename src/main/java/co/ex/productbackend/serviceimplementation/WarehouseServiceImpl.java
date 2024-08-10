package co.ex.productbackend.serviceimplementation;

import co.ex.productbackend.DTOS.WareHouseDTO;
import co.ex.productbackend.entities.Product;
import co.ex.productbackend.entities.WareHouse;
import co.ex.productbackend.repositories.ProductRepository;
import co.ex.productbackend.repositories.WarehouseRepository;
import co.ex.productbackend.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ProductRepository productsRepository;

    @Override
    public WareHouseDTO saveWarehouse(WareHouseDTO warehouseDTO) {
        WareHouse warehouse = new WareHouse();
        Product product = productsRepository.findById(warehouseDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        warehouse.setProduct(product);
        warehouse.setQuantity(warehouseDTO.getQuantity());

        warehouse = warehouseRepository.save(warehouse);
        warehouseDTO.setId(warehouse.getId());
        return warehouseDTO;
    }

    @Override
    public List<WareHouseDTO> getAllWarehouses() {
        return warehouseRepository.findAll().stream()
                .map(warehouse -> {
                    WareHouseDTO dto = new WareHouseDTO();
                    dto.setId(warehouse.getId());
                    dto.setProductId(warehouse.getProduct().getId());
                    dto.setQuantity(warehouse.getQuantity());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public WareHouseDTO getWarehouseById(Long id) {
        WareHouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));
        WareHouseDTO dto = new WareHouseDTO();
        dto.setId(warehouse.getId());
        dto.setProductId(warehouse.getProduct().getId());
        dto.setQuantity(warehouse.getQuantity());
        return dto;
    }

    @Override
    public WareHouseDTO updateWarehouse(Long id, WareHouseDTO warehouseDTO) {
        WareHouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));
        Product product = productsRepository.findById(warehouseDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        warehouse.setProduct(product);
        warehouse.setQuantity(warehouseDTO.getQuantity());
        warehouseRepository.save(warehouse);

        warehouseDTO.setId(warehouse.getId());
        return warehouseDTO;
    }

    @Override
    public void deleteWarehouse(Long id) {
        warehouseRepository.deleteById(id);
    }
}
