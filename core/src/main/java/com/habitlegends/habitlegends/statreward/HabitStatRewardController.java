package com.habitlegends.habitlegends.statreward;

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

@RestController
@RequestMapping("/api/habit-stat-reward")
public class HabitStatRewardController {

    private final HabitStatRewardService habitStatRewardService;

    public HabitStatRewardController(HabitStatRewardService habitStatRewardService) {
        this.habitStatRewardService = habitStatRewardService;
    }

    @PostMapping
    public ResponseEntity<HabitStatRewardDTO> createHabitStatReward(
            @RequestBody HabitStatRewardDTO habitStatRewardDTO) {
        return ResponseEntity.ok(habitStatRewardService.createHabitStatReward(habitStatRewardDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitStatRewardDTO> getHabitStatRewardById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(habitStatRewardService.getHabitStatRewardById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<HabitStatRewardDTO>> getAllHabitStatRewards() {
        return ResponseEntity.ok(habitStatRewardService.getAllHabitStatRewards());
    }

    @PutMapping("/{id}")
    public ResponseEntity<HabitStatRewardDTO> updateHabitStatReward(@PathVariable("id") Long id,
            @RequestBody HabitStatRewardDTO habitStatRewardDTO) {
        return ResponseEntity.ok(habitStatRewardService.updateHabitStatReward(id, habitStatRewardDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabitStatReward(@PathVariable("id") Long id) {
        habitStatRewardService.deleteHabitStatReward(id);
        return ResponseEntity.noContent().build();
    }

}
