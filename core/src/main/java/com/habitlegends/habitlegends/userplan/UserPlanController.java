package com.habitlegends.habitlegends.userplan;

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
@RequestMapping("/api/user-plan")
public class UserPlanController {

    private final UserPlanService userPlanService;

    public UserPlanController(UserPlanService userPlanService) {
        this.userPlanService = userPlanService;
    }

    @PostMapping
    public ResponseEntity<UserPlanDTO> createUserPlan(@RequestBody UserPlanDTO userPlanDTO) {
        return ResponseEntity.ok(userPlanService.createUserPlan(userPlanDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserPlanDTO> getUserPlanById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(userPlanService.getUserPlanById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserPlanDTO>> getAllUserPlans() {
        return ResponseEntity.ok(userPlanService.getAllUserPlans());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserPlanDTO> updateUserPlan(@PathVariable("id") Integer id,
            @RequestBody UserPlanDTO userPlanDTO) {
        return ResponseEntity.ok(userPlanService.updateUserPlan(id, userPlanDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserPlan(@PathVariable("id") Integer id) {
        userPlanService.deleteUserPlan(id);
        return ResponseEntity.noContent().build();
    }
}
