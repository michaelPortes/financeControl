package com.example.moneyapi.services;

import com.example.moneyapi.dto.UsersDTO;
import com.example.moneyapi.model.Users;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersServices {

    @Autowired
    private UsersDTO usersDTO;

    public Users updateUser(Long id, Users user){
        Users saveUser = searchUserById(id);
        BeanUtils.copyProperties(user, saveUser, "id");
        return usersDTO.save(user);
    }

    public void partialUpdateUser(Long id, Boolean active){
        Users saveUser = searchUserById(id);
        saveUser.setActive(active);
        usersDTO.save(saveUser);
    }

    private Users searchUserById(Long id){
        Optional<Users> saveUser = usersDTO.findById(id);
        if(saveUser.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return saveUser.get();
    }
}
