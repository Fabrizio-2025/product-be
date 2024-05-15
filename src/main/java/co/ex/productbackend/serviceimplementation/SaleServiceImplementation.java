package co.ex.productbackend.serviceimplementation;

import co.ex.productbackend.entities.Sale;
import co.ex.productbackend.repositories.GenericRepo;
import co.ex.productbackend.repositories.SaleRepository;
import co.ex.productbackend.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SaleServiceImplementation extends CRUDImplementation<Sale, Long> implements SaleService {
    @Autowired
    private SaleRepository repo;

    @Override
    protected GenericRepo<Sale, Long> getRepo() {
        return repo;
    }

    @Override
    public List<Sale> findByDate(LocalDate date) throws Exception {
        return repo.findByDate(date);
    }
}
