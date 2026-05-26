package com.tushar.personalFinanceManager.controllers;

import com.tushar.personalFinanceManager.dtos.FilterOptions;
import com.tushar.personalFinanceManager.dtos.IncomeRequestDto;
import com.tushar.personalFinanceManager.dtos.IncomeResponseDto;
import com.tushar.personalFinanceManager.services.IncomeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/incomes")
@RequiredArgsConstructor
public class IncomeController {

    private final IncomeService incomeService;

    @PostMapping
    public ResponseEntity<IncomeResponseDto> createIncome(
            @Valid @RequestBody IncomeRequestDto requestDto,
            Authentication authentication) {
        return new ResponseEntity<>(
                incomeService.createIncome(requestDto, authentication.getName()),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<IncomeResponseDto> updateIncome(
            @PathVariable Long id,
            @Valid @RequestBody IncomeRequestDto requestDto,
            Authentication authentication) {
        return ResponseEntity.ok(incomeService.updateIncome(id, requestDto, authentication.getName()));
    }

    @GetMapping
    public ResponseEntity<List<IncomeResponseDto>> getIncomes(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            Authentication authentication) {
        
        FilterOptions filterOptions = FilterOptions.builder()
                .categoryId(categoryId)
                .startDate(startDate)
                .endDate(endDate)
                .build();
                
        return ResponseEntity.ok(incomeService.getIncomes(filterOptions, authentication.getName()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncomeResponseDto> getIncomeById(
            @PathVariable Long id,
            Authentication authentication) {
        return ResponseEntity.ok(incomeService.getIncomeById(id, authentication.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncome(
            @PathVariable Long id,
            Authentication authentication) {
        incomeService.deleteIncome(id, authentication.getName());
        return ResponseEntity.noContent().build();
    }
}
