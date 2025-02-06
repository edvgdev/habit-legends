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
@RequestMapping("/api/quest-stat-reward")
public class QuestStatRewardController {

    private final QuestStatRewardService questStatRewardService;

    public QuestStatRewardController(QuestStatRewardService questStatRewardService) {
        this.questStatRewardService = questStatRewardService;
    }

    @PostMapping
    public ResponseEntity<QuestStatRewardDTO> createQuestStatReward(
            @RequestBody QuestStatRewardDTO questStatRewardDTO) {
        return ResponseEntity.ok(questStatRewardService.createQuestStatReward(questStatRewardDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestStatRewardDTO> getQuestStatRewardById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(questStatRewardService.getQuestStatRewardById(id));
    }

    // TODO: Change to specification
    @GetMapping("/all")
    public ResponseEntity<List<QuestStatRewardDTO>> getAllQuestStatRewards() {
        return ResponseEntity.ok(questStatRewardService.getAllQuestStatRewards());
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestStatRewardDTO> updateQuestStatReward(@PathVariable("id") Long id,
            @RequestBody QuestStatRewardDTO questStatRewardDTO) {
        return ResponseEntity.ok(questStatRewardService.updateQuestStatReward(id, questStatRewardDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestStatReward(@PathVariable("id") Long id) {
        questStatRewardService.deleteQuestStatReward(id);
        return ResponseEntity.noContent().build();
    }

}
