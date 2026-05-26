package com.tushar.personalFinanceManager.controllers;

import com.tushar.personalFinanceManager.dtos.ExpenseRequestDto;
import com.tushar.personalFinanceManager.dtos.ExpenseResponseDto;
import com.tushar.personalFinanceManager.dtos.FilterOptions;
import com.tushar.personalFinanceManager.services.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<ExpenseResponseDto> createExpense(
            @Valid @RequestBody ExpenseRequestDto requestDto,
            Authentication authentication) {
        return new ResponseEntity<>(
                expenseService.createExpense(requestDto, authentication.getName()),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponseDto> updateExpense(
            @PathVariable Long id,
            @Valid @RequestBody ExpenseRequestDto requestDto,
            Authentication authentication) {
        return ResponseEntity.ok(expenseService.updateExpense(id, requestDto, authentication.getName()));
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponseDto>> getExpenses(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            Authentication authentication) {
        
        FilterOptions filterOptions = FilterOptions.builder()
                .categoryId(categoryId)
                .startDate(startDate)
                .endDate(endDate)
                .build();
                
        return ResponseEntity.ok(expenseService.getExpenses(filterOptions, authentication.getName()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponseDto> getExpenseById(
            @PathVariable Long id,
            Authentication authentication) {
        return ResponseEntity.ok(expenseService.getExpenseById(id, authentication.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(
            @PathVariable Long id,
            Authentication authentication) {
        expenseService.deleteExpense(id, authentication.getName());
        return ResponseEntity.noContent().build();
    }
}
