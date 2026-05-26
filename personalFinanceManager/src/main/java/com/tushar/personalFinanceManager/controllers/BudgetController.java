package com.tushar.personalFinanceManager.controllers;

import com.tushar.personalFinanceManager.dtos.BudgetRequestDto;
import com.tushar.personalFinanceManager.dtos.BudgetResponseDto;
import com.tushar.personalFinanceManager.services.BudgetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/budgets")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    @PostMapping
    public ResponseEntity<BudgetResponseDto> createBudget(
            @Valid @RequestBody BudgetRequestDto requestDto,
            Authentication authentication) {
        return new ResponseEntity<>(
                budgetService.createBudget(requestDto, authentication.getName()),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<BudgetResponseDto> updateBudget(
            @PathVariable Long id,
            @Valid @RequestBody BudgetRequestDto requestDto,
            Authentication authentication) {
        return ResponseEntity.ok(budgetService.updateBudget(id, requestDto, authentication.getName()));
    }

    @GetMapping
    public ResponseEntity<List<BudgetResponseDto>> getBudgets(Authentication authentication) {
        return ResponseEntity.ok(budgetService.getBudgets(authentication.getName()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BudgetResponseDto> getBudgetById(
            @PathVariable Long id,
            Authentication authentication) {
        return ResponseEntity.ok(budgetService.getBudgetById(id, authentication.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(
            @PathVariable Long id,
            Authentication authentication) {
        budgetService.deleteBudget(id, authentication.getName());
        return ResponseEntity.noContent().build();
    }
}
