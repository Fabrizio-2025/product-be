package co.ex.productbackend.repositories;

import co.ex.productbackend.entities.Sale;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface SaleRepository extends GenericRepo<Sale, Long> {
    List<Sale> findByDate(LocalDate date);


}
