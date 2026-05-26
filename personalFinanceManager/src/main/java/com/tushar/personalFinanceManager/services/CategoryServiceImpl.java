package com.tushar.personalFinanceManager.services;

import com.tushar.personalFinanceManager.dtos.CategoryRequestDto;
import com.tushar.personalFinanceManager.dtos.CategoryResponseDto;
import com.tushar.personalFinanceManager.entities.Category;
import com.tushar.personalFinanceManager.exceptions.BadRequestException;
import com.tushar.personalFinanceManager.exceptions.ResourceNotFoundException;
import com.tushar.personalFinanceManager.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) {
        if (categoryRepository.findByCategoryName(categoryRequestDto.getCategoryName()).isPresent()) {
            throw new BadRequestException("Category with this name already exists");
        }
        
        Category category = new Category();
        category.setCategoryName(categoryRequestDto.getCategoryName());
        
        Category savedCategory = categoryRepository.save(category);
        return mapToDto(savedCategory);
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        return mapToDto(category);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        categoryRepository.delete(category);
    }
    
    private CategoryResponseDto mapToDto(Category category) {
        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setId(category.getId());
        dto.setCategoryName(category.getCategoryName());
        return dto;
    }
}
