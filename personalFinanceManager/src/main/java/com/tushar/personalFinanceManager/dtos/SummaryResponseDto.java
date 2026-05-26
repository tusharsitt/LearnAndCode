package com.tushar.personalFinanceManager.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SummaryResponseDto {
    private Double totalIncome;
    private Double totalExpense;
    private Double balance;
    private Map<String, Double> categoryWiseSpending;
}
