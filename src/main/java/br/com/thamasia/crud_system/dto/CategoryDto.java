package br.com.thamasia.crud_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    @NotBlank(message = "The category name is required.")
    @Size(min = 2 , max = 50,message = "Name must be between two and fifty characters")
    private String nameCategory;
}
