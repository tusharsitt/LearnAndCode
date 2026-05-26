package com.tushar.personalFinanceManager.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Positive
    @NotNull
    Double amount;

    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    Category category;


    // TODO : Maybe we can update to the auditing thing later, by creating a base entity with audit fields of @CreatedDate & @LastModifiedDate
    @Column(name = "date")
    LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference
    User user;
}
