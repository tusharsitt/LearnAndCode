package com.tushar.personalFinanceManager.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BudgetResponseDto {
    private Long id;
    private Double limit;
    private CategoryResponseDto category;
    private LocalDate startDate;
    private LocalDate endDate;
}
