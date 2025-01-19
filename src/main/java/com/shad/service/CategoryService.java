package com.shad.service;

import java.util.List;

import com.shad.dto.request.CategoryDto;
import com.shad.dto.response.CategoryResponse;
import com.shad.entity.Category;

public interface CategoryService {
	
	public Boolean saveCategory(CategoryDto categoryDto);
	
	public List<CategoryDto> getAllCategory();

	public List<CategoryResponse> getActiveCategory();
}
