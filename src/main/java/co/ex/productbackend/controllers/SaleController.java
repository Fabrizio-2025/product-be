package co.ex.productbackend.controllers;

import co.ex.productbackend.DTOS.SaleDTO;
import co.ex.productbackend.entities.Sale;
import co.ex.productbackend.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sales")
@CrossOrigin(origins = "http://localhost:5173")
public class SaleController {
    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public List<SaleDTO> getAllSales() {
        return saleService.getAllSales().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDTO> getSaleById(@PathVariable Long id) {
        return saleService.getSaleById(id)
                .map(this::convertToDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public SaleDTO createSale(@RequestBody SaleDTO saleDTO) {
        Sale sale = convertToEntity(saleDTO);
        Sale newSale = saleService.saveSale(sale);
        return convertToDto(newSale);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleDTO> updateSale(@PathVariable Long id, @RequestBody SaleDTO saleDTO) {
        Sale sale = convertToEntity(saleDTO);
        sale.setId(id); // Asegúrate de asignar el ID para actualizar la entidad existente
        Sale updatedSale = saleService.saveSale(sale);
        return ResponseEntity.ok(convertToDto(updatedSale));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/date")
    public List<SaleDTO> getSalesByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return saleService.findSalesByDate(date).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private SaleDTO convertToDto(Sale sale) {
        return new SaleDTO(sale.getId(), sale.getDate());
    }

    private Sale convertToEntity(SaleDTO saleDTO) {
        Sale sale = new Sale();
        sale.setId(saleDTO.getId());
        sale.setDate(saleDTO.getDate());
        return sale;
    }
}
