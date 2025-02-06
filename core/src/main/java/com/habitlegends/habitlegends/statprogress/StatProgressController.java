package com.habitlegends.habitlegends.statprogress;

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
@RequestMapping("/api/stat-progress")
public class StatProgressController {

    private final StatProgressService statProgressService;

    public StatProgressController(StatProgressService statProgressService) {
        this.statProgressService = statProgressService;
    }

    @PostMapping
    public ResponseEntity<StatProgressDTO> createStatProgress(@RequestBody StatProgressDTO statProgressDTO) {
        return ResponseEntity.ok(statProgressService.createStatProgress(statProgressDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatProgressDTO> getStatProgressById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(statProgressService.getStatProgressById(id));
    }

    // TODO: Change to specification
    @GetMapping("/all")
    public ResponseEntity<List<StatProgressDTO>> getAllUserHabitStats() {
        return ResponseEntity.ok(statProgressService.getAllStatProgress());
    }

    // TODO: Change to specification
    @GetMapping("/all-user-stat/{userId}")
    public ResponseEntity<List<UserStatDetails>> getAllUserStatByUser(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(statProgressService.getAllUserStatByUser(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatProgressDTO> updateStatProgress(@PathVariable("id") Long id,
            @RequestBody StatProgressDTO statProgressDTO) {
        return ResponseEntity.ok(statProgressService.updateStatProgress(id, statProgressDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatProgress(@PathVariable("id") Long id) {
        statProgressService.deleteStatProgress(id);
        return ResponseEntity.noContent().build();
    }

}
