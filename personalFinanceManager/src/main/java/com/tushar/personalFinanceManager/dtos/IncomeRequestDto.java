package com.tushar.personalFinanceManager.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class IncomeRequestDto {

    @Positive(message = "Income amount must be positive")
    @NotNull(message = "Income amount is required")
    private Double incomeAmount;

    @NotNull(message = "Category ID is required")
    private Long categoryId;

    @NotNull(message = "Date of income is required")
    private LocalDate dateOfIncome;
}
