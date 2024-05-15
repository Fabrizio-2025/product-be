package co.ex.productbackend.controllers;

import co.ex.productbackend.DTOS.ProductDTO;
import co.ex.productbackend.DTOS.SaleDetailDTO;
import co.ex.productbackend.entities.Product;
import co.ex.productbackend.entities.SaleDetail;
import co.ex.productbackend.services.ProductService;
import co.ex.productbackend.services.SaleDetailService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sale-details")
public class SaleDetailController {

    @Autowired
    private SaleDetailService service;

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<SaleDetailDTO>> listar() throws Exception {
        List<SaleDetailDTO> lista = service.listar().stream().map(p->mapper.map(p, SaleDetailDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDetailDTO> listarPorId(@PathVariable("id") Long id) throws Exception {
        SaleDetail obj = service.listarPorId(id);
        SaleDetailDTO dtoResponse = mapper.map(obj, SaleDetailDTO.class);
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }



    @PostMapping
    public ResponseEntity<SaleDetailDTO> registrar(@Valid @RequestBody SaleDetailDTO dto) throws Exception {
        SaleDetail obj = service.registrar(mapper.map(dto, SaleDetail.class));
        SaleDetailDTO dtoResponse = mapper.map(obj, SaleDetailDTO.class);
        return new ResponseEntity<>(dtoResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleDetailDTO> modificar(@Valid @RequestBody SaleDetailDTO dto, @PathVariable("id") Long id) throws Exception {
        SaleDetail obj = service.modificar(mapper.map(dto, SaleDetail.class));
        SaleDetailDTO dtoResponse = mapper.map(obj, SaleDetailDTO.class);
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) throws Exception {
        service.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
