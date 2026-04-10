package br.com.thamasia.crud_system.service;

import br.com.thamasia.crud_system.dto.CategoryDto;
import br.com.thamasia.crud_system.dto.CategoryResponseDto;
import br.com.thamasia.crud_system.exception.CategoryNotFoundException;
import br.com.thamasia.crud_system.exception.CategoryWithProductsException;
import br.com.thamasia.crud_system.exception.NameCategoryDuplicateException;
import br.com.thamasia.crud_system.model.Category;
import br.com.thamasia.crud_system.repository.CategoryRepository;
import br.com.thamasia.crud_system.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Category registerCategory(CategoryDto categoryDto) {
        if (categoryRepository.existsByNameCategory(categoryDto.getNameCategory())){
            throw new NameCategoryDuplicateException("Category name already registered");
        }

        Category category = Category.builder()
                .nameCategory(categoryDto.getNameCategory())
                .build();

        categoryRepository.save(category);

        return category;
    }

    @Transactional(readOnly = true)
    public List<CategoryResponseDto> listCategories(){
        return categoryRepository.findAll()
                .stream()
                .map(CategoryResponseDto::toCategoryResponseDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public CategoryResponseDto searchById(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        return CategoryResponseDto.toCategoryResponseDto(category);
    }

    @Transactional
    public void updateCategory(Long id,CategoryDto dto){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        if (categoryRepository.existsByNameCategoryAndIdCategoryNot(dto.getNameCategory(),id)){
            throw new NameCategoryDuplicateException("Category name already registered");
        }

        category.setNameCategory(dto.getNameCategory());

        categoryRepository.save(category);
    }

    @Transactional
    public void deleteCategory(Long id){
        categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        if (productRepository.existsByCategoryIdCategory(id)){
            throw new CategoryWithProductsException("You cannot delete a category containing products");
        }

        categoryRepository.deleteById(id);
    }
}
