package co.ex.productbackend.services;

import co.ex.productbackend.entities.Product;

import java.util.List;


public interface ProductService extends ICRUD<Product, Long>{
    List<Product> listByBrand(String brand) throws Exception;
}
