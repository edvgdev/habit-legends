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

import com.habitlegends.habitlegends.details.HabitAndStatRewardsDetails;
import com.habitlegends.habitlegends.details.HabitDetails;
import com.habitlegends.habitlegends.service.HabitService;

@RestController
@RequestMapping("/api/habit")
public class HabitController {

    private final HabitService habitService;

    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    @PostMapping
    public ResponseEntity<HabitDetails> createHabit(@RequestBody HabitAndStatRewardsDetails habitStatRewardsDTO) {
        return ResponseEntity.ok(habitService.createHabit(habitStatRewardsDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitDetails> getHabitById(@PathVariable Long id) {
        return ResponseEntity.ok(habitService.getHabitById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<HabitDetails>> getAllHabits() {
        return ResponseEntity.ok(habitService.getAllHabits());
    }

    @PutMapping("/{id}")
    public ResponseEntity<HabitDetails> updateHabit(@PathVariable Long id,
            @RequestBody HabitAndStatRewardsDetails habitStatRewardsDTO) {
        return ResponseEntity.ok(habitService.updateHabit(id, habitStatRewardsDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabit(@PathVariable Long id) {
        habitService.deleteHabit(id);
        return ResponseEntity.noContent().build();
    }
}
