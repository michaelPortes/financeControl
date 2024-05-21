package com.example.moneyapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @NotNull
    @Column(name = "date_maturity")
    @JsonProperty("date_maturity")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dateMaturity;

    @Column(name = "date_payment")
    @JsonProperty("date_payment")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate datePayment;

    @NotNull
    private Long cost;

    private String observation;

    @NotNull
    @Enumerated(EnumType.STRING)
    private LaunchType type;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoriesModel categoriesId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "users_id")
    private UsersModel userId;
}
