package com.example.moneyapi.services;

import com.example.moneyapi.dto.UsersDTO;
import com.example.moneyapi.model.UsersModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersServices {

    @Autowired
    private UsersDTO usersDTO;

    public UsersModel updateUser(Long id, UsersModel user){
        UsersModel saveUser = searchUserById(id);
        BeanUtils.copyProperties(user, saveUser, "id");
        return usersDTO.save(user);
    }

    public void partialUpdateUser(Long id, Boolean active){
        UsersModel saveUser = searchUserById(id);
        saveUser.setActive(active);
        usersDTO.save(saveUser);
    }

    private UsersModel searchUserById(Long id){
        Optional<UsersModel> saveUser = usersDTO.findById(id);
        if(saveUser.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return saveUser.get();
    }
}
