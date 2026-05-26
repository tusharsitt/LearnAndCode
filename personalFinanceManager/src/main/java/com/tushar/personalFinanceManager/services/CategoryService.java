package com.tushar.personalFinanceManager.services;

import com.tushar.personalFinanceManager.dtos.CategoryRequestDto;
import com.tushar.personalFinanceManager.dtos.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto);
    List<CategoryResponseDto> getAllCategories();
    CategoryResponseDto getCategoryById(Long id);
    void deleteCategory(Long id);
}
