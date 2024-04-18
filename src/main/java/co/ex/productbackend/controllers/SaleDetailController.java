package co.ex.productbackend.controllers;

import co.ex.productbackend.DTOS.SaleDetailDTO;
import co.ex.productbackend.entities.Product;
import co.ex.productbackend.entities.Sale;
import co.ex.productbackend.entities.SaleDetail;
import co.ex.productbackend.services.ProductService;
import co.ex.productbackend.services.SaleDetailService;
import co.ex.productbackend.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sale-details")
@CrossOrigin(origins = "http://localhost:5173")
public class SaleDetailController {
    private final SaleDetailService saleDetailService;
    private final SaleService saleService; // Añadido para buscar la entidad Sale
    private final ProductService productService; // Añadido para buscar la entidad Product


    @Autowired
    public SaleDetailController(SaleDetailService saleDetailService, SaleService saleService, ProductService productService) {
        this.saleDetailService = saleDetailService;
        this.saleService = saleService;
        this.productService = productService;
    }

    @GetMapping
    public List<SaleDetailDTO> getAllSaleDetails() {
        return saleDetailService.getAllSaleDetails().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDetailDTO> getSaleDetailById(@PathVariable Long id) {
        return saleDetailService.getSaleDetailById(id)
                .map(this::convertToDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public SaleDetailDTO addSaleDetail(@RequestBody SaleDetailDTO saleDetailDTO) {
        SaleDetail saleDetail = convertToEntity(saleDetailDTO);
        SaleDetail newSaleDetail = saleDetailService.saveSaleDetail(saleDetail);
        return convertToDTO(newSaleDetail);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleDetailDTO> updateSaleDetail(@PathVariable Long id, @RequestBody SaleDetailDTO saleDetailDTO) {
        SaleDetail saleDetail = convertToEntity(saleDetailDTO);
        saleDetail.setId(id);
        SaleDetail updatedSaleDetail = saleDetailService.saveSaleDetail(saleDetail);
        return ResponseEntity.ok(convertToDTO(updatedSaleDetail));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSaleDetail(@PathVariable Long id) {
        saleDetailService.deleteSaleDetail(id);
        return ResponseEntity.ok().build();
    }

    private SaleDetailDTO convertToDTO(SaleDetail saleDetail) {
        return new SaleDetailDTO(
                saleDetail.getId(),
                saleDetail.getSale().getId(),
                saleDetail.getProduct().getId(),
                saleDetail.getProduct().getName(), // Asumiendo que quieras enviar el nombre del producto
                saleDetail.getProduct().getPrice(), // Asumiendo que el producto tiene un precio
                saleDetail.getQuantity(),
                saleDetail.getPrice()
        );
    }

    private SaleDetail convertToEntity(SaleDetailDTO saleDetailDTO) {
        SaleDetail saleDetail = new SaleDetail();
        Sale sale = saleService.getSaleById(saleDetailDTO.getSaleId())
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        Product product = productService.getProductById(saleDetailDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        saleDetail.setSale(sale);
        saleDetail.setProduct(product);
        saleDetail.setQuantity(saleDetailDTO.getQuantity());
        saleDetail.setPrice(saleDetailDTO.getSalePrice());
        return saleDetail;
    }
}
