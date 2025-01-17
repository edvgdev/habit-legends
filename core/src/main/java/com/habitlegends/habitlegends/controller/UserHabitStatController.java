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

import com.habitlegends.habitlegends.dto.UserHabitStatDTO;
import com.habitlegends.habitlegends.service.UserHabitStatService;

@RestController
@RequestMapping("/api/user-habit-stat")
public class UserHabitStatController {

    private final UserHabitStatService userHabitStatService;

    public UserHabitStatController(UserHabitStatService userHabitStatService) {
        this.userHabitStatService = userHabitStatService;
    }

    @PostMapping
    public ResponseEntity<UserHabitStatDTO> createUserHabitStat(@RequestBody UserHabitStatDTO userHabitStatDTO) {
        return ResponseEntity.ok(userHabitStatService.createUserHabitStat(userHabitStatDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserHabitStatDTO> getUserHabitStatById(@PathVariable Long id) {
        return ResponseEntity.ok(userHabitStatService.getUserHabitStatById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserHabitStatDTO>> getAllUserHabitStats() {
        return ResponseEntity.ok(userHabitStatService.getAllUserHabitStats());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserHabitStatDTO> updateUserHabitStat(@PathVariable Long id,
            @RequestBody UserHabitStatDTO userHabitStatDTO) {
        return ResponseEntity.ok(userHabitStatService.updateUserHabitStat(id, userHabitStatDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserHabitStat(@PathVariable Long id) {
        userHabitStatService.deleteUserHabitStat(id);
        return ResponseEntity.noContent().build();
    }

}
