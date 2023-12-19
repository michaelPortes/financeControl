package com.example.moneyapi.dto;

import com.example.moneyapi.dto.LaunchRepositorys.LaunchRepositoryQuery;
import com.example.moneyapi.model.LaunchModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;


@Repository
public interface LaunchDTO extends JpaRepository<LaunchModel, Long> {
}
