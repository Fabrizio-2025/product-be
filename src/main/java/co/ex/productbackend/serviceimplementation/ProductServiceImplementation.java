package co.ex.productbackend.serviceimplementation;

import co.ex.productbackend.entities.Product;
import co.ex.productbackend.repositories.GenericRepo;
import co.ex.productbackend.repositories.ProductRepository;
import co.ex.productbackend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImplementation extends CRUDImplementation<Product,Long> implements ProductService{
    @Autowired
    private ProductRepository repo;

    @Override
    protected GenericRepo<Product, Long> getRepo() {
        return repo;
    }

    @Override
    public List<Product> listByBrand(String brand) throws Exception {
        return repo.findByBrand(brand);
    }
}
