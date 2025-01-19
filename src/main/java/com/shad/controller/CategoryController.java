package com.shad.controller;

import java.net.http.HttpHeaders;
import java.util.List;

import com.shad.dto.request.CategoryDto;
import com.shad.dto.response.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import com.shad.entity.Category;
import com.shad.service.CategoryService;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/save-category")
    public ResponseEntity<?> savedCategory(@RequestBody CategoryDto categoryDto) {
        Boolean savedCategory = categoryService.saveCategory(categoryDto);

        if (savedCategory) {
            return new ResponseEntity<>("Saved Success Category", HttpStatus.CREATED);

        } else {
            return new ResponseEntity<>(" Not Saved Success Category", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllCategory() {
        List<CategoryDto> categories = categoryService.getAllCategory();

        if (CollectionUtils.isEmpty(categories)) {
            return ResponseEntity.noContent().build();

        } else {
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }

    }

    @GetMapping("/active")
    public ResponseEntity<?> getActiveCategory() {
        List<CategoryResponse> categories = categoryService.getActiveCategory();

        if (CollectionUtils.isEmpty(categories)) {
            return ResponseEntity.noContent().build();

        } else {
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }

    }

    @GetMapping("{id}")
    public ResponseEntity<?> getCategoryDetailsById(@PathVariable Integer id) {
        CategoryDto categoryDto = categoryService.getCategoryById(id);
        if (ObjectUtils.isEmpty(categoryDto)) {
            return new ResponseEntity<>("Category not found with Id=" + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Integer id) {
        Boolean deleted = categoryService.deleteCategory(id);
        if (deleted) {
            return new ResponseEntity<>("Category Deleted Successfully", HttpStatus.OK);

        }
        return new ResponseEntity<>("Category Not Deleted", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
