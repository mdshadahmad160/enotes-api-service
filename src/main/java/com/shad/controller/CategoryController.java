package com.shad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shad.entity.Category;
import com.shad.service.CategoryService;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/save-category")
	public ResponseEntity<?> savedCategory(@RequestBody Category category) {
		Boolean savedCategory = categoryService.saveCategory(category);

		if (savedCategory) {
			return new ResponseEntity<>("Saved Success Category", HttpStatus.CREATED);

		} else {
			return new ResponseEntity<>(" Not Saved Success Category", HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@GetMapping("/categoryt")
	public ResponseEntity<?> getAllCategory() {
		List<Category> categories = categoryService.getAllCategory();

		if (CollectionUtils.isEmpty(categories)) {
			return ResponseEntity.noContent().build();

		} else {
			return new ResponseEntity<>(categories, HttpStatus.OK);
		}

	}

}
