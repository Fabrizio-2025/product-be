package co.ex.productbackend.repositories;

import co.ex.productbackend.entities.WareHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<WareHouse, Long> {
}
