package br.com.thamasia.crud_system.controller;

import br.com.thamasia.crud_system.dto.ProductDto;
import br.com.thamasia.crud_system.dto.ProductResponseDto;
import br.com.thamasia.crud_system.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<Void> registerProduct(@RequestBody @Valid ProductDto dto){
        service.registerProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> listProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(service.listProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> searchById(@PathVariable("id") Long idProduct){
        return ResponseEntity.status(HttpStatus.OK).body(service.searchById(idProduct));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<ProductResponseDto>> listProductsByCategory(@PathVariable("id") Long idProduct){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.listProductsByCategory(idProduct));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable("id") Long idProduct,
                                              @RequestBody @Valid ProductDto dto){
        service.updateProduct(idProduct,dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long idProduct){
        service.deleteProduct(idProduct);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
