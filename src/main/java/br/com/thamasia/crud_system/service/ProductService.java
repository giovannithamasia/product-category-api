package br.com.thamasia.crud_system.service;

import br.com.thamasia.crud_system.dto.ProductDto;
import br.com.thamasia.crud_system.dto.ProductResponseDto;
import br.com.thamasia.crud_system.exception.CategoryNotFoundException;
import br.com.thamasia.crud_system.exception.ProductNotFoundException;
import br.com.thamasia.crud_system.model.Category;
import br.com.thamasia.crud_system.model.Product;
import br.com.thamasia.crud_system.repository.CategoryRepository;
import br.com.thamasia.crud_system.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Product registerProduct(ProductDto dto) {
        Category category = categoryRepository.findById(dto.getIdCategory())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        Product product = Product.builder()
                .nameProduct(dto.getNameProduct())
                .price(dto.getPrice())
                .category(category)
                .build();


        productRepository.save(product);

        return product;
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDto> listProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductResponseDto::toProductResponseDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProductResponseDto searchById(Long idProduct) {
        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        return ProductResponseDto.toProductResponseDto(product);
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDto> listProductsByCategory(Long idCategory) {
        categoryRepository.findById(idCategory)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        List<Product> product = productRepository.findByCategoryIdCategory(idCategory);

        return product.stream()
                .map(ProductResponseDto::toProductResponseDto)
                .toList();
    }

    @Transactional
    public void updateProduct(Long idProduct, ProductDto dto) {
        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        Category category = categoryRepository.findById(dto.getIdCategory())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        product.setNameProduct(dto.getNameProduct());
        product.setPrice(dto.getPrice());
        product.setCategory(category);

        productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(Long idProduct) {
        productRepository.findById(idProduct)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        productRepository.deleteById(idProduct);
    }
}