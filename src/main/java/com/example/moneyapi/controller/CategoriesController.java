package com.example.moneyapi.controller;

import com.example.moneyapi.event.UriServices;
import com.example.moneyapi.model.Categories;
import com.example.moneyapi.dto.CategoriesDTO;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

    @Autowired
    private CategoriesDTO categoriesDTO;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public ResponseEntity<?> listing(){
        List<Categories> categories = categoriesDTO.findAll();
        return !categories.isEmpty() ? ResponseEntity.ok(categories) : ResponseEntity.ok("Empty list");
    }

    @PostMapping
    public ResponseEntity<Categories> createCategory(@Valid @RequestBody Categories categories, HttpServletResponse response){
        Categories saveCategories = categoriesDTO.save(categories);
        publisher.publishEvent(new UriServices(this, response, saveCategories.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(saveCategories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categories> findCategoryById(@PathVariable Long id){
        Optional<Categories> categories = categoriesDTO.findById(id);
        return categories.isPresent() ? ResponseEntity.ok(categories.get()) : ResponseEntity.notFound().build();
    }

}
