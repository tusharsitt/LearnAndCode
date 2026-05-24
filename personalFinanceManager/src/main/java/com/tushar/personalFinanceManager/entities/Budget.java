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
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Positive
    @NotNull
    Double limit;

    @OneToOne
    @JoinColumn(name = "catergory_id", referencedColumnName = "id")
    Category category;

    // TODO: can introduce a custom annotation for checking startDate >= endDate

    @NotNull
    LocalDate startDate;

    @NotNull
    LocalDate endDate;

    // TODO: Check if introducing a status filed adds any value
}
