package com.tushar.personalFinanceManager.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BudgetRequestDto {

    @Positive(message = "Budget limit must be positive")
    @NotNull(message = "Budget limit is required")
    private Double limit;

    @NotNull(message = "Category ID is required")
    private Long categoryId;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;
}
