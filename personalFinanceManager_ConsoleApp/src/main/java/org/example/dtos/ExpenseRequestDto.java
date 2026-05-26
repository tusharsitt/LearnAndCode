package org.example.dtos;

import lombok.Data;

import java.time.LocalDate;

/**
 * DTO for creating or updating an expense.
 */
@Data
public class ExpenseRequestDto {
    private Double amount;
    private Long categoryId;
    private LocalDate date;
}
