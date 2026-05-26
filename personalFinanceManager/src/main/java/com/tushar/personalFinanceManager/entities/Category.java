package com.tushar.personalFinanceManager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
// TODO: See if this, and if yes than how, can be done via enums or not
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // ! This will automatically create a table level sequence object in db.
    Long id;

    @Column(name = "title", unique = true)
    @NotBlank
    String categoryName;

}
