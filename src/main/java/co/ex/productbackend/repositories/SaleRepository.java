package co.ex.productbackend.repositories;

import co.ex.productbackend.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SaleRepository extends GenericRepo<Sale, Long> {
    @Query("SELECT s FROM Sale s WHERE s.date = :date")
    List<Sale> findByDate(@Param("date") LocalDate date);


}
