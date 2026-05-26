package org.example.dtos;

import lombok.Data;

import java.time.LocalDate;

/**
 * DTO representing an expense record from the API.
 */
@Data
public class ExpenseResponseDto {
    private Long id;
    private Double amount;
    private CategoryResponseDto category;
    private LocalDate date;
}
