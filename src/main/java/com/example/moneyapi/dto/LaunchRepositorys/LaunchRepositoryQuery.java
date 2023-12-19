package com.example.moneyapi.dto.LaunchRepositorys;

import com.example.moneyapi.filters.LaunchFilter;
import com.example.moneyapi.model.LaunchModel;

import java.util.List;


public interface LaunchRepositoryQuery {
    List<LaunchModel> filtersLaunch(LaunchFilter launchFilter);
}
