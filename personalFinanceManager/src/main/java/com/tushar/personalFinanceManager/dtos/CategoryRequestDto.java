package com.tushar.personalFinanceManager.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequestDto {
    @NotBlank(message = "Category title is required")
    private String categoryName;
}
