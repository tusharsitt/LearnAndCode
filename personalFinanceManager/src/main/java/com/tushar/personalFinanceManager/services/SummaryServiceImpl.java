package com.tushar.personalFinanceManager.services;

import com.tushar.personalFinanceManager.dtos.FilterOptions;
import com.tushar.personalFinanceManager.dtos.SummaryResponseDto;
import com.tushar.personalFinanceManager.entities.Expense;
import com.tushar.personalFinanceManager.entities.Income;
import com.tushar.personalFinanceManager.entities.User;
import com.tushar.personalFinanceManager.exceptions.ResourceNotFoundException;
import com.tushar.personalFinanceManager.repositories.ExpenseRepository;
import com.tushar.personalFinanceManager.repositories.IncomeRepository;
import com.tushar.personalFinanceManager.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class SummaryServiceImpl implements SummaryService {

    private final ExpenseRepository expenseRepository;
    private final IncomeRepository incomeRepository;
    private final UserRepository userRepository;

    @Override
    public SummaryResponseDto getSummary(FilterOptions filterOptions, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Stream<Expense> expenseStream = expenseRepository.findByUserId(user.getId()).stream();
        Stream<Income> incomeStream = incomeRepository.findByUserId(user.getId()).stream();

        if (filterOptions != null) {
            if (filterOptions.getStartDate() != null) {
                expenseStream = expenseStream.filter(e -> !e.getDate().isBefore(filterOptions.getStartDate()));
                incomeStream = incomeStream.filter(i -> !i.getDateOfIncome().isBefore(filterOptions.getStartDate()));
            }
            if (filterOptions.getEndDate() != null) {
                expenseStream = expenseStream.filter(e -> !e.getDate().isAfter(filterOptions.getEndDate()));
                incomeStream = incomeStream.filter(i -> !i.getDateOfIncome().isAfter(filterOptions.getEndDate()));
            }
            // Category filter doesn't normally apply to total summary, but if provided we filter it. 
            // Usually summary is across all categories. For the sake of this, let's allow it.
            if (filterOptions.getCategoryId() != null) {
                expenseStream = expenseStream.filter(e -> e.getCategory().getId().equals(filterOptions.getCategoryId()));
                incomeStream = incomeStream.filter(i -> i.getCategory().getId().equals(filterOptions.getCategoryId()));
            }
        }

        List<Expense> filteredExpenses = expenseStream.collect(Collectors.toList());
        List<Income> filteredIncomes = incomeStream.collect(Collectors.toList());

        Double totalExpense = filteredExpenses.stream().mapToDouble(Expense::getAmount).sum();
        Double totalIncome = filteredIncomes.stream().mapToDouble(Income::getIncomeAmount).sum();

        Map<String, Double> categoryWiseSpending = new HashMap<>();
        for (Expense expense : filteredExpenses) {
            String categoryName = expense.getCategory().getCategoryName();
            categoryWiseSpending.put(
                    categoryName, 
                    categoryWiseSpending.getOrDefault(categoryName, 0.0) + expense.getAmount()
            );
        }

        return SummaryResponseDto.builder()
                .totalIncome(totalIncome)
                .totalExpense(totalExpense)
                .balance(totalIncome - totalExpense)
                .categoryWiseSpending(categoryWiseSpending)
                .build();
    }
}
