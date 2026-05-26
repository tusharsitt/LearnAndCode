package com.tushar.personalFinanceManager.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilterOptions {
    private LocalDate startDate;
    private LocalDate endDate;
    private Long categoryId;
}
