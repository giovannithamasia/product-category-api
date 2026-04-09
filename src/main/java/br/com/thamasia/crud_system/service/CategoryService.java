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
        if (repository.existsByNameCategory(categoryDto.getNameCategory())){
            throw new RuntimeException("Category name already registered");
        }

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

    public CategoryResponseDto searchById(Long id){
        Category category = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        return CategoryResponseDto.toCategoryResponseDto(category);
    }

    public void updateCategory(Long id,CategoryDto dto){
        Category category = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        if (repository.existsByNameCategoryAndIdCategoryNot(dto.getNameCategory(),id)){
            throw new RuntimeException("Category name already registered");
        }

        category.setNameCategory(dto.getNameCategory());

        repository.save(category);
    }

    public void deleteCategory(Long id){
        Category category = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        repository.deleteById(id);
    }
}
