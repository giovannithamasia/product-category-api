package br.com.thamasia.crud_system.dto;

import br.com.thamasia.crud_system.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDto {

    private Long idProduct;
    private String nameProduct;
    private BigDecimal price;
    private CategoryResponseDto category;

    public static ProductResponseDto toProductResponseDto(Product p){
        return ProductResponseDto.builder()
                .idProduct(p.getIdProduct())
                .nameProduct(p.getNameProduct())
                .price(p.getPrice())
                .category(CategoryResponseDto.toCategoryResponseDto(p.getCategory()))
                .build();
    }
}