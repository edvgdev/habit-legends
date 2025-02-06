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
@RequestMapping("/api/user-quest")
public class UserQuestController {

    private final UserQuestService userQuestService;

    public UserQuestController(UserQuestService userQuestService) {
        this.userQuestService = userQuestService;
    }

    @PostMapping
    public ResponseEntity<UserQuestDTO> createUserQuest(@RequestBody UserQuestDetails userQuestDetails) {
        return ResponseEntity.ok(userQuestService.createUserQuest(userQuestDetails));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserQuestDTO> getUserQuestById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userQuestService.getUserQuestById(id));
    }

    // TODO: Change to Specification
    @GetMapping("/all")
    public ResponseEntity<List<UserQuestDTO>> getAllUserQuests() {
        return ResponseEntity.ok(userQuestService.getAllUserQuests());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserQuestDTO> updateUserQuest(@PathVariable("id") Long id,
            @RequestBody UserQuestDTO userQuestDTO) {
        return ResponseEntity.ok(userQuestService.updateUserQuest(id, userQuestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserQuest(@PathVariable("id") Long id) {
        userQuestService.deleteUserQuest(id);
        return ResponseEntity.noContent().build();
    }

    // TODO: Change to Specification
    @GetMapping("/all-details/{userId}")
    public ResponseEntity<List<UserQuestDetails>> getAllUserQuestDetails(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userQuestService.getAllUserQuestDetails(userId));
    }

}
