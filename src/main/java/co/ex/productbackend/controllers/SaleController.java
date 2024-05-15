package co.ex.productbackend.controllers;

import co.ex.productbackend.DTOS.SaleDTO;
import co.ex.productbackend.entities.Sale;
import co.ex.productbackend.services.SaleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
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
    public ResponseEntity<Void> registrar(@RequestBody SaleDTO dtoRequest) throws Exception {
        Sale p = mapper.map(dtoRequest, Sale.class);
        Sale obj = service.registrar(p);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> modificar(@RequestBody SaleDTO dtoRequest, @PathVariable("id") Long id) throws Exception {
        Sale sale = service.listarPorId((dtoRequest.getId()));
        if (sale == null) {
            throw new Exception("ID not found " + dtoRequest.getId());
        }
        Sale p = mapper.map(dtoRequest, Sale.class);
        p.setId(id);
        Sale obj = service.modificar(p);
        return ResponseEntity.ok().build();
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
    public ResponseEntity<List<SaleDTO>> listarPorFecha(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws Exception {
        List<Sale> sales = service.findByDate(date);
        List<SaleDTO> dtoList = sales.stream().map(sale -> mapper.map(sale, SaleDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

}
