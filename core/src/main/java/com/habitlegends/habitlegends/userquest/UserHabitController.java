package com.habitlegends.habitlegends.userquest;

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
@RequestMapping("/api/user-habit")
public class UserHabitController {

    private final UserHabitService userHabitService;

    public UserHabitController(UserHabitService userHabitService) {
        this.userHabitService = userHabitService;
    }

    @PostMapping
    public ResponseEntity<UserHabitDTO> createUserHabit(@RequestBody UserHabitDetails userHabitDetails) {
        return ResponseEntity.ok(userHabitService.createUserHabit(userHabitDetails));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserHabitDTO> getUserHabitById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userHabitService.getUserHabitById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserHabitDTO>> getAllUserHabits() {
        return ResponseEntity.ok(userHabitService.getAllUserHabits());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserHabitDTO> updateUserHabit(@PathVariable("id") Long id,
            @RequestBody UserHabitDTO userHabitDTO) {
        return ResponseEntity.ok(userHabitService.updateUserHabit(id, userHabitDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserHabit(@PathVariable("id") Long id) {
        userHabitService.deleteUserHabit(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all-details/{userId}")
    public ResponseEntity<List<UserHabitDetails>> getAllUserHabitDetails(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userHabitService.getAllUserHabitDetails(userId));
    }

}
