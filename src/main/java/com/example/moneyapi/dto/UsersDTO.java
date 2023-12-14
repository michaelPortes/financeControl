package com.example.moneyapi.dto;

import com.example.moneyapi.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersDTO extends JpaRepository<UsersModel, Long> {
}
