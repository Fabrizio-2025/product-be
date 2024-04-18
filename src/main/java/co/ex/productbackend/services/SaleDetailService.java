package co.ex.productbackend.services;

import co.ex.productbackend.entities.Product;
import co.ex.productbackend.entities.Sale;
import co.ex.productbackend.entities.SaleDetail;
import co.ex.productbackend.repositories.SaleDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class SaleDetailService {
    private final SaleDetailRepository saleDetailRepository;

    @Autowired
    public SaleDetailService(SaleDetailRepository saleDetailRepository) {
        this.saleDetailRepository = saleDetailRepository;
    }

    public List<SaleDetail> getAllSaleDetails() {
        return saleDetailRepository.findAll();
    }

    public Optional<SaleDetail> getSaleDetailById(Long id) {
        return saleDetailRepository.findById(id);
    }

    public SaleDetail saveSaleDetail(SaleDetail saleDetail) {
        return saleDetailRepository.save(saleDetail);
    }

    public void deleteSaleDetail(Long id) {
        saleDetailRepository.deleteById(id);
    }

    public List<SaleDetail> findSaleDetailsBySaleId(Long saleId) {
        return saleDetailRepository.findBySaleId(saleId);
    }

    public List<SaleDetail> findSaleDetailsByProductId(Long productId) {
        return saleDetailRepository.findByProductId(productId);
    }


}
