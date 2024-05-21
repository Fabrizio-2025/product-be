package co.ex.productbackend.services;

import co.ex.productbackend.entities.Product;
import co.ex.productbackend.repositories.ProductRepository;
import co.ex.productbackend.repositories.SaleDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface ProductService extends ICRUD<Product, Long> {
    List<Product> listByBrand(String brand) throws Exception;


}
