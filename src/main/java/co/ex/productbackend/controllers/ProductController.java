package co.ex.productbackend.controllers;

import co.ex.productbackend.DTOS.ProductDTO;
import co.ex.productbackend.entities.Product;
import co.ex.productbackend.exception.ModeloNotFoundException;
import co.ex.productbackend.response.ProductResponse;
import co.ex.productbackend.services.ProductService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public ResponseEntity<ProductResponse> listarPorId(@PathVariable("id") Long id) throws Exception {
        Product obj = service.listarPorId(id);
        if (obj == null) {
            throw new ModeloNotFoundException("ID not found " + id);
        }

        ProductDTO dtoResponse = mapper.map(obj, ProductDTO.class);
        String formattedId = String.format("PROD-%02d", dtoResponse.getId());

        ProductResponse response = new ProductResponse(
                formattedId,
                dtoResponse.getName(),
                dtoResponse.getDescription(),
                dtoResponse.getBrand(),
                dtoResponse.getPrice()
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> registrar(@Valid @RequestBody ProductDTO dtoRequest) throws Exception{
        Product p = mapper.map(dtoRequest,Product.class);
        Product obj = service.registrar(p);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> Modificar(@Valid @RequestBody ProductDTO dtoRequest) throws Exception{
        Product product = service.listarPorId((dtoRequest.getId()));
        if(product == null){
            throw new ModeloNotFoundException("Id No encontrado "+dtoRequest.getId());
        }
        Product p = mapper.map(dtoRequest, Product.class);

        Product obj = service.modificar(p);

        ProductDTO dtoResponse = mapper.map(obj, ProductDTO.class);

        return new ResponseEntity<>(dtoResponse,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) throws Exception{
        Product obj = service.listarPorId(id);
        if (obj==null) {
            throw new ModeloNotFoundException("Id No encontrado "+id);
        } else {
            service.eliminar(id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
