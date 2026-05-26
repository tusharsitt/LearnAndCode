package com.tushar.personalFinanceManager.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    String name;

    @Column(unique = true)
    @Email
    String email;
    String password;


    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    List<Expense> expenses;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    List<Income> incomes;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    List<Budget> budgets;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
