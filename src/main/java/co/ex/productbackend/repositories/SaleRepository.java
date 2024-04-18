package co.ex.productbackend.repositories;

import co.ex.productbackend.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findByDate(LocalDate date);


}
