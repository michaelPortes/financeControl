package com.example.moneyapi.dto;

import com.example.moneyapi.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesDTO extends JpaRepository<Categories, Long> {}
