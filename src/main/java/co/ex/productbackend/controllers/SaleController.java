package co.ex.productbackend.controllers;

import co.ex.productbackend.DTOS.SaleDTO;
import co.ex.productbackend.entities.Sale;
import co.ex.productbackend.services.SaleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<SaleDTO>> listar() throws Exception {
        List<SaleDTO> lista = service.listar().stream().map(p -> mapper.map(p, SaleDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDTO> listarPorId(@PathVariable("id") Long id) throws Exception {
        Sale obj = service.listarPorId(id);
        SaleDTO dtoResponse = mapper.map(obj, SaleDTO.class);
        return ResponseEntity.ok(dtoResponse);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> registrar(@RequestBody SaleDTO dtoRequest) throws Exception {
        Sale p = mapper.map(dtoRequest, Sale.class);
        Sale obj = service.registrar(p);
        SaleDTO dtoResponse = mapper.map(obj, SaleDTO.class);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Sale created successfully, Samurai.");
        response.put("sale", dtoResponse);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@RequestBody SaleDTO dtoRequest, @PathVariable("id") Long id) throws Exception {
        Sale sale = service.listarPorId(id);
        if (sale == null) {
            throw new Exception("The sale does not exist, Samurai.");
        }
        Sale p = mapper.map(dtoRequest, Sale.class);
        p.setId(id);
        Sale obj = service.modificar(p);

        SaleDTO dtoResponse = mapper.map(obj, SaleDTO.class);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Sale updated successfully, Samurai.");
        response.put("sale", dtoResponse);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) throws Exception {
        Sale obj = service.listarPorId(id);
        if (obj == null) {
            throw new Exception("ID not found " + id);
        }
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/date")
    public ResponseEntity<?> listarPorFecha(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws Exception {
        List<Sale> sales = service.findByDate(date);
        if (sales.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "No sales found for the given date, Samurai.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        List<SaleDTO> dtoList = sales.stream().map(sale -> mapper.map(sale, SaleDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

}
