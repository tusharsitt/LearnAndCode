package org.example.dtos;

import lombok.Data;

import java.util.Map;

/**
 * DTO representing the financial summary report from the API.
 */
@Data
public class SummaryResponseDto {
    private Double totalIncome;
    private Double totalExpense;
    private Double balance;
    private Map<String, Double> categoryWiseSpending;
}
