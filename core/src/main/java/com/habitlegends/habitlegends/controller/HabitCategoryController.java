package com.habitlegends.habitlegends.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.habitlegends.habitlegends.dto.HabitCategoryDTO;
import com.habitlegends.habitlegends.service.HabitCategoryService;

@RestController
@RequestMapping("/api/habit-category")
public class HabitCategoryController {

    private final HabitCategoryService habitCategoryService;

    public HabitCategoryController(HabitCategoryService habitCategoryService) {
        this.habitCategoryService = habitCategoryService;
    }

    @PostMapping
    public ResponseEntity<HabitCategoryDTO> createHabitCategory(@RequestBody HabitCategoryDTO habitCategoryDTO) {
        return ResponseEntity.ok(habitCategoryService.createHabitCategory(habitCategoryDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitCategoryDTO> getHabitCategoryById(@PathVariable Integer id) {
        return ResponseEntity.ok(habitCategoryService.getHabitCategoryById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<HabitCategoryDTO>> getAllHabitCategories() {
        return ResponseEntity.ok(habitCategoryService.getAllHabitCategories());
    }

    @PutMapping("/{id}")
    public ResponseEntity<HabitCategoryDTO> updateHabitCategory(@PathVariable Integer id,
            @RequestBody HabitCategoryDTO habitCategoryDTO) {
        return ResponseEntity.ok(habitCategoryService.updateHabitCategory(id, habitCategoryDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabitCategory(@PathVariable Integer id) {
        habitCategoryService.deleteHabitCategory(id);
        return ResponseEntity.noContent().build();
    }

}
