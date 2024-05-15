package co.ex.productbackend.repositories;

import co.ex.productbackend.entities.Product;
import co.ex.productbackend.entities.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleDetailRepository extends GenericRepo<SaleDetail, Long> {


    @Query("SELECT sd.product FROM SaleDetail sd WHERE sd.sale.id = :saleId")
    List<Product> findProductsBySaleId(@Param("saleId") Long saleId);


}
