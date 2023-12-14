package com.example.moneyapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "launch")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class LaunchModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String description;

    @Column(name = "date_maturity")
    @NotNull
    private LocalDate dateMaturity;

    @Column(name = "date_payment")
    private LocalDate datePayment;

    @NotNull
    private Long cost;

    private String observation;

    @Enumerated(EnumType.STRING)
    private LaunchType type;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoriesModel categoriesId;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private UsersModel userId;



}
