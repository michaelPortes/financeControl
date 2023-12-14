package com.example.moneyapi.controller;

import com.example.moneyapi.dto.LaunchDTO;
import com.example.moneyapi.event.UriServices;
import com.example.moneyapi.model.LaunchModel;
import jakarta.persistence.Lob;
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
@RequestMapping("/launch")
public class LaunchController {
    @Autowired
    private LaunchDTO launchDTO;

    @Autowired
    private ApplicationEventPublisher publisher;
    @GetMapping
    public ResponseEntity<?> listAllLaunch(){
        List<LaunchModel> launch = launchDTO.findAll();
        return !launch.isEmpty() ? ResponseEntity.ok(launch) : ResponseEntity.ok("Empty list");
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaunchModel> findLaunchById(@PathVariable Long id){
        Optional<LaunchModel> launch = launchDTO.findById(id);
        return launch.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LaunchModel> createLaunch(@Valid @RequestBody LaunchModel launch, HttpServletResponse response){
        LaunchModel saveLaunch = launchDTO.save(launch);
        publisher.publishEvent(new UriServices(this, response, saveLaunch.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(saveLaunch);
    }
}
