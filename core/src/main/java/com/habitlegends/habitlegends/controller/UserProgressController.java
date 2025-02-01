package com.habitlegends.habitlegends.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.habitlegends.habitlegends.details.UserProgressDetails;
import com.habitlegends.habitlegends.dto.UserProgressDTO;
import com.habitlegends.habitlegends.service.UserProgressService;

@RestController
@RequestMapping("/api/user-progress")
public class UserProgressController {

    private final UserProgressService userProgressService;

    public UserProgressController(UserProgressService userProgressService) {
        this.userProgressService = userProgressService;
    }

    @PostMapping
    public ResponseEntity<UserProgressDTO> createUserProgress(@RequestBody UserProgressDTO userProgressDTO) {
        return ResponseEntity.ok(userProgressService.createUserProgress(userProgressDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProgressDTO> getUserProgressById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userProgressService.getUserProgressById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserProgressDTO>> getAllUserProgress() {
        return ResponseEntity.ok(userProgressService.getAllUserProgress());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserProgress(@PathVariable("id") Long id) {
        userProgressService.deleteUserProgress(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/details/{userId}")
    public ResponseEntity<UserProgressDetails> getUserProgressDetails(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userProgressService.getUserProgressDetails(userId));
    }

}
