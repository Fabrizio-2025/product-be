package co.ex.productbackend.services;

import co.ex.productbackend.DTOS.WareHouseDTO;

import java.util.List;

public interface WarehouseService {
    WareHouseDTO saveWarehouse(WareHouseDTO warehouseDTO);
    List<WareHouseDTO> getAllWarehouses();
    WareHouseDTO getWarehouseById(Long id);
    WareHouseDTO updateWarehouse(Long id, WareHouseDTO warehouseDTO);
    void deleteWarehouse(Long id);
}
