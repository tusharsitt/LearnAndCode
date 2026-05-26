package org.example.dtos;

import lombok.Data;

import java.time.LocalDate;

/**
 * DTO for creating or updating an income record.
 */
@Data
public class IncomeRequestDto {
    private Double incomeAmount;
    private Long categoryId;
    private LocalDate dateOfIncome;
}
