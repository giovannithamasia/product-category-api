package br.com.thamasia.crud_system.controller;

import br.com.thamasia.crud_system.dto.CategoryDto;
import br.com.thamasia.crud_system.dto.CategoryResponseDto;
import br.com.thamasia.crud_system.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @PostMapping
    public ResponseEntity<Void> registerCategory(@RequestBody CategoryDto dto){
        service.registerCategory(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> listCategories(){
        return ResponseEntity.status(HttpStatus.OK).body(service.listCategories());
    }
}
