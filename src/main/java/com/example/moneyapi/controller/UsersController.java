package com.example.moneyapi.controller;

import com.example.moneyapi.dto.UsersDTO;
import com.example.moneyapi.event.UriServices;
import com.example.moneyapi.model.UsersModel;
import com.example.moneyapi.services.UsersServices;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersDTO usersDTO;

    @Autowired
    private UsersServices usersServices;

    @Autowired
    private ApplicationEventPublisher publisher;
    @GetMapping
    public ResponseEntity<?> listAllUsers() {
        List<UsersModel> users = usersDTO.findAll();
        return !users.isEmpty() ? ResponseEntity.ok(users) : ResponseEntity.ok( "Empty list");
    }

    @PostMapping
    public ResponseEntity<UsersModel> createUser(@Valid @RequestBody UsersModel users, HttpServletResponse response){
        UsersModel saveUsers = usersDTO.save(users);
        publisher.publishEvent(new UriServices(this, response, saveUsers.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(saveUsers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersModel> findUsersById(@PathVariable Long id){
        Optional<UsersModel> user = usersDTO.findById(id);
        return user.isPresent() ? ResponseEntity.ok(user.get()) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id){
        usersDTO.deleteById(id);
    }

    @PutMapping("/{id}")
        public ResponseEntity<UsersModel> updateUser(@PathVariable Long id, @Valid @RequestBody UsersModel user){
        UsersModel saveUser = usersServices.updateUser(id, user);
        return ResponseEntity.ok(saveUser);
    }

    @PutMapping("/{id}/active")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void partialUpdateUser(@PathVariable Long id, @Valid @RequestBody Boolean active){
        usersServices.partialUpdateUser(id, active);
    }
}
