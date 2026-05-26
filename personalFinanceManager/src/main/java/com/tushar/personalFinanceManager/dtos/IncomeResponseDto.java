package com.tushar.personalFinanceManager.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class IncomeResponseDto {
    private Long id;
    private Double incomeAmount;
    private CategoryResponseDto category;
    private LocalDate dateOfIncome;
}
