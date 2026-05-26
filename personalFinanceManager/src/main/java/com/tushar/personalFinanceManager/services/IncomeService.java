package com.tushar.personalFinanceManager.services;

import com.tushar.personalFinanceManager.dtos.FilterOptions;
import com.tushar.personalFinanceManager.dtos.IncomeRequestDto;
import com.tushar.personalFinanceManager.dtos.IncomeResponseDto;

import java.util.List;

public interface IncomeService {
    IncomeResponseDto createIncome(IncomeRequestDto requestDto, String userEmail);
    IncomeResponseDto updateIncome(Long id, IncomeRequestDto requestDto, String userEmail);
    List<IncomeResponseDto> getIncomes(FilterOptions filterOptions, String userEmail);
    IncomeResponseDto getIncomeById(Long id, String userEmail);
    void deleteIncome(Long id, String userEmail);
}
