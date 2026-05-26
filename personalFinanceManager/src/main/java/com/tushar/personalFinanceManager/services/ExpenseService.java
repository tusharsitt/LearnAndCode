package com.tushar.personalFinanceManager.services;

import com.tushar.personalFinanceManager.dtos.ExpenseRequestDto;
import com.tushar.personalFinanceManager.dtos.ExpenseResponseDto;
import com.tushar.personalFinanceManager.dtos.FilterOptions;

import java.util.List;

public interface ExpenseService {
    ExpenseResponseDto createExpense(ExpenseRequestDto requestDto, String userEmail);
    ExpenseResponseDto updateExpense(Long id, ExpenseRequestDto requestDto, String userEmail);
    List<ExpenseResponseDto> getExpenses(FilterOptions filterOptions, String userEmail);
    ExpenseResponseDto getExpenseById(Long id, String userEmail);
    void deleteExpense(Long id, String userEmail);
}
