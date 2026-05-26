package com.tushar.personalFinanceManager.services;

import com.tushar.personalFinanceManager.dtos.BudgetRequestDto;
import com.tushar.personalFinanceManager.dtos.BudgetResponseDto;
import com.tushar.personalFinanceManager.dtos.CategoryResponseDto;
import com.tushar.personalFinanceManager.entities.Budget;
import com.tushar.personalFinanceManager.entities.Category;
import com.tushar.personalFinanceManager.entities.User;
import com.tushar.personalFinanceManager.exceptions.ResourceNotFoundException;
import com.tushar.personalFinanceManager.repositories.BudgetRepository;
import com.tushar.personalFinanceManager.repositories.CategoryRepository;
import com.tushar.personalFinanceManager.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public BudgetResponseDto createBudget(BudgetRequestDto requestDto, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
                
        Category category = categoryRepository.findById(requestDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Budget budget = new Budget();
        budget.setLimit(requestDto.getLimit());
        budget.setStartDate(requestDto.getStartDate());
        budget.setEndDate(requestDto.getEndDate());
        budget.setCategory(category);
        budget.setUser(user);

        Budget savedBudget = budgetRepository.save(budget);
        return mapToDto(savedBudget);
    }

    @Override
    public BudgetResponseDto updateBudget(Long id, BudgetRequestDto requestDto, String userEmail) {
        Budget budget = getBudgetEntity(id, userEmail);
        
        Category category = categoryRepository.findById(requestDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        budget.setLimit(requestDto.getLimit());
        budget.setStartDate(requestDto.getStartDate());
        budget.setEndDate(requestDto.getEndDate());
        budget.setCategory(category);

        Budget updatedBudget = budgetRepository.save(budget);
        return mapToDto(updatedBudget);
    }

    @Override
    public List<BudgetResponseDto> getBudgets(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
                
        return budgetRepository.findByUserId(user.getId()).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BudgetResponseDto getBudgetById(Long id, String userEmail) {
        return mapToDto(getBudgetEntity(id, userEmail));
    }

    @Override
    public void deleteBudget(Long id, String userEmail) {
        Budget budget = getBudgetEntity(id, userEmail);
        budgetRepository.delete(budget);
    }

    private Budget getBudgetEntity(Long id, String userEmail) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Budget not found"));
        if (!budget.getUser().getEmail().equals(userEmail)) {
            throw new ResourceNotFoundException("Budget not found");
        }
        return budget;
    }

    private BudgetResponseDto mapToDto(Budget budget) {
        BudgetResponseDto dto = new BudgetResponseDto();
        dto.setId(budget.getId());
        dto.setLimit(budget.getLimit());
        dto.setStartDate(budget.getStartDate());
        dto.setEndDate(budget.getEndDate());
        
        CategoryResponseDto categoryDto = new CategoryResponseDto();
        categoryDto.setId(budget.getCategory().getId());
        categoryDto.setCategoryName(budget.getCategory().getCategoryName());
        dto.setCategory(categoryDto);
        
        return dto;
    }
}
