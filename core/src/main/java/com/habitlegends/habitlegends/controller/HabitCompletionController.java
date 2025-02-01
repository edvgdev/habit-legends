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

import com.habitlegends.habitlegends.dto.HabitCompletionDTO;
import com.habitlegends.habitlegends.service.HabitCompletionService;

@RestController
@RequestMapping("/api/habit-completion")
public class HabitCompletionController {

    private final HabitCompletionService habitCompletionService;

    public HabitCompletionController(HabitCompletionService habitCompletionService) {
        this.habitCompletionService = habitCompletionService;
    }

    @PostMapping
    public ResponseEntity<HabitCompletionDTO> createHabitCompletion(
            @RequestBody HabitCompletionDTO habitCompletionDTO) {
        return ResponseEntity.ok(habitCompletionService.createHabitCompletion(habitCompletionDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitCompletionDTO> getHabitCompletionById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(habitCompletionService.getHabitCompletionById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<HabitCompletionDTO>> getAllHabitCompletions() {
        return ResponseEntity.ok(habitCompletionService.getAllHabitCompletions());
    }

    @GetMapping("/all-user-completed/{id}")
    public ResponseEntity<List<HabitCompletionDTO>> getAllHabitCompletionsOfUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(habitCompletionService.getAllHabitCompletionsOfUser(id));
    }

    @GetMapping("/all-user-completed-today/{id}")
    public ResponseEntity<List<HabitCompletionDTO>> getAllHabitCompletionsOfUserToday(@PathVariable("id") Long id) {
        return ResponseEntity.ok(habitCompletionService.getAllHabitCompletionsOfUserToday(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HabitCompletionDTO> updateHabitCompletion(@PathVariable("id") Long id,
            @RequestBody HabitCompletionDTO habitCompletionDTO) {
        return ResponseEntity.ok(habitCompletionService.updateHabitCompletion(id, habitCompletionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabitCompletion(@PathVariable("id") Long id) {
        habitCompletionService.deleteHabitCompletion(id);
        return ResponseEntity.noContent().build();
    }

}
