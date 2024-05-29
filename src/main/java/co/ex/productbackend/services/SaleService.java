package co.ex.productbackend.services;

import co.ex.productbackend.entities.Sale;

import java.util.List;


public interface SaleService extends ICRUD<Sale, Long> {

    List<Sale> findByDate(String date);

}
