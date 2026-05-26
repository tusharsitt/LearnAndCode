package org.example.dtos;

import lombok.Data;

import java.time.LocalDate;

/**
 * DTO representing a budget record from the API.
 */
@Data
public class BudgetResponseDto {
    private Long id;
    private Double limit;
    private CategoryResponseDto category;
    private LocalDate startDate;
    private LocalDate endDate;
}
