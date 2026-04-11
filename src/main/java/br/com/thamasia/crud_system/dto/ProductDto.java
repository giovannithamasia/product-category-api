package br.com.thamasia.crud_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @NotBlank(message = "The product name is required")
    @Size(min = 2,max = 100,message = "The name must be between two and one hundred characters long")
    private String nameProduct;

    @Positive(message = "The price cannot be negative or zero")
    @NotNull(message = "The price cannot be null")
    private BigDecimal price;

    @NotNull(message = "The category id cannot be null")
    private Long idCategory;
}