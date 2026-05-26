package com.tushar.personalFinanceManager.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpenseResponseDto {
    private Long id;
    private Double amount;
    private CategoryResponseDto category;
    private LocalDate date;
}
