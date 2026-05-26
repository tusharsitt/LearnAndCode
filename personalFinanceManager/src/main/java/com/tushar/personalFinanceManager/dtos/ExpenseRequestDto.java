package com.tushar.personalFinanceManager.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpenseRequestDto {

    @Positive(message = "Amount must be positive")
    @NotNull(message = "Amount is required")
    private Double amount;

    @NotNull(message = "Category ID is required")
    private Long categoryId;

    @NotNull(message = "Date is required")
    private LocalDate date;
}
