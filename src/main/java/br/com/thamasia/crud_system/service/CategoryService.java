package br.com.thamasia.crud_system.service;

import br.com.thamasia.crud_system.dto.CategoryDto;
import br.com.thamasia.crud_system.dto.CategoryResponseDto;
import br.com.thamasia.crud_system.model.Category;
import br.com.thamasia.crud_system.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public void registerCategory(CategoryDto categoryDto) {
        Category category = Category.builder()
                .nameCategory(categoryDto.getNameCategory())
                .build();

        repository.save(category);
    }

    public List<CategoryResponseDto> listCategories(){
        return repository.findAll()
                .stream()
                .map(CategoryResponseDto::toCategoryResponseDto)
                .toList();
    }
}
