package com.example.moneyapi.services;

import com.example.moneyapi.dto.LaunchDTO;
import com.example.moneyapi.model.LaunchModel;
import com.example.moneyapi.model.UsersModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class LaunchServices {

    @Autowired
    private UsersServices usersServices;

    @Autowired
    private LaunchDTO launchDTO;
    public LaunchModel verifyValidUser(LaunchModel launch){
        UsersModel verifyUser = usersServices.searchUserById(launch.getUserId().getId());
        if(verifyUser.equals(null) || verifyUser.getActive().equals(false)){
            throw new EmptyResultDataAccessException(1);
        }
        return launchDTO.save(launch);
    }
}
