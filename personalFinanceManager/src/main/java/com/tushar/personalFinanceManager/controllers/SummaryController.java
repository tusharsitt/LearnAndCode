package com.tushar.personalFinanceManager.controllers;

import com.tushar.personalFinanceManager.dtos.FilterOptions;
import com.tushar.personalFinanceManager.dtos.SummaryResponseDto;
import com.tushar.personalFinanceManager.services.SummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/summary")
@RequiredArgsConstructor
public class SummaryController {

    private final SummaryService summaryService;

    @GetMapping
    public ResponseEntity<SummaryResponseDto> getSummary(
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(required = false) Long categoryId,
            Authentication authentication) {
        
        FilterOptions filterOptions = FilterOptions.builder()
                .startDate(startDate)
                .endDate(endDate)
                .categoryId(categoryId)
                .build();
                
        return ResponseEntity.ok(summaryService.getSummary(filterOptions, authentication.getName()));
    }
}
