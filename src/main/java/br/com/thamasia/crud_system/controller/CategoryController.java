package br.com.thamasia.crud_system.controller;

import br.com.thamasia.crud_system.dto.CategoryDto;
import br.com.thamasia.crud_system.dto.CategoryResponseDto;
import br.com.thamasia.crud_system.model.Category;
import br.com.thamasia.crud_system.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController implements GenericController{

    private final CategoryService service;

    @PostMapping
    public ResponseEntity<Void> registerCategory(@RequestBody @Valid CategoryDto dto) {
        Category category = service.registerCategory(dto);

        URI location = generateHeaderLocation(category.getIdCategory());

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> listCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(service.listCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> searchById(@PathVariable("id") Long idCategory) {
        return ResponseEntity.status(HttpStatus.OK).body(service.searchById(idCategory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCategory(@PathVariable("id") Long idCategory,
                                               @RequestBody @Valid CategoryDto dto) {
        service.updateCategory(idCategory, dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long idCategory){
        service.deleteCategory(idCategory);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
