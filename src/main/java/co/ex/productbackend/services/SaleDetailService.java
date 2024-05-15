package co.ex.productbackend.services;


import co.ex.productbackend.entities.Product;
import co.ex.productbackend.entities.SaleDetail;

import java.util.List;

public interface SaleDetailService extends ICRUD<SaleDetail, Long>{

    List<Product> findProductsBySaleId(Long saleId);

}
