package co.ex.productbackend.serviceimplementation;

import co.ex.productbackend.entities.Product;
import co.ex.productbackend.entities.SaleDetail;
import co.ex.productbackend.repositories.GenericRepo;
import co.ex.productbackend.repositories.SaleDetailRepository;
import co.ex.productbackend.services.SaleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.ex.productbackend.services.ProductService;


import java.math.BigDecimal;
import java.util.List;

@Service
public class SaleDetailServiceImplementation extends CRUDImplementation<SaleDetail, Long> implements SaleDetailService {
    @Autowired
    private SaleDetailRepository repo;

    @Autowired
    private ProductService productService;

    @Override
    protected GenericRepo<SaleDetail, Long> getRepo() {
        return repo;
    }

    @Override
    public List<Product> listProductsBySaleId(Long saleId) {
        return repo.listProductsBySaleId(saleId);
    }

    @Override
    public BigDecimal calculateTotalPriceBySaleId(Long saleId) {
        List<SaleDetail> saleDetails = findBySaleId(saleId);
        return saleDetails.stream()
                .map(saleDetail -> {
                    try {
                        Product product = productService.listarPorId(saleDetail.getProduct().getId());
                        return product.getPrice().multiply(BigDecimal.valueOf(saleDetail.getQuantity()));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<SaleDetail> findBySaleId(Long saleId) {
        return repo.findBySaleId(saleId);
    }

    @Override
    public SaleDetail registrar(SaleDetail saleDetail) throws Exception {
        // Obtener el producto asociado
        Product product = productService.listarPorId(saleDetail.getProduct().getId());
        if (product == null) {
            throw new Exception("Product not found");
        }

        // Calcular el precio total
        saleDetail.setPrice(product.getPrice().multiply(BigDecimal.valueOf(saleDetail.getQuantity())));

        return repo.save(saleDetail);
    }
}
