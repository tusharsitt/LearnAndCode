package org.example.dtos;

import lombok.Data;

/**
 * DTO representing a category in the system.
 */
@Data
public class CategoryResponseDto {
    private Long id;
    private String categoryName;
}
