package com.example.moneyapi.dto;

import com.example.moneyapi.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersDTO extends JpaRepository<Users, Long> {
}
