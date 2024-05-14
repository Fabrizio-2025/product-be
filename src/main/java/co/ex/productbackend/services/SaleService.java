package co.ex.productbackend.services;

import co.ex.productbackend.entities.Sale;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


public interface SaleService extends ICRUD<Sale, Long>{

    List<Sale> findByDate(LocalDate date) throws Exception;
}
