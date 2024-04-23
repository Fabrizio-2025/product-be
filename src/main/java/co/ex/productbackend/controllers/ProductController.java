package co.ex.productbackend.controllers;

import co.ex.productbackend.DTOS.ProductDTO;
import co.ex.productbackend.entities.Product;
import co.ex.productbackend.exception.ModeloNotFoundException;
import co.ex.productbackend.services.ProductService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> listar() throws Exception {
        List<ProductDTO> lista = service.listar().stream().map(p->mapper.map(p, ProductDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> listarPorId(@PathVariable("id") Long id) throws Exception {
        ProductDTO dtoResponse;
        Product obj = service.listarPorId(id);
        if (obj==null) {
            throw new ModeloNotFoundException("Id No encontrado "+id);

        } else {
            dtoResponse = mapper.map(obj, ProductDTO.class);
        }

        return new ResponseEntity<>(dtoResponse,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> registrar(@Valid @RequestBody ProductDTO dtoRequest) throws Exception{
        Product p = mapper.map(dtoRequest,Product.class);
        Product obj = service.registrar(p);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
