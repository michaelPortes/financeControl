package com.example.moneyapi.dto;

import com.example.moneyapi.model.CategoriesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesDTO extends JpaRepository<CategoriesModel, Long> {}
