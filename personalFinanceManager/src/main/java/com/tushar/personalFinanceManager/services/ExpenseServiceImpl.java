package com.tushar.personalFinanceManager.services;

import com.tushar.personalFinanceManager.dtos.CategoryResponseDto;
import com.tushar.personalFinanceManager.dtos.ExpenseRequestDto;
import com.tushar.personalFinanceManager.dtos.ExpenseResponseDto;
import com.tushar.personalFinanceManager.dtos.FilterOptions;
import com.tushar.personalFinanceManager.entities.Category;
import com.tushar.personalFinanceManager.entities.Expense;
import com.tushar.personalFinanceManager.entities.User;
import com.tushar.personalFinanceManager.exceptions.ResourceNotFoundException;
import com.tushar.personalFinanceManager.repositories.CategoryRepository;
import com.tushar.personalFinanceManager.repositories.ExpenseRepository;
import com.tushar.personalFinanceManager.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ExpenseResponseDto createExpense(ExpenseRequestDto requestDto, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        Category category = categoryRepository.findById(requestDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Expense expense = new Expense();
        expense.setAmount(requestDto.getAmount());
        expense.setDate(requestDto.getDate());
        expense.setCategory(category);
        expense.setUser(user);

        Expense savedExpense = expenseRepository.save(expense);
        return mapToDto(savedExpense);
    }

    @Override
    public ExpenseResponseDto updateExpense(Long id, ExpenseRequestDto requestDto, String userEmail) {
        Expense expense = getExpenseEntity(id, userEmail);
        
        Category category = categoryRepository.findById(requestDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        expense.setAmount(requestDto.getAmount());
        expense.setDate(requestDto.getDate());
        expense.setCategory(category);

        Expense updatedExpense = expenseRepository.save(expense);
        return mapToDto(updatedExpense);
    }

    @Override
    public List<ExpenseResponseDto> getExpenses(FilterOptions filterOptions, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
                
        Stream<Expense> expenseStream = expenseRepository.findByUserId(user.getId()).stream();

        if (filterOptions != null) {
            if (filterOptions.getCategoryId() != null) {
                expenseStream = expenseStream.filter(e -> e.getCategory().getId().equals(filterOptions.getCategoryId()));
            }
            if (filterOptions.getStartDate() != null) {
                expenseStream = expenseStream.filter(e -> !e.getDate().isBefore(filterOptions.getStartDate()));
            }
            if (filterOptions.getEndDate() != null) {
                expenseStream = expenseStream.filter(e -> !e.getDate().isAfter(filterOptions.getEndDate()));
            }
        }

        return expenseStream.map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public ExpenseResponseDto getExpenseById(Long id, String userEmail) {
        return mapToDto(getExpenseEntity(id, userEmail));
    }

    @Override
    public void deleteExpense(Long id, String userEmail) {
        Expense expense = getExpenseEntity(id, userEmail);
        expenseRepository.delete(expense);
    }

    private Expense getExpenseEntity(Long id, String userEmail) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found"));
        if (!expense.getUser().getEmail().equals(userEmail)) {
            throw new ResourceNotFoundException("Expense not found"); 
        }
        return expense;
    }

    private ExpenseResponseDto mapToDto(Expense expense) {
        ExpenseResponseDto dto = new ExpenseResponseDto();
        dto.setId(expense.getId());
        dto.setAmount(expense.getAmount());
        dto.setDate(expense.getDate());
        
        CategoryResponseDto categoryDto = new CategoryResponseDto();
        categoryDto.setId(expense.getCategory().getId());
        categoryDto.setCategoryName(expense.getCategory().getCategoryName());
        dto.setCategory(categoryDto);
        
        return dto;
    }
}
