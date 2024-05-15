package co.ex.productbackend.services;


import co.ex.productbackend.entities.SaleDetail;

import java.util.List;

public interface SaleDetailService extends ICRUD<SaleDetail, Long>{

    List<SaleDetail> findBySaleId(Long saleId) throws Exception;

}
