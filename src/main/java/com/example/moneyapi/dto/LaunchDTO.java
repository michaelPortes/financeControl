package com.example.moneyapi.dto;

import com.example.moneyapi.model.LaunchModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaunchDTO extends JpaRepository<LaunchModel, Long> {
}
