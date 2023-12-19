package com.example.moneyapi.filters;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class LaunchFilter {

    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDateOf;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate  dueDateBy;
}
