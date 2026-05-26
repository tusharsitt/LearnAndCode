package org.example.dtos;

import lombok.Data;

import java.time.LocalDate;

/**
 * DTO for creating or updating a budget.
 */
@Data
public class BudgetRequestDto {
    private Double limit;
    private Long categoryId;
    private LocalDate startDate;
    private LocalDate endDate;
}
