package co.ex.productbackend.serviceimplementation;

import co.ex.productbackend.entities.Product;
import co.ex.productbackend.entities.SaleDetail;
import co.ex.productbackend.repositories.GenericRepo;
import co.ex.productbackend.repositories.SaleDetailRepository;
import co.ex.productbackend.services.SaleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleDetailServiceImplementation extends CRUDImplementation<SaleDetail,Long> implements SaleDetailService {
    @Autowired
    private SaleDetailRepository repo;

    @Override
    protected GenericRepo<SaleDetail, Long> getRepo() {
        return repo;
    }

    @Override
    public List<Product> findProductsBySaleId(Long saleId) {
        return repo.findProductsBySaleId(saleId);
    }
}
