package com.shad.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.shad.dto.request.CategoryDto;
import com.shad.dto.response.CategoryResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.shad.entity.Category;
import com.shad.repository.CategoryRepository;
import com.shad.service.CategoryService;

import javax.swing.text.html.Option;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Boolean saveCategory(CategoryDto categoryDto) {
		/*categoryDto.setIsDeleted(false);
		categoryDto.setCreatedBy(1);
		categoryDto.setCreatedOn(new Date());
		Category savedCategory = categoryRepository.save(categoryDto);
		if (ObjectUtils.isEmpty(savedCategory)) {

			return false;
		}*/
        /*Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setIsActive(categoryDto.getIsActive());*/
        Category category = modelMapper.map(categoryDto, Category.class);
        if (ObjectUtils.isEmpty(category.getId())) {
            category.setIsDeleted(false);
            category.setCreatedBy(1);
            category.setCreatedOn(new Date());
        } else {
            updateCategory(category);
        }

        Category savedCategory = categoryRepository.save(category);
        if (ObjectUtils.isEmpty(savedCategory)) {
            return false;
        }
        return true;
    }

    private void updateCategory(Category category) {
        Optional<Category> findById = categoryRepository.findById(category.getId());
        if (findById.isPresent()) {
            Category existsCategory = findById.get();
            category.setCreatedBy(existsCategory.getCreatedBy());
            category.setCreatedOn(existsCategory.getCreatedOn());
            category.setIsDeleted(existsCategory.getIsDeleted());
            category.setUpdatedBy(1);
            category.setUpdatedOn(new Date());
        }
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepository.findByIsDeletedFalse();

        List<CategoryDto> categoryDtoList = categories.stream().map(cat -> modelMapper.map(cat,
                CategoryDto.class)).toList();

        return categoryDtoList;
    }

    @Override
    public List<CategoryResponse> getActiveCategory() {
        List<Category> categories = categoryRepository.findByIsActiveTrueAndIsDeletedFalse();
        List<CategoryResponse> categoryResponses = categories.stream()
                .map(cat -> modelMapper.map(cat, CategoryResponse.class))
                .toList();
        return categoryResponses;
    }

    @Override
    public CategoryDto getCategoryById(Integer id) {
        Optional<Category> findByCategory = categoryRepository.findByIdAndIsDeletedFalse(id);
        if (findByCategory.isPresent()) {
            Category category = findByCategory.get();
            return modelMapper.map(category, CategoryDto.class);
        }
        return null;
    }

    @Override
    public Boolean deleteCategory(Integer id) {
        Optional<Category> findByCategory = categoryRepository.findById(id);
        if (findByCategory.isPresent()) {
            Category category = findByCategory.get();
            category.setIsDeleted(true);
            categoryRepository.save(category);
            return true;
        }
        return false;
    }

}
