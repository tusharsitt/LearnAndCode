package com.tushar.personalFinanceManager.services;

import com.tushar.personalFinanceManager.dtos.FilterOptions;
import com.tushar.personalFinanceManager.dtos.SummaryResponseDto;

public interface SummaryService {
    SummaryResponseDto getSummary(FilterOptions filterOptions, String userEmail);
}
