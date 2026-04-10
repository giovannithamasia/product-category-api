package br.com.thamasia.crud_system.dto;

import br.com.thamasia.crud_system.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponseDto {

    private Long idCategory;
    private String nameCategory;

    public static CategoryResponseDto toCategoryResponseDto(Category c) {
        return CategoryResponseDto.builder()
                .idCategory(c.getIdCategory())
                .nameCategory(c.getNameCategory())
                .build();
    }
}
