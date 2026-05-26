package com.tushar.personalFinanceManager.services;

import com.tushar.personalFinanceManager.dtos.BudgetRequestDto;
import com.tushar.personalFinanceManager.dtos.BudgetResponseDto;

import java.util.List;

public interface BudgetService {
    BudgetResponseDto createBudget(BudgetRequestDto requestDto, String userEmail);
    BudgetResponseDto updateBudget(Long id, BudgetRequestDto requestDto, String userEmail);
    List<BudgetResponseDto> getBudgets(String userEmail);
    BudgetResponseDto getBudgetById(Long id, String userEmail);
    void deleteBudget(Long id, String userEmail);
}
