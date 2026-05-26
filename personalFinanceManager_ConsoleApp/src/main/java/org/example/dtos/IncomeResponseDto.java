package org.example.dtos;

import lombok.Data;

import java.time.LocalDate;

/**
 * DTO representing an income record from the API.
 */
@Data
public class IncomeResponseDto {
    private Long id;
    private Double incomeAmount;
    private CategoryResponseDto category;
    private LocalDate dateOfIncome;
}
