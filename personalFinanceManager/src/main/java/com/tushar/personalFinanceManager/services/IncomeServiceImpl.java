package com.tushar.personalFinanceManager.services;

import com.tushar.personalFinanceManager.dtos.CategoryResponseDto;
import com.tushar.personalFinanceManager.dtos.FilterOptions;
import com.tushar.personalFinanceManager.dtos.IncomeRequestDto;
import com.tushar.personalFinanceManager.dtos.IncomeResponseDto;
import com.tushar.personalFinanceManager.entities.Category;
import com.tushar.personalFinanceManager.entities.Income;
import com.tushar.personalFinanceManager.entities.User;
import com.tushar.personalFinanceManager.exceptions.ResourceNotFoundException;
import com.tushar.personalFinanceManager.repositories.CategoryRepository;
import com.tushar.personalFinanceManager.repositories.IncomeRepository;
import com.tushar.personalFinanceManager.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository incomeRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public IncomeResponseDto createIncome(IncomeRequestDto requestDto, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
                
        Category category = categoryRepository.findById(requestDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Income income = new Income();
        income.setIncomeAmount(requestDto.getIncomeAmount());
        income.setDateOfIncome(requestDto.getDateOfIncome());
        income.setCategory(category);
        income.setUser(user);

        Income savedIncome = incomeRepository.save(income);
        return mapToDto(savedIncome);
    }

    @Override
    public IncomeResponseDto updateIncome(Long id, IncomeRequestDto requestDto, String userEmail) {
        Income income = getIncomeEntity(id, userEmail);
        
        Category category = categoryRepository.findById(requestDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        income.setIncomeAmount(requestDto.getIncomeAmount());
        income.setDateOfIncome(requestDto.getDateOfIncome());
        income.setCategory(category);

        Income updatedIncome = incomeRepository.save(income);
        return mapToDto(updatedIncome);
    }

    @Override
    public List<IncomeResponseDto> getIncomes(FilterOptions filterOptions, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
                
        Stream<Income> incomeStream = incomeRepository.findByUserId(user.getId()).stream();

        if (filterOptions != null) {
            if (filterOptions.getCategoryId() != null) {
                incomeStream = incomeStream.filter(i -> i.getCategory().getId().equals(filterOptions.getCategoryId()));
            }
            if (filterOptions.getStartDate() != null) {
                incomeStream = incomeStream.filter(i -> !i.getDateOfIncome().isBefore(filterOptions.getStartDate()));
            }
            if (filterOptions.getEndDate() != null) {
                incomeStream = incomeStream.filter(i -> !i.getDateOfIncome().isAfter(filterOptions.getEndDate()));
            }
        }

        return incomeStream.map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public IncomeResponseDto getIncomeById(Long id, String userEmail) {
        return mapToDto(getIncomeEntity(id, userEmail));
    }

    @Override
    public void deleteIncome(Long id, String userEmail) {
        Income income = getIncomeEntity(id, userEmail);
        incomeRepository.delete(income);
    }

    private Income getIncomeEntity(Long id, String userEmail) {
        Income income = incomeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Income not found"));
        if (!income.getUser().getEmail().equals(userEmail)) {
            throw new ResourceNotFoundException("Income not found");
        }
        return income;
    }

    private IncomeResponseDto mapToDto(Income income) {
        IncomeResponseDto dto = new IncomeResponseDto();
        dto.setId(income.getId());
        dto.setIncomeAmount(income.getIncomeAmount());
        dto.setDateOfIncome(income.getDateOfIncome());
        
        CategoryResponseDto categoryDto = new CategoryResponseDto();
        categoryDto.setId(income.getCategory().getId());
        categoryDto.setCategoryName(income.getCategory().getCategoryName());
        dto.setCategory(categoryDto);
        
        return dto;
    }
}
