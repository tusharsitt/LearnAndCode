package com.tushar.personalFinanceManager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long Id;

    @Column(name = "date")
    @NotNull
    LocalDate dateOfIncome;

    @Column(name = "amount")
    @NotNull
    @Positive
    Double incomeAmount;

    // TODO: We can also introduce income sources.

}
