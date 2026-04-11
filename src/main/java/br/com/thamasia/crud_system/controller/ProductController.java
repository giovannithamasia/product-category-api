package br.com.thamasia.crud_system.controller;

import br.com.thamasia.crud_system.dto.ProductDto;
import br.com.thamasia.crud_system.dto.ProductResponseDto;
import br.com.thamasia.crud_system.model.Product;
import br.com.thamasia.crud_system.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController implements GenericController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<Void> registerProduct(@RequestBody @Valid ProductDto dto){
        Product product = service.registerProduct(dto);

        URI location = generateHeaderLocation(product.getIdProduct());

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> listProducts(){
        return ResponseEntity.ok(service.listProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> searchById(@PathVariable("id") Long idProduct){
        return ResponseEntity.ok(service.searchById(idProduct));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<ProductResponseDto>> listProductsByCategory(@PathVariable("id") Long idCategory){
        return ResponseEntity.ok(service.listProductsByCategory(idCategory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable("id") Long idProduct,
                                              @RequestBody @Valid ProductDto dto){
        service.updateProduct(idProduct,dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long idProduct){
        service.deleteProduct(idProduct);
        return ResponseEntity.noContent().build();
    }
}