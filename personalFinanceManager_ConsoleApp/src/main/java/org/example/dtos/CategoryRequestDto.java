package org.example.dtos;

import lombok.Data;

/**
 * DTO for creating or updating a category.
 */
@Data
public class CategoryRequestDto {
    private String categoryName;
}
